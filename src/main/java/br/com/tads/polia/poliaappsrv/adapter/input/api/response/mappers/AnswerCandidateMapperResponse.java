package br.com.tads.polia.poliaappsrv.adapter.input.api.response.mappers;


import br.com.tads.polia.poliaappsrv.adapter.input.api.response.AnswerCandidateResponse;
import br.com.tads.polia.poliaappsrv.domain.entity.AnswerCandidate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerCandidateMapperResponse {

    AnswerCandidateMapperResponse INSTANCE = Mappers.getMapper(AnswerCandidateMapperResponse.class);

    AnswerCandidateResponse answerCandidateToAnswerCandidateResponse(AnswerCandidate answer);
    List<AnswerCandidateResponse> listAnswersCandidateToAnswerCandidateResponses(List<AnswerCandidate> answers);
}
