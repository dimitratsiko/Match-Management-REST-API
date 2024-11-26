package com.match.mapper;


import com.match.domain.match.odds.MatchOdd;
import com.match.model.match.odds.MatchOddDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MatchOddMapper {
    MatchOdd mapToEntity(MatchOddDTO matchOddDTO);
    MatchOddDTO mapToDTO(MatchOdd matchOdd);
}
