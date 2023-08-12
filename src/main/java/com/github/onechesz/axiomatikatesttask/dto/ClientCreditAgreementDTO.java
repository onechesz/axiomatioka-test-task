package com.github.onechesz.axiomatikatesttask.dto;

import java.time.LocalDateTime;

public class ClientCreditAgreementDTO {
    private int id;
    private String lastname;
    private String firstname;
    private String surname;
    private boolean isSigned;
    private LocalDateTime signDate;
    private String isSignedRu;

    public ClientCreditAgreementDTO() {

    }

    public ClientCreditAgreementDTO(int id, String lastname, String firstname, String surname, boolean isSigned, LocalDateTime signDate) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.surname = surname;
        this.isSigned = isSigned;
        this.signDate = signDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isSigned() {
        return isSigned;
    }

    public void setSigned(boolean signed) {
        isSigned = signed;
    }

    public LocalDateTime getSignDate() {
        return signDate;
    }

    public void setSignDate(LocalDateTime signDate) {
        this.signDate = signDate;
    }

    public String getIsSignedRu() {
        return isSignedRu;
    }

    public void setIsSignedRu(String isSignedRu) {
        this.isSignedRu = isSignedRu;
    }
}
