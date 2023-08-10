package com.github.onechesz.axiomatikatesttask.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class WorkDTO {
    @Size(min = 1, max = 255)
    private String organizationName;
    @Size(min = 1, max = 255)
    private String job;
    @NotEmpty
    private LocalDate workStart;
    private LocalDate workEnd;

    public WorkDTO() {

    }

    public WorkDTO(String organizationName, String job, LocalDate workStart, LocalDate workEnd) {
        this.organizationName = organizationName;
        this.job = job;
        this.workStart = workStart;
        this.workEnd = workEnd;
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
}
