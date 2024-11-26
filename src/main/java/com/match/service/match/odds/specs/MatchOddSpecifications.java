package com.match.service.match.odds.specs;


import com.match.domain.match.odds.MatchOdd;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

public class MatchOddSpecifications {
    public static Specification<MatchOdd> hasId(Long id) {
        return (root, query, builder) -> builder.equal(root.get("id"), id);
    }
    public static Specification<MatchOdd> hasMatchId(Long matchId) {
        return (root, query, builder) -> builder.equal(root.get("match").get("id"), matchId);
    }
    public static Specification<MatchOdd> hasSpecifier(String specifier) {
        return (root, query, builder) -> builder.like(builder.lower(root.get("specifier")), "%" + specifier.toLowerCase() + "%");
    }
    public static Specification<MatchOdd> hasOdd(Double odd) {
        return (root, query, builder) -> builder.equal(root.get("odd"), odd);
    }
    public static Specification<MatchOdd> isOddBetween(Double minOdd, Double maxOdd) {
        if (Objects.isNull(minOdd) && Objects.isNull(maxOdd)) return null;
        if (Objects.isNull(minOdd)) return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("odd"), maxOdd);
        if (Objects.isNull(maxOdd)) return (root, query, builder) -> builder.greaterThanOrEqualTo(root.get("odd"), minOdd);
        return (root, query, builder) -> builder.between(root.get("odd"), minOdd, maxOdd);
    }
}
