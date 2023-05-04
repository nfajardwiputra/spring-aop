package chapter.aop.modules.employee.service.internal.impl;

import chapter.aop.modules.employee.model.entity.Employee;
import chapter.aop.modules.employee.service.delegate.EmployeeDelegateService;
import chapter.aop.modules.employee.service.internal.EmployeeService;
import chapter.aop.modules.employee.transform.EmployeeMapper;
import chapter.aop.shared.template.ResponseTemplate;
import chapter.aop.shared.transform.ResponseEntityTransform;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final ResponseEntityTransform entityTransform;

    private final EmployeeDelegateService delegateService;

    @Override
    public ResponseEntity<ResponseTemplate<Object>> findAll() {
        return entityTransform.response(delegateService
                .findAll().stream()
                .map(EmployeeMapper.INSTANCE::employeeToEmployeeResponse)
                .collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<ResponseTemplate<Object>> findDetail(String id) {
        return entityTransform.response(EmployeeMapper.INSTANCE
                .employeeToEmployeeResponse(delegateService.findById(id)
                        .orElse(null)));
    }

    @Override
    public ResponseEntity<ResponseTemplate<Object>> insert(Employee employee) {
        return entityTransform.response(EmployeeMapper.INSTANCE
                .employeeToEmployeeResponse(delegateService.save(employee)));
    }

    @Override
    public ResponseEntity<ResponseTemplate<Object>> update(Employee employee) {
        return entityTransform.response(EmployeeMapper.INSTANCE
                .employeeToEmployeeResponse(delegateService.save(employee)));
    }

    @Override
    public ResponseEntity<ResponseTemplate<Object>> delete(String id) {
        delegateService.deleteById(id);

        return entityTransform.response();
    }

}
