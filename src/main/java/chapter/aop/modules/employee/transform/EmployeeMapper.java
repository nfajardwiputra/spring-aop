package chapter.aop.modules.employee.transform;

import chapter.aop.modules.employee.dto.request.EmployeeRequest;
import chapter.aop.modules.employee.dto.response.EmployeeResponse;
import chapter.aop.modules.employee.model.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeResponse employeeToEmployeeResponse(Employee param);

    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    Employee employeeRequestToEmployee(EmployeeRequest param);

}
