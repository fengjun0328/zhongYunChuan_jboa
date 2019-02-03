package cn.jboa.dao;

import cn.jboa.entity.Employee;

import java.util.List;

/**
 * 员工：Dao
 */
public interface EmployeeMapper {

    /**
     * 根据员工编码查询员工
     * @param sn 员工号
     * @return
     */
    Employee findEmployeeBySn(String sn);

    /**
     * 获取登录员工的经理
     * @param employee
     * @return
     */
    Employee getManager(Employee employee);

    /**
     * 获取总经理对象
     * @return
     */
    List<Employee> getGeneralManager();

    /**
     * 获取财务对象
     * @return
     */
    List<Employee> getCashier();
}
