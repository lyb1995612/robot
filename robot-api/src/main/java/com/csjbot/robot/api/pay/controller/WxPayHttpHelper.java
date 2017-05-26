package com.csjbot.robot.api.pay.controller;

import com.csjbot.robot.api.pay.model.WxClientRequest;
import com.csjbot.robot.api.pay.model.WxClientResponse;
import com.csjbot.robot.api.pay.service.MediaTypeParser;
import com.csjbot.robot.biz.pay.model.OrderStatus;
import com.csjbot.robot.biz.pay.model.PayStatus;
import com.csjbot.robot.biz.pay.model.ReStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;
import java.util.Map;

import static com.csjbot.robot.biz.pay.util.WxPayParamName.K_RETURN_CODE;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.*;

/** 封装HTTP请求和消息序列化、反序列化相关帮助操作 */
public class WxPayHttpHelper {
    @Autowired
    @Qualifier("jsonParser")
    private MediaTypeParser jsonParser;
    @Autowired
    @Qualifier("xmlParser")
    private MediaTypeParser xmlParser;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RestTemplate restTemplateWithCert;

    private String doPost(RestTemplate template, URI url, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(TEXT_XML);
        RequestEntity<String> req = new RequestEntity<>(body, headers, HttpMethod.POST, url);
        return template.postForObject(url, req, String.class);
    }

    String sendWxPost(URI url, String reqXml) {
        return doPost(restTemplate, url, reqXml);
    }

    String sendWxPostWithCert(URI url, String reqXml) {
        return doPost(restTemplateWithCert, url, reqXml);
    }

    Map<String, String> deserializeWxXml(String body) {
        return (body == null) ? null : xmlParser.deserializeToMap(body);
    }

    String serializeWxXml(Map<String, String> params) {
        return xmlParser.serialize(params, "xml");
    }

    WxClientRequest deserializeClientJson(String json) {
        return (json == null) ? null : jsonParser.deserialize(json, WxClientRequest.class);
    }


    private static final Map<String, String> CALLBACK_OK = Collections.singletonMap(K_RETURN_CODE, ReStatus.SUCCESS.name());
    private static final Map<String, String> CALLBACK_FAIL = Collections.singletonMap(K_RETURN_CODE, ReStatus.FAIL.name());

    ResponseEntity<String> wxCallbackOkResponse() {
        return wxCallbackResponse(CALLBACK_OK);
    }

    ResponseEntity<String> wxCallbackFailResponse() {
        return wxCallbackResponse(CALLBACK_FAIL);
    }

    ResponseEntity<String> wxCallbackResponse(Map<String, String> map) {
        return textResponse(OK, TEXT_XML, serializeWxXml(map));
    }

    ResponseEntity<String> clientOkResponse(WxClientResponse res) {
        return jsonResponse(OK, res);
    }

    ResponseEntity<String> clientErrResponse(HttpStatus status, String errMsg) {
        return clientErrResponse(status, null, null, errMsg, null);
    }

    ResponseEntity<String> clientErrResponse(HttpStatus status, String orderId, String errMsg, String reqId) {
        return clientErrResponse(status, orderId, null, null, null, errMsg, reqId);
    }

    ResponseEntity<String> clientErrResponse(HttpStatus status, String pseudoNo,
                                             OrderStatus orderStatus, String errMsg, String id) {
        return clientErrResponse(status, null, pseudoNo, orderStatus, null, errMsg, id);
    }

    ResponseEntity<String> clientErrResponse(HttpStatus status, String orderId, String pseudoNo,
                                             OrderStatus orderStatus, PayStatus payStatus,
                                             String errMsg, String id) {
        return clientErrResponse(status, orderId, pseudoNo, orderStatus, payStatus,
            String.valueOf(status.value()), status.getReasonPhrase(), errMsg, id);
    }

    ResponseEntity<String> clientErrResponse(HttpStatus status, String orderId, String pseudoNo,
                                             OrderStatus orderStatus, PayStatus payStatus,
                                             String errCode, String errCodeDesc,
                                             String errMsg, String id) {
        WxClientResponse res = new WxClientResponse();
        res.setOrderId(orderId);
        res.setOrderPseudoNo(pseudoNo);
        res.setOrderStatus(orderStatus);
        res.setPayStatus(payStatus);
        res.setErrCode(errCode);
        res.setErrDesc(errCodeDesc);
        res.setRemark(errMsg);
        res.setId(id);
        return clientErrResponse(status, res);
    }

    ResponseEntity<String> clientErrResponse(HttpStatus status, WxClientResponse res) {
        return jsonResponse(status, res);
    }

    ResponseEntity<String> jsonResponse(HttpStatus status, Object obj) {
        return textResponse(status, APPLICATION_JSON, jsonParser.serialize(obj));
    }

    ResponseEntity<String> xmlResponse(HttpStatus status, Object obj) {
        return textResponse(status, APPLICATION_XML, xmlParser.serialize(obj));
    }

    ResponseEntity<String> textResponse(HttpStatus status, MediaType mediaType, String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        return new ResponseEntity<>(body, headers, status);
    }

}
