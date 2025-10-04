package br.com.tads.polia.poliaappsrv.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weight {
    private String type;
    private BigDecimal weight;
}
