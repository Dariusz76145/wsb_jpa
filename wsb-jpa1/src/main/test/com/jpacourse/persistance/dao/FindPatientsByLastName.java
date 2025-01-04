package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.impl.PatientDaoImpl;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
public class FindPatientsByLastName {

    @Autowired
    private PatientDaoImpl patientDao;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void cleanDatabase() {
        // Usuwanie rekordów w odpowiedniej kolejności
        entityManager.createQuery("DELETE FROM MedicalTreatmentEntity").executeUpdate();
        entityManager.createQuery("DELETE FROM VisitEntity").executeUpdate();
        entityManager.createQuery("DELETE FROM PatientEntity").executeUpdate();
        entityManager.createQuery("DELETE FROM AddressEntity").executeUpdate();
    }

    @Test
    public void testFindPatientsByLastName() {
        // Przygotowanie danych testowych - tworzenie adresu
        AddressEntity address = new AddressEntity();
        address.setCity("New York");
        address.setAddressLine1("123 Main St");
        address.setPostalCode("10001");
        entityManager.persist(address);

        // Tworzenie pacjentów
        PatientEntity patient1 = new PatientEntity();
        patient1.setFirstName("John");
        patient1.setLastName("Doe");
        patient1.setDateOfBirth(LocalDate.of(1985, 5, 20));
        patient1.setTelephoneNumber("123456789");
        patient1.setPatientNumber("PN001");
        patient1.setAge(37);
        patient1.setAddress(address);
        entityManager.persist(patient1);

        PatientEntity patient2 = new PatientEntity();
        patient2.setFirstName("Jane");
        patient2.setLastName("Doe");
        patient2.setDateOfBirth(LocalDate.of(1990, 10, 15));
        patient2.setTelephoneNumber("987654321");
        patient2.setPatientNumber("PN002");
        patient2.setAge(32);
        patient2.setAddress(address);
        entityManager.persist(patient2);

        PatientEntity patient3 = new PatientEntity();
        patient3.setFirstName("Alice");
        patient3.setLastName("Smith");
        patient3.setDateOfBirth(LocalDate.of(1975, 3, 10));
        patient3.setTelephoneNumber("555666777");
        patient3.setPatientNumber("PN003");
        patient3.setAge(48);
        patient3.setAddress(address);
        entityManager.persist(patient3);

        // Wywołanie metody DAO
        List<PatientEntity> results = patientDao.findPatientsByLastName("Doe");

        // Weryfikacja wyników
        assertEquals(2, results.size(), "Should return 2 patients with last name 'Doe'");
        assertTrue(results.stream().anyMatch(p -> p.getFirstName().equals("John")), "Result should contain 'John Doe'");
        assertTrue(results.stream().anyMatch(p -> p.getFirstName().equals("Jane")), "Result should contain 'Jane Doe'");
    }
}
