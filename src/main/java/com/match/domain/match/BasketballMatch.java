package com.match.domain.match;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
@DiscriminatorValue(value = Match.BASKETBALL)
@SuppressWarnings("serial")
@EqualsAndHashCode(callSuper = false)
public class BasketballMatch extends Match implements Serializable {
    // Add extra basketball related columns like players scored and etc
    @Column(name = "basketball_notes")
    @Size(max = 1024)
    private String basketballNotes;
}
