/**
 *
 */
package com.csjbot.robot.api.pms.controller;

import com.alibaba.fastjson.JSONObject;

import com.csjbot.robot.biz.pms.service.ProductServiceDAO;
import com.csjbot.robot.biz.util.FileZipUtil;
import com.csjbot.robot.biz.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.ParseException;

/**
 * @author 作者：Zhangyangyang
 * @version 创建时间：2017年3月21日 上午10:00:35 类说明
 */
@Controller
public class ProductControllers {
    @Autowired
    private ProductServiceDAO productServiceDAO;

	// 登录
	@RequestMapping(value = "/pdt/login", method = RequestMethod.POST)
	@ResponseBody
	public void login(@RequestBody JSONObject data, HttpServletResponse response, HttpServletRequest request) {
		System.out.println(System.getProperty("file.encoding"));
		ResponseUtil.write(response, productServiceDAO.login(data.getString("account"), data.getString("password")));
	}

	// 获得产品信息
	@RequestMapping(value = "/pdt/getRobotProductInfo", method = RequestMethod.GET)
	@ResponseBody
	public void getRobotProductInfo(HttpServletRequest request, HttpServletResponse response) {
		if (judgeHead(request)) {
			ResponseUtil.write(response, productServiceDAO.getProductInfo(request));
		} else {
			ResponseUtil.backErrorInfo(response, "请求授权失败！");
		}

    }

    // 获得广告信息
    @RequestMapping(value = "/pdt/getADInfo", method = RequestMethod.GET)
    @ResponseBody
    public void getADInfo(HttpServletRequest request, HttpServletResponse response) {
        if (judgeHead(request)) {
            ResponseUtil.write(response, productServiceDAO.getADInfo(request));
        } else {
            ResponseUtil.backErrorInfo(response, "请求授权失败！");
        }

    }


	// 文件下载
	@RequestMapping(value = "/pdt/downFile", method = RequestMethod.POST)
	@ResponseBody
	public void downFile(@RequestBody JSONObject data, HttpServletRequest request, HttpServletResponse response) {
		if (judgeHead(request)) {
			ResponseUtil.write(response, productServiceDAO.downFile(data.getString("fileName")));
		} else {
			ResponseUtil.backErrorInfo(response, "请求授权失败！");
		}
	}

	// 验证http 头内容
	public boolean judgeHead(HttpServletRequest request) {
		String key = request.getHeader("key").toString();
		String time = request.getHeader("time").toString();
		String sign = request.getHeader("sign").toString();
		boolean flag = productServiceDAO.judegHttpHeader(key, time, sign);
		return flag;
	}
	//添加订单
	@RequestMapping(value = "/pdt/addOrder", method = RequestMethod.POST)
	@ResponseBody
	public void addOrder(@RequestBody JSONObject data, HttpServletRequest request, HttpServletResponse response) throws ParseException, ParseException {
		ResponseUtil.write(response, productServiceDAO.addOrder(data));
	}

	//根据order_id查询订单
	@RequestMapping(value = "/pdt/showOrderInfo", method = RequestMethod.GET)
	@ResponseBody
	public void showOrderInfo(HttpServletRequest request, HttpServletResponse response) {
		String order_id = request.getParameter("orderid");
		ResponseUtil.write(response, productServiceDAO.showOrderInfo(order_id));
	}
	//跳转路径
	@RequestMapping(value = "/pdt/redirectPay", method = RequestMethod.GET)
	@ResponseBody
	public void redirectPay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		String url = productServiceDAO.findRedirectUrl(key);
		response.sendRedirect(url);
	}

	//下载文件接口
	@RequestMapping(value = "/pdt/downFile", method = RequestMethod.GET)
	@ResponseBody
	public void downFile(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		File file = new File(request.getParameter("Url")); //要下载的文件绝对路径
		FileZipUtil.assignPermission(file);
		InputStream ins = new BufferedInputStream(new FileInputStream(request.getParameter("filePath")));
		byte [] buffer = new byte[ins.available()];
		ins.read(buffer);
		ins.close();
		response.reset();
		response.addHeader("Content-Disposition", "attachment;filename=" + new String(request.getParameter("fileName").getBytes()));
		response.addHeader("Content-Length", "" + file.length());
		OutputStream ous = new BufferedOutputStream(response.getOutputStream());
		response.setContentType("application/octet-stream");
		ous.write(buffer);
		ous.flush();
		ous.close();
	}

}

