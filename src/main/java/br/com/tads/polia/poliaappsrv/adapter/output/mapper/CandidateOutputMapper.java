package br.com.tads.polia.poliaappsrv.adapter.output.mapper;


import br.com.tads.polia.poliaappsrv.adapter.output.bd.CandidateEntity;
import br.com.tads.polia.poliaappsrv.adapter.output.bd.projection.CandidateNearProjection;
import br.com.tads.polia.poliaappsrv.domain.entity.Candidate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CandidateOutputMapper {

    CandidateOutputMapper INSTANCE = Mappers.getMapper(CandidateOutputMapper.class);

    CandidateEntity candidateToCandidateEntity(Candidate candidate);
    Candidate candidateEntityToCandidate(CandidateEntity entity);

    List<Candidate> listCandidateEntityToListCandidate(List<CandidateEntity> entities);
    List<Candidate> listCandidateProjectionToListCandidate(List<CandidateNearProjection> entities);

}
