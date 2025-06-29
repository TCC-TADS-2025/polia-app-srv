package br.com.tads.polia.poliaappsrv.domain.enums;

public enum LevelOfEducation {
    FUNDAMENTAL,
    MEDIO,
    SUPERIOR,
    POS_GRADUACAO,
    MESTRADO,
    DOUTORADO,
    OTHER,
    NOT_INFORMED;

    public String getDescription() {
        return this.name();
    }
}
