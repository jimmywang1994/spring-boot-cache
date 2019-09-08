package com.ww.springbootcache.controller;

import com.ww.springbootcache.bean.Employee;
import com.ww.springbootcache.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id) {
        Employee employee = employeeService.getEmpById(id);
        return employee;
    }

    @GetMapping("/emp")
    public Employee updateEmp(Employee employee) {
        Employee emp = employeeService.updateEmp(employee);
        return emp;
    }

    @GetMapping("/deleteEmp")
    public String delEmp(Integer id) {
        employeeService.deleteEmp(id);
        return "success";
    }


    @GetMapping("/emp/lastname/{lastName}")
    public Employee getEmpByLastName(@PathVariable("lastName") String lastName){
        return employeeService.getEmpByLastName(lastName);
    }
}
