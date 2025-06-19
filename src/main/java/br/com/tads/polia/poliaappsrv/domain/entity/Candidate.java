package br.com.tads.polia.poliaappsrv.domain.entity;


import br.com.tads.polia.poliaappsrv.domain.enums.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "candidates")
public class Candidate {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String cpf;

    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String name;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    @Size(min = 2, max = 50, message = "Nacionalidade deve ter entre 2 e 50 caracteres")
    private String nationality;

    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "Genero deve ter entre 2 e 100 caracteres")
    private Gender gender;

    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "Raça deve ter entre 2 e 100 caracteres")
    private Race race;

    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "Estado Civil deve ter entre 2 e 100 caracteres")
    private CivilStatus civilStatus;

    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "Instrução deve ter entre 2 e 100 caracteres")
    private LevelOfEducation levelOfEducation;

    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "Ocupação deve ter entre 2 e 100 caracteres")
    private String occupation;

    @Column(nullable = false)
    @Size(max = 3, message = "Reeleição deve ter 3 caracteres")
    private Reelection reelection;

    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "Coligação deve ter entre 2 e 100 caracteres")
    private String coalition;

    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "Cargo deve ter entre 2 e 100 caracteres")
    private String position;

    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "Partido deve ter entre 2 e 100 caracteres")
    private Party party;

    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "Estado deve ter entre 2 e 100 caracteres")
    private String state;

    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "Cidade deve ter entre 2 e 100 caracteres")
    private String city;

    @Column(nullable = false)
    private Integer candidacyNumber;

    @Column(nullable = false)
    private Double candidateAsset;

    @Column(nullable = false)
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private List<String> proposals;

    public Candidate() {
    }

    public Candidate(String cpf, String name,LocalDate birthday, String nationality,Gender gender,Race race, CivilStatus civilStatus, LevelOfEducation levelOfEducation,String occupation, Reelection reelection, String coalition, String position, Party party, String state, String city, Integer candidacyNumber, Double candidateAsset, List<String> proposals) {
        this.cpf = cpf;
        this.name = name;
        this.birthday = birthday;
        this.nationality = nationality;
        this.gender = gender;
        this.race = race;
        this.civilStatus = civilStatus;
        this.levelOfEducation = levelOfEducation;
        this.occupation = occupation;
        this.reelection = reelection;
        this.coalition = coalition;
        this.position = position;
        this.party = party;
        this.state = state;
        this.city = city;
        this.candidacyNumber = candidacyNumber;
        this.candidateAsset = candidateAsset;
        this.proposals = proposals;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public LevelOfEducation getLevelOfEducation() {
        return levelOfEducation;
    }

    public void setLevelOfEducation(LevelOfEducation levelOfEducation) {
        this.levelOfEducation = levelOfEducation;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public CivilStatus getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(CivilStatus civilStatus) {
        this.civilStatus = civilStatus;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getCandidacyNumber() {
        return candidacyNumber;
    }

    public void setCandidacyNumber(Integer candidacyNumber) {
        this.candidacyNumber = candidacyNumber;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Reelection getReelection() {
        return reelection;
    }

    public void setReelection(Reelection reelection) {
        this.reelection = reelection;
    }

    public String getCoalition() {
        return coalition;
    }

    public void setCoalition(String coalition) {
        this.coalition = coalition;
    }

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getCandidateAsset() {
        return candidateAsset;
    }

    public void setCandidateAsset(Double candidateAsset) {
        this.candidateAsset = candidateAsset;
    }

    public List<String> getProposals() {
        return proposals;
    }

    public void setProposals(List<String> proposals) {
        this.proposals = proposals;
    }
}
