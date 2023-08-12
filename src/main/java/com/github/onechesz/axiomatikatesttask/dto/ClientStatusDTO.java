package com.github.onechesz.axiomatikatesttask.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ClientStatusDTO {
    private int id;
    private String lastname;
    private String firstname;
    private String surname;
    private BigDecimal sum;
    private boolean isApproved;
    private LocalDate date;
    private int daysTerm;

    public ClientStatusDTO() {

    }

    public ClientStatusDTO(int id, String lastname, String firstname, String surname, BigDecimal sum, boolean isApproved, LocalDate date, int daysTerm) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.surname = surname;
        this.sum = sum;
        this.isApproved = isApproved;
        this.date = date;
        this.daysTerm = daysTerm;
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

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
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
}
