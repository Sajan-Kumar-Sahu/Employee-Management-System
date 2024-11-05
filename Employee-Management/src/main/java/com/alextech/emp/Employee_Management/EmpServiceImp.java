package com.alextech.emp.Employee_Management;

import java.util.*;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpServiceImp implements EmpService{

    @Autowired
    private EmpRepository empRepository;

    
    @Override
    public String createEmployee(Employee employee) {
        EmpEntity empEntity = new EmpEntity();
        BeanUtils.copyProperties(employee, empEntity);
        empRepository.save(empEntity);
        return "Saved Succesfully";
    }

    @Override
    public Employee readEmployee(Long id) {
        EmpEntity empEntity = empRepository.findById(id).get();
        Employee employee = new Employee();
        BeanUtils.copyProperties(empEntity, employee);
        return employee;
       
    }

    @Override
    public List<Employee> readEmployees() {
        List<EmpEntity> employeesList = empRepository.findAll();
        List<Employee> employees = new ArrayList<>();

        for (EmpEntity empEntity : employeesList) {
            Employee emp = new Employee();
            emp.setId(empEntity.getId());
            emp.setName(empEntity.getName());
            emp.setPhone(empEntity.getPhone());
            emp.setEmail(empEntity.getEmail());
            employees.add(emp);
            
        }
        return employees;          

    }

    @Override
    public boolean deleteEmployee(Long id) {
        EmpEntity emp = empRepository.findById(id).get();
        empRepository.delete(emp);


      // employees.remove(id);
       return true;
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        EmpEntity exiatingEmployee = empRepository.findById(id).get();
        exiatingEmployee.setName(employee.getName());
        exiatingEmployee.setPhone(employee.getPhone());
        exiatingEmployee.setEmail(employee.getEmail()); 
        empRepository.save(exiatingEmployee);
      return "Update Succesfully";
    }

    
}
