package com.auca.studentRegistration.Controller;

import com.auca.studentRegistration.Model.AcademicUnit;
import com.auca.studentRegistration.Model.Semester;
import com.auca.studentRegistration.Model.Student;
import com.auca.studentRegistration.Model.StudentRegistration;
import com.auca.studentRegistration.Service.SemesterService;
import com.auca.studentRegistration.Service.StudentService;
import com.auca.studentRegistration.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private UnitService academicUnitService;

    @PostMapping("/save")
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        if (student != null) {
            String message = studentService.saveStudent(student);
            return message != null ? ResponseEntity.status(HttpStatus.CREATED).body("Student Saved Successfully") :
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Student Not Saved");
        } else {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Student>> listStudents() {
        List<Student> students = studentService.listStudents();
        return ResponseEntity.ok(students);
    }

    @PutMapping("/update/{registrationNumber}")
    public ResponseEntity<String> updateStudent(@PathVariable String registrationNumber, @RequestBody Student student) {
        if (student != null) {
            String message = studentService.updateStudent(registrationNumber, student);
            return message != null ? ResponseEntity.ok("Student Updated Successfully") :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not Updated Successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }

    @DeleteMapping("/delete/{registrationNumber}")
    public ResponseEntity<String> deleteStudent(@PathVariable String registrationNumber) {
        if (registrationNumber != null) {
            String message = studentService.deleteStudent(registrationNumber);
            return message != null ? ResponseEntity.ok("Student Deleted Successfully") :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Not Deleted Successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }

    @GetMapping("/listBySemester/{semesterId}")
    public ResponseEntity<List<StudentRegistration>> listStudentsBySemester(@PathVariable String semesterId) {
        Semester semester = semesterService.getSemesterById(semesterId);

        if (semester != null) {
            List<StudentRegistration> students = studentService.getStudentsBySemester(semester);
            return ResponseEntity.ok(students);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listByDepartmentAndSemester/{departmentCode}/{semesterId}")
    public ResponseEntity<List<StudentRegistration>> listStudentsByDepartmentAndSemester(
            @PathVariable String departmentCode,
            @PathVariable String semesterId) {

        AcademicUnit department = academicUnitService.getAcademicUnitByCode(departmentCode);
        Semester semester = semesterService.getSemesterById(semesterId);

        if (department != null && semester != null) {
            List<StudentRegistration> students = studentService.getStudentsByDepartmentAndSemester(department, semester);
            return ResponseEntity.ok(students);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
