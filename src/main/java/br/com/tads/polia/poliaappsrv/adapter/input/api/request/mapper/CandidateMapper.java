package br.com.tads.polia.poliaappsrv.adapter.input.api.request.mapper;

import br.com.tads.polia.poliaappsrv.adapter.input.api.request.CandidateRequest;
import br.com.tads.polia.poliaappsrv.domain.dto.CandidateDTO;
import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;
import org.mapstruct.factory.Mappers;

public interface CandidateMapper {

    CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);

    CandidateDTO CandidateResquestToCandidateDTO(CandidateRequest request);

    CandidateDTO candidateToCandidateDTO(Candidate candidate);
}
