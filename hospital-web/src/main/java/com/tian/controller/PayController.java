package com.tian.controller;

import com.tian.service.OuterPatientRegisterService;
import com.tian.util.CommonResult;
import com.tian.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tianwc  公众号：java后端技术全栈、面试专栏
 * @version 1.0.0
 * @date 2022年11月24日 09:33
 */
@Slf4j
@Controller
@RequestMapping
public class PayController {

    @Reference(version = "1.0.0")
    private OuterPatientRegisterService outerPatientRegisterService;

    /**
     *  支付结果通知类型 我们项目只有预支付单支护结构回调通知，所以，可以不管这个notify_type字段
     *
     *  注意：真实环境这里需要验签哈 根据 sign_type和sign
     *
     */
    @PostMapping("/callback")
    @ResponseBody
    public CommonResult callback(HttpServletRequest request) {

        String tradeStatus = request.getParameter("trade_status");
        String tradeNo = request.getParameter("out_trade_no");
        if (StringUtil.isEmpty(tradeStatus) || StringUtil.isEmpty(tradeNo)) {
            log.error("支付宝回调 参数为空");
        }
        log.info("notify parameters： trade_status={} out_trade_no={}", tradeStatus, tradeNo);
        return outerPatientRegisterService.payNotify(tradeNo, tradeStatus);
    }
}
