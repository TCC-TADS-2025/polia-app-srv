package br.com.tads.polia.poliaappsrv.adapter.output.bd;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "favorite_candidates", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_id", "candidate_id"})
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteCandidateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "candidate_id", nullable = false)
    private UUID candidateId;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
