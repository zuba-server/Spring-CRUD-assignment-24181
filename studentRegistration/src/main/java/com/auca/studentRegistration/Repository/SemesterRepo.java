package com.auca.studentRegistration.Repository;

import com.auca.studentRegistration.Model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterRepo extends JpaRepository<Semester, String> {
}
