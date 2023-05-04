package chapter.aop.modules.employee.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeResponse {

    private String id;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private String name;

}
