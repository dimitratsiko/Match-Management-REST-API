package com.match.service.impl;


import com.match.common.exception.MatchNotFoundException;
import com.match.common.exception.MatchOddNotFoundException;
import com.match.domain.match.Match;
import com.match.domain.match.odds.MatchOdd;
import com.match.mapper.MatchOddMapper;
import com.match.model.match.MatchDTO;
import com.match.model.match.odds.MatchOddDTO;
import com.match.model.specs.MatchOddSpecs;
import com.match.model.specs.MatchSpecs;
import com.match.service.MatchFacade;
import com.match.service.match.MatchBaseService;
import com.match.service.match.MatchService;
import com.match.service.match.odds.MatchOddService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchFacadeImpl extends MatchBaseService<Match, MatchDTO> implements MatchFacade {

    private final MatchService matchService;
    private final MatchOddService matchOddService;
    private final MatchOddMapper matchOddMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public MatchDTO saveMatch(MatchDTO matchDTO) {
        if (Objects.nonNull(matchDTO.getId())) return updateMatch(matchDTO);
        Match match = mapToEntity(matchDTO);
        return mapToDTO(matchService.save(match));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public MatchDTO updateMatch(MatchDTO matchDTO) {
        if (Objects.isNull(matchDTO.getId())) return saveMatch(matchDTO);
        Match match = mapToEntity(matchDTO);
        return mapToDTO(matchService.update(match));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteMatchById(Long id) {
        if (Objects.isNull(id)) throw new MatchNotFoundException("Match Id is Null!");
        Match match = matchService.findById(id);
        matchService.delete(match);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public MatchDTO getMatchById(Long id) {
        if (Objects.isNull(id)) throw new MatchNotFoundException("Match Id is Null!");
        return mapToDTO(matchService.findById(id));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<MatchDTO> getMatchesBySpecs(MatchSpecs matchSpecs) {
        if (Objects.isNull(matchSpecs)) return getAllMatches();
        return matchService.findBySpecs(matchSpecs).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<MatchDTO> getAllMatches() {
        return matchService.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public MatchOddDTO saveMatchOdd(MatchOddDTO matchOddDTO) {
        if (Objects.nonNull(matchOddDTO.getId())) return updateMatchOdd(matchOddDTO);
        MatchOdd matchOdd = matchOddMapper.mapToEntity(matchOddDTO);
        return matchOddMapper.mapToDTO(matchOddService.save(matchOdd));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public MatchOddDTO updateMatchOdd(MatchOddDTO matchOddDTO) {
        if (Objects.isNull(matchOddDTO.getId())) return saveMatchOdd(matchOddDTO);
        MatchOdd matchOdd = matchOddMapper.mapToEntity(matchOddDTO);
        return matchOddMapper.mapToDTO(matchOddService.update(matchOdd));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteMatchOddById(Long id) {
        if (Objects.isNull(id)) throw new MatchOddNotFoundException("Match Odd Id is Null!");
        MatchOdd matchOdd = matchOddService.findById(id);
        matchOddService.delete(matchOdd);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public MatchOddDTO getMatchOddById(Long id) {
        if (Objects.isNull(id)) throw new MatchOddNotFoundException("Match Odd Id is Null!");
        return matchOddMapper.mapToDTO(matchOddService.findById(id));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<MatchOddDTO> getMatchOddsBySpecs(MatchOddSpecs matchOddSpecs) {
        if (Objects.isNull(matchOddSpecs)) return getAllMatchOdds();
        return matchOddService.findBySpecs(matchOddSpecs).stream()
                .map(matchOddMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<MatchOddDTO> getAllMatchOdds() {
        return matchOddService.findAll().stream()
                .map(matchOddMapper::mapToDTO)
                .collect(Collectors.toList());
    }
}
