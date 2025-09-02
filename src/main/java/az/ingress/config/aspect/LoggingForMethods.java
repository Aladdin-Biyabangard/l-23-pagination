    package az.ingress.config.aspect;

    import lombok.extern.slf4j.Slf4j;
    import org.aspectj.lang.ProceedingJoinPoint;
    import org.aspectj.lang.annotation.Around;
    import org.aspectj.lang.annotation.Aspect;
    import org.springframework.stereotype.Component;

    @Slf4j
    @Aspect
    @Component
    public class LoggingForMethods {


        @Around("@within(az.ingress.config.annotation.LogClass)")
        public Object logMethod(ProceedingJoinPoint joinPoint) throws Throwable {
            var methodName = joinPoint.getSignature().toShortString();
            log.info("ActionLog.{}.STARTED", methodName);
            try {
                Object response = joinPoint.proceed();
                log.info("ActionLog.{}.SUCCESS", methodName);
                return response;
            } catch (Exception ex) {
                log.error("ActionLog.{}.FAILED - Error: {}", methodName, ex.getMessage());
                throw ex;
            }
        }
    }
