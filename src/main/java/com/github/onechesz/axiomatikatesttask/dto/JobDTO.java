package com.github.onechesz.axiomatikatesttask.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class JobDTO {
    @Size(min = 1, max = 255)
    private String organizationName;
    @Size(min = 1, max = 255)
    private String title;
    @NotNull
    private LocalDate start;
    private LocalDate end;

    public JobDTO() {

    }

    public JobDTO(String organizationName, String title, LocalDate start, LocalDate end) {
        this.organizationName = organizationName;
        this.title = title;
        this.start = start;
        this.end = end;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "JobDTO{" +
                "organizationName='" + organizationName + '\'' +
                ", job='" + title + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
