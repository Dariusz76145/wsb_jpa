package com.jpacourse.dto;

import java.time.LocalDateTime;
import java.util.List;

public class VisitTO {
    private Long id;
    private String description;
    private LocalDateTime time;
    private String doctorFirstName; // Imię lekarza
    private String doctorLastName;  // Nazwisko lekarza
    private List<String> medicalTreatmentDescriptions; // Lista typów leczenia

    // Gettery i settery
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public List<String> getMedicalTreatmentDescriptions() {
        return medicalTreatmentDescriptions;
    }

    public void setMedicalTreatmentDescriptions(List<String> medicalTreatmentDescriptions) {
        this.medicalTreatmentDescriptions = medicalTreatmentDescriptions;
    }
}
