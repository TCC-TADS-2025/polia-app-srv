package br.com.tads.polia.poliaappsrv.port.output.bd.repository;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.FavoriteCandidateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FavoriteCandidateRepository extends JpaRepository<FavoriteCandidateEntity, UUID> {
    
    List<FavoriteCandidateEntity> findByUserId(String userId);
    
    Optional<FavoriteCandidateEntity> findByUserIdAndCandidateId(String userId, UUID candidateId);
    
    void deleteByUserIdAndCandidateId(String userId, UUID candidateId);
}
