package br.com.tads.polia.poliaappsrv.adapter.output.bd;

import br.com.tads.polia.poliaappsrv.domain.enums.EixoType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "questionsWeights")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionAnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idQuestionWeight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionId", nullable = false)
    private QuestionEntity question;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EixoType type;

    @Min(-2)
    @Max(2)
    private Integer weightX;

    @Min(-2)
    @Max(2)
    private Integer weightY;

}
