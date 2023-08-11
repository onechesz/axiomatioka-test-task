package com.github.onechesz.axiomatikatesttask.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "job")
public class JobEntity {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "organization_name", nullable = false)
    private String organizationName;
    @Column(name = "title", nullable = false)
    private String title;
    @Column(name = "start", nullable = false)
    private LocalDate start;
    @Column(name = "\"end\"")
    private LocalDate end;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity clientEntity;

    public JobEntity() {

    }

    public JobEntity(String organizationName, String title, LocalDate start, LocalDate end) {
        this.organizationName = organizationName;
        this.title = title;
        this.start = start;
        this.end = end;
    }

    public JobEntity(int id, String organizationName, String title, LocalDate start, LocalDate end, ClientEntity clientEntity) {
        this.id = id;
        this.organizationName = organizationName;
        this.title = title;
        this.start = start;
        this.end = end;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String job) {
        this.title = job;
    }

    public LocalDate getWorkStart() {
        return start;
    }

    public void setWorkStart(LocalDate workStart) {
        this.start = workStart;
    }

    public LocalDate getWorkEnd() {
        return end;
    }

    public void setWorkEnd(LocalDate workEnd) {
        this.end = workEnd;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }
}
