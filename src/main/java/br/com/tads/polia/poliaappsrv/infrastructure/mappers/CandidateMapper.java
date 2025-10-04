package br.com.tads.polia.poliaappsrv.infrastructure.mappers;

import br.com.tads.polia.poliaappsrv.adapter.output.bd.CandidateEntity;
import br.com.tads.polia.poliaappsrv.domain.dto.candidate.CandidateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import javax.sql.rowset.serial.SerialClob;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateMapper {

    CandidateDTO toDTO(CandidateEntity candidate);
    
    CandidateEntity toEntity(CandidateDTO candidateDTO);
    
    @Mapping(target = "proposals", qualifiedByName = "clobToString")
    List<CandidateDTO> toDTOList(List<CandidateEntity> candidates);
    
    @Mapping(target = "proposals", qualifiedByName = "stringToClob")
    List<CandidateEntity> toEntityList(List<CandidateDTO> candidateDTOs);

    @Named("clobToString")
    static String clobToString(Clob clob) {
        if (clob == null) return null;
        try (Reader reader = clob.getCharacterStream()) {
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[2048];
        int read;
        while ((read = reader.read(buf)) != -1) {
            sb.append(buf, 0, read);
        }
        return sb.toString();
        } catch (IOException | SQLException e) {
        throw new RuntimeException();
        }
    }

    @Named("stringToClob")
    static Clob stringToClob(String content) throws SQLException {
        if (content == null) {
            return new SerialClob(new char[0]);
        }
        return new SerialClob(content.toCharArray());
    }
}