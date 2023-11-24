package com.auca.studentRegistration.Controller;

import com.auca.studentRegistration.Model.*;
import com.auca.studentRegistration.Service.CourseService;
import com.auca.studentRegistration.Service.SemesterService;
import com.auca.studentRegistration.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/course", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private SemesterService semesterService;

    @PostMapping("/save")
    public ResponseEntity<String> createCourse(@RequestBody Course course) {
        if (course != null) {
            String message = courseService.saveCourse(course);
            return message != null ? ResponseEntity.ok("Course Saved Successfully") :
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Course Not Saved");
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Course>> listCourses() {
        List<Course> courses = courseService.listCourses();
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/update/{code}")
    public ResponseEntity<String> updateCourse(@PathVariable CourseDefinition code, @RequestBody Course course) {
        if (course != null) {
            String message = courseService.updateCourse(code, course);
            return message != null ? ResponseEntity.ok("Course Updated Successfully") :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course Not Updated Successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Integer id) {
        if (id != null) {
            String message = courseService.deleteCourse(id);
            return message != null ? ResponseEntity.ok("Course Deleted Successfully") :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course Not Deleted Successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }

    @GetMapping("/listByDepartmentAndSemester/{departmentCode}/{semesterId}")
    public ResponseEntity<List<Course>> listCoursesByDepartmentAndSemester(
            @PathVariable String departmentCode,
            @PathVariable String semesterId) {
        AcademicUnit department = unitService.getAcademicUnitByCode(departmentCode);
        Semester semester = semesterService.getSemesterById(semesterId);

        if (department != null && semester != null) {
            List<Course> courses = courseService.getCoursesByDepartmentAndSemester(department, semester);
            return ResponseEntity.ok(courses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
