package com.match.service.match.odds.impl;


import com.match.common.exception.MatchNotFoundException;
import com.match.common.exception.MatchOddNotFoundException;
import com.match.domain.match.Match;
import com.match.domain.match.odds.MatchOdd;
import com.match.model.specs.MatchOddSpecs;
import com.match.repository.match.MatchRepository;
import com.match.repository.match.odds.MatchOddRepository;
import com.match.service.match.odds.MatchOddService;
import com.match.service.match.odds.specs.MatchOddSpecifications;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MatchOddServiceImpl implements MatchOddService {

    private final MatchOddRepository matchOddRepository;
    private final MatchRepository matchRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public MatchOdd save(MatchOdd matchOdd) {
        Match match = matchRepository.findById(matchOdd.getMatchId())
                .orElseThrow(() -> new MatchNotFoundException(String.format("Match with id '%d' not found", matchOdd.getMatchId())));
        matchOdd.setMatch(match);
        return matchOddRepository.save(matchOdd);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public MatchOdd update(MatchOdd matchOdd) {
        if (Objects.isNull(matchOdd.getId())) return save(matchOdd);
        Match match = matchRepository.findById(matchOdd.getMatchId())
                .orElseThrow(() -> new MatchNotFoundException(String.format("Match with id '%d' not found", matchOdd.getMatchId())));
        matchOdd.setMatch(match);
        return matchOddRepository.save(matchOdd);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(MatchOdd matchOdd) {
        matchOddRepository.delete(matchOdd);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public MatchOdd findById(Long id) {
        MatchOdd matchOdd = matchOddRepository.findById(id)
                .orElseThrow(() -> new MatchOddNotFoundException(String.format("Match Odd with id '%d' not found", id)));
        matchOdd.setMatchId(Optional.ofNullable(matchOdd.getMatch())
                .map(Match::getId).orElse(null));
        return matchOdd;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<MatchOdd> findBySpecs(MatchOddSpecs matchOddSpecs) {
        if (Objects.isNull(matchOddSpecs)) return List.of();
        Specification<MatchOdd> spec = Specification.where(null);
        if (Objects.nonNull(matchOddSpecs.getId())) spec = spec.and(MatchOddSpecifications.hasId(matchOddSpecs.getId()));
        if (Objects.nonNull(matchOddSpecs.getMatchId())) spec = spec.and(MatchOddSpecifications.hasMatchId(matchOddSpecs.getMatchId()));
        if (StringUtils.isNotBlank(matchOddSpecs.getSpecifier())) spec = spec.and(MatchOddSpecifications.hasSpecifier(matchOddSpecs.getSpecifier()));
        if (Objects.nonNull(matchOddSpecs.getOdd())) spec = spec.and(MatchOddSpecifications.hasOdd(matchOddSpecs.getOdd()));
        if (Objects.nonNull(matchOddSpecs.getMinOdd()) || Objects.nonNull(matchOddSpecs.getMaxOdd())) spec = spec.and(MatchOddSpecifications.isOddBetween(matchOddSpecs.getMinOdd(), matchOddSpecs.getMaxOdd()));
        List<MatchOdd> matchOdds = matchOddRepository.findAll(spec);
        matchOdds.forEach(matchOdd -> matchOdd.setMatchId(Optional.ofNullable(matchOdd.getMatch())
                .map(Match::getId).orElse(null)));
        return matchOdds;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<MatchOdd> findAll() {
        List<MatchOdd> matchOdds = matchOddRepository.findAll();
        matchOdds.forEach(matchOdd -> matchOdd.setMatchId(Optional.ofNullable(matchOdd.getMatch())
                .map(Match::getId).orElse(null)));
        return matchOdds;
    }
}
