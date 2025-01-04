package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.entity.DoctorEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PatientEntity addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitTime, String description) {
        // Find the patient
        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        if (patient == null) {
            throw new IllegalArgumentException("Patient with ID " + patientId + " not found.");
        }

        // Find the doctor
        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);
        if (doctor == null) {
            throw new IllegalArgumentException("Doctor with ID " + doctorId + " not found.");
        }

        // Create a new visit
        VisitEntity visit = new VisitEntity();
        visit.setTime(visitTime);
        visit.setDescription(description);
        visit.setDoctorEntity(doctor);
        visit.setPatientEntity(patient);

        // Add the visit to the patient's list of visits
        patient.getVisits().add(visit);

        // Cascade merge the patient to save the new visit
        return entityManager.merge(patient);
    }
}
