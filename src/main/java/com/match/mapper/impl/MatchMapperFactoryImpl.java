package com.match.mapper.impl;


import com.match.domain.match.Match;
import com.match.mapper.MatchMapper;
import com.match.mapper.MatchMapperFactory;
import com.match.model.match.MatchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MatchMapperFactoryImpl implements MatchMapperFactory {

    private final Map<Class<? extends Match>, MatchMapper<Match, MatchDTO>> matchMappers;

    @Autowired
    @SuppressWarnings({"rawtypes", "unchecked"})
    public MatchMapperFactoryImpl(List<MatchMapper> mappers) {
        matchMappers = new HashMap<>();
        mappers.forEach(mapper -> matchMappers.put(mapper.getCorrelationClass(), mapper));
    }

    @Override
    public MatchMapper<Match, MatchDTO> getMatchMapper(Class<? extends Match> clazz) {
        return Optional.ofNullable(matchMappers.get(clazz))
                .orElseThrow(() -> new IllegalArgumentException(String.format("No MatchMapper found for class %s", clazz.getSimpleName())));
    }
}
