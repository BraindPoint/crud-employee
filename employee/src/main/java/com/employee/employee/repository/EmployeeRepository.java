package com.employee.employee.repository;

import com.employee.employee.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM employee e WHERE e.document_id = ?1", nativeQuery = true)
    Employee findByDocumentId(String documentId);



}