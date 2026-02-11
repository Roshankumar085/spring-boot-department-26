package com.spring_boot.app.services;

import com.spring_boot.app.entity.Department;
import com.spring_boot.app.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    // CREATE
    public List<Department> saveAllDepartments(List<Department> departments) {
        return departmentRepository.saveAll(departments);
    }

    // READ ALL
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // READ BY ID
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found with id: " + id));
    }

    // UPDATE
    public Department updateDepartment(Long id, Department department) {
        Department existingDepartment = getDepartmentById(id);

        existingDepartment.setDepartmentName(department.getDepartmentName());
        existingDepartment.setDepartmentAddress(department.getDepartmentAddress());
        existingDepartment.setDepartmentCode(department.getDepartmentCode());

        return departmentRepository.save(existingDepartment);
    }

    // DELETE
    public void deleteDepartment(Long id) {
        Department department = getDepartmentById(id);
        departmentRepository.delete(department);
    }
}
