package chapter.aop.modules.employee.service.delegate.impl;

import chapter.aop.modules.employee.model.entity.Employee;
import chapter.aop.modules.employee.model.repository.EmployeeRepository;
import chapter.aop.modules.employee.service.delegate.EmployeeDelegateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeDelegateServiceImpl implements EmployeeDelegateService {

    private final EmployeeRepository repository;

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Employee> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return repository.saveAndFlush(employee);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

}
