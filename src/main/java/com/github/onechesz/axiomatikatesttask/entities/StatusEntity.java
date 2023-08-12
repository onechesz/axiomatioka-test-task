package com.github.onechesz.axiomatikatesttask.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Репрезентует таблицу "status" базы данных, представляет из себя сущность статуса одобрения завяки на кредит
 */
@Entity
@Table(name = "status")
public class StatusEntity {
    @Column(name = "client_id")
    @Id
    private int clientId;
    @Column(name = "is_approved", nullable = false)
    private boolean isApproved;
    @Column(name = "date", nullable = false)
    private LocalDate date;
    @Column(name = "days_term")
    private int daysTerm;
    @OneToOne
    @MapsId
    @JoinColumn(name = "client_id")
    private ClientEntity clientEntity;

    public StatusEntity() {

    }

    public StatusEntity(boolean isApproved, LocalDate date, ClientEntity clientEntity) {
        this.isApproved = isApproved;
        this.date = date;
        this.clientEntity = clientEntity;
    }

    public StatusEntity(int clientId, boolean isApproved, LocalDate date, int daysTerm, ClientEntity clientEntity) {
        this.clientId = clientId;
        this.isApproved = isApproved;
        this.date = date;
        this.daysTerm = daysTerm;
        this.clientEntity = clientEntity;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getDaysTerm() {
        return daysTerm;
    }

    public void setDaysTerm(int daysTerm) {
        this.daysTerm = daysTerm;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }
}
