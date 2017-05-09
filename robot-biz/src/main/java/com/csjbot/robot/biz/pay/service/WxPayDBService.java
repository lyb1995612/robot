package com.csjbot.robot.biz.pay.service;

import com.csjbot.robot.biz.pay.model.PmsPayDetailWx;
import com.csjbot.robot.biz.pay.model.PmsRefundDetailWx;

public interface WxPayDBService extends OrderPayDBService {
    int newWxPayRecord(PmsPayDetailWx record);

    int updateWxPayRecord(PmsPayDetailWx record);

    PmsPayDetailWx getWxPayRecord(String orderId);

    boolean wxPayRecordExists(String orderId);

    int newWxRefundRecord(PmsRefundDetailWx record);

    // int updateWxRefundRecord(PmsRefundDetailWx record);

    // PmsRefundDetailWx getWxRefundRecord(String refundNo);


}
