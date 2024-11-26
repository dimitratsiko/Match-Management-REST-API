package com.match.domain.match.odds;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.match.domain.match.Match;
import com.match.validation.BasicValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "match_odd")
@EqualsAndHashCode(callSuper = false)
public class MatchOdd implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "match_odd_id_seq")
    @SequenceGenerator(name = "match_odd_id_seq", sequenceName = "match_odd_id_seq", allocationSize = 1, initialValue = 1)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", referencedColumnName = "id", nullable = false)
    private Match match;

    @Transient
    private Long matchId;

    @Column(name = "specifier")
    @Size(max = 80)
    @NotNull(groups = BasicValidation.class, message = "Specifier cannot be null or empty, Please provide a valid one!")
    private String specifier;

    @Column(name = "odd")
    @DecimalMin(groups = BasicValidation.class, value = "0.0", message = "The value must be greater than or equal to 0.0")
    @NotNull(groups = BasicValidation.class, message = "Odd cannot be null or empty, Please provide a valid one!")
    private Double odd;
}
