package cn.jboa.service.impl;

import cn.jboa.common.Constants;
import cn.jboa.dao.*;
import cn.jboa.entity.*;
import cn.jboa.service.CheckResultService;
import cn.jboa.util.DateUtil;
import javafx.scene.control.TableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *  保险审批结果：Service实现
 */
@Service("checkResultService")
public class CheckResultServiceImpl implements CheckResultService {

    @Autowired
    private CheckResultMapper checkResultMapper;

    @Autowired
    private ClaimVoucherMapper claimVoucherMapper;

    @Autowired
    private EmployeeMapper employeeMappper;

    @Autowired
    private ClaimVoucherStatisticsMapper claimVoucherStatisticsMapper;

    @Autowired
    private ClaimVouYearStatisticsMapper claimVouYearStatisticsMapper;

    @Override
    public boolean saveCheckResult(CheckResult checkResult) {
        boolean bRet = false;
        try {
            ClaimVoucher claimVoucher=claimVoucherMapper.get(checkResult.getClaimVoucher().getId());
            Employee checkEmployee = checkResult.getCheckEmployee();
            claimVoucher = updateClaimVoucherStatus(checkEmployee.getPosition().getNameCn()
                    ,checkResult.getResult(),claimVoucher);
            claimVoucher.setModifyTime(new Date());
            claimVoucher.setNextDeal(null);
            int result = checkResultMapper.save(checkResult);
            if (result==0)
                throw new Exception("添加报销单审批结果失败");
            result = claimVoucherMapper.updateClaimVoucher(claimVoucher);
            if (result==0)
                throw new Exception("修改报销单失败");
            if (claimVoucher.getStatus().equals(Constants.CLAIMVOUCHER_PAID)){
                Double totalCount=claimVoucher.getTotalAccount();
                int year= DateUtil.getYear(claimVoucher.getCreateTime());
                int month=DateUtil.getMonth(claimVoucher.getCreateTime());
                Date modifyTime=new Date();
                Department dept=claimVoucher.getCreator().getDepartment();

                ClaimVoucherStatistics claimVoucherStatistics;
                claimVoucherStatistics = new ClaimVoucherStatistics(totalCount,year,month,modifyTime,dept);

                ClaimVouYearStatistics claimVouYearStatistics;
                claimVouYearStatistics = new ClaimVouYearStatistics(totalCount,year,modifyTime,dept);
                result=claimVoucherStatisticsMapper.update(claimVoucherStatistics);
                if (result==0)
                    result=claimVoucherStatisticsMapper.save(claimVoucherStatistics);
                if (result==0)
                    throw new Exception("修改或添加月度统计错误");
                result=claimVouYearStatisticsMapper.update(claimVouYearStatistics);
                if (result==0)
                    claimVouYearStatisticsMapper.save(claimVouYearStatistics);
                if (result==0)
                    throw new Exception("修改或添加年度统计错误");
            }
            bRet=result==0?false:true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return bRet;
    }

    private ClaimVoucher updateClaimVoucherStatus(String position, String checkResult, ClaimVoucher claimVoucher) {
        if (checkResult.equals(Constants.CHECKRESULT_PASS)){
            //已通过
            if (position.equals(Constants.POSITION_FM)){
                if (claimVoucher.getTotalAccount()>=5000){
                    //待审批，并且处理人为总经理
                    claimVoucher.setStatus(Constants.CLAIMVOUCHER_APPROVING);
                    claimVoucher.setNextDeal(employeeMappper.getGeneralManager().get(0));
                }else{
                    //已审批，处理人为财务
                    claimVoucher.setStatus(Constants.CLAIMVOUCHER_APPROVED);
                    claimVoucher.setNextDeal(employeeMappper.getCashier().get(0));
                }
            }else if(position.equals(Constants.POSITION_GM)){
                //总经理已审批，处理人为财务
                claimVoucher.setStatus(Constants.CLAIMVOUCHER_APPROVED);
                claimVoucher.setNextDeal(employeeMappper.getCashier().get(0));
            }else{
                //财务已审批，处理人为空
                claimVoucher.setStatus(Constants.CLAIMVOUCHER_PAID);
                claimVoucher.setNextDeal(null);
            }
        }else if (checkResult.equals(Constants.CHECKRESULT_REJECT)){
            //已拒绝
            claimVoucher.setStatus(Constants.CLAIMVOUCHER_TERMINATED);
            claimVoucher.setNextDeal(null);
        }else if (checkResult.equals(Constants.CHECKRESULT_BACK)){
            //已打回
            claimVoucher.setStatus(Constants.CLAIMVOUCHER_BACK);
            claimVoucher.setNextDeal(claimVoucher.getCreator());
        }
        return claimVoucher;
    }
}
