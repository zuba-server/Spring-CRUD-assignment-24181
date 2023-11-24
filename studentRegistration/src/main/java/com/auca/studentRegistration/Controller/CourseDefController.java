package com.auca.studentRegistration.Controller;

import com.auca.studentRegistration.Model.CourseDefinition;
import com.auca.studentRegistration.Service.CourseDefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/course-definition", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class CourseDefController {

    @Autowired
    private CourseDefService courseDefService;

    @PostMapping("/save")
    public ResponseEntity<String> createDefinition(@RequestBody CourseDefinition courseDefinition) {
        if (courseDefinition != null) {
            String message = courseDefService.saveDef(courseDefinition);
            return message != null ? ResponseEntity.status(HttpStatus.CREATED).body("Course Definition Saved Successfully") :
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Course Definition Not Saved");
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<CourseDefinition>> listDefinitions() {
        List<CourseDefinition> definitions = courseDefService.listDefs();
        return ResponseEntity.ok(definitions);
    }

    @PutMapping("/update/{code}")
    public ResponseEntity<String> updateDefinition(@PathVariable String code, @RequestBody CourseDefinition courseDefinition) {
        if (courseDefinition != null) {
            String message = courseDefService.updateDef(code, courseDefinition);
            return message != null ? ResponseEntity.ok("Course Definition Updated Successfully") :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course Definition Not Updated Successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }

    @DeleteMapping("/delete/{code}")
    public ResponseEntity<String> deleteDefinition(@PathVariable String code) {
        if (code != null) {
            String message = courseDefService.deleteDef(code);
            return message != null ? ResponseEntity.ok("Course Definition Deleted Successfully") :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course Definition Not Deleted Successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }
}
