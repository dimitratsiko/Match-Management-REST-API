package com.match.domain.match;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue(value = Match.FOOTBALL)
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
public class FootballMatch extends Match implements Serializable {
    // Add extra football related columns like players scored and etc
    @Column(name = "football_notes")
    @Size(max = 1024)
    private String footballNotes;
}
