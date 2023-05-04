package chapter.aop.modules.employee.controller;

import chapter.aop.modules.employee.dto.request.EmployeeRequest;
import chapter.aop.modules.employee.service.internal.EmployeeService;
import chapter.aop.modules.employee.transform.EmployeeMapper;
import chapter.aop.shared.constant.Path;
import chapter.aop.shared.template.ResponseTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(Path.EMPLOYEE)
public class EmployeeController {

    private final EmployeeService service;

    @GetMapping
    public ResponseEntity<ResponseTemplate<Object>> findAll() {
        return service.findAll();
    }

    @GetMapping(Path.BY_ID)
    public ResponseEntity<ResponseTemplate<Object>> findDetail(@PathVariable String id) {
        return service.findDetail(id);
    }

    @PostMapping
    public ResponseEntity<ResponseTemplate<Object>> insert(@Validated @RequestBody EmployeeRequest param) {
        return service.insert(EmployeeMapper.INSTANCE.employeeRequestToEmployee(param));
    }

    @PutMapping
    public ResponseEntity<ResponseTemplate<Object>> update(@Validated @RequestBody EmployeeRequest param) {
        return service.update(EmployeeMapper.INSTANCE.employeeRequestToEmployee(param));
    }

    @DeleteMapping(Path.BY_ID)
    public ResponseEntity<ResponseTemplate<Object>> delete(@PathVariable String id) {
        return service.delete(id);
    }

}
