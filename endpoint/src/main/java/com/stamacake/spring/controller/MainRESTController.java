package com.stamacake.spring.controller;

import com.stamacake.spring.dao.EmployeeDAO;
import com.stamacake.spring.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
public class MainRESTController {

    @Value("${URL_CONFIRM}")
    private String URL_CONFIRM;

    @Autowired
    private EmployeeDAO employeeDAO;

    private static final Logger logger = LoggerFactory.getLogger(MainRESTController.class);;

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    HttpEntity<String> entity;

    @RequestMapping("/")
    @ResponseBody
    public String welcome() {
        return "Welcome";
    }

    @RequestMapping(value = "/employees", //
            method = RequestMethod.GET,
            produces = { "application/json" })


    public  @ResponseBody String getEmployees() {

        logger.info("Request: "+ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        logger.info("From/to: "+((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRemoteAddr());
        List<Employee> list = employeeDAO.getAllEmployees();

        HttpEntity<String> entity2;
        entity2= new HttpEntity<String>(list.toString(), new HttpHeaders());
        logger.info("ENTITY: "+entity2.getBody());

        ResponseEntity<String> response = restTemplate.exchange(URL_CONFIRM,
                HttpMethod.POST, entity2, String.class);

        logger.info("BODY "+response.getBody());

        logger.info("Result: "+list.toString());



        return response.getBody().toString();
    }

    @RequestMapping(value = "/employee/{empNo}", //
            method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Employee getEmployee(@PathVariable("empNo") String empNo) {
        logger.info("Request: "+ServletUriComponentsBuilder.fromCurrentRequest().toUriString());
        logger.info("From/to: "+((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRemoteAddr());
        logger.info("Result: "+employeeDAO.getEmployee(empNo).toString());
        return employeeDAO.getEmployee(empNo);
    }


    @RequestMapping(value = "/employee", //
            method = RequestMethod.POST, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Employee addEmployee(@RequestBody Employee emp) {

        logger.info("Creating employee: " + emp.getEmpNo());
        return employeeDAO.addEmployee(emp);
    }


    @RequestMapping(value = "/employee", //
            method = RequestMethod.PUT, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Employee updateEmployee(@RequestBody Employee emp) {

        logger.info("Creating employee: " + emp.getEmpNo());

        return employeeDAO.updateEmployee(emp);
    }


    @RequestMapping(value = "/employee/{empNo}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public void deleteEmployee(@PathVariable("empNo") String empNo) {

        logger.info("(Service Side) Deleting employee: " + empNo);

        employeeDAO.deleteEmployee(empNo);
    }

}
