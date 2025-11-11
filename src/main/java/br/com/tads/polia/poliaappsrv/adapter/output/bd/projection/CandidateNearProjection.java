package br.com.tads.polia.poliaappsrv.adapter.output.bd.projection;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public interface CandidateNearProjection {
    UUID getId();
    String getName();
    LocalDate getBirthday();
    String getNationality();
    String getGender();
    String getRace();
    String getCivilStatus();
    String getLevelOfEducation();
    String getOccupation();
    Boolean getReelection();
    String getCoalition();
    String getPosition();
    String getParty();
    String getState();
    String getCity();
    Integer getCandidacyNumber();
    BigDecimal getCandidateAsset();
    Integer getCoordinateX();
    Integer getCoordinateY();
    String getStatusIa();
    LocalDateTime getDataInclusao();
    LocalDateTime getDataAlteracao();
   
}
