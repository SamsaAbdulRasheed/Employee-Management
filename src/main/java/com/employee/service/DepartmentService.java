package com.employee.service;

import com.employee.Repository.DepartmentRepository;
import com.employee.Repository.EmployeeRepository;
import com.employee.bean.Department;
import com.employee.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }



    public Department updateDepartment(Long id, Department updatedDepartment) {
        if(departmentRepository.existsById(id)) {
            updatedDepartment.setId(id);
            return departmentRepository.save(updatedDepartment);
        }
        return null;
    }

    public void deleteDepartment(Long id) {
        Department department=departmentRepository.findById(id).orElseThrow(()-> new RuntimeException("Department not found"));
        if (!department.getEmployees().isEmpty()) {

/* throw instead of just printing the error because throwing an exception is the correct way
to signal an error in APIs*/
                throw new RuntimeException("Cannot delete department with assigned employees");
            }
            departmentRepository.deleteById(id);
    }
//get department by id
    public Department getDepartmentById(Long id) {
      return  departmentRepository.findById(id).orElse(null);
    }
}

