package com.Subhajit.ApointmentBooking.App.Doctor; // Potential package name issue here



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column; // Added for more control over schema

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Changed to lowercase 'id'

    @Column(nullable = false, length = 100) // Example constraints
    private String doctorName; // Corrected typo: 'doctorName'

    @Column(nullable = false, unique = true, length = 20) // Example constraints
    private String contact;

    @Column(length = 255) // Example constraints
    private String address;

    @Column(nullable = false, length = 50) // Example constraints
    private String timing; // Corrected typo: 'timing'

    @Column(nullable = false, length = 50) // Example constraints
    private String availableDays; // Consistent with React frontend

    // --- Constructors ---
    // No-argument constructor (REQUIRED by JPA)
    public Doctor() {
    }

    // All-argument constructor (useful for creating new instances)
    public Doctor(String doctorName, String contact, String address, String timing, String availableDays) {
        this.doctorName = doctorName;
        this.contact = contact;
        this.address = address;
        this.timing = timing;
        this.availableDays = availableDays;
    }

    // --- Getters and Setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id; // Use 'this.id' for clarity
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(String availableDays) {
        this.availableDays = availableDays;
    }

    // --- toString() method ---
    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", doctorName='" + doctorName + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                ", timing='" + timing + '\'' +
                ", availableDays='" + availableDays + '\'' +
                '}';
    }
}