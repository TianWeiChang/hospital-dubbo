package com.tian.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.tian.config.RedisConfig;
import com.tian.entity.DoctorInfo;
import com.tian.entity.OuterPatientRegister;
import com.tian.entity.PatientSystemMessage;
import com.tian.service.PatientSystemMessageService;
import com.tian.util.RedisPrefixConstant;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tianwc  公众号：java后端技术全栈、面试专栏
 * @version 1.0.0
 * @date 2022年11月21日 08:46
 */
@Controller
public class PatientSystemMessageController {
    @Reference(version = "1.0.0")
    private PatientSystemMessageService patientSystemMessageService;
    @Resource
    private RedisConfig redisConfig;

    @RequestMapping("/patient/msg/list")
    @ResponseBody
    public Object list(HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        OuterPatientRegister outerPatientRegister = JSONObject.parseObject(redisConfig.get(RedisPrefixConstant.SESSION_KEY_PREFIX + sessionId), OuterPatientRegister.class);
        List<PatientSystemMessage> patientSystemMessages=patientSystemMessageService.selectListByPatientId(outerPatientRegister.getId());
        PageInfo<PatientSystemMessage> pageInfo = new PageInfo<>(patientSystemMessages);
        return getTableData(pageInfo);
    }
    private static Map<String, Object> getTableData(PageInfo pageInfo) {
        Map<String, Object> tableData = new HashMap<>(8);
        //这是layui要求返回的json数据格式
        tableData.put("code", 0);
        tableData.put("msg", "");
        //将全部数据的条数作为count传给前台（一共多少条）
        tableData.put("count", pageInfo.getTotal());
        //将分页后的数据返回（每页要显示的数据）
        tableData.put("data", pageInfo.getList());
        return tableData;
    }
}
