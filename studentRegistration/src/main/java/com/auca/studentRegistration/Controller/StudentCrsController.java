package com.auca.studentRegistration.Controller;

import com.auca.studentRegistration.Model.Course;
import com.auca.studentRegistration.Model.StudentCourse;
import com.auca.studentRegistration.Model.StudentRegistration;
import com.auca.studentRegistration.Service.CourseDefService;
import com.auca.studentRegistration.Service.CourseService;
import com.auca.studentRegistration.Service.SemesterService;
import com.auca.studentRegistration.Service.StudCourseService;
import com.auca.studentRegistration.Service.StudRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/studCourse", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class StudentCrsController {

    @Autowired
    private StudCourseService studCrsService;

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private StudRegistrationService regService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseDefService defService;

    @PostMapping("/save")
    public ResponseEntity<String> createStudCrs(@RequestBody StudentCourse studCrs) {
        if (studCrs != null) {
            String message = studCrsService.saveStudCourse(studCrs);
            return message != null ? ResponseEntity.status(HttpStatus.CREATED).body("Student Course Saved Successfully") :
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Student Course Not Saved");
        } else {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<StudentCourse>> listStudCrs() {
        List<StudentCourse> studCrs = studCrsService.listStudentsCourse();
        return ResponseEntity.ok(studCrs);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Integer id, @RequestBody StudentCourse studCrs) {
        if (studCrs != null) {
            String message = studCrsService.updateStudCourse(id, studCrs);
            return message != null ? ResponseEntity.ok("Student Course Updated Successfully") :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Course Not Updated Successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudCrs(@PathVariable Integer id) {
        if (id != null) {
            String message = studCrsService.deleteStudCourse(id);
            return message != null ? ResponseEntity.ok("Student Course Deleted Successfully") :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Course Not Deleted Successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }

    @GetMapping("/listByCourse/{studentId}")
    public ResponseEntity<List<StudentCourse>> listByCourse(@PathVariable String studentId) {
        StudentRegistration stud = regService.getRegistrationByStudentId(studentId);

        if (stud != null) {
            List<StudentCourse> crs = studCrsService.getCoursesByStudentId(studentId);
            return ResponseEntity.ok(crs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listByCourseAndSemester/{courseCode}/{semesterId}")
    public ResponseEntity<List<StudentCourse>> listByCourseAndSemester(@PathVariable Integer courseCode, @PathVariable String semesterId) {
        Course course = courseService.getCourseById(courseCode);

        if (course != null) {
            List<StudentCourse> crs = studCrsService.getStudentByCourseAndSemester(course, semesterId);
            return ResponseEntity.ok(crs);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
