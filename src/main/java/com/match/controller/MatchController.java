package com.match.controller;


import com.match.aop.LogExecutionTime;
import com.match.model.match.MatchDTO;
import com.match.model.specs.MatchSpecs;
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
@RequestMapping("/api/match")
public class MatchController {

    private final MatchFacade matchFacade;

    @LogExecutionTime
    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveMatch(@Valid @RequestBody MatchDTO matchDTO) {
        return new ResponseEntity<>(matchFacade.saveMatch(matchDTO), HttpStatus.CREATED);
    }

    @LogExecutionTime
    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateMatch(@Valid @RequestBody MatchDTO matchDTO) {
        return new ResponseEntity<>(matchFacade.updateMatch(matchDTO), HttpStatus.OK);
    }

    @LogExecutionTime
    @DeleteMapping(value = "/{matchId}")
    public ResponseEntity<?> deleteMatch(@Valid @PathVariable(name = "matchId") Long matchId) {
        matchFacade.deleteMatchById(matchId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @LogExecutionTime
    @GetMapping(value = "/{matchId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMatch(@Valid @PathVariable(name = "matchId") Long matchId) {
        return new ResponseEntity<>(matchFacade.getMatchById(matchId), HttpStatus.OK);
    }

    @LogExecutionTime
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMatches(@ModelAttribute MatchSpecs matchSpecs) {
        return new ResponseEntity<>(matchFacade.getMatchesBySpecs(matchSpecs), HttpStatus.OK);
    }
}
