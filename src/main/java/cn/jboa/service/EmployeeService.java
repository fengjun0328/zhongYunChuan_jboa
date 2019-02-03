package cn.jboa.service;

import cn.jboa.entity.Employee;

/**
 * 员工：service
 */
public interface EmployeeService {

    /**
     * 员工登录
     * @param employee
     * @return
     */
    Employee login(Employee employee);

    /**
     * 获取登录员工的经理
     * @param employee
     * @return
     */
    Employee getManager(Employee employee);
}
