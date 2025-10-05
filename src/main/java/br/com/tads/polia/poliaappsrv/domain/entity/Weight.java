package br.com.tads.polia.poliaappsrv.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weight {
    private UUID idQuestionWeight;
    private String type;
    private Integer weightX;
    private Integer weightY;
    private UUID question;
}
