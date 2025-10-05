package br.com.tads.polia.poliaappsrv.adapter.output.mapper;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.AnswerEntity;
import br.com.tads.polia.poliaappsrv.domain.entity.Answer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AnswerOutputMapper {
    AnswerOutputMapper INSTANCE = Mappers.getMapper(AnswerOutputMapper.class);

    @Mapping(source = "questionId", target = "questionAnswer.idQuestionWeight")
    @Mapping(source = "userId", target = "userId.id")
    AnswerEntity answerToAnswerEntity(Answer answer);

    List<AnswerEntity> listAnswerToAnswerEntity(List<Answer> answerDomains);

    @Mapping(source = "questionAnswer.idQuestionWeight", target = "questionId")
    @Mapping(source = "userId.id", target = "userId")
    Answer answerEntityToAnswer(AnswerEntity entity);

    List<Answer> listAnswerEntityToAnswer(List<AnswerEntity> entities);
}
