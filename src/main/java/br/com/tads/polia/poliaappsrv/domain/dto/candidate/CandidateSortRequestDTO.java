package br.com.tads.polia.poliaappsrv.domain.dto.candidate;

import br.com.tads.polia.poliaappsrv.domain.enums.SortDirection;
import br.com.tads.polia.poliaappsrv.domain.enums.SortField;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateSortRequestDTO {

    @Builder.Default
    private String sortBy = "name";

    @Builder.Default
    private String direction = "ASC";

    public SortField getSortFieldEnum() {
        return SortField.fromString(sortBy);
    }

    public SortDirection getSortDirectionEnum() {
        return SortDirection.fromString(direction);
    }

    public static CandidateSortRequestDTO createDefault() {
        return CandidateSortRequestDTO.builder()
            .sortBy("name")
            .direction("ASC")
            .build();
    }
}
