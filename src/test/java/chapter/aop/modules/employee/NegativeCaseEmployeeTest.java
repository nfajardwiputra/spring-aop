package chapter.aop.modules.employee;

import chapter.aop.ApplicationTest;
import chapter.aop.modules.employee.dto.request.EmployeeRequest;
import chapter.aop.shared.constant.Path;
import chapter.aop.shared.util.JsonUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@DisplayName("Negative Case Employee Integration Test")
class NegativeCaseEmployeeTest extends ApplicationTest {

    private final String ID = "123";

    @Test
    @Order(1)
    @DisplayName("Get Detail Invalid Employee")
    void findDetail() throws Exception {
        performRequest(MockMvcRequestBuilders.get(Path.EMPLOYEE + "/" + ID))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    @Order(2)
    @DisplayName("Insert New Employee with ID")
    void insert() throws Exception {
        performRequest(MockMvcRequestBuilders.post(Path.EMPLOYEE).content(generateParam()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    @Test
    @Order(3)
    @DisplayName("Update Existing Invalid Employee")
    void update() throws Exception {
        performRequest(MockMvcRequestBuilders.put(Path.EMPLOYEE).content(generateParam()))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

    private String generateParam() {
        return JsonUtil.toStringJson(easyRandom.nextObject(EmployeeRequest.class));
    }

    @Test
    @Order(4)
    @DisplayName("Delete Existing Invalid Employee")
    void delete() throws Exception {
        performRequest(MockMvcRequestBuilders.delete(Path.EMPLOYEE + "/" + ID))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andReturn();
    }

}
