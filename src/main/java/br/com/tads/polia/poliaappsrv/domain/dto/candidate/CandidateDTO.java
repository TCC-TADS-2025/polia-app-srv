package br.com.tads.polia.poliaappsrv.domain.dto.candidate;

import br.com.tads.polia.poliaappsrv.domain.enums.*;
import br.com.tads.polia.poliaappsrv.infrastructure.config.ClobDeserializer;

import java.math.BigDecimal;
import java.sql.Clob;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public record CandidateDTO(
    @JsonProperty("id")
    String id,

    @JsonProperty("cpf")
    String cpf,
    
    @JsonProperty("name")
    String name,
    
    @JsonProperty("birthday")
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate birthday,
    
    @JsonProperty("nationality")
    String nationality,
    
    @JsonProperty("gender")
    Gender gender,
    
    @JsonProperty("race")
    Race race,
    
    @JsonProperty("civilStatus")
    CivilState civilStatus,
    
    @JsonProperty("levelOfEducation")
    String levelOfEducation,
    
    @JsonProperty("occupation")
    String occupation,
    
    @JsonProperty("reelection")
    Boolean reelection,
    
    @JsonProperty("coalition")
    String coalition,
    
    @JsonProperty("position")
    String position,
    
    @JsonProperty("party")
    String party,
    
    @JsonProperty("state")
    String state,
    
    @JsonProperty("city")
    String city,
    
    @JsonProperty("candidacyNumber")
    Integer candidacyNumber,
    
    @JsonProperty("candidateAsset")
    BigDecimal candidateAsset,
    
    @JsonProperty("proposals")
    String proposals
) {
    
}
