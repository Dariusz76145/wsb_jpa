INSERT INTO ADDRESS (id, city, addressLine1, addressLine2, postalCode)
VALUES (1, 'Wroclaw', 'Inzynierska 10', NULL, '53-500'),
       (2, 'Wroclaw', 'Robotnicza 15', 'Building B', '30-002');

-- Wstawienie danych do tabeli PATIENT
INSERT INTO PATIENT (id, firstName, lastName, telephoneNumber, email, patientNumber, dateOfBirth, address_id)
VALUES
    (1, 'John', 'Doe', '123456789', 'john.doe@example.com', 'P001', '1980-05-20', 1),
    (2, 'Jane', 'Smith', '987654321', 'jane.smith@example.com', 'P002', '1990-08-15', 2);

-- Wstawienie danych do tabeli DOCTOR
INSERT INTO DOCTOR (id, firstName, lastName, telephoneNumber, email, doctorNumber)
VALUES
    (1, 'Alice', 'Brown', '111222333', 'alice.brown@example.com', 'D001'),
    (2, 'Robert', 'Johnson', '444555666', 'robert.johnson@example.com', 'D002');

-- Wstawienie danych do tabeli VISIT
INSERT INTO VISIT (id, description, time, doctor_id, patient_id)
VALUES
    (1, 'Initial consultation', '2024-01-10 09:00:00', 1, 1),
    (2, 'Follow-up', '2024-01-15 11:00:00', 2, 2);

-- Wstawienie danych do tabeli MEDICAL_TREATMENT
INSERT INTO MEDICAL_TREATMENT (id, description, visit_id)
VALUES
    (1, 'Prescribed medication', 1),
    (2, 'Physical therapy', 2);
