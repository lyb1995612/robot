package com.csjbot.robot.biz.pay.dao;
import com.csjbot.robot.biz.pay.model.PmsRefundDetailWx;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component("wxRefundMapper")
@Mapper
public interface PmsRefundDetailWxMapper {
    int insert(PmsRefundDetailWx reord);

    int update(PmsRefundDetailWx record);

    PmsRefundDetailWx get(String refundNo); // todo

    // Integer sum(String orderId);
}