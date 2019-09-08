package com.ww.springbootcache;

import com.ww.springbootcache.bean.Employee;
import com.ww.springbootcache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootCacheApplicationTests {

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    EmployeeMapper employeeMapper;


    @Autowired
    RedisTemplate<Object,Employee> myTemplate;

    @Test
    public void test01() {
        stringRedisTemplate.opsForList().leftPush("mylist", "1");
        stringRedisTemplate.opsForList().leftPush("mylist", "2");
    }

    @Test
    public void contextLoads() {
        Employee emp = employeeMapper.getEmpById(1);
        System.out.println(emp);
    }

    @Test
    public void test02() {
        Employee emp = employeeMapper.getEmpById(1);
        //默认如果存对象，则用jdk序列化机制
        myTemplate.opsForValue().set("emp-02", emp);
        //1.将数据以json形式保存
    }

}
