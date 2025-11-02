package br.com.tads.polia.poliaappsrv.adapter.output.mapper;


import br.com.tads.polia.poliaappsrv.adapter.output.bd.AnswerCandidateEntity;
import br.com.tads.polia.poliaappsrv.domain.entity.AnswerCandidate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerCandidateOutputMapper {
    AnswerCandidateOutputMapper INSTANCE = Mappers.getMapper(AnswerCandidateOutputMapper.class);

    @Mapping(source = "questionId", target = "questionAnswer.idQuestionWeight")
    @Mapping(source = "candidateId", target = "candidateId.id")
    AnswerCandidateEntity answerCandidateToAnswerCandidateEntity(AnswerCandidate answerCandidate);

    List<AnswerCandidateEntity> listAnswerCandidateToAnswerCandidateEntity(List<AnswerCandidate> answerDomains);

    @Mapping(source = "questionAnswer.idQuestionWeight", target = "questionId")
    @Mapping(source = "candidateId.id", target = "candidateId")
    AnswerCandidate answerCandidateEntityToAnswerCandidate(AnswerCandidateEntity entity);

    List<AnswerCandidate> listAnswerCandidateEntityToAnswerCandidate(List<AnswerCandidateEntity> entities);

}
