package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.VisitEntity;

import java.util.stream.Collectors;

public class VisitMapper {

    public static VisitTO toVisitTO(VisitEntity entity) {
        VisitTO to = new VisitTO();
        to.setId(entity.getId());
        to.setDescription(entity.getDescription());
        to.setTime(entity.getTime());
        to.setDoctorFirstName(entity.getDoctorEntity().getFirstName());
        to.setDoctorLastName(entity.getDoctorEntity().getLastName());
        to.setMedicalTreatmentDescriptions(entity.getMedicalTreatments().stream()
                .map(medicalTreatment -> medicalTreatment.getDescription())
                .collect(Collectors.toList()));
        return to;
    }
}
