package br.com.tads.polia.poliaappsrv.adapter.input.api.response;

import br.com.tads.polia.poliaappsrv.domain.enums.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteCandidateResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String name;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthday;
    private String nationality;
    private Gender gender;
    private Race race;
    private CivilState civilStatus;
    private LevelOfEducation levelOfEducation;
    private String occupation;
    private Boolean reelection;
    private String coalition;
    private String position;
    private String party;
    private String state;
    private String city;
    private Integer candidacyNumber;
    private BigDecimal candidateAsset;
    private String proposals;
    private String statusIa;
    private Integer coordinateX;
    private Integer coordinateY;
}
