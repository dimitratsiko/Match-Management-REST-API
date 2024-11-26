package com.match.model.specs;


import com.match.domain.match.Sport;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchSpecs {
    private Long id;
    private String description;
    private LocalDate matchDate;
    @Schema(type = "string", format = "time", example = "15:00:00")
    private LocalTime matchTime;
    private String teamA;
    private String teamB;
    private Sport sport;
    private LocalDate startDate;
    private LocalDate endDate;
}
