package com.auca.studentRegistration.Controller;

import com.auca.studentRegistration.Model.AcademicUnit;
import com.auca.studentRegistration.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/unit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UnitController {

    @Autowired
    private UnitService unitService;

    @PostMapping("/save")
    public ResponseEntity<String> createUnit(@RequestBody AcademicUnit unit) {
        if (unit != null) {
            String message = unitService.saveUnit(unit);
            return message != null ? ResponseEntity.status(HttpStatus.CREATED).body("Unit Saved Successfully") :
                    ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unit Not Saved");
        } else {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }

    @GetMapping("/listUnits")
    public ResponseEntity<List<AcademicUnit>> listUnits() {
        List<AcademicUnit> units = unitService.listUnits();
        return ResponseEntity.ok(units);
    }

    @PutMapping("/updateUnit/{code}")
    public ResponseEntity<String> updateUnit(@PathVariable String code, @RequestBody AcademicUnit unit) {
        if (unit != null) {
            String message = unitService.updateUnit(code, unit);
            return message != null ? ResponseEntity.ok("Unit Updated Successfully") :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unit Not Updated Successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }

    @DeleteMapping("/deleteUnit/{code}")
    public ResponseEntity<String> deleteUnit(@PathVariable String code) {
        if (code != null) {
            String message = unitService.deleteUnit(code);
            return message != null ? ResponseEntity.ok("Unit Deleted Successfully") :
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Unit Not Deleted Successfully");
        } else {
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }
}
