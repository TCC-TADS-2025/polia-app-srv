package br.com.tads.polia.poliaappsrv.domain.usecase;

import br.com.tads.polia.poliaappsrv.adapter.output.FavoriteCandidateOutputPort;
import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;
import br.com.tads.polia.poliaappsrv.domain.entity.FavoriteCandidate;
import br.com.tads.polia.poliaappsrv.port.output.ICandidateOutputPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CandidateUseCase {

    private final CoordinateCandidateCalculatorUseCase coordinateCandidateCalculatorUseCase;
    private final ICandidateOutputPort outputPort;
    private final FavoriteCandidateOutputPort favoriteCandidateOutputPort;

    public CandidateUseCase(ICandidateOutputPort outputPort, CoordinateCandidateCalculatorUseCase coordinateCandidateCalculatorUseCase, FavoriteCandidateOutputPort favoriteCandidateOutputPort) {
        this.outputPort = outputPort;
        this.coordinateCandidateCalculatorUseCase = coordinateCandidateCalculatorUseCase;
        this.favoriteCandidateOutputPort = favoriteCandidateOutputPort;
    }

    public Candidate createCandidate(Candidate candidate) {
        if (candidate == null) {
            throw new IllegalArgumentException("Candidate cannot be null or empty");
        }
        Candidate createdCandidate = outputPort.createCandidate(candidate);
        coordinateCandidateCalculatorUseCase.calculateCoordinateForCandidate(createdCandidate.getId());
        return createdCandidate;
    }

    public List<Candidate> getAllCandidates() {
        return outputPort.getAllCandidates();
    }

    public List<Candidate> findNextsByUserId(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        return outputPort.findNextsByUserId(userId);
    }

    public Candidate getCandidateById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        return outputPort.getCandidateById(id);
    }

    public Candidate updateAdminById(UUID id, Candidate candidate) {
        if (candidate == null) {
            throw new IllegalArgumentException("Candidate or Candidate ID cannot be null or empty");
        }
        Candidate createdCandidate = outputPort.updateCandidateById(id,candidate);
        coordinateCandidateCalculatorUseCase.calculateCoordinateForCandidate(createdCandidate.getId());
        return createdCandidate;
    }

    public void deleteCandidateById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        outputPort.deleteAdminById(id);
    }
    
    public FavoriteCandidate addFavorite(String userId, UUID candidateId) {
        if (userId == null || candidateId == null) {
            throw new IllegalArgumentException("User ID and Candidate ID cannot be null");
        }
        return favoriteCandidateOutputPort.addFavorite(userId, candidateId);
    }

    public void removeFavorite(String userId, UUID candidateId) {
        if (userId == null || candidateId == null) {
            throw new IllegalArgumentException("User ID and Candidate ID cannot be null");
        }
        favoriteCandidateOutputPort.removeFavorite(userId, candidateId);
    }

    public List<FavoriteCandidate> getUserFavorites(String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        return favoriteCandidateOutputPort.getUserFavorites(userId);
    }

    public List<Candidate> getUserFavoriteCandidates(String userId) {
        if (userId == null) {
            throw new IllegalArgumentException("User ID cannot be null");
        }
        List<FavoriteCandidate> favorites = favoriteCandidateOutputPort.getUserFavorites(userId);
        return favorites.stream()
            .map(fav -> outputPort.getCandidateById(fav.getCandidateId()))
            .filter(candidate -> candidate != null)
            .toList();
    }

    public boolean isFavorited(String userId, UUID candidateId) {
        if (userId == null || candidateId == null) {
            throw new IllegalArgumentException("User ID and Candidate ID cannot be null");
        }
        return favoriteCandidateOutputPort.isFavorited(userId, candidateId);
    }
}
