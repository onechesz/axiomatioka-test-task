package com.github.onechesz.axiomatikatesttask.dto;

import com.github.onechesz.axiomatikatesttask.entities.JobEntity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.jetbrains.annotations.Contract;

import java.time.LocalDate;

/**
 * Необходим при передаче данных от клиента в базу данных, валидации полей
 */
public class JobDTO {
    @Size(min = 1, max = 255, message = "должно быть от 1 до 255 симоволов")
    private String organizationName;
    @Size(min = 1, max = 255, message = "должно быть от 1 до 255 символов")
    private String title;
    @NotNull(message = "не может быть пустым")
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

    @Contract(value = "_ -> new", pure = true)
    public static @org.jetbrains.annotations.NotNull JobEntity convertToJobEntity(@org.jetbrains.annotations.NotNull JobDTO jobDTO) {
        return new JobEntity(jobDTO.organizationName, jobDTO.title, jobDTO.start, jobDTO.end);
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
