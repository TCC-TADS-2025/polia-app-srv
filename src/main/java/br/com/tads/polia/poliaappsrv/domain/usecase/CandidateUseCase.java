package br.com.tads.polia.poliaappsrv.domain.usecase;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.CandidateEntity;
import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;
import br.com.tads.polia.poliaappsrv.port.output.ICandidateOutputPort;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.CandidateRepository;
import br.com.tads.polia.poliaappsrv.domain.dto.candidate.CandidateDTO;
import br.com.tads.polia.poliaappsrv.infrastructure.mappers.CandidateMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CandidateUseCase {

    @Autowired
    private CandidateMapper candidateMapper;

    @Autowired
    private CandidateRepository candidateRepository;

    private final ICandidateOutputPort outputPort;

    public CandidateUseCase(ICandidateOutputPort outputPort) {
        this.outputPort = outputPort;
    }

    @Transactional
    public CandidateDTO createCandidate(CandidateDTO dto) {
        CandidateEntity candidate = new CandidateEntity();
        candidate.setName(dto.getName());
        candidate.setBirthday(dto.getBirthday());
        candidate.setNationality(dto.getNationality());
        candidate.setGender(dto.getGender());
        candidate.setRace(dto.getRace());
        candidate.setCivilStatus(dto.getCivilStatus());
        candidate.setLevelOfEducation(dto.getLevelOfEducation());
        candidate.setOccupation(dto.getOccupation());
        candidate.setReelection(dto.getReelection());
        candidate.setCoalition(dto.getCoalition());
        candidate.setPosition(dto.getPosition());
        candidate.setParty(dto.getParty());
        candidate.setState(dto.getState());
        candidate.setCity(dto.getCity());
        candidate.setCandidacyNumber(dto.getCandidacyNumber());
        candidate.setCandidateAsset(dto.getCandidateAsset());
        candidate.setProposals(dto.getProposals());
        candidateRepository.save(candidate);
        return dto;
    }

    public List<Candidate> getAllCandidates() {
        return outputPort.getAllCandidates();
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
        return outputPort.updateCandidateById(id,candidate);
//        if (!candidateRepository.existsById(id)) {
//            throw new EntityNotFoundException("Candidate not found with ID: " + id);
//        }
//        CandidateEntity candidateToUpdate = new CandidateEntity();
//        candidateToUpdate.setId(id);
//        candidateToUpdate.setName(candidateDTO.getName());
//        candidateToUpdate.setBirthday(candidateDTO.getBirthday());
//        candidateToUpdate.setNationality(candidateDTO.getNationality());
//        candidateToUpdate.setGender(candidateDTO.getGender());
//        candidateToUpdate.setRace(candidateDTO.getRace());
//        candidateToUpdate.setCivilStatus(candidateDTO.getCivilStatus());
//        candidateToUpdate.setLevelOfEducation(candidateDTO.getLevelOfEducation());
//        candidateToUpdate.setOccupation(candidateDTO.getOccupation());
//        candidateToUpdate.setReelection(candidateDTO.getReelection());
//        candidateToUpdate.setCoalition(candidateDTO.getCoalition());
//        candidateToUpdate.setPosition(candidateDTO.getPosition());
//        candidateToUpdate.setParty(candidateDTO.getParty());
//        candidateToUpdate.setState(candidateDTO.getState());
//        candidateToUpdate.setCity(candidateDTO.getCity());
//        candidateToUpdate.setCandidacyNumber(candidateDTO.getCandidacyNumber());
//        candidateToUpdate.setCandidateAsset(candidateDTO.getCandidateAsset());
//        candidateToUpdate.setProposals(candidateDTO.getProposals());
//        CandidateEntity updatedCandidate = candidateRepository.save(candidateToUpdate);
//
//        return candidateMapper.toDTO(updatedCandidate);
    }

    public void deleteCandidateById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        outputPort.deleteAdminById(id);
    }
}
