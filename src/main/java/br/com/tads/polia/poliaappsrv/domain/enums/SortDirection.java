package br.com.tads.polia.poliaappsrv.domain.enums;

public enum SortDirection {
    ASC("ASC"),
    DESC("DESC");

    private final String value;

    SortDirection(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static SortDirection fromString(String value) {
        if (value == null || value.isEmpty()) {
            return ASC;
        }
        return SortDirection.valueOf(value.toUpperCase());
    }

    public org.springframework.data.domain.Sort.Direction toSpringDirection() {
        return this == ASC ? org.springframework.data.domain.Sort.Direction.ASC : org.springframework.data.domain.Sort.Direction.DESC;
    }
}
