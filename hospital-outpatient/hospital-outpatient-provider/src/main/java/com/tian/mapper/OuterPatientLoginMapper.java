package com.tian.mapper;

import com.tian.entity.OuterPatientRegister;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OuterPatientLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OuterPatientRegister record);

    OuterPatientRegister selectByPrimaryKey(Integer id);

    OuterPatientRegister selectByPhone(String phone);

    List<OuterPatientRegister> selectAll();

    int updateByPrimaryKey(OuterPatientRegister record);
}