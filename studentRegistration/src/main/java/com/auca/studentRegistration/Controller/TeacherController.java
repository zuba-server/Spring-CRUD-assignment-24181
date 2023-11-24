package com.auca.studentRegistration.Controller;

import com.auca.studentRegistration.Model.Teacher;
import com.auca.studentRegistration.Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/teacher", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/save")
    public ResponseEntity<String> createTeacher(@RequestBody Teacher teacher) {
        if (teacher != null) {
            String message = teacherService.saveTeacher(teacher);
            return message != null ? ResponseEntity.status(HttpStatus.CREATED).body("Teacher Saved Successfully") :
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Teacher Not Saved");
        } else {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @GetMapping("/listTeachers")
    public ResponseEntity<List<Teacher>> listTeachers() {
        List<Teacher> teachers = teacherService.listTeacher();
        return ResponseEntity.ok(teachers);
    }

    @PutMapping("/updateTeacher/{trCode}")
    public ResponseEntity<String> updateTeacher(@PathVariable String trCode, @RequestBody Teacher teacher) {
        if (teacher != null) {
            String message = teacherService.updateTeacher(trCode, teacher);
            return message != null ? ResponseEntity.ok("Teacher Updated Successfully") :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher Not Updated Successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }

    @DeleteMapping("/deleteTeacher/{trCode}")
    public ResponseEntity<String> deleteTeacher(@PathVariable String trCode) {
        if (trCode != null) {
            String message = teacherService.deleteTeacher(trCode);
            return message != null ? ResponseEntity.ok("Teacher Deleted Successfully") :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Teacher Not Deleted Successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }
}
