package br.com.tads.polia.poliaappsrv.adapter.input.api.request.mapper;

import br.com.tads.polia.poliaappsrv.adapter.input.api.request.CandidateRequest;
import br.com.tads.polia.poliaappsrv.domain.dto.candidate.CandidateDTO;
import br.com.tads.polia.poliaappsrv.adapter.output.bd.CandidateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CandidateMapper {

    CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);

    CandidateDTO CandidateResquestToCandidateDTO(CandidateRequest request);

    CandidateDTO candidateToCandidateDTO(CandidateEntity candidate);
}
