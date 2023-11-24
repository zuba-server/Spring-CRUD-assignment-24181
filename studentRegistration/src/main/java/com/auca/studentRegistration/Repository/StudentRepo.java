package com.auca.studentRegistration.Repository;

import com.auca.studentRegistration.Model.Semester;
import com.auca.studentRegistration.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student, String> {
}
