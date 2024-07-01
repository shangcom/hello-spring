package hello.hello_spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect // SpringAOP
public class TimeTraceAop {

    /*
     * execution : Pointcut. 표현식으로 Advice가 적용될 JoinPoint 지정.
     * JoinPoint: 타겟 메서드의 정보에 접근할 수 있지만, 실행하거나 제어 불가.
     * ProceedingJoinPoint: JoinPoint의 하위 인터페이스로, 타겟 메서드를 실행하고 제어할 수 있음.
     * ProceedingJoinPoint는 @Around 어드바이스에서만 사용하고, JoinPoint는 그 외의 어드바이스(@Before, @After, @AfterReturning, @AfterThrowing)에서 사용.
     */
    @Around("execution(* hello.hello_spring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString()); // 메서드의 시그니처를 문자열로 반환
        System.out.println("Method: " + joinPoint.getSignature().getName()); // 메서드의 시그니처를 반환
        System.out.println("Arguments: " + Arrays.toString(joinPoint.getArgs())); // 메서드의 인수 목록을 배열로 반환
        System.out.println("Target: " + joinPoint.getTarget().getClass().getName()); // 실제 호출된 대상 객체(타겟)를 반환
        System.out.println("This: " + joinPoint.getThis().getClass().getName()); // 프록시 객체를 반환
        try {
            return joinPoint.proceed(); // 타켓 메서드 호출
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs +
                    "ms");
        }
    }
}