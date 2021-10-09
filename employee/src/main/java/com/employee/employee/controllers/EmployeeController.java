package com.employee.employee.controllers;


import com.employee.employee.model.Employee;
import com.employee.employee.service.EmployeeService;
import com.employee.employee.util.Constantine;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.albatross.zelpers.miscelanea.ExceptionHandler;
import pe.albatross.zelpers.miscelanea.JsonHelper;
import pe.albatross.zelpers.miscelanea.JsonResponse;
import pe.albatross.zelpers.miscelanea.PhobosException;

import java.util.List;



/**
 * Esta clase es la capa controladora del API
 * @author: Orlando Camavilca Chavez
 * @version: 10/10/2021
 */

@Controller
@RequestMapping("/employee")
@Api(tags = "employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;


    @ResponseBody
    @PostMapping("create")
    @ApiOperation(value = "Crear Empleado", notes = "Servicios para crear los datos de un nuevo empleado")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Empleado creado correctamente"),
            @ApiResponse(code = 400, message = "Datos Invalidos") })
    public JsonResponse createEmployee(@RequestBody Employee employee) {
        JsonResponse response = new JsonResponse();
        try {
            service.createEmployee(employee);
            response.setMessage(Constantine.CREATE);
            response.setSuccess(Boolean.TRUE);
        } catch (PhobosException e) {
            ExceptionHandler.handlePhobosEx(e, response);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, response);
        }
        return response;
    }

    @ResponseBody
    @PostMapping("update")
    @ApiOperation(value = "Actualizar Empleado", notes = "Servicios para actualizar los datos de un empleado")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Datos del empleado actualizados correctamente"),
            @ApiResponse(code = 400, message = "Datos Invalidos") })
    public JsonResponse updateEmployee(@RequestBody Employee employee) {
        JsonResponse response = new JsonResponse();
        try {
            service.update(employee);
            response.setMessage(Constantine.UPDATE);
            response.setSuccess(Boolean.TRUE);
        } catch (PhobosException e) {
            ExceptionHandler.handlePhobosEx(e, response);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, response);
        }
        return response;
    }

    @ResponseBody
    @DeleteMapping("delete")
    @ApiOperation(value = "Eliminar Empleado", notes = "Servicios para eliminar los datos de un empleado")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Datos del empleado eliminados correctamente"),
            @ApiResponse(code = 400, message = "Datos Invalidos") })
    public JsonResponse deleteEmployee(@RequestBody Employee employee) {
        JsonResponse response = new JsonResponse();
        try {
            service.delete(employee);
            response.setMessage(Constantine.DELETE);
            response.setSuccess(Boolean.TRUE);
        } catch (PhobosException e) {
            ExceptionHandler.handlePhobosEx(e, response);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, response);
        }
        return response;
    }

    @ResponseBody
    @GetMapping("all")
    @ApiOperation(value = "Listar todos los empleados", notes = "Servicios para listar los datos de todos los empleados")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Empleados listados correctamente"),
            @ApiResponse(code = 400, message = "No se pudo listar los empleados") })
    public JsonResponse allEmployee() {
        JsonResponse response = new JsonResponse();
        JsonNodeFactory jsonFactory = JsonNodeFactory.instance;
        ArrayNode arrayNode = new ArrayNode(jsonFactory);
        try {
            List<Employee> employees = service.allEmployees();
            for (Employee employee : employees) {
                ObjectNode node = JsonHelper.createJson(employee, jsonFactory, new String[]{"*"});
                arrayNode.add(node);
            }
            response.setData(arrayNode);
            response.setSuccess(Boolean.TRUE);
            response.setTotal(employees.size());
        } catch (PhobosException e) {
            ExceptionHandler.handlePhobosEx(e, response);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, response);
        }
        return response;
    }

    @ResponseBody
    @GetMapping("read/{documentId}")
    @ApiOperation(value = "Datos de un empleado", notes = "Servicios para listar los datos de un empleado")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Datos listados correctamente "),
            @ApiResponse(code = 400, message = "No se pudo listar los datos del empleado") })
    public JsonResponse readEmployee(@RequestParam String documentId) {
        JsonResponse response = new JsonResponse();
        try {
            Employee employee = service.findByDocumentId(documentId);
            response.setData(employee);
            response.setSuccess(Boolean.TRUE);
        } catch (PhobosException e) {
            ExceptionHandler.handlePhobosEx(e, response);
        } catch (Exception e) {
            ExceptionHandler.handleException(e, response);
        }
        return response;
    }

}
