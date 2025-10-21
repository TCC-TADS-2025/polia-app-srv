package br.com.tads.polia.poliaappsrv.adapter.output;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.CandidateEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.mapper.CandidateOutputMapper;
import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;
import br.com.tads.polia.poliaappsrv.port.output.ICandidateOutputPort;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.CandidateRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CandidateOutputPort implements ICandidateOutputPort {

    @Autowired
    private CandidateOutputMapper MAPPER;

    private final CandidateRepository candidateRepository;

    public CandidateOutputPort(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
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
    public Candidate getCandidateById(UUID id) {
        var result = MAPPER.candidateEntityToCandidate(candidateRepository.findById(id).orElse(null));
        if (result == null) {
            return null;
        }
        return result;
    }

    @Override
    public void deleteAdminById(UUID id) {
        CandidateEntity candidate = candidateRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Candidate not found with ID: " + id));
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
        CandidateEntity candidateEntity = MAPPER.candidateToCandidateEntity(result);
        candidateRepository.save(candidateEntity);
        Candidate candidateUpdated = MAPPER.candidateEntityToCandidate(candidateEntity);
        return candidateUpdated;
    }
}
