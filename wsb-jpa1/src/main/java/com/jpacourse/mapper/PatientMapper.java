package com.jpacourse.mapper;

import com.jpacourse.dto.PatientTO;
import com.jpacourse.persistence.entity.PatientEntity;

import java.util.stream.Collectors;

public class PatientMapper {
    public static PatientTO toPatientTO(PatientEntity entity) {
        PatientTO to = new PatientTO();
        to.setId(entity.getId());
        to.setFirstName(entity.getFirstName());
        to.setLastName(entity.getLastName());
        to.setTelephoneNumber(entity.getTelephoneNumber());
        to.setEmail(entity.getEmail());
        to.setPatientNumber(entity.getPatientNumber());
        to.setDateOfBirth(entity.getDateOfBirth());
        to.setAddress(entity.getAddress().getAddressLine1() + ", " + entity.getAddress().getCity());
        to.setAge(entity.getAge()); // Mapowanie nowego pola
        to.setVisits(entity.getVisits().stream()
                .map(visit -> VisitMapper.toVisitTO(visit))
                .collect(Collectors.toList()));
        return to;
    }
}
