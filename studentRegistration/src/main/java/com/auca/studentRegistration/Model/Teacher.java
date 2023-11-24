package com.auca.studentRegistration.Model; // Adjusted package name to follow Java conventions

import jakarta.persistence.*;

@Entity
@Table(name = "teacher") // Adjusted table name for consistency
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tr_id") // Explicitly specified column name for clarity
    private int id;

    @Column(name = "tr_code", unique = true, nullable = false) // Added constraints for tr_code
    private String code;

    @Column(name = "full_names", nullable = false) // Added constraints for full_names
    private String fullNames;

    @Column(name = "email", nullable = false) // Added constraints for email
    private String email;

    @Column(name = "phone_nbr", nullable = false) // Added constraints for phone_nbr
    private String phoneNbr;

    @Enumerated(EnumType.STRING)
    private Qualification qualification;

    public Teacher() {
    }

    public Teacher(int id, String code, String fullNames, String email, String phoneNbr, Qualification qualification) {
        this.id = id;
        this.code = code;
        this.fullNames = fullNames;
        this.email = email;
        this.phoneNbr = phoneNbr;
        this.qualification = qualification;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFullNames() {
        return fullNames;
    }

    public void setFullNames(String fullNames) {
        this.fullNames = fullNames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNbr() {
        return phoneNbr;
    }

    public void setPhoneNbr(String phoneNbr) {
        this.phoneNbr = phoneNbr;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }
}
