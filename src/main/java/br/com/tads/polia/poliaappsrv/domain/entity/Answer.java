package br.com.tads.polia.poliaappsrv.domain.entity;

import br.com.tads.polia.poliaappsrv.domain.dto.user.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {

    private UUID answerId;
    private UUID questionId;
    private Integer answerWeight;
    private String userId;
}
