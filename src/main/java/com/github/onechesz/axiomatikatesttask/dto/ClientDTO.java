package com.github.onechesz.axiomatikatesttask.dto;

import com.github.onechesz.axiomatikatesttask.entities.ClientEntity;
import com.github.onechesz.axiomatikatesttask.entities.JobEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.List;

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
    @Valid
    private List<JobDTO> jobDTOList;
    @Max(value = 99999999)
    private BigDecimal sum;

    public ClientDTO() {

    }

    public ClientDTO(String firstname, String surname, String lastname, String passport, String familyStatus, String address, String phoneNumber, List<JobDTO> jobDTOList, BigDecimal sum) {
        this.firstname = firstname;
        this.surname = surname;
        this.lastname = lastname;
        this.passport = passport;
        this.familyStatus = familyStatus;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.jobDTOList = jobDTOList;
        this.sum = sum;
    }

    @Contract(value = "_ -> new", pure = true)
    public static @NotNull ClientEntity convertToClientEntity(@NotNull ClientDTO clientDTO) {
        ClientEntity clientEntity = new ClientEntity(clientDTO.firstname, clientDTO.surname, clientDTO.lastname, clientDTO.passport, clientDTO.familyStatus, clientDTO.address, clientDTO.phoneNumber, clientDTO.jobDTOList.stream().map(JobDTO::convertToJobEntity).toList(), clientDTO.sum);

        for (JobEntity jobEntity : clientEntity.getJobEntityList())
            jobEntity.setClientEntity(clientEntity);

        return clientEntity;
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

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public List<JobDTO> getJobDTOList() {
        return jobDTOList;
    }

    public void setJobDTOList(List<JobDTO> jobDTOList) {
        this.jobDTOList = jobDTOList;
    }

    @Override
    public String toString() {
        return "ClientDTO{" + "firstname='" + firstname + '\'' + ", surname='" + surname + '\'' + ", lastname='" + lastname + '\'' + ", passport='" + passport + '\'' + ", familyStatus='" + familyStatus + '\'' + ", address='" + address + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", jobDTOList=" + jobDTOList + ", sum='" + sum + '\'' + '}';
    }
}
