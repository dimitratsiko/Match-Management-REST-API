package com.match.model.match;


import com.match.domain.match.BasketballMatch;
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
public class BasketballMatchDTO extends MatchDTO implements Serializable {

    private String basketballNotes;

    @Override
    public Class<? extends Match> getEntityClass() {return BasketballMatch.class;}
}
