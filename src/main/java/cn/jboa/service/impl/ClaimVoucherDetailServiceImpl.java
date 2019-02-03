package cn.jboa.service.impl;

import cn.jboa.dao.ClaimVoucherDetailMapper;
import cn.jboa.dao.ClaimVoucherMapper;
import cn.jboa.entity.ClaimVoucherDetail;
import cn.jboa.service.ClaimVoucherDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 报销单详细信息结果：Service实现
 */
@Service("claimVoucherDetailService")
public class ClaimVoucherDetailServiceImpl implements ClaimVoucherDetailService {

    @Autowired
    private ClaimVoucherDetailMapper claimVoucherDetailMapper;

    @Autowired
    private ClaimVoucherMapper claimVoucherMapper;

    @Override
    public int saveClaimVoucherDetail(ClaimVoucherDetail claimVoucherDetail) {
        try {
            claimVoucherDetail.getClaimVoucher().setModifyTime(new Date());
            int result = claimVoucherMapper.updateClaimVoucher(claimVoucherDetail.getClaimVoucher());
            if (result!=1){
                throw new Exception("修改报销单总报销费用出现错误");
            }
            result = claimVoucherDetailMapper.save(claimVoucherDetail);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean deleteClaimVoucherDetail(int id) {
        int row=claimVoucherDetailMapper.deleteClaimVoucherDetailById(id);
        if (row==1){
            return true;
        }
        return false;
    }
}
