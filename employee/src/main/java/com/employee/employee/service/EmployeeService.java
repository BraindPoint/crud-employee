package com.employee.employee.service;


import com.employee.employee.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    void createEmployee(Employee employee);

    List<Employee> allEmployees();

    void update(Employee employee);

    void delete(Employee employee);

    Employee findByDocumentId(String documentId);

    Optional<Employee> findById(Long id);


}
