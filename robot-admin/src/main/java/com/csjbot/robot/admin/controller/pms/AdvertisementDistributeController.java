package com.csjbot.robot.admin.controller.pms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.csjbot.robot.base.util.StringUtil;
import com.csjbot.robot.base.web.entity.ResultEntity;
import com.csjbot.robot.base.web.entity.ResultEntityHashMapImpl;
import com.csjbot.robot.biz.Constants;
import com.csjbot.robot.biz.cms.model.CmsRobot;
import com.csjbot.robot.biz.cms.service.CmsRobotService;
import com.csjbot.robot.biz.pms.model.PmsAdvertisementDistribute;
import com.csjbot.robot.biz.pms.model.Pms_advertisement;
import com.csjbot.robot.biz.pms.service.PmsService;
import com.csjbot.robot.biz.ums.model.User;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

/**          
 * Description: 广告分配
 * @author lyb       
 * @created 2017-05-10 下午16:45:27    
 */
@Controller
@RequestMapping("/ad")
public class AdvertisementDistributeController {

			
	@Autowired
	private PmsService pmsService;
	@Autowired
	private CmsRobotService cmsRobotService;
	
	/**
	     * @discription 广告列表�?
	     * @author CJay       
	     * @created 2017�?3�?23�? 上午11:03:59
	 */
	@RequestMapping(value = "/list")
    public ModelAndView portal() {
        ModelAndView mv = new ModelAndView("pms/advertisement_distribute_list");
        return mv;
    }
	
	/**
	     * @discription 跳转到新增页�?
	     * @author CJay       
	     * @created 2017�?3�?23�? 上午11:03:42
	 */
	@RequestMapping(value = "/toAdvertisement_distributeAdd")
    public ModelAndView toProductAdd(HttpServletRequest request) {
		List<CmsRobot> cmsRobots = cmsRobotService.getAdCmsRobots();
		List<Pms_advertisement> pms_advertisements=pmsService.selectAllAd();
		request.setAttribute("cmsRobots", cmsRobots);
		request.setAttribute("pms_advertisements", pms_advertisements);
        ModelAndView mv = new ModelAndView("pms/advertisement_distribute_add");
        return mv;
    }	
	
	/**
     * @discription 跳转到修改页�?
     * @author lyb       
     * @created 2017�?5�?15�? 下午13:56:42
	 */
	@RequestMapping(value = "{sn}/toAdvertisement_distributeUpdate")
	public ModelAndView toProducUpdate(@PathVariable String sn) {
		List<Pms_advertisement> pms_advertisements=pmsService.selectAllAd();
		List<String> advertise_fks=pmsService.selectAdvertisementBySn(sn);
		for (Pms_advertisement pms_advertisement : pms_advertisements) {
			for (int i=0;i<advertise_fks.size();i++) {
				if (pms_advertisement.getId().equals(advertise_fks.get(i))) {
					pms_advertisement.setChecked(true);;
					advertise_fks.remove(i);
					break;
				}
			}
		}
	    ModelAndView mv = new ModelAndView("pms/advertisement_distribute_update");
	    mv.addObject("sn",sn);
	    mv.addObject("pms_advertisements",pms_advertisements);	    
	    return mv;
	}
	
	/**
	     * @discription 新增广告分配
	     * @author CJay       
	     * @created 2017�?3�?23�? 上午11:03:26
	 */		
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<String> productAdd(@RequestBody List<String> advertisement_ids, HttpServletRequest request,HttpServletResponse response){
        JSONObject result = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding("UTF-8");
        User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);      
        String msg = "";
        String sn=advertisement_ids.get(advertisement_ids.size()-1);
        advertisement_ids.remove(advertisement_ids.size()-1);
        List<PmsAdvertisementDistribute> pmsAdvertisementDistributes=new ArrayList<PmsAdvertisementDistribute>();
        for (String advertisement_id : advertisement_ids) {
        	PmsAdvertisementDistribute pmsAdvertisementDistribute=new PmsAdvertisementDistribute();
            String id = StringUtil.createUUID();       
            pmsAdvertisementDistribute.setId(id);
            pmsAdvertisementDistribute.setSn(sn);
            pmsAdvertisementDistribute.setAdvertise_fk(advertisement_id);
            pmsAdvertisementDistribute.setCreator_fk(loginUser.getId());
            pmsAdvertisementDistribute.setUpdater_fk(loginUser.getId());
            pmsAdvertisementDistributes.add(pmsAdvertisementDistribute);
		}
        
        if(pmsService.insertAd(pmsAdvertisementDistributes)){
        	msg = ResultEntity.KW_STATUS_SUCCESS;
        }
        
        result.put("msg", msg);
        return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}

	/**
     * @discription 修改广告分配
     * @author CJay       
     * @created 2017�?3�?23�? 上午11:03:26
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<String> productUpdate(@RequestBody List<String> advertisement_ids,HttpServletRequest request,HttpServletResponse response){
       
		JSONObject result = new JSONObject();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        response.setCharacterEncoding("UTF-8");
        User loginUser = (User) request.getSession().getAttribute(Constants.CURRENT_USER);      
        String msg = "";
        String sn=advertisement_ids.get(advertisement_ids.size()-1);
        advertisement_ids.remove(advertisement_ids.size()-1);
        if (!pmsService.deleteAdByPrimaryKey(sn)) {
			msg=ResultEntity.KW_STATUS_FAIL;
		}else {
			List<PmsAdvertisementDistribute> pmsAdvertisementDistributes=new ArrayList<PmsAdvertisementDistribute>();
	        for (String advertisement_id : advertisement_ids) {
	        	PmsAdvertisementDistribute pmsAdvertisementDistribute=new PmsAdvertisementDistribute();
	            String id = StringUtil.createUUID();       
	            pmsAdvertisementDistribute.setId(id);
	            pmsAdvertisementDistribute.setSn(sn);
	            pmsAdvertisementDistribute.setAdvertise_fk(advertisement_id);
	            pmsAdvertisementDistribute.setCreator_fk(loginUser.getId());
	            pmsAdvertisementDistribute.setUpdater_fk(loginUser.getId());
                pmsAdvertisementDistributes.add(pmsAdvertisementDistribute);
			}
            if(pmsService.insertAd(pmsAdvertisementDistributes)){
            	msg = ResultEntity.KW_STATUS_SUCCESS;
            }
		}
  
        result.put("msg", msg);
        return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);

	}
	
	/**
     * @discription 删除广告分配
     * @author CJay       
     * @created 2017�?3�?24�? 上午8:52:41
	 */
	@RequestMapping(value = "{sn}/advertisementDistributeDelete")
	public ResponseEntity<String> ProductDelete(@PathVariable String sn,HttpServletResponse response){
		JSONObject result = new JSONObject();
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    response.setCharacterEncoding("UTF-8");
	    String msg = "删除失败！";
		if (pmsService.deleteAdByPrimaryKey(sn)) {
			msg = ResultEntity.KW_STATUS_SUCCESS;
		}	 	    
	    result.put("msg", msg);
	    return new ResponseEntity<String>(result.toString(), headers, HttpStatus.OK);
	}

	/**
     * @discription 搜索广告分配
     * @author CJay       
     * @created 2017�?3�?24�? 上午8:52:41
	 */
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

            String sn = request.getParameter("sn");
            if (StringUtil.notEmpty(sn)) {
                params.put("sn", sn);
            }
            String sortString = null;
            if (orderName != null && !"".equals(orderName) && dir != null && !"".equals(dir)) {
                sortString = orderName + "." + dir;
            }
            PageList<PmsAdvertisementDistribute> list = pmsService.adDisPage(params, (start / length) + 1, length, sortString);
            result = new ResultEntityHashMapImpl(ResultEntity.KW_STATUS_SUCCESS, "search success");
            if (list != null) {
                result.addObject("data", list);
                result.addObject("recordsFiltered", list.size());
                result.addObject("recordsTotal", list.size());
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
