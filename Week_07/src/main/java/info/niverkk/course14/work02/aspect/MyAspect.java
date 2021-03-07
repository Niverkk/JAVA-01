package info.niverkk.course14.work02.aspect;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import info.niverkk.course14.work02.service.JOrderService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 思路：注入基类，根据是否使用注解设置不同dao，对应不同数据源
 * 现在启动就报错，解决不了
 * @author JKXAING on 2021/3/6
 */
@Aspect
@Component
public class MyAspect {
    @Autowired
    private Map<String, BaseMapper> map;

    @Pointcut("execution(* info.niverkk.course14.work02.dao*.*(..))")
    public void p1(){};

    @Pointcut("execution(* info.niverkk.course14.work02.service..*.*(..))")
    public void p2(){};

    @Pointcut("execution(* info.niverkk.course14.work02.controller..*.*(..))")
    public void p3(){};

    @Before(value = "p1()")
    public void before(JoinPoint joinPoint){
        System.out.println("before ...");
    }

    @Before(value = "@annotation(readOnly) || p2()")
    public void before2(JoinPoint joinPoint, ReadOnly readOnly) throws Exception {
        map.forEach((k,v)->{
            System.out.println(k+ ": "+ v);
        });

        JOrderService target = (JOrderService)joinPoint.getTarget();
        Field baseMapper = target.getClass().getDeclaredField("baseMapper");
        baseMapper.setAccessible(true);
        // 只读数据库
        if(readOnly != null){
            baseMapper.set(target, map.get("JOrderDao2"));
        }else{
            baseMapper.set(target, map.get("hrDao"));
        }

        System.out.println("before2 ...");
    }

    //@Before(value = "p3()")
    //public void before3(JoinPoint joinPoint){
    //    System.out.println("before3 ...");
    //}


}
