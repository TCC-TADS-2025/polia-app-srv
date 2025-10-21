package br.com.tads.polia.poliaappsrv.domain.usecase;

import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;
import br.com.tads.polia.poliaappsrv.infrastructure.mappers.CandidateMapper;
import br.com.tads.polia.poliaappsrv.port.output.ICandidateOutputPort;
import br.com.tads.polia.poliaappsrv.port.output.bd.repository.CandidateRepository;
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

    public Candidate createCandidate(Candidate candidate) {
        if (candidate == null) {
            throw new IllegalArgumentException("Candidate cannot be null or empty");
        }
        return outputPort.createCandidate(candidate);
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
    }

    public void deleteCandidateById(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        outputPort.deleteAdminById(id);
    }
}
