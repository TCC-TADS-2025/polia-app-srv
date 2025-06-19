package br.com.tads.polia.poliaappsrv.domain.usecase;


import br.com.tads.polia.poliaappsrv.adapter.output.repositories.CandidateRepository;
import br.com.tads.polia.poliaappsrv.domain.dto.CandidateDTO;
import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CandidateUseCase {

    @Autowired
    private CandidateRepository candidateRepository;

    public CandidateDTO createCandidate(CandidateDTO candidateDTO) {
        Candidate candidate = new Candidate();
        candidate.setName(candidateDTO.getName());
        candidate.setParty(candidateDTO.getParty());
        candidate.setGender(candidateDTO.getGender());
        candidate.setRace(candidateDTO.getRace());
        candidate.setReelection(candidateDTO.getReelection());
        candidate.setVotes(candidateDTO.getVotes());

        Candidate savedCandidate = candidateRepository.save(candidate);
        return new CandidateDTO(savedCandidate);
    }

}
