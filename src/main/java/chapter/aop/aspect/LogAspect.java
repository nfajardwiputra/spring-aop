package chapter.aop.aspect;

import chapter.aop.shared.util.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Aspect
@Component
public class LogAspect extends BaseAspect {

    /**
     * Advice dijalankan sebelum code yang ada pada join point. (disini Class Controller)
     * Advice ini tidak memiliki kemampuan untuk mencegah flow proses dieksekusi.
     *
     * @param joinPoint {@link JoinPoint}
     */
    @Before("bean(*Controller)")
    public void logBeforeControllerCall(JoinPoint joinPoint) {
        logClass("* Before Request", joinPoint);
        logParameter(joinPoint);
    }

    /**
     * Advice dijalankan setelah join point (disini Class Controller) baik error atau success.
     *
     * @param joinPoint {@link JoinPoint}
     */
    @After("bean(*Controller)")
    public void logAfterControllerCall(JoinPoint joinPoint) {
        logClass("* After Request", joinPoint);
    }

    /**
     * Advice dijalankan jika method pada joint point (disini Class Controller)
     * dieksekusi dengan hasil sukses atau tidak ada error.
     *
     * @param joinPoint {@link JoinPoint}
     * @param response  {@link Object} variable name defined in 'returning = "response"'
     */
    @AfterReturning(pointcut = "bean(*Controller)", returning = "response")
    public void logAfterControllerCallSuccess(JoinPoint joinPoint, Object response) {
        logClass("* After Success Request", joinPoint);

        if (Objects.nonNull(response)) {
            String result = JsonUtil.toStringJson(response);

            log.info("  Response is {}", result);
        }
    }

    /**
     * Advice dijalankan jika method pada joint point (disini Class Controller) terjadi error.
     *
     * @param joinPoint {@link JoinPoint}
     * @param exception {@link Object} variable name defined in 'throwing = "exception"'
     */
    @AfterThrowing(pointcut = "bean(*Controller)", throwing = "exception")
    public void logAfterControllerCallError(JoinPoint joinPoint, Exception exception) {
        logClass("* After Error Request", joinPoint);

        if (Objects.nonNull(exception)) {
            log.info("  Exception is", exception);
        }
    }

    /**
     * Deklarasi point cut untuk digunakan pada method selanjutnya (onRepositoryExecute)
     */
    @Pointcut("(bean(*Repository))")
    public void repositoryExecute() {}

    /**
     * Advice dijalakan ketika eksekusi method pada class dengan anotasi @Repository
     * dengan @Arround value dari deklarasi point cut diatas
     *
     * @param joinPoint {@link ProceedingJoinPoint}
     * @return {@link Object}
     * @throws Throwable throwing error during execute
     */
    @Around("repositoryExecute()")
    public Object onRepositoryExecute(ProceedingJoinPoint joinPoint) throws Throwable {
        logClass("* On Repository", joinPoint);
        logParameter(joinPoint);

        final long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();

        log.info("  Process take {} millis to finish", System.currentTimeMillis() - startTime);

        return result;
    }

}
