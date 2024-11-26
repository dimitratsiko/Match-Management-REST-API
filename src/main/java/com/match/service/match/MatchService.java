package com.match.service.match;


import com.match.domain.match.Match;
import com.match.model.specs.MatchSpecs;

import java.util.List;

public interface MatchService {
    Match save(Match match);
    Match update(Match match);
    void delete(Match match);
    Match findById(Long id);
    List<Match> findBySpecs(MatchSpecs matchSpecs);
    List<Match> findAll();
}
