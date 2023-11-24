package com.auca.studentRegistration.Controller;

import com.auca.studentRegistration.Model.AcademicUnit;
import com.auca.studentRegistration.Model.Semester;
import com.auca.studentRegistration.Model.StudentRegistration;
import com.auca.studentRegistration.Service.StudRegistrationService;
import com.auca.studentRegistration.Service.SemesterService;
import com.auca.studentRegistration.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/studentRegistration", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class StudRegController {

    @Autowired
    private StudRegistrationService regService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private SemesterService semesterService;

    @PostMapping("/saveRegistration")
    public ResponseEntity<String> createReg(@RequestBody StudentRegistration studentReg) {
        if (studentReg != null) {
            String message = regService.saveRegistration(studentReg);
            if (message != null && message.startsWith("Student Registered Successfully")) {
                return ResponseEntity.status(HttpStatus.CREATED).body(message);
            } else if (message != null && message.startsWith("Student with ID")) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Student Not Registered");
            }
        } else {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @GetMapping("/listRegistrations")
    public ResponseEntity<List<StudentRegistration>> listRegs() {
        List<StudentRegistration> studentReg = regService.listStudentsReg();
        return ResponseEntity.ok(studentReg);
    }

    @PutMapping("/updateRegistration/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Integer id, @RequestBody StudentRegistration regStudent) {
        if (regStudent != null) {
            String message = regService.updateStudentReg(id, regStudent);
            return message != null ? ResponseEntity.ok("Student Registration Updated Successfully") :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Registration Not Updated Successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }

    @DeleteMapping("/deleteRegistration/{id}")
    public ResponseEntity<String> deleteStudReg(@PathVariable Integer id) {
        if (id != null) {
            String message = regService.deleteStudentReg(id);
            return message != null ? ResponseEntity.ok("Student Registration Deleted Successfully") :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Registration Not Deleted Successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }

    @GetMapping("/listByDepartmentAndSemester/{departmentCode}/{semesterId}")
    public ResponseEntity<List<StudentRegistration>> listRegistrationsByDepartmentAndSemester(
            @PathVariable String departmentCode,
            @PathVariable String semesterId) {

        AcademicUnit department = unitService.getAcademicUnitByCode(departmentCode);
        Semester semester = semesterService.getSemesterById(semesterId);

        if (department != null && semester != null) {
            List<StudentRegistration> registrations = regService.getRegistrationsByDepartmentAndSemester(department, semester);
            return ResponseEntity.ok(registrations);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listBySemester/{semesterId}")
    public ResponseEntity<List<StudentRegistration>> listRegistrationsBySemester(@PathVariable String semesterId) {
        Semester semester = semesterService.getSemesterById(semesterId);

        if (semester != null) {
            List<StudentRegistration> registrations = regService.getRegistrationsBySemester(semester);
            return ResponseEntity.ok(registrations);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
