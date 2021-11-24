package aop;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * aop 通过配置文件方式
 * @Author: siyan.liu
 * @Date: 2021/10/19
 */
public class AopConfigTest {
    public void begin() {
        System.out.println("-------1、begin------");
    }

    public void end() {
        System.out.println("-------4、end-------");
    }

    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("-------1、begin around-------");
        joinPoint.proceed();
        System.out.println("-------3、end around-------");
    }
}
