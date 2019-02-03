package cn.jboa.controller;

import cn.jboa.common.Constants;
import cn.jboa.entity.CheckResult;
import cn.jboa.entity.Employee;
import cn.jboa.service.CheckResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * 处理有关报销单生怕的请求
 */
@Controller
@RequestMapping("/checkResult")
public class CheckResultController {

    @Autowired
    private CheckResultService checkResultService;

    /**
     * 审批报销单
     * @return
     */
    @RequestMapping(value = "/checkClaimVoucher")
    public String checkClaimVoucher(HttpSession session,CheckResult checkResult){
        Employee employee = (Employee) session.getAttribute(Constants.AUTH_EMPLOYEE);
        checkResult.setCheckEmployee(employee);
        checkResult.setCheckTime(new Date());
        boolean bRet = checkResultService.saveCheckResult(checkResult);
        if (bRet==true)
            return "redirect:/claimVoucher/searchClaimVoucher";
        return "redirect:/claimVoucher/toCheck/"+checkResult.getClaimVoucher().getId();
    }
}
