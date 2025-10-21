package br.com.tads.polia.poliaappsrv.adapter.input.api.request.mapper;

import br.com.tads.polia.poliaappsrv.adapter.input.api.request.CandidateRequest;
import br.com.tads.polia.poliaappsrv.adapter.output.bd.CandidateEntity;
import br.com.tads.polia.poliaappsrv.domain.dto.candidate.CandidateDTO;
import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CandidateMapperRequest {

    CandidateMapperRequest INSTANCE = Mappers.getMapper(CandidateMapperRequest.class);

    CandidateDTO CandidateResquestToCandidateDTO(CandidateRequest request);

    CandidateDTO candidateToCandidateDTO(CandidateEntity candidate);

    Candidate candidateRequestToCandidate(CandidateRequest request);
}
