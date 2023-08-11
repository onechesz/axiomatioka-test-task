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
    @OneToMany(mappedBy = "clientEntity", cascade = CascadeType.ALL)
    private List<JobEntity> jobEntityList;
    @Column(name = "sum", nullable = false)
    private BigDecimal sum;
    @OneToOne(mappedBy = "clientEntity", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private StatusEntity statusEntity;
    @OneToOne(mappedBy = "clientEntity", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private CreditAgreementEntity creditAgreementEntity;

    public ClientEntity() {

    }

    public ClientEntity(String firstname, String surname, String lastname, String passport, String familyStatus, String address, String phoneNumber, List<JobEntity> jobEntityList, BigDecimal sum) {
        this.firstname = firstname;
        this.surname = surname;
        this.lastname = lastname;
        this.passport = passport;
        this.familyStatus = familyStatus;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.jobEntityList = jobEntityList;
        this.sum = sum;
    }

    public ClientEntity(int id, String firstname, String surname, String lastname, String passport, String familyStatus, String address, String phoneNumber, List<JobEntity> jobEntityList, BigDecimal sum, StatusEntity statusEntity, CreditAgreementEntity creditAgreementEntity) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
        this.lastname = lastname;
        this.passport = passport;
        this.familyStatus = familyStatus;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.jobEntityList = jobEntityList;
        this.sum = sum;
        this.statusEntity = statusEntity;
        this.creditAgreementEntity = creditAgreementEntity;
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

    public List<JobEntity> getJobEntityList() {
        return jobEntityList;
    }

    public void setJobEntityList(List<JobEntity> jobEntityList) {
        this.jobEntityList = jobEntityList;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public StatusEntity getStatusEntity() {
        return statusEntity;
    }

    public void setStatusEntity(StatusEntity statusEntity) {
        this.statusEntity = statusEntity;
    }

    public CreditAgreementEntity getCreditAgreementEntity() {
        return creditAgreementEntity;
    }

    public void setCreditAgreementEntity(CreditAgreementEntity creditAgreementEntity) {
        this.creditAgreementEntity = creditAgreementEntity;
    }
}
