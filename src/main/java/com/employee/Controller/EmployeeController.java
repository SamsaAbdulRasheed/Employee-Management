package com.employee.Controller;

import com.employee.bean.Department;
import com.employee.bean.Employee;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    //endpoint to create employee
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){
        return  employeeService.createEmployee(employee);
    }
    //Fetch All Employees
    @GetMapping
    public Page<Employee> getAllEmployee(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "20") int size){
        return employeeService.getAllEmployee(page,size);
    }



    //updating an existing employee
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
        Employee employee = employeeService.updateEmployee(id, updatedEmployee);
        return ResponseEntity.ok(employee);
    }

    //Update Employee's Department
    @PutMapping("/{id}/department/{department}")
        public Employee updateEmployeeDepartment(@PathVariable Long id , @PathVariable Department department){

        return employeeService.updateEmployeeDepartment(id , department);
    }
}
