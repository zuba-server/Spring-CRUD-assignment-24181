package com.auca.studentRegistration.Repository;

import com.auca.studentRegistration.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, String> {
}
