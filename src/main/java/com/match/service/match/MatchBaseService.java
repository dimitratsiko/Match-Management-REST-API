package com.match.service.match;


import com.match.domain.match.Match;
import com.match.mapper.MatchMapper;
import com.match.mapper.MatchMapperFactory;
import com.match.model.match.MatchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class MatchBaseService<E extends Match, D extends MatchDTO> {

    @Autowired
    protected MatchMapperFactory matchMapperFactory;

    @SuppressWarnings("unchecked")
    protected MatchMapper<E, D> getMapper(Class<? extends Match> entityClass) {
        return (MatchMapper<E, D>) matchMapperFactory.getMatchMapper(entityClass);
    }

    protected E mapToEntity(D dto) {
        MatchMapper<E, D> mapper = getMapper(dto.getEntityClass());
        return mapper.dtoToEntity(dto);
    }

    protected D mapToDTO(E entity) {
        MatchMapper<E, D> mapper = getMapper(entity.getClass());
        return mapper.entityToDto(entity);
    }
}
