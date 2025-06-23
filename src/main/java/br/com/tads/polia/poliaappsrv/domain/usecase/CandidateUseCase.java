package br.com.tads.polia.poliaappsrv.domain.usecase;


import br.com.tads.polia.poliaappsrv.adapter.output.repositories.CandidateRepository;
import br.com.tads.polia.poliaappsrv.domain.dto.candidate.CandidateDTO;
import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;
import br.com.tads.polia.poliaappsrv.infrastructure.mappers.CandidateMapper;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

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
    
    @Transactional
    public CandidateDTO createCandidate(CandidateDTO dto) {
        Candidate candidate = new Candidate();
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
    
    public List<CandidateDTO> getAllCandidates() {
        List<Candidate> candidates = candidateRepository.findAll();
        return candidateMapper.toDTOList(candidates);
    }

    public CandidateDTO getCandidateById(UUID id) {
        Candidate candidate = candidateRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Candidate not found with ID: " + id));
        return candidateMapper.toDTO(candidate);
    }

    public CandidateDTO updateCandidate(UUID id, CandidateDTO candidateDTO) {
        if (!candidateRepository.existsById(id)) {
            throw new EntityNotFoundException("Candidate not found with ID: " + id);
        }
        Candidate candidateToUpdate = new Candidate();
        candidateToUpdate.setName(candidateDTO.getName());
        candidateToUpdate.setBirthday(candidateDTO.getBirthday());
        candidateToUpdate.setNationality(candidateDTO.getNationality());
        candidateToUpdate.setGender(candidateDTO.getGender());
        candidateToUpdate.setRace(candidateDTO.getRace());
        candidateToUpdate.setCivilStatus(candidateDTO.getCivilStatus());
        candidateToUpdate.setLevelOfEducation(candidateDTO.getLevelOfEducation());
        candidateToUpdate.setOccupation(candidateDTO.getOccupation());
        candidateToUpdate.setReelection(candidateDTO.getReelection());
        candidateToUpdate.setCoalition(candidateDTO.getCoalition());
        candidateToUpdate.setPosition(candidateDTO.getPosition());
        candidateToUpdate.setParty(candidateDTO.getParty());
        candidateToUpdate.setState(candidateDTO.getState());
        candidateToUpdate.setCity(candidateDTO.getCity());
        candidateToUpdate.setCandidacyNumber(candidateDTO.getCandidacyNumber());
        candidateToUpdate.setCandidateAsset(candidateDTO.getCandidateAsset());
        candidateToUpdate.setProposals(candidateDTO.getProposals());
        Candidate updatedCandidate = candidateRepository.save(candidateToUpdate);
        
        return candidateMapper.toDTO(updatedCandidate);
    }

    public void deleteCandidate(UUID id) {
        if (!candidateRepository.existsById(id)) {
            throw new EntityNotFoundException("Candidate not found with ID: " + id);
        }
        candidateRepository.deleteById(id);
    }
}
