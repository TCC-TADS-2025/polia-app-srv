package br.com.tads.polia.poliaappsrv.port.output;

import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;

import java.util.List;
import java.util.UUID;

public interface ICandidateOutputPort {

    Candidate createCandidate(Candidate candidate);
    List<Candidate> getAllCandidates();
    List<Candidate> getAllCandidatesSortedByName();
    List<Candidate> getAllCandidatesSorted(String sortBy, String direction);
    List<Candidate> findNextsByUserId(String userId);
    Candidate getCandidateById(UUID id);
    void deleteAdminById(UUID id);
    Candidate updateCandidateById(UUID id, Candidate candidate);
}
