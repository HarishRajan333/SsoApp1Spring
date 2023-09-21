package com.mycompany.application2.Controller;

import com.mycompany.application2.Response.Employee;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmsController {

    List<Employee> employees = new ArrayList<>();

    @GetMapping("/tenentEmployee/getEms-data")
    public String tenentEmployee() {
        return "Login in as Employee Management System's Tenent Employee.";
    }

    @GetMapping("/tenentAdmin/getEms-data")
    public String tenentAdmin() {
        return "Login in as Employee Management System's Tenent Admin.";
    }

    @GetMapping("/platformAdmin/getEms-data")
    public String platformAdmin() {
        return "Login in as Employee Management System's Platform Admin.";
    }

    @GetMapping("/platformEmployee/getEms-data")
    public String platformEmployee() {
        return "Login in as Employee Management System's Platform Employee.";
    }

    @PostMapping("/addTenent-employee")
    public List<Employee> addEmployee(@RequestBody Employee employee) {
        try {
            employees.add(employee);
            return employees;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return employees;
        }
    }

    @GetMapping("/getEmployee")
    public List<Employee> getEmployee() {
        return employees;
    }
}
