package com.match.mapper;


import com.match.domain.match.FootballMatch;
import com.match.model.match.FootballMatchDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FootballMatchMapper extends MatchMapper<FootballMatch, FootballMatchDTO> {
    @Override
    default Class<? extends FootballMatch> getCorrelationClass() {
        return FootballMatch.class;
    }
}
