// src/main/java/com/Subhajit.ApointmentBooking.App.repository/DoctorRepository.java
package com.Subhajit.ApointmentBooking.App.repository;

import com.Subhajit.ApointmentBooking.App.Doctor.Doctor; // IMPORT STATEMENT MUST MATCH YOUR DOCTOR CLASS'S PACKAGE
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // This interface is intentionally empty.
    // Spring Data JPA will automatically provide implementations for
    // CRUD operations (save, findById, findAll, deleteById, etc.)
}