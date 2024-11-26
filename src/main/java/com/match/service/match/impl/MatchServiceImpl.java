package com.match.service.match.impl;


import com.match.common.exception.MatchNotFoundException;
import com.match.domain.match.Match;
import com.match.model.specs.MatchSpecs;
import com.match.repository.match.MatchRepository;
import com.match.service.match.MatchService;
import com.match.service.match.specs.MatchSpecifications;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MatchServiceImpl implements MatchService {

    private final MatchRepository matchRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Match save(Match match) {
        match.getOdds().forEach(odd -> {
            odd.setMatchId(match.getId());
            odd.setMatch(match);
        });
        return matchRepository.save(match);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Match update(Match match) {
        match.getOdds().forEach(odd -> {
            odd.setMatchId(match.getId());
            odd.setMatch(match);
        });        return matchRepository.save(match);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Match match) {
        matchRepository.delete(match);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Match findById(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new MatchNotFoundException(String.format("Match with id '%d' not found", id)));
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Match> findBySpecs(MatchSpecs matchSpecs) {
        if (Objects.isNull(matchSpecs)) return List.of();
        Specification<Match> spec = Specification.where(null);
        if (Objects.nonNull(matchSpecs.getId())) spec = spec.and(MatchSpecifications.hasId(matchSpecs.getId()));
        if (StringUtils.isNotBlank(matchSpecs.getDescription())) spec = spec.and(MatchSpecifications.hasDescription(matchSpecs.getDescription()));
        if (Objects.nonNull(matchSpecs.getMatchDate())) spec = spec.and(MatchSpecifications.hasMatchDate(matchSpecs.getMatchDate()));
        if (Objects.nonNull(matchSpecs.getMatchTime())) spec = spec.and(MatchSpecifications.hasMatchTime(matchSpecs.getMatchTime()));
        if (StringUtils.isNotBlank(matchSpecs.getTeamA())) spec = spec.and(MatchSpecifications.hasTeamA(matchSpecs.getTeamA()));
        if (StringUtils.isNotBlank(matchSpecs.getTeamB())) spec = spec.and(MatchSpecifications.hasTeamB(matchSpecs.getTeamB()));
        if (Objects.nonNull(matchSpecs.getSport())) spec = spec.and(MatchSpecifications.hasSport(matchSpecs.getSport()));
        if (Objects.nonNull(matchSpecs.getStartDate()) && Objects.nonNull(matchSpecs.getEndDate())) spec = spec.and(MatchSpecifications.isBetweenDates(matchSpecs.getStartDate(), matchSpecs.getEndDate()));
        return matchRepository.findAll(spec);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Match> findAll() {
        return matchRepository.findAll();
    }
}
