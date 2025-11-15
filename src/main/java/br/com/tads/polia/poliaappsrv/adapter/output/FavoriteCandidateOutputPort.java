package br.com.tads.polia.poliaappsrv.adapter.output;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.FavoriteCandidateEntity;
import br.com.tads.polia.poliaappsrv.domain.entity.FavoriteCandidate;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.FavoriteCandidateRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoriteCandidateOutputPort {

    private final FavoriteCandidateRepository favoriteCandidateRepository;

    public FavoriteCandidate addFavorite(String userId, UUID candidateId) {
        var existing = favoriteCandidateRepository.findByUserIdAndCandidateId(userId, candidateId);
        if (existing.isPresent()) {
            return mapToFavoriteCandidate(existing.get());
        }
        
        FavoriteCandidateEntity entity = new FavoriteCandidateEntity();
        entity.setUserId(userId);
        entity.setCandidateId(candidateId);
        
        FavoriteCandidateEntity saved = favoriteCandidateRepository.save(entity);
        return mapToFavoriteCandidate(saved);
    }

    @Transactional
    public void removeFavorite(String userId, UUID candidateId) {
        favoriteCandidateRepository.deleteByUserIdAndCandidateId(userId, candidateId);
    }

    public List<FavoriteCandidate> getUserFavorites(String userId) {
        return favoriteCandidateRepository.findByUserId(userId)
            .stream()
            .map(this::mapToFavoriteCandidate)
            .toList();
    }

    public boolean isFavorited(String userId, UUID candidateId) {
        return favoriteCandidateRepository.findByUserIdAndCandidateId(userId, candidateId).isPresent();
    }

    private FavoriteCandidate mapToFavoriteCandidate(FavoriteCandidateEntity entity) {
        return new FavoriteCandidate(
            entity.getId(),
            entity.getUserId(),
            entity.getCandidateId(),
            entity.getCreatedAt()
        );
    }
}
