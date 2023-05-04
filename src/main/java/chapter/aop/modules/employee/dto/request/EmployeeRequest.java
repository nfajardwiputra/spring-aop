package chapter.aop.modules.employee.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeRequest {

    private String id;

    @NotBlank
    private String name;

}
