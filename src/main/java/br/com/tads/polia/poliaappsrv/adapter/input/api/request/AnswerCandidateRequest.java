package br.com.tads.polia.poliaappsrv.adapter.input.api.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerCandidateRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID candidateId;
    private UUID questionId;
    private Integer answerWeight;

}
