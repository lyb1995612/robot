package com.csjbot.robot.api.pay.service;

import com.csjbot.robot.api.pay.model.WxClientRequest;
import com.csjbot.robot.api.pay.model.WxPayDataWrapper;

import java.util.Map;

/** 微信支付XML消息生成校验服务 */
public interface WxPayDataService {
    /**
     * 检查微信返回消息的签名
     *
     * @param params 从微信返回消息解析出来的键值对
     * @return boolean true-校验通过 false-校验失败
     */
    boolean checkSign(Map<String, String> params);

    boolean checkSign(Map<String, String> params, String sign);

    /**
     * 生成微信支付请求的签名值
     *
     * @param params 请求参数键值对
     * @return 签名字串
     */
    String computeSign(Map<String, String> params);

    WxPayDataWrapper buildOrderData(WxClientRequest request);

    WxPayDataWrapper buildCloseData(String orderId);

    WxPayDataWrapper buildQueryData(String orderId);

    WxPayDataWrapper buildRefundData(String orderId, Integer refundFee);

    WxPayDataWrapper buildRefundQueryData(String refundNo); // todo
}
