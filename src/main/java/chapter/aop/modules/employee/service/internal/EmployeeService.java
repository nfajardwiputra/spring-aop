package chapter.aop.modules.employee.service.internal;

import chapter.aop.modules.employee.model.entity.Employee;
import chapter.aop.shared.template.ResponseTemplate;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {

    ResponseEntity<ResponseTemplate<Object>> findAll();

    ResponseEntity<ResponseTemplate<Object>> findDetail(String id);

    ResponseEntity<ResponseTemplate<Object>> insert(Employee employee);

    ResponseEntity<ResponseTemplate<Object>> update(Employee employee);

    ResponseEntity<ResponseTemplate<Object>> delete(String id);

}
