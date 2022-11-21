package com.tian.service;

import com.tian.entity.PatientSystemMessage;

import java.util.List;

/**
 * @author tianwc 公众号：java后端技术全栈、面试专栏
 * @version 1.0.0
 * @date 2022年11月21日 08:36
 */
public interface PatientSystemMessageService {

    List<PatientSystemMessage> selectListByPatientId(Integer patientId);

    int insert(PatientSystemMessage record);

    int updateByPrimaryKey(PatientSystemMessage record);
}
