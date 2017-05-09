package com.csjbot.robot.biz.pay.service;

import com.csjbot.robot.biz.pay.dao.PmsPayDetailWxMapper;
import com.csjbot.robot.biz.pay.dao.PmsRefundDetailWxMapper;
import com.csjbot.robot.biz.pay.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("wxPayDBService")
public class WxPayDBServiceImpl extends OrderPayDBServiceImpl implements WxPayDBService {

    @Autowired
    private PmsPayDetailWxMapper wxPayMapper;
    @Autowired
    private PmsRefundDetailWxMapper wxRefundMapper;

    public WxPayDBServiceImpl() {
    }

    @Override
    public int newWxPayRecord(PmsPayDetailWx record) {
        return wxPayMapper.insert(record);
    }

    @Override
    public int updateWxPayRecord(PmsPayDetailWx record) {
        return wxPayMapper.update(record);
    }

    @Override
    public PmsPayDetailWx getWxPayRecord(String orderId) {
        return wxPayMapper.get(orderId);
    }

    @Override
    public boolean wxPayRecordExists(String orderId) {
        return wxPayMapper.exists(orderId);
    }

    @Override
    public int newWxRefundRecord(PmsRefundDetailWx record) {
        return wxRefundMapper.insert(record);
    }

    // @Override
    // public int updateWxRefundRecord(PmsRefundDetailWx record) {
    //     return wxRefundMapper.update(record);
    // }
    //
    // @Override
    // public PmsRefundDetailWx getWxRefundRecord(String refundNo) {
    //     return wxRefundMapper.get(refundNo);
    // }

}
