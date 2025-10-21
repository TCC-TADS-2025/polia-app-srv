package br.com.tads.polia.poliaappsrv.adapter.input.api.response.mappers;


import br.com.tads.polia.poliaappsrv.adapter.input.api.response.CandidateResponse;
import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateMapperResponse {

    CandidateMapperResponse INSTANCE = Mappers.getMapper(CandidateMapperResponse.class);

    CandidateResponse candidateTOCandidateResponse (Candidate candidate);

    List<CandidateResponse> listCandidateToListCandidateResponse (List<Candidate> candidates);

}
