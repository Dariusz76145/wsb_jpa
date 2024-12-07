-- Dane dla tabeli ADDRESS
INSERT INTO ADDRESS (id, addressLine1, addressLine2, city, postalCode)
VALUES
    (1, '123 Main St', NULL, 'New York', '10001'),
    (2, '456 Elm St', 'Apt 2', 'Los Angeles', '90001'),
    (3, '789 Maple Ave', NULL, 'Chicago', '60601');

-- Dane dla tabeli PATIENT
INSERT INTO PATIENT (id, firstName, lastName, telephoneNumber, email, patientNumber, dateOfBirth, address_id, age)
VALUES
    (1, 'John', 'Doe', '123456789', 'john.doe@example.com', 'P001', '1980-05-20', 1, 43),
    (2, 'Jane', 'Smith', '987654321', 'jane.smith@example.com', 'P002', '1990-08-15', 2, 33),
    (3, 'Alice', 'Johnson', '564738291', 'alice.johnson@example.com', 'P003', '1995-03-10', 3, 28);

-- Dane dla tabeli DOCTOR
INSERT INTO DOCTOR (id, firstName, lastName, telephoneNumber, email, doctorNumber)
VALUES
    (1, 'Dr. Gregory', 'House', '111222333', 'gregory.house@example.com', 'D001'),
    (2, 'Dr. Meredith', 'Grey', '444555666', 'meredith.grey@example.com', 'D002'),
    (3, 'Dr. Derek', 'Shepherd', '777888999', 'derek.shepherd@example.com', 'D003');

-- Dane dla tabeli VISIT
INSERT INTO VISIT (id, description, time, DOCTOR_ID, PATIENT_ID)
VALUES
    (1, 'Routine check-up', '2024-01-15 10:00:00', 1, 1),
    (2, 'Consultation for back pain', '2024-01-20 14:30:00', 2, 2),
    (3, 'Follow-up on surgery', '2024-02-05 09:00:00', 3, 1),
    (4, 'Physical examination', '2024-03-01 11:15:00', 1, 3),
    (5, 'Diet consultation', '2024-03-10 16:45:00', 2, 3);

-- Dane dla tabeli MEDICAL_TREATMENT
INSERT INTO MEDICAL_TREATMENT (id, description, VISIT_ID)
VALUES
    (1, 'Blood test', 1),
    (2, 'MRI scan', 2),
    (3, 'Physical therapy', 3),
    (4, 'Prescription for painkillers', 4),
    (5, 'Dietary supplements recommendation', 5);
