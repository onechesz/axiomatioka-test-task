package com.github.onechesz.axiomatikatesttask.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ClientDTO {
    @Size(min = 1, max = 255)
    private String firstname;
    @Size(min = 1, max = 255)
    private String surname;
    @Size(min = 1, max = 255)
    private String lastname;
    @Pattern(regexp = "^\\d{10}$")
    private String passport;
    private String familyStatus;
    @Size(min = 1, max = 255)
    private String address;
    @Pattern(regexp = "^8\\d{10}$")
    private String phoneNumber;
    @Max(value = 99999999)
    private String sum;

    public ClientDTO() {

    }

    public ClientDTO(String firstname, String surname, String lastname, String passport, String familyStatus, String address, String phoneNumber, String sum) {
        this.firstname = firstname;
        this.surname = surname;
        this.lastname = lastname;
        this.passport = passport;
        this.familyStatus = familyStatus;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.sum = sum;
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

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", passport='" + passport + '\'' +
                ", familyStatus='" + familyStatus + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", sum='" + sum + '\'' +
                '}';
    }
}
