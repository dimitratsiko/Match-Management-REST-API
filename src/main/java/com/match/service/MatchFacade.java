package com.match.service;


import com.match.model.match.MatchDTO;
import com.match.model.match.odds.MatchOddDTO;
import com.match.model.specs.MatchOddSpecs;
import com.match.model.specs.MatchSpecs;

import java.util.List;

public interface MatchFacade {
    // Match Facade Handlers
    MatchDTO saveMatch(MatchDTO matchDTO);
    MatchDTO updateMatch(MatchDTO matchDTO);
    void deleteMatchById(Long id);
    MatchDTO getMatchById(Long id);
    List<MatchDTO> getMatchesBySpecs(MatchSpecs matchSpecs);
    List<MatchDTO> getAllMatches();
    // Match Odds Facade Handler
    MatchOddDTO saveMatchOdd(MatchOddDTO matchOddDTO);
    MatchOddDTO updateMatchOdd(MatchOddDTO matchOddDTO);
    void deleteMatchOddById(Long id);
    MatchOddDTO getMatchOddById(Long id);
    List<MatchOddDTO> getMatchOddsBySpecs(MatchOddSpecs matchOddSpecs);
    List<MatchOddDTO> getAllMatchOdds();
}
