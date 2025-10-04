package br.com.tads.polia.poliaappsrv.adapter.output.mapper;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.QuestionEntity;
import br.com.tads.polia.poliaappsrv.domain.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionOutputMapper {

    QuestionOutputMapper INSTANCE = Mappers.getMapper(QuestionOutputMapper.class);

    @Mapping(source = "id", target = "questionId")
    Question questionEntityToQuestion (QuestionEntity entity);

    List<Question> listQuestionEntityToQuestion(List<QuestionEntity> entities);
}
