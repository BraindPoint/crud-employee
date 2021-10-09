package com.employee.employee.util;

import com.employee.employee.model.Employee;

import java.math.BigDecimal;
import java.util.Date;


public class UtilTest {

    public static Employee employee() {
        return Employee.builder()
                .id(new Long(0))
                .documentId("A1")
                .name("Orlando")
                .lastName("Camavilca")
                .age(24)
                .birth(new Date())
                .salary(new BigDecimal(2000))
                .build();
    }
}
