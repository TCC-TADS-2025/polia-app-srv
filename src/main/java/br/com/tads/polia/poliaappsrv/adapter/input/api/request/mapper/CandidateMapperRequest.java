package br.com.tads.polia.poliaappsrv.adapter.input.api.request.mapper;

import br.com.tads.polia.poliaappsrv.adapter.input.api.request.CandidateRequest;
import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CandidateMapperRequest {

    CandidateMapperRequest INSTANCE = Mappers.getMapper(CandidateMapperRequest.class);

    Candidate candidateRequestToCandidate(CandidateRequest request);
}
