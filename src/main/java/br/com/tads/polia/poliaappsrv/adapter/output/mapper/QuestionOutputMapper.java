package br.com.tads.polia.poliaappsrv.adapter.output.mapper;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.QuestionAnswerEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.bd.QuestionEntity;
import br.com.tads.polia.poliaappsrv.domain.entity.Question;
import br.com.tads.polia.poliaappsrv.domain.entity.Weight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface QuestionOutputMapper {

    QuestionOutputMapper INSTANCE = Mappers.getMapper(QuestionOutputMapper.class);

    @Mapping(source = "id", target = "questionId")
    Question questionEntityToQuestion (QuestionEntity entity);

    List<Question> listQuestionEntityToQuestion(List<QuestionEntity> entities);

    @Mapping(target = "question", source = "question", qualifiedByName = "questionEntityToUuid")
    Weight weightEntityToWeight(QuestionAnswerEntity entity);

    List<Weight> listWeightEntityToWeight(List<QuestionAnswerEntity> entities);

    @org.mapstruct.Named("questionEntityToUuid")
    static UUID questionEntityToUuid(QuestionEntity questionEntity) {
        return questionEntity != null ? questionEntity.getId() : null;
    }
}
