package com.match.mapper;


import com.match.domain.match.BasketballMatch;
import com.match.model.match.BasketballMatchDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BasketballMatchMapper extends MatchMapper<BasketballMatch, BasketballMatchDTO> {
    @Override
    default Class<? extends BasketballMatch> getCorrelationClass() {
        return BasketballMatch.class;
    }
}
