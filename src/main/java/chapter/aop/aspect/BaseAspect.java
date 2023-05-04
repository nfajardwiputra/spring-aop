package chapter.aop.aspect;

import chapter.aop.shared.util.JsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public abstract class BaseAspect {

    protected Logger log;

    BaseAspect() {
        this.log = LoggerFactory.getLogger(this.getClass());
    }

    protected void logClass(String prefix, JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();

        String className = signature.getDeclaringType().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        log.info("{} {}::{}", prefix, className, methodName);
    }

    protected void logParameter(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();

        Object[] inputs = joinPoint.getArgs();
        String[] parameterNames = signature.getParameterNames();

        if (signature.getParameterNames().length > 0) {
            for (int i = 0; i < signature.getParameterNames().length; i++) {
                if (Objects.nonNull(inputs[i])) {
                    String input = JsonUtil.toStringJson(inputs[i]);

                    log.info("  Parameter {} = {}", parameterNames[i], input);
                }
            }
        } else {
            log.info("  No parameter found");
        }
    }

}
