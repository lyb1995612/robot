package com.csjbot.robot.admin.controller.pms;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.csjbot.robot.base.page.Page;
import com.csjbot.robot.base.util.StringUtil;
import com.csjbot.robot.base.web.entity.ResultEntity;
import com.csjbot.robot.base.web.entity.ResultEntityHashMapImpl;
import com.csjbot.robot.biz.Constants;
import com.csjbot.robot.biz.pms.model.PmsAdvertisement;
import com.csjbot.robot.biz.pms.service.PmsService;
import com.csjbot.robot.biz.sys.model.SysAttachment;
import com.csjbot.robot.biz.sys.service.SysAttachService;
import com.csjbot.robot.biz.ums.model.User;
import com.csjbot.robot.biz.util.FileUtil;
import com.github.miemiedev.mybatis.paginator.domain.PageList;



  
/**          
 * Description 广告管理
 * @author CJay       
 * @created 2017�?3�?27�? 下午3:28:54    
 */
@Controller
@RequestMapping("adv")
public class AdvertisementController {
	
	private Logger logger = Logger.getLogger(AdvertisementController.class);
	
	@Autowired
	private PmsService pmsService;
	
	@Autowired
	private SysAttachService attachService;
	
	/**
	     * @discription 广告列表
	     * @author CJay       
	     * @created 2017�?3�?28�? 上午10:27:33
	 */
	@RequestMapping(value = "/list")
    public ModelAndView portal() {
        ModelAndView mv = new ModelAndView("pms/advertisement_list");
        return mv;
    }
	
	/**
	     * @discription 广告修改
	     * @author CJay       
	     * @created 2017�?3�?29�? 上午10:50:58
	 */
	@RequestMapping(value = "{id}/toAdvertisementUpdate")
	public ModelAndView toAdvertisementUpdate(@PathVariable String id,PmsAdvertisement pmsAdvertisement,HttpServletRequest request,HttpSession session) {
	    ModelAndView mv = new ModelAndView("pms/advertisement_update");
	    session = request.getSession();
	    String type = pmsAdvertisement.getType()+"";
	    session.setAttribute("type", type);
	    mv = advEvent(mv,id);
	    mv.addObject("editable",1);
	    return mv;
	}
	
	
	public ModelAndView advEvent(ModelAndView mv,String id){
		PmsAdvertisement advertisement = pmsService.selectAdvByPrimaryKey(id);
		mv.addObject("type", advertisement.getType());
		System.out.println(mv.getModelMap().toString());
	    mv.addObject("advertisement",advertisement);
	    mv.addObject("pic_url","/attach/"+advertisement.getId()+"/"+Constants.Attachment.Type.ADVERTISMENT_PIC+"/pic");
	    SysAttachment attach = attachService.getAttachByTransInfo(advertisement.getId(), Constants.Attachment.Type.ADVERTISMENT_AUDIO);
	    if(attach!=null){
	    	mv.addObject("audio_url","/attach/"+advertisement.getId()+"/"+Constants.Attachment.Type.ADVERTISMENT_AUDIO+"/"+attach.getName());
	    	mv.addObject("audio_name",attach.getOriginal_name());
	    }
	    attach = attachService.getAttachByTransInfo(advertisement.getId(), Constants.Attachment.Type.ADVERTISMENT_VEDIO);
	    if(attach!=null){
	    	mv.addObject("vedio_url","/attach/"+advertisement.getId()+"/"+Constants.Attachment.Type.ADVERTISMENT_VEDIO+"/"+attach.getName());
	    	mv.addObject("vedio_name",attach.getOriginal_name());
	    }
		return mv;
	}
	
	/***
	     * @discription 广告详情
	     * @author CJay       
	     * @created 2017�?3�?29�? 上午9:29:12
	 */
	@RequestMapping(value = "{id}/toAdvertisementDetail")
	public ModelAndView toAdvertisementDetail(@PathVariable String id) {
	    ModelAndView mv = new ModelAndView("pms/advertisement_update");
	    mv = advEvent(mv,id);
	    mv.addObject("editable",0);
	    return mv;
	}		
	
	/**
	     * @discription 跳转到广告新增页
	     * @author CJay       
	     * @created 2017�?3�?28�? 上午10:46:43
	 */
	@RequestMapping(value = "/toAdvertisementAdd")
    public ModelAndView toAdvertisementAdd() {
        ModelAndView mv = new ModelAndView("pms/advertisement_add");
        return mv;
    }
	
	/**
	     * @discription 新增广告
	     * @author CJay       
	     * @created 2017�?3�?28�? 上午11:48:59
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> advertiseAdd(PmsAdvertisement pmsAdvertisement,HttpServletRequest request,HttpServletResponse response){
        JSONObject result = new JSONObject();
        int type=Integer.parseInt(request.getParameter("type"));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding("UTF-8");
        FileUtil fileUtil = new FileUtil();
        User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
        String id = StringUtil.createUUID();
        pmsAdvertisement.setId(id);
        pmsAdvertisement.setType(type);
        pmsAdvertisement.setCreator_fk(loginUser.getId());
        pmsAdvertisement.setUpdater_fk(loginUser.getId());
        String msg = "";
        String flag = null;
        SysAttachment attach = new SysAttachment();
        attach.setTransaction_id(id);
        attach.setOwner_fk(loginUser.getId());
        attach.setCreator_fk(loginUser.getId());
        attach.setUpdater_fk(loginUser.getId());
        attach.setSort(0);
        attach.setMemo(pmsAdvertisement.getMemo());
        if (request instanceof MultipartHttpServletRequest) {
        	MultipartFile photo =  ((MultipartHttpServletRequest) request).getFile("photo");
		    if (photo != null) {
		    	flag = fileUtil.uploadAndModifyAttach(attachService,attach,Constants.Attachment.Type.ADVERTISMENT_PIC, photo, Constants.UPLOAD_PATH, Constants.Attachment.Path.ADVERTISMENT_FILE_PATH);
		        if ("error".equals(flag)) {
		        	msg = ResultEntity.KW_STATUS_FAIL;
		        	result.put("msg", msg);
		        	logger.error("Advertisement upload Picture error!");
		            return new ResponseEntity<String>(result.toString() ,headers, HttpStatus.OK);
		        }
		    }
		    MultipartFile audio =  ((MultipartHttpServletRequest) request).getFile("audio");
		    if(audio!=null){
		    	flag = fileUtil.uploadAndModifyAttach(attachService,attach,Constants.Attachment.Type.ADVERTISMENT_AUDIO, audio, Constants.UPLOAD_PATH, Constants.Attachment.Path.ADVERTISMENT_FILE_PATH);
		    	if("error".equals(flag)){
		        	msg = ResultEntity.KW_STATUS_FAIL;
		        	result.put("msg", msg);
		        	logger.error("Advertisement upload Audio error!");
		            return new ResponseEntity<String>(result.toString() ,headers, HttpStatus.OK);
		    	}
		    }
		    MultipartFile vedio =  ((MultipartHttpServletRequest) request).getFile("vedio");
		    if(vedio!=null){
		    	flag = fileUtil.uploadAndModifyAttach(attachService,attach,Constants.Attachment.Type.ADVERTISMENT_VEDIO, vedio, Constants.UPLOAD_PATH, Constants.Attachment.Path.ADVERTISMENT_FILE_PATH);
		    	if("error".equals(flag)){
		        	msg = ResultEntity.KW_STATUS_FAIL;
		        	result.put("msg", msg);
		        	logger.error("Advertisement upload Vedio error!");
		            return new ResponseEntity<String>(result.toString() ,headers, HttpStatus.OK);
		    	}
		    }		    
        }   
        if(pmsService.insertAdvertisement(pmsAdvertisement)){
        	msg = ResultEntity.KW_STATUS_SUCCESS;
        }
        result.put("msg", msg);
        return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}
	
	
/**
     * @discription 修改广告
     * @author CJay       
     * @created 2017�?3�?29�? 下午4:52:52
 */
@RequestMapping(value = "/update", method = RequestMethod.POST)
public ResponseEntity<String> advertiseUpdate(PmsAdvertisement pmsAdvertisement,HttpServletRequest request,HttpServletResponse response){
    System.out.println("update。�?��?��?��?��??");
	JSONObject result = new JSONObject();
	//String typeStr=request.getParameter("type");
    //int type=Integer.parseInt(typeStr);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    response.setCharacterEncoding("UTF-8");
    FileUtil fileUtil = new FileUtil();
    User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
    pmsAdvertisement.setUpdater_fk(loginUser.getId());
    String msg = "";
    String flag = null;
    SysAttachment attach = new SysAttachment();
    attach.setTransaction_id(pmsAdvertisement.getId());
    attach.setUpdater_fk(loginUser.getId());
    attach.setMemo(pmsAdvertisement.getMemo());
    if (request instanceof MultipartHttpServletRequest) {
    	MultipartFile photo =  ((MultipartHttpServletRequest) request).getFile("photo");
	    if (photo != null) {
	    	attachService.deleteByTransInfo(attach.getTransaction_id(),Constants.Attachment.Type.ADVERTISMENT_PIC);
	    	flag = fileUtil.uploadAndModifyAttach(attachService,attach,Constants.Attachment.Type.ADVERTISMENT_PIC, photo, Constants.UPLOAD_PATH, Constants.Attachment.Path.ADVERTISMENT_FILE_PATH);
	        if ("error".equals(flag)) {
	        	msg = ResultEntity.KW_STATUS_FAIL;
	        	result.put("msg", msg);
	        	logger.error("Product upload Picture error!");
	            return new ResponseEntity<String>(result.toString() ,headers, HttpStatus.OK);
	        }
	    }
	    MultipartFile audio =  ((MultipartHttpServletRequest) request).getFile("audio");
	    if(audio!=null){
	    	attachService.deleteByTransInfo(attach.getTransaction_id(),Constants.Attachment.Type.ADVERTISMENT_AUDIO);
	    	flag = fileUtil.uploadAndModifyAttach(attachService,attach,Constants.Attachment.Type.ADVERTISMENT_AUDIO, audio, Constants.UPLOAD_PATH, Constants.Attachment.Path.ADVERTISMENT_FILE_PATH);
	    	if("error".equals(flag)){
	        	msg = ResultEntity.KW_STATUS_FAIL;
	        	result.put("msg", msg);
	        	logger.error("Product upload Audio error!");
	            return new ResponseEntity<String>(result.toString() ,headers, HttpStatus.OK);
	    	}
	    }
	    MultipartFile vedio =  ((MultipartHttpServletRequest) request).getFile("vedio");
	    if(vedio!=null){
	    	attachService.deleteByTransInfo(attach.getTransaction_id(),Constants.Attachment.Type.ADVERTISMENT_VEDIO);
	    	flag = fileUtil.uploadAndModifyAttach(attachService,attach,Constants.Attachment.Type.ADVERTISMENT_VEDIO, vedio, Constants.UPLOAD_PATH, Constants.Attachment.Path.ADVERTISMENT_FILE_PATH);
	    	if("error".equals(flag)){
	        	msg = ResultEntity.KW_STATUS_FAIL;
	        	result.put("msg", msg);
	        	logger.error("Product upload Vedio error!");
	            return new ResponseEntity<String>(result.toString() ,headers, HttpStatus.OK);
	    	}
	    }		    
    }   
    if(pmsService.updateAdvertisement(pmsAdvertisement)){
    	msg = ResultEntity.KW_STATUS_SUCCESS;
    }
    result.put("msg", msg);
    return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
}	
	
	/**
	     * @discription 广告删除 
	     * @author CJay       
	     * @created 2017�?3�?28�? 下午7:43:22
	 */
	@RequestMapping(value = "{id}/advertisementDelete")
	public ResponseEntity<String> AdvertisementDelete(@PathVariable String id,HttpServletResponse response){
		JSONObject result = new JSONObject();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    response.setCharacterEncoding("UTF-8");
	    String msg = ResultEntity.KW_STATUS_SUCCESS;
	    try {
	    	attachService.deleteByTransInfo(id.trim(),Constants.Attachment.Type.ADVERTISMENT_PIC);
	    	attachService.deleteByTransInfo(id.trim(), Constants.Attachment.Type.ADVERTISMENT_AUDIO);
	    	attachService.deleteByTransInfo(id.trim(), Constants.Attachment.Type.ADVERTISMENT_VEDIO);
		    pmsService.deleteAdvByPrimaryKey(id);
		} catch (Exception e) {
			msg=e.getMessage();
		}
	    result.put("msg", msg);
	    return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}
	
   @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ResponseEntity<ResultEntity> page(HttpServletRequest request, HttpServletResponse response) {
        ResultEntity result = null;
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            int length = Integer.valueOf(request.getParameter("length"));
            int start = Integer.valueOf(request.getParameter("start"));
            String orderColIndex = request.getParameter("order[0][column]");
            String dir = request.getParameter("order[0][dir]");
            String orderName = request.getParameter("columns[" + orderColIndex + "][data]");
            String name = request.getParameter("name");
            if (StringUtil.notEmpty(name)) {
                params.put("name", name);
            }
            String sortString = null;
            if (orderName != null && !"".equals(orderName) && dir != null && !"".equals(dir)) {
                sortString = orderName + "." + dir;
            }
            Page<Map<String, Object>> pageMap = pmsService.advPage(params, (start / length) + 1, length, sortString);
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "search success");
            if (pageMap.getRows() != null && pageMap.getRows().size() > 0) {
                result.addObject("data", pageMap.getRows());
                result.addObject("recordsFiltered", pageMap.getTotal());
                result.addObject("recordsTotal", pageMap.getTotal());
            } else {
                result.addObject("data", null);
                result.addObject("recordsFiltered", 0);
                result.addObject("recordsTotal", 0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_FAIL, "Internal Server Error!");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding("UTF-8");
        return new ResponseEntity<ResultEntity>(result, headers, HttpStatus.OK);
    }	
}
