package br.com.tads.polia.poliaappsrv.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private UUID questionId;
    private String text;
//    private List<Weight> weights;
}
