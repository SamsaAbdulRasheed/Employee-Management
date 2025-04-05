package com.employee.service;

import com.employee.Repository.EmployeeRepository;
import com.employee.bean.Department;
import com.employee.bean.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Page<Employee> getAllEmployee(int page , int size) {
        Pageable pageable= PageRequest.of(page, size);
        return employeeRepository.findAll(pageable);
    }



    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        if (employeeRepository.existsById(id)) {
            return employeeRepository.save(updatedEmployee);
        }
        throw new RuntimeException("Employee not found");
    }

    public Employee updateEmployeeDepartment(Long id, Department department) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setId(id);
        employee.setDepartment(department);
    return   employeeRepository.save(employee);
    }

}



