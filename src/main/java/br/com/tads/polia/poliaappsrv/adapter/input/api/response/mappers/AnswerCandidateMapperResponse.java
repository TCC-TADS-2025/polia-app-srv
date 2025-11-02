package br.com.tads.polia.poliaappsrv.adapter.input.api.response.mappers;


import br.com.tads.polia.poliaappsrv.adapter.input.api.response.AnswerResponse;
import br.com.tads.polia.poliaappsrv.domain.entity.AnswerCandidate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerCandidateMapperResponse {

    AnswerCandidateMapperResponse INSTANCE = Mappers.getMapper(AnswerCandidateMapperResponse.class);

    AnswerResponse answerCandidateToAnswerResponse(AnswerCandidate answer);
    List<AnswerResponse> listAnswersCandidateToAnswerResponses(List<AnswerCandidate> answers);
}
