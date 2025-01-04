package com.jpacourse.service;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.mapper.PatientMapper;
import com.jpacourse.persistence.dao.PatientRepository;
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
}
