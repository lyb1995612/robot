package com.csjbot.robot.api.scs.controller;

import com.alibaba.fastjson.JSONObject;

import com.csjbot.robot.biz.scs.service.ScsService;
import com.csjbot.robot.biz.util.FileZipUtil;
import com.csjbot.robot.biz.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by Zhangyangyang on 2017/4/13.
 */
@Controller
public class ScsDishController {
    @Autowired
    private ScsService scsService;

    // 查询所有菜品信息
    @RequestMapping(value = "/scs/findAllDishInfo", method = RequestMethod.GET)
    @ResponseBody
    public void findAllDishInfo(HttpServletResponse response, HttpServletRequest request)
            throws IOException {
        ResponseUtil.write(response,scsService.findAllDishInfo(request));
    }
    
    // 按SN查询所有菜品信息
    @RequestMapping(value = "/scs/showDishInfoBySn", method = RequestMethod.GET)
    @ResponseBody
    public void showDishInfoBySn(HttpServletResponse response, HttpServletRequest request)
            throws IOException {
        //ResponseUtil.write(response,scsService.findAllDishInfo(request));
        ResponseUtil.write(response, scsService.findDishInfoBySn(request));
    }
    
    //按SN查询桌位
    @RequestMapping(value = "/scs/showDeskInfoBySn", method = RequestMethod.GET)
    @ResponseBody
    public void showDeskInfoBySN(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //int type = Integer.valueOf(request.getParameter("type"));
        ResponseUtil.write(response, scsService.findDeskInfoBySn(request));
       // ResponseUtil.write(response,scsService.showAllDeskInfo(type));
    }
    
  
    

  //按SN删除桌位
    @RequestMapping(value = "/scs/deleteDeskInfoBysn", method = RequestMethod.POST)
    @ResponseBody
    public void deleteDeskInfoBySn(@RequestBody JSONObject data, HttpServletResponse response)
            throws IOException {
        String sn=data.get("sn").toString();
        ResponseUtil.write(response,scsService.deleteDeskInfoBySn(sn));
    }

    //查询所有菜品类型信息
    @RequestMapping(value = "/scs/findAllDishTypeInfo", method = RequestMethod.GET)
    @ResponseBody
    public void findAllDishTypeInfo( HttpServletResponse response,HttpServletRequest request)
            throws IOException {
        ResponseUtil.write(response,scsService.findAllDishType(request));
    }

    
    //获得送餐附件信息
   /* @RequestMapping(value = "/scs/showDishAccessoryBySn", method = RequestMethod.GET)
    @ResponseBody
    public void showDishAccessoryBySn(HttpServletResponse response, HttpServletRequest request)
            throws IOException {
        ResponseUtil.write(response,scsService.findScsAccessoryBySn(request));
    }*/
    
    
    //获得送餐附件信息
    @RequestMapping(value = "/scs/showDishAccessory", method = RequestMethod.GET)
    @ResponseBody
    public void showDishAccessory(HttpServletResponse response, HttpServletRequest request)
            throws IOException {
        ResponseUtil.write(response,scsService.showAccessoryS(request));
    }
    //添加桌位
    @RequestMapping(value = "/scs/addDeskInfo", method = RequestMethod.POST)
    @ResponseBody
    public void addDeskInfo(@RequestBody JSONObject data, HttpServletResponse response)
            throws IOException {
        ResponseUtil.write(response,scsService.addDeskInfo(data));
    }
    
    
    
    
    //删除桌位
    @RequestMapping(value = "/scs/deleteDeskInfo", method = RequestMethod.POST)
    @ResponseBody
    public void deleteDeskInfo(@RequestBody JSONObject data, HttpServletResponse response)
            throws IOException {
        ResponseUtil.write(response,scsService.deleteDeskInfo(data));
    }
    //查询所有桌位
    @RequestMapping(value = "/scs/showAllDeskInfo", method = RequestMethod.GET)
    @ResponseBody
    public void showAllDeskInfo(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int type = Integer.valueOf(request.getParameter("type"));
        ResponseUtil.write(response,scsService.showAllDeskInfo(type));
    }
    
 

    //下载文件接口
    @RequestMapping(value = "/scs/downFile", method = RequestMethod.GET)
    @ResponseBody
    public void downFile(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        File file = new File(request.getParameter("filePath")); //要下载的文件绝对路径
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
