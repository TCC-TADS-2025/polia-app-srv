package br.com.tads.polia.poliaappsrv.domain.enums;

public enum Role {
    ADMIN;
    public String getDescription() {
        return this.name();
    }
    public String getAuthority() {
        return "ROLE_" + this.name();
    }
}
