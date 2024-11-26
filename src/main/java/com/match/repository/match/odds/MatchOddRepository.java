package com.match.repository.match.odds;


import com.match.domain.match.odds.MatchOdd;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchOddRepository extends JpaRepository<MatchOdd, Long>, JpaSpecificationExecutor<MatchOdd> {
}
