package br.com.tads.polia.poliaappsrv.adapter.output.repositories;

import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate,String> {
}
