package br.com.tads.polia.poliaappsrv.adapter.input.api.response;

import br.com.tads.polia.poliaappsrv.domain.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private LocalDate birthday;
    private String nationality;
    private Gender gender;
    private Race race;
    private CivilState civilStatus;
    private LevelOfEducation levelOfEducation;
    private String occupation;
    private boolean reelection;
    private String coalition;
    private String position;
    private Party party;
    private String state;
    private String city;
    private Integer candidacyNumber;
    private Double candidateAsset;
    private String proposals;
}
