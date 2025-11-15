package br.com.tads.polia.poliaappsrv.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoriteCandidate {

    private UUID id;
    private String userId;
    private UUID candidateId;
    private LocalDateTime createdAt;
}
