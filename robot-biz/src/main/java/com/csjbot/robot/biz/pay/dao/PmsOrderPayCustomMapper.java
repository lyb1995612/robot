package com.csjbot.robot.biz.pay.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

// todo
@Component("customMapper")
@Mapper
public interface PmsOrderPayCustomMapper {
    List<Map<String, String>> getAccount();

    Integer getUnitPrice(String itemId);


}
