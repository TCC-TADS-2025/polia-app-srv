package br.com.tads.polia.poliaappsrv.adapter.input.api.response.mappers;

import br.com.tads.polia.poliaappsrv.adapter.input.api.response.AnswerResponse;
import br.com.tads.polia.poliaappsrv.domain.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapperResponse {
    AnswerMapperResponse INSTANCE = Mappers.getMapper(AnswerMapperResponse.class);

    AnswerResponse answerToAnswerResponse(Answer answer);
    List<AnswerResponse> listAnswersToAnswerResponses(List<Answer> answers);
}
