package com.match.controller;


import com.match.aop.LogExecutionTime;
import com.match.model.match.odds.MatchOddDTO;
import com.match.model.specs.MatchOddSpecs;
import com.match.service.MatchFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/match-odd")
public class MatchOddController {

    private final MatchFacade matchFacade;

    @LogExecutionTime
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveMatchOdd(@Valid @RequestBody MatchOddDTO matchOddDTO) {
        return new ResponseEntity<>(matchFacade.saveMatchOdd(matchOddDTO), HttpStatus.CREATED);
    }

    @LogExecutionTime
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateMatchOdd(@Valid @RequestBody MatchOddDTO matchOddDTO) {
        return new ResponseEntity<>(matchFacade.updateMatchOdd(matchOddDTO), HttpStatus.OK);
    }

    @LogExecutionTime
    @DeleteMapping(value = "/{matchOddId}")
    public ResponseEntity<?> deleteMatchOdd(@Valid @PathVariable(name = "matchOddId") Long matchOddId) {
        matchFacade.deleteMatchOddById(matchOddId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @LogExecutionTime
    @GetMapping(value = "/{matchOddId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMatchOdd(@Valid @PathVariable(name = "matchOddId") Long matchOddId) {
        return new ResponseEntity<>(matchFacade.getMatchOddById(matchOddId), HttpStatus.OK);
    }

    @LogExecutionTime
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMatchOdds(@ModelAttribute MatchOddSpecs matchOddSpecs) {
        return new ResponseEntity<>(matchFacade.getMatchOddsBySpecs(matchOddSpecs), HttpStatus.OK);
    }
}
