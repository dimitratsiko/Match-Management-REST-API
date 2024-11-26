package com.match.mapper;


import com.match.domain.match.Match;
import com.match.model.match.MatchDTO;

public interface MatchMapper<E extends Match, D extends MatchDTO> {
    D entityToDto(E entity);
    E dtoToEntity(D dto);

    Class<? extends E> getCorrelationClass();
}
