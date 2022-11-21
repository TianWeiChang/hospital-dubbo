package com.tian.controller;

import com.tian.config.RedisConfig;
import com.tian.dto.OuterPatientLoginReq;
import com.tian.entity.OuterPatientRegister;
import com.tian.enums.ResultCode;
import com.tian.service.OuterPatientLoginService;
import com.tian.util.CommonResult;
import com.tian.util.RedisPrefixConstant;
import com.tian.util.StringUtil;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author tianwc  公众号：java后端技术全栈、面试专栏
 * @version 1.0.0
 * @date 2022年10月28日 10:27
 * <p>
 * 外网访问（登录、挂号）
 */
@Controller
public class OuterPatientLoginController {

    @Reference(version = "1.0.0")
    private OuterPatientLoginService outerPatientLoginService;
    @Resource
    private RedisConfig redisConfig;

    /**
     * 发送短信验证码
     */
    @PostMapping("/send")
    @ResponseBody
    public CommonResult sendMsg(@RequestBody OuterPatientLoginReq outerPatientLoginReq) {
        if (StringUtil.isEmpty(outerPatientLoginReq.getPhone())) {
            return CommonResult.failed(ResultCode.VALIDATE_FAILED);
        }
        return outerPatientLoginService.sendCode(outerPatientLoginReq);
    }

    /**
     * 患者使用使用手机号码+验证码 登录
     * 返回 token 后期的请求必须带着 token
     */
    @PostMapping("/login")
    public String login(Model model, OuterPatientLoginReq outerPatientLoginReq, HttpServletRequest request) {

        if (StringUtil.isEmpty(outerPatientLoginReq.getCode())) {
            model.addAttribute("msg", "验证码为空!");
            return "view/login";
        }
        if (StringUtil.isEmpty(outerPatientLoginReq.getPhone())) {
            model.addAttribute("msg", "手机号为空!");
            return "view/login";
        }

        CommonResult result = outerPatientLoginService.login(outerPatientLoginReq);
        //跳转首页
        if (result.getCode() == ResultCode.SUCCESS.getCode()) {
            String sessionId = request.getSession().getId();
            redisConfig.add(RedisPrefixConstant.SESSION_KEY_PREFIX + sessionId, (OuterPatientRegister) result.getData());
            return "view/index";
        }
        return "view/login";
    }


    @RequestMapping("/init/patient/msg")
    public String init() {
        return "view/patient_msg";
    }

    @RequestMapping("/patient/register")
    public String initRegister() {
        return "view/register";
    }
}
