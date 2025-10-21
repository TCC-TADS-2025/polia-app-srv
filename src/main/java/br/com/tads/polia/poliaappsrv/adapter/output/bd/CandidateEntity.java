package br.com.tads.polia.poliaappsrv.adapter.output.bd;

import br.com.tads.polia.poliaappsrv.domain.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "candidates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String name;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    @Size(min = 2, max = 50, message = "Nacionalidade deve ter entre 2 e 50 caracteres")
    private String nationality;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Race race;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CivilState civilStatus;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private LevelOfEducation levelOfEducation;

    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "Ocupação deve ter entre 2 e 100 caracteres")
    private String occupation;

    @Column(nullable = false)
    private Boolean reelection;

    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "Coligação deve ter entre 2 e 100 caracteres")
    private String coalition;

    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "Cargo deve ter entre 2 e 100 caracteres")
    private String position;

    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "Partido deve ter entre 2 e 100 caracteres")
    private String party;

    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "Estado deve ter entre 2 e 100 caracteres")
    private String state;

    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "Cidade deve ter entre 2 e 100 caracteres")
    private String city;

    @Column(nullable = false)
    private Integer candidacyNumber;

    @Column(nullable = false)
    private BigDecimal candidateAsset;

    @Lob
    @Column(nullable = false)
    private String proposals;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime dataInclusao;

    @UpdateTimestamp
    private LocalDateTime dataAlteracao;

}
