package com.match.model.match;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.match.domain.match.Match;
import com.match.domain.match.Sport;
import com.match.model.match.odds.MatchOddDTO;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = MatchDTO.PROPERTY_KEY,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FootballMatchDTO.class,
                name = MatchDTO.FOOTBALL),
        @JsonSubTypes.Type(value = BasketballMatchDTO.class,
                name = MatchDTO.BASKETBALL)})
public abstract class MatchDTO implements Serializable {

    public static final String PROPERTY_KEY = "sport";
    public static final String FOOTBALL = "FOOTBALL";
    public static final String BASKETBALL = "BASKETBALL";

    private Long id;
    private String description;
    private LocalDate matchDate;
    private LocalTime matchTime;
    private String teamA;
    private String teamB;
    private Sport sport;
    private List<MatchOddDTO> odds = new ArrayList<>();

    @JsonIgnore
    public abstract Class<? extends Match> getEntityClass();
}
