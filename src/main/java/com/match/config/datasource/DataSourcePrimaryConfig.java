package com.match.config.datasource;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Objects;
import java.util.Properties;

@Slf4j
@Configuration
@ConfigurationProperties("match.datasource-primary")
@ConditionalOnProperty(value = "match.datasource-primary.enabled", havingValue = "true")
@EnableJpaRepositories(entityManagerFactoryRef = "primaryEntityManager", transactionManagerRef = "primaryTransactionManager", basePackages = {"com.match.repository"})
public class DataSourcePrimaryConfig extends HikariConfig {

    protected static final String PERSISTENCE_UNIT_NAME = "primary";
    protected Properties JPA_PRIMARY_PROPERTIES;

    @Value("${match.datasource-primary.enabled}")
    private boolean enabled;

    @Value("${match.datasource-primary.platform}")
    private String platform;

    @Value("${match.datasource-primary.hikari.minimumIdle}")
    private int minimumIdle;

    @Value("${match.datasource-primary.hikari.maximumPoolSize}")
    private int maximumPoolSize;

    @Value("${match.datasource-primary.hikari.idleTimeout}")
    private int idleTimeout;

    @Value("${match.datasource-primary.hikari.connectionTimeout}")
    private int connectionTimeout;

    @Value("${match.datasource-primary.hikari.dialect}")
    private String dialect;

    @Value("${match.datasource-primary.hikari.jdbcLobNonContextualCreation}")
    private boolean jdbcLobNonContextualCreation;

    @Value("${match.datasource-primary.hikari.showSql}")
    private boolean showSql;

    @Value("${match.datasource-primary.hikari.formatSql}")
    private boolean formatSql;

    @Value("${match.datasource-primary.hikari.readOnly}")
    private boolean readOnly;

    @Value("${match.datasource-primary.hikari.poolName}")
    private String poolName;

    @Value("${match.datasource-primary.jdbc-template-fetch-size: -1 }")
    private int jdbcTemplateFetchSize;

    @PostConstruct
    public void setup() {
        log.info("DataSourcePrimaryConfig Initialization [{}] for platform [{}]", enabled, platform);
        setMinimumIdle(minimumIdle);
        setMaximumPoolSize(maximumPoolSize);
        setIdleTimeout(idleTimeout);
        setReadOnly(readOnly);
        setPoolName(poolName);
        setConnectionTimeout(connectionTimeout);
        JPA_PRIMARY_PROPERTIES = new Properties();
        JPA_PRIMARY_PROPERTIES.put("hibernate.dialect", dialect);
        JPA_PRIMARY_PROPERTIES.put("hibernate.jdbc.lob.non_contextual_creation", jdbcLobNonContextualCreation);
        JPA_PRIMARY_PROPERTIES.put("hibernate.show_sql", showSql);
        JPA_PRIMARY_PROPERTIES.put("hibernate.format_sql", formatSql);
    }

    @Bean(name = "primaryDataSource")
    public HikariDataSource dataSourcePrimary() {
        return new HikariDataSource(this);
    }

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean primaryEntityManager() {
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactory.setDataSource(dataSourcePrimary());
        entityManagerFactory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactory.setPersistenceUnitName(PERSISTENCE_UNIT_NAME);
        entityManagerFactory.setJpaVendorAdapter(adapter);
        entityManagerFactory.setPackagesToScan("com.match.domain");
        entityManagerFactory.setJpaProperties(JPA_PRIMARY_PROPERTIES);
        entityManagerFactory.setEntityManagerFactoryInterface(EntityManagerFactory.class);
        return entityManagerFactory;
    }

    @Bean
    public PlatformTransactionManager primaryTransactionManager() {
        return new JpaTransactionManager(Objects.requireNonNull(primaryEntityManager().getObject()));
    }

    @Bean(name = "primaryJdbcTemplate")
    @DependsOn("primaryDataSource")
    public JdbcTemplate primaryJdbcTemplate(@Qualifier("primaryDataSource") HikariDataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.setFetchSize(jdbcTemplateFetchSize);
        return jdbcTemplate;
    }

    @Bean(name = "primaryNamedParameterJdbcTemplate")
    @DependsOn("primaryDataSource")
    public NamedParameterJdbcTemplate primaryNamedParameterJdbcTemplate(@Qualifier("primaryDataSource") HikariDataSource dataSource) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        namedParameterJdbcTemplate.getJdbcTemplate().setFetchSize(jdbcTemplateFetchSize);
        return namedParameterJdbcTemplate;
    }
}
