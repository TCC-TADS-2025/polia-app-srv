package br.com.tads.polia.poliaappsrv.adapter.output;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.CandidateEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.mapper.CandidateOutputMapper;
import br.com.tads.polia.poliaappsrv.domain.dto.user.UserDTO;
import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;
import br.com.tads.polia.poliaappsrv.domain.enums.SortDirection;
import br.com.tads.polia.poliaappsrv.domain.enums.SortField;
import br.com.tads.polia.poliaappsrv.port.output.ICandidateOutputPort;
import br.com.tads.polia.poliaappsrv.adapter.output.FavoriteCandidateOutputPort;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.UserRepository;
import br.com.tads.polia.poliaappsrv.adapter.output.bd.UserEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.bd.AdminEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.CandidateRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CandidateOutputPort implements ICandidateOutputPort {

    @Autowired
    private CandidateOutputMapper MAPPER;

    private final CandidateRepository candidateRepository;
    private final AnswerCandidateOutputPort answerCandidateOutputPort;
    private final FavoriteCandidateOutputPort favoriteCandidateOutputPort;
    private final UserRepository userRepository;

    public CandidateOutputPort(CandidateRepository candidateRepository,
                               AnswerCandidateOutputPort answerCandidateOutputPort,
                               FavoriteCandidateOutputPort favoriteCandidateOutputPort,
                               UserRepository userRepository) {
        this.candidateRepository = candidateRepository;
        this.answerCandidateOutputPort = answerCandidateOutputPort;
        this.favoriteCandidateOutputPort = favoriteCandidateOutputPort;
        this.userRepository = userRepository;
    }

    @Override
    public Candidate createCandidate(Candidate candidate) {
        CandidateEntity candidateEntity = new CandidateEntity();
        candidateEntity.setName(candidate.getName());
        candidateEntity.setBirthday(candidate.getBirthday());
        candidateEntity.setNationality(candidate.getNationality());
        candidateEntity.setGender(candidate.getGender());
        candidateEntity.setRace(candidate.getRace());
        candidateEntity.setCivilStatus(candidate.getCivilStatus());
        candidateEntity.setLevelOfEducation(candidate.getLevelOfEducation());
        candidateEntity.setOccupation(candidate.getOccupation());
        candidateEntity.setReelection(candidate.getReelection());
        candidateEntity.setCoalition(candidate.getCoalition());
        candidateEntity.setPosition(candidate.getPosition());
        candidateEntity.setParty(candidate.getParty());
        candidateEntity.setState(candidate.getState());
        candidateEntity.setCity(candidate.getCity());
        candidateEntity.setCandidacyNumber(candidate.getCandidacyNumber());
        candidateEntity.setCandidateAsset(candidate.getCandidateAsset());
        candidateEntity.setProposals(candidate.getProposals());
        candidateEntity.setStatusIa(candidate.getStatusIa());
        candidateRepository.save(candidateEntity);
        Candidate candidateCreated = MAPPER.candidateEntityToCandidate(candidateEntity);
        return candidateCreated;
    }

    @Override
    public List<Candidate> getAllCandidates() {
        var result = MAPPER.listCandidateEntityToListCandidate(candidateRepository.findAll());
        if(result == null || result.isEmpty()) {
            return null;
        }
        return result;
    }

    @Override
    public List<Candidate> getAllCandidatesSortedByName() {
        var result = MAPPER.listCandidateEntityToListCandidate(candidateRepository.findAllByOrderByNameAsc());
        if(result == null || result.isEmpty()) {
            return null;
        }
        return result;
    }

    @Override
    public List<Candidate> getAllCandidatesSorted(String sortBy, String direction) {
        SortField sortField = SortField.fromString(sortBy);
        SortDirection sortDirection = SortDirection.fromString(direction);
        
        Sort sort = Sort.by(
            sortDirection.toSpringDirection(),
            sortField.getFieldName()
        );
        
        var result = MAPPER.listCandidateEntityToListCandidate(
            candidateRepository.findAll(sort)
        );
        
        if(result == null || result.isEmpty()) {
            return null;
        }
        return result;
    }

    @Override
    public Candidate getCandidateById(UUID id) {
        var result = MAPPER.candidateEntityToCandidate(candidateRepository.findById(id).orElse(null));
        if (result == null) {
            return null;
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            UserDTO userDTO = (UserDTO) auth.getPrincipal();
            String userId = userDTO.getId();
            boolean fav = favoriteCandidateOutputPort.isFavorited(userId, id);
            result.setFavorite(fav);
        }

        return result;
    }

    @Override
    @Transactional
    public void deleteAdminById(UUID id) {
        candidateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Candidate not found with ID: " + id));
        answerCandidateOutputPort.deleteByCandidateId(id);
        candidateRepository.deleteById(id);
    }

    @Override
    public Candidate updateCandidateById(UUID id, Candidate candidate) {
        var result = MAPPER.candidateEntityToCandidate(candidateRepository.findById(id).orElse(null));
        if (result == null) {
            return null;
        }
        result.setName(candidate.getName());
        result.setBirthday(candidate.getBirthday());
        result.setNationality(candidate.getNationality());
        result.setGender(candidate.getGender());
        result.setRace(candidate.getRace());
        result.setCivilStatus(candidate.getCivilStatus());
        result.setLevelOfEducation(candidate.getLevelOfEducation());
        result.setOccupation(candidate.getOccupation());
        result.setReelection(candidate.getReelection());
        result.setCoalition(candidate.getCoalition());
        result.setPosition(candidate.getPosition());
        result.setParty(candidate.getParty());
        result.setState(candidate.getState());
        result.setCity(candidate.getCity());
        result.setCandidacyNumber(candidate.getCandidacyNumber());
        result.setCandidateAsset(candidate.getCandidateAsset());
        result.setProposals(candidate.getProposals());
        result.setStatusIa(candidate.getStatusIa());
        CandidateEntity candidateEntity = MAPPER.candidateToCandidateEntity(result);
        candidateRepository.save(candidateEntity);
        Candidate candidateUpdated = MAPPER.candidateEntityToCandidate(candidateEntity);
        return candidateUpdated;
    }

    @Override
    public List<Candidate> findNextsByUserId(String userId) {
        var result = MAPPER.listCandidateProjectionToListCandidate(candidateRepository.findNextsByUserId(userId));
        if(result == null || result.isEmpty()) {
            return null;
        }
        return result;
    }
}
