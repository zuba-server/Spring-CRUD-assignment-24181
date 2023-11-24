package com.auca.studentRegistration.Repository;

import com.auca.studentRegistration.Model.AcademicUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepo extends JpaRepository<AcademicUnit,String> {
}
