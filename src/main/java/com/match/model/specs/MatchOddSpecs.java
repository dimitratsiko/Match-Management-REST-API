package com.match.model.specs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MatchOddSpecs {
    private Long id;
    private Long matchId;
    private String specifier;
    private Double odd;
    private Double minOdd;
    private Double maxOdd;
}
