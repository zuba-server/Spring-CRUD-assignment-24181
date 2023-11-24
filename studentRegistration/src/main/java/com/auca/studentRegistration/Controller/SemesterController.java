package com.auca.studentRegistration.Controller;

import com.auca.studentRegistration.Model.Semester;
import com.auca.studentRegistration.Service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/semester", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class SemesterController {

    @Autowired
    private SemesterService semesterService;

    @PostMapping("/save")
    public ResponseEntity<String> createSemester(@RequestBody Semester semester) {
        if (semester != null) {
            String message = semesterService.saveSemester(semester);
            return message != null ? ResponseEntity.status(HttpStatus.CREATED).body("Semester Saved Successfully") :
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Semester Not Saved");
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Semester>> listSemesters() {
        List<Semester> semesters = semesterService.listSemesters();
        return ResponseEntity.ok(semesters);
    }

    @PutMapping("/update/{code}")
    public ResponseEntity<String> updateSemester(@PathVariable String code, @RequestBody Semester semester) {
        if (semester != null) {
            String message = semesterService.updateSemester(code, semester);
            return message != null ? ResponseEntity.ok("Semester Updated Successfully") :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Semester Not Updated Successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<String> deleteSemester(@PathVariable String code) {
        if (code != null) {
            String message = semesterService.deleteSemester(code);
            return message != null ? ResponseEntity.ok("Semester Deleted Successfully") :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Semester Not Deleted Successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }
}
