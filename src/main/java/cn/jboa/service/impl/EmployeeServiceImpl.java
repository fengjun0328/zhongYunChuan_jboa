package cn.jboa.service.impl;

import cn.jboa.dao.EmployeeMapper;
import cn.jboa.entity.Employee;
import cn.jboa.exception.JboaException;
import cn.jboa.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 员工：service实现
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    static Logger logger = Logger.getLogger(ClaimVoucherServiceImpl.class);

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee login(Employee employee) {
        Employee emp = employeeMapper.findEmployeeBySn(employee.getSn());
        if (emp != null && emp.getPassword().equals(employee.getPassword())){
            return emp;
        }else{
            throw new JboaException("Invalid sn or password!");
        }
    }

    @Override
    public Employee getManager(Employee employee) {
        return employeeMapper.getManager(employee);
    }
}
