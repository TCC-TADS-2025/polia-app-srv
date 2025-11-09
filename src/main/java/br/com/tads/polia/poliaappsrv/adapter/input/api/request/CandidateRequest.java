package br.com.tads.polia.poliaappsrv.adapter.input.api.request;

import br.com.tads.polia.poliaappsrv.domain.enums.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class CandidateRequest implements Serializable {
    private static final long serialVersionUID = 1L;

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
    private Double candidateAsset;
    private String proposals;
    private String statusIa;
}
