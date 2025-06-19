package br.com.tads.polia.poliaappsrv.infrastructure.mappers;

import br.com.tads.polia.poliaappsrv.domain.dto.CandidateDTO;
import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;
import org.springframework.stereotype.Component;

@Component
public class CandidateMapper {

    public Candidate toEntity(CandidateDTO dto) {

        return new Candidate(
                dto.cpf(),
                dto.name(),
                dto.birthday(),
                dto.nationality(),
                dto.gender(),
                dto.race(),
                dto.civilStatus(),
                dto.levelOfEducation(),
                dto.occupation(),
                dto.reelection(),
                dto.coalition(),
                dto.position(),
                dto.party(),
                dto.state(),
                dto.city(),
                dto.candidacyNumber(),
                dto.candidateAsset(),
                dto.proposals()
        );
    }
}
