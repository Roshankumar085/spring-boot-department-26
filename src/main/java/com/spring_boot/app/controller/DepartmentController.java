package com.spring_boot.app.controller;

import com.spring_boot.app.entity.Department;
import com.spring_boot.app.services.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Department Controller", description = "CRUD APIs for Department")
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    // CREATE
    @Operation(summary = "Create a new department")
    @PostMapping("/bulk")
    public ResponseEntity<List<Department>> addDepartments(
            @RequestBody List<Department> departments) {

        List<Department> saved = service.saveAllDepartments(departments);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    // READ ALL
    @Operation(summary = "Get all departments")
    @GetMapping
    public List<Department> getAllDepartments() {
        return service.getAllDepartments();
    }

    // READ BY ID
    @Operation(summary = "Get department by ID")
    @GetMapping("/{id}")
    public Department getDepartment(@PathVariable Long id) {
        return service.getDepartmentById(id);
    }

    // UPDATE
    @Operation(summary = "Update department by ID")
    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id,
                                       @RequestBody Department department) {
        return service.updateDepartment(id, department);
    }

    // DELETE
    @Operation(summary = "Delete department by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable Long id) {
        service.deleteDepartment(id);
        return ResponseEntity.ok("Department deleted successfully");
    }
}
