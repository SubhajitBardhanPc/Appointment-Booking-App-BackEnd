// src/main/java/com/Subhajit/ApointmentBooking/App/controller/DoctorController.java
package com.Subhajit.ApointmentBooking.App.controller;

import com.Subhajit.ApointmentBooking.App.Doctor.Doctor; // IMPORTANT: Ensure this matches your Doctor.java's package
import com.Subhajit.ApointmentBooking.App.repository.DoctorRepository; // IMPORTANT: Ensure this matches your DoctorRepository.java's package
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional; // Used for handling potential absence of a doctor by ID

@RestController // Marks this class as a REST controller, handling HTTP requests
@RequestMapping("/api/doctors") // Defines the base URL path for all endpoints in this controller
@CrossOrigin(origins = "http://localhost:3000") // Allows your React app (on port 3000) to access these endpoints
public class DoctorController {

    @Autowired // Spring will automatically inject an instance of DoctorRepository here
    private DoctorRepository doctorRepository;

    // --- API Endpoints ---

    // GET all doctors: HTTP GET request to http://localhost:8080/api/doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        // Uses the repository to fetch all Doctor entities from the database
        return doctorRepository.findAll();
    }

    // GET a single doctor by ID: HTTP GET request to http://localhost:8080/api/doctors/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        // Attempts to find a doctor by its ID
        Optional<Doctor> doctor = doctorRepository.findById(id);
        // If the doctor is found, return 200 OK with the doctor object
        // Otherwise, return 404 Not Found
        return doctor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // POST a new doctor: HTTP POST request to http://localhost:8080/api/doctors
    // The request body should contain the JSON representation of the Doctor object
    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        // Saves the new doctor entity to the database
        Doctor savedDoctor = doctorRepository.save(doctor);
        // Return 201 Created status with the saved doctor object (which now has an ID)
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    // PUT update an existing doctor: HTTP PUT request to http://localhost:8080/api/doctors/{id}
    // The request body should contain the updated JSON representation of the Doctor object
    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctorDetails) {
        // First, find the existing doctor by ID
        return doctorRepository.findById(id)
                .map(doctor -> {
                    // Update the fields of the found doctor with the new details from the request body
                    doctor.setDoctorName(doctorDetails.getDoctorName());
                    doctor.setContact(doctorDetails.getContact());
                    doctor.setAddress(doctorDetails.getAddress());
                    doctor.setTiming(doctorDetails.getTiming());
                    doctor.setAvailableDays(doctorDetails.getAvailableDays());

                    // Save the updated doctor back to the database
                    Doctor updatedDoctor = doctorRepository.save(doctor);
                    // Return 200 OK with the updated doctor object
                    return ResponseEntity.ok(updatedDoctor);
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // If doctor not found, return 404 Not Found
    }

    // DELETE a doctor: HTTP DELETE request to http://localhost:8080/api/doctors/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDoctor(@PathVariable Long id) {
        try {
            // Delete the doctor by ID from the database
            doctorRepository.deleteById(id);
            // Return 204 No Content status, indicating successful deletion with no body
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // If any error occurs during deletion (e.g., ID not found, though deleteById is tolerant)
            // return 500 Internal Server Error
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}