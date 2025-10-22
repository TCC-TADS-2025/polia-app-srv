package br.com.tads.polia.poliaappsrv.port.output.bd.repository;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.AnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnswerRepository extends JpaRepository<AnswerEntity, UUID> {
    List<AnswerEntity> findByUserId_Id(String userId);
    Optional<AnswerEntity> findByUserId_IdAndQuestionAnswer_IdQuestionWeight(String userId, UUID questionAnswerId);

}
