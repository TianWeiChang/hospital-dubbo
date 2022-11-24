package com.tian.service;

import com.tian.util.CommonResult;

/**
 * @author tianwc 公众号：java后端技术全栈、面试专栏
 * @version 1.0.0
 * @date 2022年10月28日 18:25
 * <p>
 * 外网挂号
 */
public interface OuterPatientRegisterService {

    /**
     * 支付成功 回调
     *
     * @param tradeNo   单号
     * @param payStatus 状态
     * @return 更新支付状态成功
     */
    CommonResult payNotify(String tradeNo, String payStatus);
}
