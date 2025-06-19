package br.com.tads.polia.poliaappsrv.domain.dto;

import br.com.tads.polia.poliaappsrv.domain.enums.*;

import java.time.LocalDate;
import java.util.List;

public record CandidateDTO(
        String cpf,
        String name,
        LocalDate birthday,
        String nationality,
        Gender gender,
        Race race,
        CivilStatus civilStatus,
        LevelOfEducation levelOfEducation,
        String occupation,
        Reelection reelection,
        String coalition,
        String position,
        Party party,
        String state,
        String city,
        Integer candidacyNumber,
        Double candidateAsset,
        List<String> proposals
        ) {
}
