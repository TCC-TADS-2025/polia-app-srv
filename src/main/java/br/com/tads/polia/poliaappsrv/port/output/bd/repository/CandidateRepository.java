package br.com.tads.polia.poliaappsrv.port.output.bd.repository;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.CandidateEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.bd.projection.CandidateNearProjection;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateEntity, UUID> {

    List<CandidateEntity> findAllByOrderByNameAsc();

    List<CandidateEntity> findAll(Sort sort);

    @Query(nativeQuery = true, value = """
            
            SELECT
            c.*
            FROM candidates c
            JOIN users u ON u.id = :id
            WHERE c.coordinatex IS NOT NULL
            AND c.coordinatey IS NOT NULL
            ORDER BY ((c.coordinatex - u.coordinatex)^2 + (c.coordinatey - u.coordinatey)^2) 
            ASC NULLS LAST
    """)
    List<CandidateNearProjection> findNextsByUserId(@Param("id") String id);
}
