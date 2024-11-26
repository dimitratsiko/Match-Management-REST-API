package com.match.domain.match;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.match.domain.match.odds.MatchOdd;
import com.match.validation.BasicValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "match")
@DiscriminatorColumn(name = "sport")
@EqualsAndHashCode(callSuper = false)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Match implements Serializable {

    public static final String FOOTBALL = "FOOTBALL";
    public static final String BASKETBALL = "BASKETBALL";

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_id_seq")
    @SequenceGenerator(name = "match_id_seq", sequenceName = "match_id_seq", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name = "description")
    @Size(max = 1024)
    @NotNull(groups = BasicValidation.class, message = "Description cannot be null or empty, Please provide a valid one!")
    private String description;

    @Column(name = "match_date")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @NotNull(groups = BasicValidation.class, message = "Match Date cannot be null or empty, Please provide a valid one!")
    private LocalDate matchDate;

    @Column(name = "match_time")
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonSerialize(using = LocalTimeSerializer.class)
    @NotNull(groups = BasicValidation.class, message = "Match Time cannot be null or empty, Please provide a valid one!")
    private LocalTime matchTime;

    @Column(name = "team_a")
    @Size(max = 256)
    @NotNull(groups = BasicValidation.class, message = "Team A cannot be null or empty, Please provide a valid one!")
    private String teamA;

    @Column(name = "team_b")
    @Size(max = 256)
    @NotNull(groups = BasicValidation.class, message = "Team B cannot be null or empty, Please provide a valid one!")
    private String teamB;

    @Enumerated(EnumType.STRING)
    @Column(name = "sport", insertable = false, updatable = false)
    private Sport sport;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "match", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<MatchOdd> odds = new ArrayList<>();
}
