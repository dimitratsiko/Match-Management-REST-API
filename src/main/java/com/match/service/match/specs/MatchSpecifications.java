package com.match.service.match.specs;


import com.match.domain.match.Match;
import com.match.domain.match.Sport;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalTime;

public class MatchSpecifications {
    public static Specification<Match> hasId(Long id) {
        return ((root, query, builder) -> builder.equal(root.get("id"), id));
    }
    public static Specification<Match> hasDescription(String description) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("description")), "%" + description.toLowerCase() + "%");
    }
    public static Specification<Match> hasMatchDate(LocalDate matchDate) {
        return (root, query, builder) -> builder.equal(root.get("matchDate"), matchDate);
    }
    public static Specification<Match> hasMatchTime(LocalTime matchTime) {
        return (root, query, builder) -> builder.equal(root.get("matchTime"), matchTime);
    }
    public static Specification<Match> hasTeamA(String teamA) {
        return (root, query, builder) ->
                builder.like(builder.lower(root.get("teamA")), "%" + teamA.toLowerCase() + "%");
    }
    public static Specification<Match> hasTeamB(String teamB) {
        return (root, query, builder) ->
                builder.like(builder.lower(root.get("teamB")), "%" + teamB.toLowerCase() + "%");
    }
    public static Specification<Match> hasSport(Sport sport) {
        return (root, query, builder) -> builder.equal(root.get("sport"), sport);
    }
    public static Specification<Match> isBetweenDates(LocalDate startDate, LocalDate endDate) {
        return (root, query, builder) ->
                builder.between(root.get("matchDate"), startDate, endDate);
    }
}
