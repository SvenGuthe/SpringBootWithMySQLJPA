package hackathon.smartcity.springwithmysql.repository;

import hackathon.smartcity.springwithmysql.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
