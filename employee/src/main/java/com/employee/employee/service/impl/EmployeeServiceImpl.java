package com.employee.employee.service.impl;

import com.employee.employee.repository.EmployeeRepository;
import com.employee.employee.model.Employee;
import com.employee.employee.service.EmployeeService;
import com.employee.employee.util.Constantine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.albatross.zelpers.miscelanea.PhobosException;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(readOnly = true)
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeRepository repository;

    @Override
    @Transactional
    public void createEmployee(Employee employee) {
        if (employee.getDocumentId() == null || employee.getName() == null) {
            throw new PhobosException(Constantine.FIELS_NO_COMPLET);
        }
        repository.save(employee);
    }

    @Override
    public List<Employee> allEmployees() {
        return repository.findAll();
    }

    @Override
    public void update(Employee employee) {
        Employee employeeDB = repository.findByDocumentId(employee.getDocumentId());
        if (employeeDB == null) {
            throw new PhobosException(Constantine.NO_EXIST_DB);
        }
        repository.save(employeeDB.builder()
                .age(employee.getAge())
                .birth(employee.getBirth())
                .name(employee.getName())
                .lastName(employee.getLastName())
                .salary(employee.getSalary())
                .build());
    }



    @Override
    public void delete(Employee employee) {
        Employee employeeDB = repository.findByDocumentId(employee.getDocumentId());
        if (employeeDB == null) {
            throw new PhobosException(Constantine.NO_EXIST_DB);
        }
        repository.delete(employee);
    }

    @Override
    public Employee findByDocumentId(String documentId) {
        Employee employeeDB = repository.findByDocumentId(documentId);
        if (employeeDB == null) {
            throw new PhobosException(Constantine.NO_EXIST_DB);
        }
        return employeeDB;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return repository.findById(id);
    }
}