package com.match.domain.match;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.Objects;

@Getter
@RequiredArgsConstructor
public enum Sport {
    FOOTBALL(1L,"Football"),
    BASKETBALL(2L,"Basketball");

    private final Long id;
    private final String title;

    public static Long getIdFromSport(Sport sport) {
        if (Objects.isNull(sport)) return null;
        return sport.id;
    }

    public static Sport getSportFromId(Long id) {
        if (Objects.isNull(id)) return null;
        return Arrays.stream(Sport.values())
                .filter(sport -> Objects.equals(sport.id, id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Sport with id %d not found", id)));
    }
}
