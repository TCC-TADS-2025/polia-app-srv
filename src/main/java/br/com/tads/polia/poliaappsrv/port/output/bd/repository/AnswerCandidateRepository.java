package br.com.tads.polia.poliaappsrv.port.output.bd.repository;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.AnswerCandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AnswerCandidateRepository extends JpaRepository<AnswerCandidateEntity, UUID> {
    List<AnswerCandidateEntity> findByCandidateId_Id(UUID candidateId);
    Optional<AnswerCandidateEntity> findByCandidateId_IdAndQuestionAnswer_IdQuestionWeight(UUID candidateId, UUID questionAnswerId);
    @Query("SELECT a FROM AnswerCandidateEntity a WHERE a.candidateId.id = :candidateId")
    List<AnswerCandidateEntity> findByCandidateId(@Param("candidateId") UUID candidateId);
}
