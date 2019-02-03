package cn.jboa.service.impl;

import cn.jboa.common.Constants;
import cn.jboa.dao.CheckResultMapper;
import cn.jboa.dao.ClaimVoucherDetailMapper;
import cn.jboa.dao.ClaimVoucherMapper;
import cn.jboa.entity.CheckResult;
import cn.jboa.entity.ClaimVoucher;
import cn.jboa.entity.ClaimVoucherDetail;
import cn.jboa.entity.ClaimVoucherStatistics;
import cn.jboa.exception.JboaException;
import cn.jboa.service.ClaimVoucherService;
import cn.jboa.util.DateUtil;
import com.auth0.jwt.interfaces.Claim;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 报销单：Service实现
 */
@Service("claimVoucherService")
public class ClaimVoucherServiceImpl implements ClaimVoucherService {

    static Logger logger = Logger.getLogger(ClaimVoucherServiceImpl.class);

    @Autowired
    private ClaimVoucherMapper claimVoucherMapper;

    @Autowired
    private ClaimVoucherDetailMapper claimVoucherDetailMapper;

    @Autowired
    private CheckResultMapper checkResultMapper;

    @Override
    public int getClaimVoucherCount(String createSn, String dealSn, String status, String startDate, String endDate) {
        return claimVoucherMapper.getClaimVoucherCount(createSn, dealSn, status, startDate, endDate);
    }

    @Override
    public List<ClaimVoucher> getClaimVoucherPage(String createSn, String dealSn, String status, String startDate, String endDate, Integer pageNo, Integer pageSize) {
        return claimVoucherMapper.getClaimVoucherPage(createSn, dealSn, status, startDate, endDate,pageNo,pageSize);
    }

    @Override
    public Map getAllStatusMap() {
        Map statusMap = new LinkedHashMap();
        statusMap.put(Constants.CLAIMVOUCHER_CREATED, Constants.CLAIMVOUCHER_CREATED);
        statusMap.put(Constants.CLAIMVOUCHER_SUBMITTED, Constants.CLAIMVOUCHER_SUBMITTED);
        statusMap.put(Constants.CLAIMVOUCHER_BACK, Constants.CLAIMVOUCHER_BACK);
        statusMap.put(Constants.CLAIMVOUCHER_APPROVING, Constants.CLAIMVOUCHER_APPROVING);
        statusMap.put(Constants.CLAIMVOUCHER_APPROVED, Constants.CLAIMVOUCHER_APPROVED);
        statusMap.put(Constants.CLAIMVOUCHER_PAID, Constants.CLAIMVOUCHER_PAID);
        statusMap.put(Constants.CLAIMVOUCHER_TERMINATED, Constants.CLAIMVOUCHER_TERMINATED);
        return statusMap;
    }

    @Override
    public boolean saveClaimVoucher(ClaimVoucher claimVoucher) {
        boolean bRet=false;
        try {
            claimVoucher.setCreateTime(new Date());
            int cRow = claimVoucherMapper.save(claimVoucher);
            if (cRow==0){
                throw new JboaException("添加报销单失败");
            }
            for (ClaimVoucherDetail detail:claimVoucher.getDetailList()) {
                detail.setClaimVoucher(claimVoucher);
                System.out.println("id==="+detail.getClaimVoucher().getId());
                int dRow = claimVoucherDetailMapper.save(detail);
                if (dRow==0){
                    throw new JboaException("添加报销单报销项目失败");
                }
            }
            bRet=true;
        }catch (Exception e){
            e.printStackTrace();
            bRet=false;
        }
        return bRet;
    }

    @Override
    public ClaimVoucher findClaimVoucherById(int id) {
        return claimVoucherMapper.get(id);
    }

    @Override
    public boolean deleteClaimVoucherById(int id) {
        boolean bRet=false;
        try {
            //删除保险单报销项目
            int cvdDelRow=claimVoucherDetailMapper.deleteClaimVoucherDetailByMainId(id);
            //删除报销单检查信息
            int crDelRow=checkResultMapper.deleteCheckResultById(id);
            //删除报销单
            int cvDelRow=claimVoucherMapper.deleteClaimVoucherById(id);
            if (cvDelRow==0){
                throw new JboaException("删除报销单失败");
            }
            bRet=true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return bRet;
    }

    @Override
    public boolean updateClaimVoucher(ClaimVoucher claimVoucher) {
        boolean bRet=false;
        try {
            int cvUpdRow=claimVoucherMapper.updateClaimVoucher(claimVoucher);
            if (cvUpdRow==1){
                bRet=true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return bRet;
    }

    @Override
    public List<ClaimVoucher> getClaimVoucherByModifyDate(int year, int month, int departmentId) {
        Date startDate = null;
        Date endDate = null;
        List<ClaimVoucher> list = new ArrayList<ClaimVoucher>();
        try {
            if (month!=0){
                startDate = DateUtil.strToDate(year+"-"+month+"-1 00:00:00","yyyy-MM-dd hh:mm:ss");
                endDate = DateUtil.strToDate(DateUtil.getLastDayOfMonth(year, month)+" 23:59:59","yyyy-MM-dd hh:mm:ss");
            }else{
                startDate = DateUtil.strToDate(year+"-1"+"-1 00:00:00","yyyy-MM-dd hh:mm:ss");
                endDate = DateUtil.strToDate(year+"-12"+"-31 23:59:59","yyyy-MM-dd hh:mm:ss");
            }
            list = claimVoucherMapper.getClaimVoucherByModifyDate(startDate,endDate,departmentId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
