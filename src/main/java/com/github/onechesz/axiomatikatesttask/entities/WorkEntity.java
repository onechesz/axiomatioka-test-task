package com.github.onechesz.axiomatikatesttask.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "work")
public class WorkEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "organization_name", nullable = false)
    private String organizationName;
    @Column(name = "job", nullable = false)
    private String job;
    @Column(name = "work_start", nullable = false)
    private LocalDate workStart;
    @Column(name = "work_end")
    private LocalDate workEnd;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity clientEntity;

    public WorkEntity() {

    }

    public WorkEntity(int id, String organizationName, String job, LocalDate workStart, LocalDate workEnd, ClientEntity clientEntity) {
        this.id = id;
        this.organizationName = organizationName;
        this.job = job;
        this.workStart = workStart;
        this.workEnd = workEnd;
        this.clientEntity = clientEntity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public LocalDate getWorkStart() {
        return workStart;
    }

    public void setWorkStart(LocalDate workStart) {
        this.workStart = workStart;
    }

    public LocalDate getWorkEnd() {
        return workEnd;
    }

    public void setWorkEnd(LocalDate workEnd) {
        this.workEnd = workEnd;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }
}
