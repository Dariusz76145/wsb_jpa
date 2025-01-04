package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.impl.PatientDaoImpl;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PatientDaoImplTest {

    @Autowired
    private PatientDaoImpl patientDao;

    @Test
    public void testAddVisitToPatient() {
        // Przygotowanie danych testowych
        Long patientId = 1L; // Zakładamy, że istnieje pacjent o ID 1
        Long doctorId = 1L;  // Zakładamy, że istnieje lekarz o ID 1
        LocalDateTime visitTime = LocalDateTime.now();
        String description = "Routine check-up";

        // Wywołanie metody DAO
        PatientEntity updatedPatient = patientDao.addVisitToPatient(patientId, doctorId, visitTime, description);

        // Walidacja wyników
        assertNotNull(updatedPatient, "Updated patient should not be null");
        assertTrue(updatedPatient.getVisits().stream()
                        .anyMatch(visit -> visit.getDescription().equals(description) && visit.getTime().equals(visitTime)),
                "Visit should be added to the patient");
    }
}
