package cn.jboa.controller;

import cn.jboa.entity.ClaimVoucherDetail;
import cn.jboa.service.ClaimVoucherDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 处理有关报销单宝箱项目的请求
 */
@Controller
@RequestMapping("/claimVoucherDetail")
public class ClaimVoucherDetailController {

    @Autowired
    private ClaimVoucherDetailService claimVoucherDetailService;

    @RequestMapping(value = "/saveClaimVoucherDetail")
    @ResponseBody
    public int saveClaimVoucherDetail(@RequestBody ClaimVoucherDetail claimVoucherDetail){
        int result = claimVoucherDetailService.saveClaimVoucherDetail(claimVoucherDetail);
        if (result==1){
           return claimVoucherDetail.getId();
        }
        return 0;
    }

    @RequestMapping(value = "/deleteClaimVoucherDetail/{id}")
    @ResponseBody
    public boolean deleteClaimVoucherDetail(@PathVariable("id") int id){
        boolean flag=claimVoucherDetailService.deleteClaimVoucherDetail(id);
        return flag;
    }
}
