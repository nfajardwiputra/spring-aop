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

@DisplayName("Positive Case Employee Integration Test")
class PositiveCaseEmployeeTest extends ApplicationTest {

    private final String ID = "75269a0639994ef39410f4ccb15bc2fd";

    @Test
    @Order(1)
    @DisplayName("Get All Employee")
    void findAll() throws Exception {
        performRequest(MockMvcRequestBuilders.get(Path.EMPLOYEE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @Order(2)
    @DisplayName("Get Detail Employee")
    void findDetail() throws Exception {
        performRequest(MockMvcRequestBuilders.get(Path.EMPLOYEE + "/" + ID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    @Test
    @Order(3)
    @DisplayName("Insert New Employee")
    void insert() throws Exception {
        performRequest(MockMvcRequestBuilders.post(Path.EMPLOYEE).content(generateParam(null)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        findAll();
    }

    @Test
    @Order(4)
    @DisplayName("Update Existing Employee")
    void update() throws Exception {
        performRequest(MockMvcRequestBuilders.put(Path.EMPLOYEE).content(generateParam(ID)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        findDetail();
    }

    private String generateParam(String id) {
        EmployeeRequest param = easyRandom.nextObject(EmployeeRequest.class);
        param.setId(id);

        return JsonUtil.toStringJson(param);
    }

    @Test
    @Order(5)
    @DisplayName("Delete Existing Employee")
    void delete() throws Exception {
        performRequest(MockMvcRequestBuilders.delete(Path.EMPLOYEE + "/" + ID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

}
