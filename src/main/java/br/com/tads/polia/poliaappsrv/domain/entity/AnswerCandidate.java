package br.com.tads.polia.poliaappsrv.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerCandidate {

    private UUID answerId;
    private UUID questionId;
    private Integer answerWeight;
    private UUID candidateId;
}
