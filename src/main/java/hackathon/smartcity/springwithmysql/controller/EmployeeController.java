package hackathon.smartcity.springwithmysql.controller;

import hackathon.smartcity.springwithmysql.entity.Employee;
import hackathon.smartcity.springwithmysql.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "employee", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Employee> getAllEmployees(){
        return employeeService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "employee", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
        try {
            Employee results = employeeService.save(employee);
            return ResponseEntity.created(new URI("/api/employee/" + results.getId())).body(results);
        } catch (Exception e){
            return new ResponseEntity<Employee>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "employee", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        if(employee.getId() == null)
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);

        try {
            Employee results = employeeService.update(employee);
            return ResponseEntity.created(new URI("/api/employee/" + results.getId())).body(results);
        } catch (Exception e){
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        employeeService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getSpecificEmployee(@PathVariable Integer id) {
        if(employeeService.findOne(id).isPresent())
            return employeeService.findOne(id).get();
        else
            return null;
    }

}
