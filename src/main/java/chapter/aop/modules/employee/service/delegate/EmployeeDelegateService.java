package chapter.aop.modules.employee.service.delegate;

import chapter.aop.modules.employee.model.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDelegateService {

    List<Employee> findAll();

    Optional<Employee> findById(String id);

    Employee save(Employee employee);

    void deleteById(String id);

}
