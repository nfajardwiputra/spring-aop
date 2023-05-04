package chapter.aop.aspect;

import chapter.aop.modules.employee.model.entity.Employee;
import chapter.aop.modules.employee.service.delegate.EmployeeDelegateService;
import chapter.aop.shared.constant.ResponseEnum;
import chapter.aop.shared.transform.ResponseEntityTransform;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;

@Aspect
@Component
@RequiredArgsConstructor
public class EmployeeAspect extends BaseAspect {

    private final ResponseEntityTransform entityTransform;

    private final EmployeeDelegateService delegateService;

    private void info(ProceedingJoinPoint joinPoint) {
        logClass("* On Execute", joinPoint);
        logParameter(joinPoint);
    }

    /**
     * Advice dijalakan ketika eksekusi method findDetail
     * untuk menambahkan validasi id employee valid atau tidak
     * (ada di db atau tidak), jika tidak maka return BAD_REQUEST,
     * jika iya maka lanjutkan eksekusi method findDetail
     *
     * @param joinPoint {@link ProceedingJoinPoint}
     * @return {@link Object}
     * @throws Throwable throwing error during execute
     */
    @Around("execution(* chapter.aop.modules.employee.service.internal.impl.EmployeeServiceImpl.findDetail(..))")
    public Object onExecuteFindDetail(ProceedingJoinPoint joinPoint) throws Throwable {
        info(joinPoint);

        log.info("* Validate employee id...");

        String id = (String) joinPoint.getArgs()[0];
        Optional<Employee> employee = delegateService.findById(id);

        if (!employee.isPresent()) {
            log.info("  Invalid employee id, throw BAD_REQUEST");

            return entityTransform.response(ResponseEnum.BAD_REQUEST);
        }

        log.info("  Valid employee id, continues");

        return joinPoint.proceed();
    }

    /**
     * Advice dijalakan ketika eksekusi method insert
     * untuk menambahkan validasi id employee harus null
     * jika diisi maka return BAD_REQUEST,
     * jika null maka lanjutkan eksekusi method insert
     *
     * @param joinPoint {@link ProceedingJoinPoint}
     * @return {@link Object}
     * @throws Throwable throwing error during execute
     */
    @Around("execution(* chapter.aop.modules.employee.service.internal.impl.EmployeeServiceImpl.insert(..))")
    public Object onExecuteInsert(ProceedingJoinPoint joinPoint) throws Throwable {
        info(joinPoint);

        log.info("* Validate must be null employee id...");

        Employee employee = (Employee) joinPoint.getArgs()[0];

        if (Objects.nonNull(employee.getId())) {
            log.info("  Employee id is set, throw BAD_REQUEST");

            return entityTransform.response(ResponseEnum.BAD_REQUEST);
        }

        log.info("  Employee id is not set, continues");

        return joinPoint.proceed();
    }

    /**
     * Advice dijalakan ketika eksekusi method update
     * untuk menambahkan validasi id employee harus diisi dan data exist
     * jika tidak diisi atau exist data tidak ditemukan maka return BAD_REQUEST,
     * jika diisi dan exist data ditemukan maka lanjutkan eksekusi method update
     *
     * @param joinPoint {@link ProceedingJoinPoint}
     * @return {@link Object}
     * @throws Throwable throwing error during execute
     */
    @Around("execution(* chapter.aop.modules.employee.service.internal.impl.EmployeeServiceImpl.update(..))")
    public Object onExecuteUpdate(ProceedingJoinPoint joinPoint) throws Throwable {
        info(joinPoint);

        log.info("* Validate must be set employee id and found existing data...");

        Employee employee = (Employee) joinPoint.getArgs()[0];

        if (Objects.isNull(employee.getId()) || !delegateService.findById(employee.getId()).isPresent()) {
            log.info("  Employee id is null or data not found, throw BAD_REQUEST");

            return entityTransform.response(ResponseEnum.BAD_REQUEST);
        }

        log.info("  Employee id is set and found, continues");

        return joinPoint.proceed();
    }

    /**
     * Advice dijalakan ketika eksekusi method delete
     * untuk menambahkan validasi id employee harus exist
     * jika exist data tidak ditemukan maka return BAD_REQUEST,
     * jika exist data ditemukan maka lanjutkan eksekusi method delete
     *
     * @param joinPoint {@link ProceedingJoinPoint}
     * @return {@link Object}
     * @throws Throwable throwing error during execute
     */
    @Around("execution(* chapter.aop.modules.employee.service.internal.impl.EmployeeServiceImpl.delete(..))")
    public Object onExecuteDelete(ProceedingJoinPoint joinPoint) throws Throwable {
        info(joinPoint);

        log.info("* Validate must be found exist data of employee id...");

        String id = (String) joinPoint.getArgs()[0];

        if (!delegateService.findById(id).isPresent()) {
            log.info("  Employee id not found, throw BAD_REQUEST");

            return entityTransform.response(ResponseEnum.BAD_REQUEST);
        }

        log.info("  Employee id found, continues");

        return joinPoint.proceed();
    }

}
