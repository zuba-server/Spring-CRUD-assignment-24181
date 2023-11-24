package com.auca.studentRegistration.Model; // Adjusted package name to follow Java conventions

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "student_course") // Adjusted table name for consistency
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int credits;

    private BigDecimal results;

    @ManyToOne
    @JoinColumn(name = "student_registration_id") // Adjusted column name for consistency
    @JsonManagedReference
    private StudentRegistration studentRegistration;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonManagedReference
    private Course course;

    public StudentCourse() {
    }

    public StudentCourse(int id, int credits, BigDecimal results, StudentRegistration studentRegistration, Course course) {
        this.id = id;
        this.credits = credits;
        this.results = results;
        this.studentRegistration = studentRegistration;
        this.course = course;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public BigDecimal getResults() {
        return results;
    }

    public void setResults(BigDecimal results) {
        this.results = results;
    }

    public StudentRegistration getStudentRegistration() {
        return studentRegistration;
    }

    public void setStudentRegistration(StudentRegistration studentRegistration) {
        this.studentRegistration = studentRegistration;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
