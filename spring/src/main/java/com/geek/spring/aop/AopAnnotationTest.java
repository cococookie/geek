package com.geek.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * com.geek.spring.aop 通过注解方式
 * @Author: siyan.liu
 * @Date: 2021/10/19
 */

@Aspect
public class AopAnnotationTest {

    @Pointcut(value = "execution(* com.geek.spring.bean.Klass.studyMath(..))")
    private void point() {

    }

    @Before(value = "point()")
    public void begin() {
        System.out.println("-------2、AopAnnotationTest begin-------");
    }

    @AfterReturning(value = "point()")
    public void end() {
        System.out.println("-------4、AopAnnotationTest end-------");
    }

    @Around(value = "point()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("-------1、AopAnnotationTest begin around-------");
        joinPoint.proceed();
        System.out.println("-------3、AopAnnotationTest end around-------");
    }
}
