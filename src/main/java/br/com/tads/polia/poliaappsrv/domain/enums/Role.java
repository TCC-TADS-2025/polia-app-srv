package br.com.tads.polia.poliaappsrv.domain.enums;

public enum Role {
    ADMIN,
    USER;
    public String getDescription() {
        return this.name();
    }
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
