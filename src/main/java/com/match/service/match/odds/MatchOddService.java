package com.match.service.match.odds;


import com.match.domain.match.odds.MatchOdd;
import com.match.model.specs.MatchOddSpecs;

import java.util.List;

public interface MatchOddService {
    MatchOdd save(MatchOdd matchOdd);
    MatchOdd update(MatchOdd matchOdd);
    void delete(MatchOdd matchOdd);
    MatchOdd findById(Long id);
    List<MatchOdd> findBySpecs(MatchOddSpecs matchOddSpecs);
    List<MatchOdd> findAll();
}
