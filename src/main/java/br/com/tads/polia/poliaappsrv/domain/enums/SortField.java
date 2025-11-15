package br.com.tads.polia.poliaappsrv.domain.enums;

public enum SortField {
    NAME("name"),
    PARTY("party"),
    STATE("state"),
    CITY("city"),
    POSITION("position"),
    GENDER("gender"),
    RACE("race"),
    CIVIL_STATUS("civilStatus"),
    LEVEL_OF_EDUCATION("levelOfEducation"),
    OCCUPATION("occupation"),
    NATIONALITY("nationality"),
    REELECTION("reelection"),
    COALITION("coalition"),
    CANDIDACY_NUMBER("candidacyNumber"),
    CANDIDATE_ASSET("candidateAsset"),
    BIRTHDAY("birthday"),
    CREATED_AT("createdAt"),
    UPDATED_AT("updatedAt");

    private final String fieldName;

    SortField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public static SortField fromString(String value) {
        if (value == null || value.isEmpty()) {
            return NAME; // Default
        }
        try {
            return SortField.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            return NAME; // Fallback para NAME
        }
    }
}
