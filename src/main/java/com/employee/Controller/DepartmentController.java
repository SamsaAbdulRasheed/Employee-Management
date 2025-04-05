package com.employee.Controller;

import com.employee.bean.Department;
import com.employee.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    //endpoint to create department
    @PostMapping
    public Department createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }
//    Fetch All  Departments
    @GetMapping
    public List<Department> getAllDepartments(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "20")int size) {
        return departmentService.getAllDepartments();
    }

//    Update Department:
    @PutMapping("/{id}")
    public Department updateDepartment(@PathVariable Long id, @RequestBody Department updatedDepartment) {
        Department department = departmentService.updateDepartment(id, updatedDepartment);
        return department;
    }
// getdepartment by Id
    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentByid(@PathVariable() Long id){
      return  ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

//    Delete Department:
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteDepartment(@PathVariable Long id){
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("Department Deleted Successfully");
    }
}

//ResponseEntity: is meant to represent the entire HTTP response.
//  can control anything that goes into it: status code, headers, and body.