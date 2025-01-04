package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;




@DataJpaTest
@Sql(scripts = "/data.sql") // Wskazuje na plik data.sql
public class PatientDaoTest {

    @Autowired
    private EntityManager entityManager;

    @Test
    public void shouldFindPatientsOlderThanGivenAge() {
        // Kryterium wyszukiwania
        int ageThreshold = 30;

        // Utwórz zapytanie JPQL
        String jpql = "SELECT p FROM PatientEntity p WHERE p.age > :age";
        TypedQuery<PatientEntity> query = entityManager.createQuery(jpql, PatientEntity.class);
        query.setParameter("age", ageThreshold);

        // Wykonaj zapytanie
        List<PatientEntity> patients = query.getResultList();

        // Walidacja wyników
        assertEquals(1, patients.size(), "Powinien znaleźć jednego pacjenta starszego niż 30 lat");
        assertEquals("John", patients.get(0).getFirstName(), "Pacjentem powinien być John Doe");
    }
}
