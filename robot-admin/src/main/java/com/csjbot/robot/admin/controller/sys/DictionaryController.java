package com.csjbot.robot.admin.controller.sys;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.csjbot.robot.biz.base.util.StringUtil;
import com.csjbot.robot.biz.base.entity.ResultEntity;
import com.csjbot.robot.biz.base.entity.ResultEntityHashMapImpl;
import com.csjbot.robot.biz.sys.model.SysData;
import com.csjbot.robot.biz.sys.model.SysDataDictionary;
import com.csjbot.robot.biz.sys.model.param.DictionaryParam;
import com.csjbot.robot.biz.sys.service.DictionaryService;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


/**
 * 
 * @author DCJ
 */
@Controller
@RequestMapping(value="/dic")
public class DictionaryController {
	
	@Autowired
    private DictionaryService dicService;
	
	/**
	 * 数据字典父类
	 * @return
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(){
		ModelAndView mav = new ModelAndView("/sys/dic_father_list");
		return mav;
	}
	
    /**
     * 获取数据字典有效父类
     * @param param
     * @param request
     * @param builder
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<ResultEntity> search(@RequestBody DictionaryParam param, HttpServletRequest request, UriComponentsBuilder builder) {
        ResultEntity result = null;
        try {
            PageList<SysData> list = new PageList<SysData>();
            list = dicService.page( param);
            if (list != null) {
                result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "success");
                result.addObject("list", list);
                result.addObject("totalSize", list.size());
            } else {
                result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "search fail!");
            }

        } catch (Exception e) {
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "Internal Server Error!");
            e.printStackTrace();
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/dic/search").buildAndExpand().toUri());
        return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
    }
    
    /**
     * 字典数据
     */
    
    @RequestMapping(value = "/{id}/sonlist")
	public ModelAndView sonlist(@PathVariable String id){
		ModelAndView mv = new ModelAndView("/sys/dic_son_list");
		SysData sysdata = dicService.findSysDataById(Integer.parseInt(id));
		mv.addObject("sysdata", sysdata);
		return mv;
	}
    
    /**
     * 单笔数据
     */ 
    @RequestMapping(value = "/son/search/{id}", method = RequestMethod.POST)
    public ResponseEntity<ResultEntity> sonsearch(@PathVariable String id, @RequestBody DictionaryParam param, HttpServletRequest request, UriComponentsBuilder builder) {
        ResultEntity result = null;
        try {
        	param.setData_fk(id);
            PageList<SysDataDictionary> list = new PageList<SysDataDictionary>();
            list = dicService.sonpage( param);
            if (list != null) {
                result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "success");
                result.addObject("list", list);
                result.addObject("totalSize", list.size());
            } else {
                result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "search fail!");
            }

        } catch (Exception e) {
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "Internal Server Error!");
            e.printStackTrace();
        }
        
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/son/search/"+id).buildAndExpand().toUri());
        return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
    }
    
    
    
    /**
     * 新建数据
     */
    @RequestMapping(value = "/{id}/soncreate")
    public ModelAndView create(@PathVariable String id, HttpServletRequest request) throws IOException {
        ModelAndView mv = new ModelAndView("sys/dic_son");
        SysData sysdata = dicService.findSysDataById(Integer.parseInt(id));
        SysDataDictionary dicdata = new SysDataDictionary();
        dicdata.setValid((byte) 1);
        mv.addObject("sysdata", sysdata);
        mv.addObject("dicdata", dicdata);
        mv.addObject("action", "SAVE");
        return mv;
    }
    
    /**
     * 编辑数据
     */
    @RequestMapping(value = "/{id}/sonedit")
    public ModelAndView edit(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws IOException {
    	SysDataDictionary dicdata = new SysDataDictionary();
        ModelAndView mv = new ModelAndView("sys/dic_son");
        if (StringUtil.notEmpty(id)) {
        	dicdata = dicService.findSysDataDicById(Integer.parseInt(id));
            if (dicdata == null) {
                response.sendError(HttpStatus.NOT_FOUND.value());
                return null;
            }
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value());
            return null;
        }
        SysData sysdata = dicService.findSysDataById(dicdata.getDataFk());
        mv.addObject("sysdata", sysdata);
        mv.addObject("dicdata", dicdata);
        mv.addObject("action", "EDIT");
        return mv;
    }
    
    
    /**
     * insert
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<ResultEntity> save(SysDataDictionary dicdata, HttpServletRequest request, UriComponentsBuilder builder) {
        ResultEntity result = null;
        int flag = dicService.dicDataInsert(dicdata);
        if(flag>0){
        	result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "新增数据成功");
        }else{
        	result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "新增数据失败");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/dic/save").buildAndExpand().toUri());
        return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
    }
    
    
    /**
     * update
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<ResultEntity> edit(SysDataDictionary dicdata, HttpServletRequest request, UriComponentsBuilder builder) {
        ResultEntity result = null;
        int flag = dicService.dicDataUpdate(dicdata);
        if(flag>0){
        	result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "数据更新成功");
        }else{
        	result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "数据更新失败");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/dic/edit").buildAndExpand().toUri());
        return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
    }
	
	
	
}
