package chapter.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepositoryAspect extends BaseAspect {

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
