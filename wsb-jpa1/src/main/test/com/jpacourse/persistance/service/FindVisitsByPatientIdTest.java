package com.jpacourse.service;

import com.jpacourse.persistence.dao.PatientRepository;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class FindVisitsByPatientIdTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindVisitsByPatientId() {
        // Przygotowanie danych testowych
        AddressEntity address = new AddressEntity();
        address.setCity("New York");
        address.setAddressLine1("123 Main St");
        address.setPostalCode("10001");

        PatientEntity patient = new PatientEntity();
        patient.setId(1L);
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setDateOfBirth(LocalDate.of(1985, 5, 20));
        patient.setTelephoneNumber("123456789");
        patient.setPatientNumber("PN001");
        patient.setAge(37);
        patient.setAddress(address);

        VisitEntity visit1 = new VisitEntity();
        visit1.setId(1L);
        visit1.setDescription("General Checkup");
        visit1.setTime(LocalDateTime.of(2025, 1, 1, 10, 0));
        visit1.setPatientEntity(patient);

        VisitEntity visit2 = new VisitEntity();
        visit2.setId(2L);
        visit2.setDescription("Dental Appointment");
        visit2.setTime(LocalDateTime.of(2025, 1, 2, 15, 30));
        visit2.setPatientEntity(patient);

        patient.setVisits(Arrays.asList(visit1, visit2));

        // Mockowanie zachowania PatientRepository
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        // Wywołanie metody serwisu
        List<VisitEntity> visits = patientService.findVisitsByPatientId(1L);

        // Weryfikacja wyników
        assertEquals(2, visits.size(), "Should return 2 visits for patient with ID 1");
        assertEquals("General Checkup", visits.get(0).getDescription());
        assertEquals("Dental Appointment", visits.get(1).getDescription());
    }
}
