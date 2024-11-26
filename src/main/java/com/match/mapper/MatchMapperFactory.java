package com.match.mapper;


import com.match.domain.match.Match;
import com.match.model.match.MatchDTO;

public interface MatchMapperFactory {
    MatchMapper<Match, MatchDTO> getMatchMapper(Class<? extends Match> clazz);
}
