package hackathon.smartcity.springwithmysql.service;

import hackathon.smartcity.springwithmysql.entity.Employee;
import hackathon.smartcity.springwithmysql.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee save(Employee employee){
        if(employee.getId() != null && employeeRepository.existsById(employee.getId())){
            throw new EntityExistsException("There is an entity with such an ID");
        }
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee){
        if(employee.getId() != null && employeeRepository.existsById(employee.getId())){
            throw new EntityExistsException("There is an entity with such an ID");
        }
        return employeeRepository.save(employee);
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Optional<Employee> findOne(Integer id){
        return employeeRepository.findById(id);
    }

    public void delete(Integer id){
        employeeRepository.deleteById(id);
    }

}
