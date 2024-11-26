package com.match.model.match;


import com.match.domain.match.FootballMatch;
import com.match.domain.match.Match;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class FootballMatchDTO extends MatchDTO implements Serializable {

    private String footballNotes;

    @Override
    public Class<? extends Match> getEntityClass() {return FootballMatch.class;}
}
