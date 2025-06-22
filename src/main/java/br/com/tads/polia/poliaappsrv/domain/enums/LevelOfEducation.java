package br.com.tads.polia.poliaappsrv.domain.enums;

public enum LevelOfEducation {
    FUNDAMENTAL,
    MEDIO,
    SUPERIOR,
    POS_GRADUACAO,
    MESTRADO,
    DOUTORADO,
    OTHER;
    public String getDescription() {
        return this.name();
    }
}
