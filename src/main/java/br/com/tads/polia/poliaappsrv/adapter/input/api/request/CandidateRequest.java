package br.com.tads.polia.poliaappsrv.adapter.input.api.request;

import br.com.tads.polia.poliaappsrv.domain.enums.*;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
public class CandidateRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private LocalDate birthday;
    private String nationality;
    private Gender gender;
    private Race race;
    private CivilStatus civilStatus;
    private LevelOfEducation levelOfEducation;
    private String occupation;
    private Reelection reelection;
    private String coalition;
    private String position;
    private Party party;
    private String state;
    private String city;
    private Integer candidacyNumber;
    private Double candidateAsset;
    private List<String> proposals;

}
