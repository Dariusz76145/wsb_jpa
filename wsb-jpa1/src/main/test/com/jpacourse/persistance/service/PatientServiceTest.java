package com.jpacourse.persistance.service;

import com.jpacourse.persistence.dao.PatientRepository;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.AddressEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Test
    public void testShouldDeletePatientAndCascadeVisitsButKeepDoctors() {
        // given
        PatientEntity patient = new PatientEntity();
        patient.setFirstName("John");
        patient.setLastName("Doe");
        patient.setTelephoneNumber("123456789");
        patient.setEmail("john.doe@example.com");
        patient.setPatientNumber("PN12345");
        patient.setDateOfBirth(LocalDate.of(1985, 5, 20));
        patient.setAge(38);

        AddressEntity address = new AddressEntity();
        address.setAddressLine1("123 Main St");
        address.setCity("Sample City");
        address.setPostalCode("12345");
        patient.setAddress(address);

        // Tworzenie i zapis doktora
        DoctorEntity doctor = new DoctorEntity();
        doctor.setFirstName("Dr.");
        doctor.setLastName("Smith");
        doctor.setDoctorNumber("DOC12345"); // Ustawienie wymaganej wartości
        doctor.setTelephoneNumber("987654321");
        doctor.setEmail("dr.smith@example.com");
        entityManager.persist(doctor); // Zapis doktora w bazie danych

        // Tworzenie wizyt powiązanych z pacjentem i doktorem
        VisitEntity visit1 = new VisitEntity();
        visit1.setDescription("Visit 1");
        visit1.setTime(LocalDateTime.now());
        visit1.setPatientEntity(patient);
        visit1.setDoctorEntity(doctor);

        VisitEntity visit2 = new VisitEntity();
        visit2.setDescription("Visit 2");
        visit2.setTime(LocalDateTime.now().plusDays(1));
        visit2.setPatientEntity(patient);
        visit2.setDoctorEntity(doctor);

        // Dodanie wizyt do pacjenta
        patient.getVisits().add(visit1);
        patient.getVisits().add(visit2);

        // Zapis pacjenta z wizytami
        patientRepository.save(patient);

        // Ensure initial state
        assertThat(patientRepository.findById(patient.getId())).isPresent();
        assertThat(patientRepository.findById(patient.getId()).get().getVisits()).hasSize(2);

        // when - usunięcie pacjenta
        patientRepository.deleteById(patient.getId());

        // then - weryfikacja usunięcia pacjenta i wizyt, ale nie doktora
        assertThat(patientRepository.findById(patient.getId())).isNotPresent();
        DoctorEntity persistedDoctor = entityManager.find(DoctorEntity.class, doctor.getId());
        assertThat(persistedDoctor).isNotNull(); // Doktor nadal istnieje
    }
}
