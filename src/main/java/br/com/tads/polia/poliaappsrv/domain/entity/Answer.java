package br.com.tads.polia.poliaappsrv.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
    private String answerId;
    private String questionAnswerId;
    private String questionText;
    private String userId;
    private String createdAt;
    private String updatedAt;
}
