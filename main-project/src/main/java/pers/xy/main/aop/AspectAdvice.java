package pers.xy.main.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class AspectAdvice {
    @Pointcut(value = "@annotation(pers.xy.main.aop.AspectInvocation)")
    public void pointCut() {
    }

    @Around(value = "pointCut()")
    public Object cache(final ProceedingJoinPoint pjp) throws Throwable {

        Object proceed = null;
        try {
            Class<?> target = pjp.getTarget().getClass();
            AspectInvocation classAnno = target.getAnnotation(AspectInvocation.class);

            Object[] args = pjp.getArgs();
            Method method = getMethod(pjp);
            AspectInvocation methodAnno = method.getAnnotation(AspectInvocation.class);
            proceed = pjp.proceed();

        } catch (Exception e) {
            throw e;
        }
        return proceed;
    }

    public static Method getMethod(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        return signature.getMethod();
    }
}
