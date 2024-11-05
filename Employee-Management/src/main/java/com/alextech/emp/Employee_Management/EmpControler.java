package com.alextech.emp.Employee_Management;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("http://localhost:3000/")
public class EmpControler {

    //Dependency Injection
    @Autowired
    EmpService empService;

   

    //Get request for fetchig the employees information.
    @GetMapping("employees")
    public List<Employee> getAllEmployee(){
        return empService.readEmployees();
    }
        
    @GetMapping("employees/{id}")
    public Employee getEmployeeById(@PathVariable Long id){
        return empService.readEmployee(id);
    }
   
    //Post request to create or send employees information.
    @PostMapping("employees")
    public String createEmployee(@RequestBody Employee employee){
        return empService.createEmployee(employee);
    }

    //Delete request to delete a specific employee via ID.
    @DeleteMapping("employees/{id}")
    public String deleteEmployee(@PathVariable Long id){
        if(empService.deleteEmployee(id)){
            return "Deleted Successfully";
        }else
        return "Not Found";
    }

    @PutMapping("employees/{id}")
    public String updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        return empService.updateEmployee(id, employee);
    }

}