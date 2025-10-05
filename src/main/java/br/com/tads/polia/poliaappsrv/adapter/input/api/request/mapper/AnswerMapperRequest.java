package br.com.tads.polia.poliaappsrv.adapter.input.api.request.mapper;

import br.com.tads.polia.poliaappsrv.adapter.input.api.request.AnswerRequest;
import br.com.tads.polia.poliaappsrv.domain.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerMapperRequest {
    AnswerMapperRequest INSTANCE = Mappers.getMapper(AnswerMapperRequest.class);

    Answer AnswerResquestToAnswer(AnswerRequest request);
    List<Answer> listAnswerResquestToAnswer(List<AnswerRequest> requestList);
}
