package com.csjbot.robot.biz.pay.dao;

import com.csjbot.robot.biz.pay.model.PmsOrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("itemMapper")
@Mapper
public interface PmsOrderItemMapper {
    int insertList(List<PmsOrderItem> items);
}
