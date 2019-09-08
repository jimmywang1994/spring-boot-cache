package com.ww.springbootcache.service;

import com.ww.springbootcache.bean.Employee;
import com.ww.springbootcache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "emp")//抽取缓存的公共配置
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    @Cacheable(cacheNames = "emp")
    public Employee getEmpById(Integer id) {
        System.out.println("查询id号为" + id + "的员工");
        Employee employee = employeeMapper.getEmpById(id);
        return employee;
    }

    /**
     * 缓存调用方法返回内容（更新时用）
     *
     * @param employee
     * @return
     */
    @CachePut(value = "emp", key = "#result.id")
    public Employee updateEmp(Employee employee) {
        System.out.println("updateEmp:" + employee);
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * 缓存清除
     *
     * @param id
     */
    @CacheEvict( key = "#id", beforeInvocation = true)
    public void deleteEmp(Integer id) {
        System.out.println("deleteEmp:" + id);
        int i = 10 / 0;
    }

    @Caching(
            cacheable = {
                    @Cacheable( key = "#lastName")
            },
            put = {
                    @CachePut( key = "#result.id"),
                    @CachePut( key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName) {
        return employeeMapper.getEmpByLastName(lastName);
    }
}
