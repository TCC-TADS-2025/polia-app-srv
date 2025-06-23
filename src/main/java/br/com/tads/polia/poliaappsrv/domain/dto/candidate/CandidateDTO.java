package br.com.tads.polia.poliaappsrv.domain.dto.candidate;

import br.com.tads.polia.poliaappsrv.domain.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Clob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CandidateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String name;
    private LocalDate birthday;
    private String nationality;
    private Gender gender;
    private Race race;
    private CivilState civilStatus;
    private LevelOfEducation levelOfEducation;
    private String occupation;
    private Reelection reelection;
    private String coalition;
    private String position;
    private Party party;
    private String state;
    private String city;
    private Integer candidacyNumber;
    private BigDecimal candidateAsset;
    private String proposals;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;

    
}
