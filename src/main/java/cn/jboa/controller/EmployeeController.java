package cn.jboa.controller;

import cn.jboa.common.Constants;
import cn.jboa.entity.Employee;
import cn.jboa.exception.JboaException;
import cn.jboa.service.EmployeeService;
import cn.jboa.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * 该类主要处理有关员工的请求处理
 */
@Controller
@RequestMapping("/emp")
public class EmployeeController  {

    @Autowired
    private EmployeeService employeeService; //员工业务类

    @RequestMapping(value = "/login")
    public String login(Employee employee, HttpSession session){
        try {
            employee.setPassword(MD5.MD5Encode(employee.getPassword())); //MD5摘要格式密码
            employee = employeeService.login(employee); //查询员工
            Employee manager = employeeService.getManager(employee); //当前登录员工的经理
            session.setAttribute(Constants.AUTH_EMPLOYEE,employee);
            session.setAttribute(Constants.AUTH_EMPLOYEE_MANAGER,manager);
            session.setAttribute(Constants.EMPLOYEE_POSITION,employee.getPosition().getNameCn());
        }catch (JboaException ex){
            ex.printStackTrace();
            return "login";
        }
        return "index";
    }
}
