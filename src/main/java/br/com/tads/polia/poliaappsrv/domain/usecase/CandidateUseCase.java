package br.com.tads.polia.poliaappsrv.domain.usecase;


import br.com.tads.polia.poliaappsrv.adapter.output.repositories.CandidateRepository;
import br.com.tads.polia.poliaappsrv.domain.dto.candidate.CandidateDTO;
import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;
import br.com.tads.polia.poliaappsrv.infrastructure.mappers.CandidateMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateUseCase {

    @Autowired
    private CandidateMapper candidateMapper;
    
    @Autowired
    private CandidateRepository candidateRepository;
    
    public CandidateDTO createCandidate(CandidateDTO dto) {
        Candidate entity = candidateMapper.toEntity(dto);
        entity = candidateRepository.save(entity);
        return candidateMapper.toDTO(entity);
    }
    
    public List<CandidateDTO> getAllCandidates() {
        List<Candidate> candidates = candidateRepository.findAll();
        return candidateMapper.toDTOList(candidates);
    }

    public CandidateDTO getCandidateById(String cpf) {
        Candidate candidate = candidateRepository.findById(cpf)
            .orElseThrow(() -> new EntityNotFoundException("Candidate not found with CPF: " + cpf));
        return candidateMapper.toDTO(candidate);
    }

    public CandidateDTO updateCandidate(String cpf, CandidateDTO candidateDTO) {
        if (!candidateRepository.existsById(cpf)) {
            throw new EntityNotFoundException("Candidate not found with CPF: " + cpf);
        }
 
        Candidate candidateToUpdate = candidateMapper.toEntity(candidateDTO);
        candidateToUpdate.setCpf(cpf);
        Candidate updatedCandidate = candidateRepository.save(candidateToUpdate);
        
        return candidateMapper.toDTO(updatedCandidate);
    }

    public void deleteCandidate(String cpf) {
        if (!candidateRepository.existsById(cpf)) {
            throw new EntityNotFoundException("Candidate not found with CPF: " + cpf);
        }
        candidateRepository.deleteById(cpf);
    }
}
