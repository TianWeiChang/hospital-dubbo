package com.tian.service;

import com.tian.config.RedisConfig;
import com.tian.dto.OuterPatientLoginReq;
import com.tian.entity.OuterPatientRegister;
import com.tian.entity.UserRole;
import com.tian.mapper.OuterPatientLoginMapper;
import com.tian.util.CommonResult;
import com.tian.util.CreateRandomCode;
import com.tian.util.RedisPrefixConstant;
import com.tian.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author tianwc  公众号：java后端技术全栈、面试专栏
 * @version 1.0.0
 * @date 2022年10月28日 11:03
 * 外部系统挂号
 */
@Slf4j
@Service(version = "1.0.0")
public class OuterPatientLoginServiceImpl implements OuterPatientLoginService {

    @Resource
    private OuterPatientLoginMapper outerPatientLoginMapper;
    @Resource
    private RedisConfig redisConfig;

    @Reference(version = "1.0.0", retries = 0)
    private UserService userService;

    @Transactional
    @Override
    public CommonResult login(OuterPatientLoginReq outerPatientLoginReq) {
        String code = redisConfig.get(RedisPrefixConstant.SEND_CODE_KEY_PREFIX + outerPatientLoginReq.getPhone());
        if (!outerPatientLoginReq.getCode().equals(code)) {
            return CommonResult.failed("登录失败，验证码错误");
        }
        OuterPatientRegister outerPatientRegister = outerPatientLoginMapper.selectByPhone(outerPatientLoginReq.getPhone());

        if (outerPatientRegister == null) {
            outerPatientRegister = new OuterPatientRegister();
            outerPatientRegister.setPhone(outerPatientLoginReq.getPhone());
            outerPatientRegister.setCreateTime(new Date());
            outerPatientLoginMapper.insert(outerPatientRegister);
        }

        //授权
        UserRole userRole = new UserRole();
        //固定患者登录角色类型
        userRole.setIds(new Integer[]{6});
        userRole.setUserid(outerPatientRegister.getId());
        userService.saveUserRole(userRole);

        String token = StringUtil.getToken(RedisPrefixConstant.OUTER_LOGIN_KEY_PREFIX);
        redisConfig.addString(RedisPrefixConstant.OUTER_LOGIN_KEY_PREFIX + outerPatientLoginReq.getPhone(), token);
        redisConfig.add(token, outerPatientRegister);
        return CommonResult.success(outerPatientRegister);
    }

    /**
     * 实际场景中  还会套用一个模板，把code放到模板中，然后，用第三方发短信的机构发送短信
     *
     * @param outerPatientLoginReq 手机号、验证码
     * @return 短信验证码
     */
    @Override
    public CommonResult<String> sendCode(OuterPatientLoginReq outerPatientLoginReq) {
        String code = CreateRandomCode.generateCode();
        log.info("手机号：{}  验证码：{}", outerPatientLoginReq.getPhone(), code);
        //验证码 120秒  两分钟有效
        redisConfig.addString(RedisPrefixConstant.SEND_CODE_KEY_PREFIX + outerPatientLoginReq.getPhone(), code, 120, TimeUnit.SECONDS);
        return CommonResult.success("发送成功");
    }

}
