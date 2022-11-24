package com.tian.service;

import com.tian.config.MsgSender;
import com.tian.entity.PatientRegister;
import com.tian.entity.PatientSystemMessage;
import com.tian.enums.PayStatusEnum;
import com.tian.enums.StatusEnum;
import com.tian.mapper.PatientRegisterMapper;
import com.tian.util.CommonResult;
import com.tian.util.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author tianwc  公众号：java后端技术全栈、面试专栏
 * @version 1.0.0
 * @date 2022年10月28日 18:26
 * <p>
 * 外网挂号+在线支付
 */
@Slf4j
@Service(version = "1.0.0")
public class OuterPatientRegisterServiceImpl implements OuterPatientRegisterService {

    @Resource
    private PatientRegisterMapper patientRegisterMapper;
    @Resource
    private MsgSender msgSender;

    /**
     *  payNotify  success trade_status=TRADE_SUCCESS out_trade_no=1668263409801
     * @param tradeNo   单号
     * @param payStatus 状态
     */
    @Override
    public CommonResult payNotify(String tradeNo, String payStatus) {
        Integer id = Integer.valueOf(tradeNo.replaceAll(Constant.REGISTER_PAY_ORDER_PRE, ""));
        PatientRegister patientRegister = patientRegisterMapper.selectByPrimaryKey(id);
        if (patientRegister == null) {
            return CommonResult.failed("获取挂号信息失败");
        }
        //根据支付宝返回状态 修改 本地挂号单 付款状态
        PayStatusEnum payStatusEnum = PayStatusEnum.valueOfName(payStatus);
        if (payStatusEnum == null) {
            payStatusEnum = PayStatusEnum.INIT;
        }
        patientRegister.setPayStatus(payStatusEnum.getCode());
        int flag = patientRegisterMapper.updateByPrimaryKey(patientRegister);
        if (flag == 1) {
            //发送MQ消息---站内信
            PatientSystemMessage patientSystemMessage = new PatientSystemMessage();
            patientSystemMessage.setCreateTime(new Date());
            patientSystemMessage.setPatientId(patientRegister.getPatientId());
            patientSystemMessage.setStatus(StatusEnum.normal.getCode());
            // TODO: 2022/11/24 可以把站内信搞成模板形式，这样就可以王丽添加参数
            //比如：尊敬的{}，您好，您的挂号单{}单号已支付完成，请及时前往医院就诊。【xxx医院】
            patientSystemMessage.setSystemMessage("尊敬的张三，您好，您的挂号单20221124000001单号已支付完成，请及时前往医院就诊。【xxx医院】");
            msgSender.sendPaySuccessMsg(patientSystemMessage);
            return CommonResult.success("成功");
        }
        return CommonResult.success("失败");
    }
}