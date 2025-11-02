package br.com.tads.polia.poliaappsrv.adapter.input.api.request.mapper;


import br.com.tads.polia.poliaappsrv.adapter.input.api.request.AnswerCandidateRequest;
import br.com.tads.polia.poliaappsrv.domain.entity.AnswerCandidate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerCandidateMapperRequest {

    AnswerCandidateMapperRequest INSTANCE = Mappers.getMapper(AnswerCandidateMapperRequest.class);

    AnswerCandidate AnswerCandidateResquestToAnswerCandidate(AnswerCandidateRequest request);
    List<AnswerCandidate> listAnswerCandidateResquestToAnswer(List<AnswerCandidateRequest> requestList);

}
