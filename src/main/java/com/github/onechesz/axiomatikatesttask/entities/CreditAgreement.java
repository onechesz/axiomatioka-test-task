package com.github.onechesz.axiomatikatesttask.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "credit_agreement")
public class CreditAgreement {
    @Column(name = "client_id")
    @Id
    private int clientId;
    @Column(name = "is_signed", nullable = false)
    private boolean isSigned;
    @Column(name = "sign_date")
    private LocalDateTime signDate;
    @OneToOne
    @MapsId
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;

    public CreditAgreement() {

    }

    public CreditAgreement(boolean isSigned, ClientEntity clientEntity) {
        this.isSigned = isSigned;
        this.clientEntity = clientEntity;
    }

    public CreditAgreement(int clientId, boolean isSigned, LocalDateTime signDate, ClientEntity clientEntity) {
        this.clientId = clientId;
        this.isSigned = isSigned;
        this.signDate = signDate;
        this.clientEntity = clientEntity;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
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

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }
}
