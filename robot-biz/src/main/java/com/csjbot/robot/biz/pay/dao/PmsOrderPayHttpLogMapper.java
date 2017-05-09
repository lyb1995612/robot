package com.csjbot.robot.biz.pay.dao;

import com.csjbot.robot.biz.pay.model.PmsOrderPayHttpLog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component("httpLogMapper")
@Mapper
public interface PmsOrderPayHttpLogMapper {
    int insert(PmsOrderPayHttpLog record);
}