package br.com.tads.polia.poliaappsrv.adapter.input.api.response.mappers;

import br.com.tads.polia.poliaappsrv.adapter.input.api.response.CandidateResponse;
import br.com.tads.polia.poliaappsrv.domain.dto.candidate.CandidateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CandidateMapper {

    CandidateMapper INSTANCE = Mappers.getMapper(CandidateMapper.class);

    CandidateResponse CandidateDTOToCandidateResponse(CandidateDTO candidatedto);

}
