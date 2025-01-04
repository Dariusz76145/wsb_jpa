package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;

import java.time.LocalDateTime;

public interface PatientDao extends Dao<PatientEntity, Long> {

    /**
     * Adds a new visit to the patient and updates the patient in a single operation.
     *
     * @param patientId  ID of the patient
     * @param doctorId   ID of the doctor
     * @param visitTime  Date and time of the visit
     * @param description Description of the visit
     * @return Updated PatientEntity
     */
    PatientEntity addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitTime, String description);
}
