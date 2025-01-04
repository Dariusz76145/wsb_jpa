package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.dao.PatientRepository;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService {
    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(PatientMapper::toPatientTO)
                .collect(Collectors.toList());
    }

    /**
     * Finds all visits for a patient by their ID.
     *
     * @param patientId ID of the patient
     * @return List of visits for the patient
     */
    public List<VisitEntity> findVisitsByPatientId(Long patientId) {
        PatientEntity patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with ID: " + patientId));
        return patient.getVisits();
    }
}
