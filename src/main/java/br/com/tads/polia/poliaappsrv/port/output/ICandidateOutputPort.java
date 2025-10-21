package br.com.tads.polia.poliaappsrv.port.output;

import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;

import java.util.List;
import java.util.UUID;

public interface ICandidateOutputPort {

    List<Candidate> getAllCandidates();
    Candidate getCandidateById(UUID id);
    void deleteAdminById(UUID id);
    Candidate updateCandidateById(UUID id, Candidate candidate);
}
