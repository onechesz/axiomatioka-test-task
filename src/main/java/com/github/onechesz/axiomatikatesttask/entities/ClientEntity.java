package com.github.onechesz.axiomatikatesttask.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "client")
public class ClientEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "firstname", nullable = false)
    private String firstname;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "lastname", nullable = false)
    private String lastname;
    @Column(name = "passport", unique = true, nullable = false)
    private String passport;
    @Column(name = "family_status", nullable = false)
    private String familyStatus;
    @Column(name = "address", nullable = false)
    private String address;
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
    @OneToMany(mappedBy = "clientEntity")
    private List<WorkEntity> workEntityList;
    @Column(name = "sum", nullable = false)
    private BigDecimal sum;

    public ClientEntity() {

    }

    public ClientEntity(int id, String firstname, String surname, String lastname, String passport, String familyStatus, String address, String phoneNumber, List<WorkEntity> workEntityList, BigDecimal sum) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.lastname = lastname;
        this.passport = passport;
        this.familyStatus = familyStatus;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.workEntityList = workEntityList;
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getFamilyStatus() {
        return familyStatus;
    }

    public void setFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<WorkEntity> getWorkEntityList() {
        return workEntityList;
    }

    public void setWorkEntityList(List<WorkEntity> workEntityList) {
        this.workEntityList = workEntityList;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }
}
