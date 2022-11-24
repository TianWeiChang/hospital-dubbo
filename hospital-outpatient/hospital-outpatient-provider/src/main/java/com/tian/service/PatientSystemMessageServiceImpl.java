package com.tian.service;

import com.tian.entity.PatientSystemMessage;
import com.tian.mapper.PatientSystemMessageMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tianwc  公众号：java后端技术全栈、面试专栏
 * @version 1.0.0
 * @date 2022年11月21日 08:38
 * <p>
 * 患者 站内信
 */
@Slf4j
@Service(version = "1.0.0", retries = 0)
public class PatientSystemMessageServiceImpl implements PatientSystemMessageService {
    @Resource
    private PatientSystemMessageMapper patientSystemMessageMapper;

    @Override
    public List<PatientSystemMessage> selectListByPatientId(Integer patientId) {
        return patientSystemMessageMapper.selectListByPatientId(patientId);
    }

    @Override
    public int insert(PatientSystemMessage record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(PatientSystemMessage record) {
        return 0;
    }
}
