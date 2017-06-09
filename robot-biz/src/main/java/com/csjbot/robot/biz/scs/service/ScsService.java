package com.csjbot.robot.biz.scs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.csjbot.robot.base.page.Page;
import com.csjbot.robot.base.page.PageContainer;
import com.csjbot.robot.base.util.StringUtil;
import com.csjbot.robot.biz.Constants;
import com.csjbot.robot.biz.sys.dao.SysAttachmentDao;
import com.csjbot.robot.biz.sys.dao.SysDataDictionaryDao;
import com.csjbot.robot.biz.sys.model.SysAttachment;
import com.csjbot.robot.biz.sys.model.SysDataDictionary;
import com.csjbot.robot.biz.ums.dao.UserDao;
import com.csjbot.robot.biz.ums.model.User;
import com.csjbot.robot.biz.util.CharacterUtil;
import com.csjbot.robot.biz.util.JsonUtil;
import com.csjbot.robot.biz.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.biz.scs.dao.ScsAccessoryDAO;
import com.csjbot.robot.biz.scs.dao.ScsDeskDao;
import com.csjbot.robot.biz.scs.dao.ScsDishDAO;
import com.csjbot.robot.biz.scs.dao.ScsDishLinkDao;
import com.csjbot.robot.biz.scs.dao.ScsDishTypeDAO;
import com.csjbot.robot.biz.scs.dao.ScsShopDao;
import com.csjbot.robot.biz.scs.model.ScsAccessory;
import com.csjbot.robot.biz.scs.model.ScsDesk;
import com.csjbot.robot.biz.scs.model.ScsDish;
import com.csjbot.robot.biz.scs.model.ScsDishLink;
import com.csjbot.robot.biz.scs.model.ScsDishType;
import com.csjbot.robot.biz.scs.model.ScsShop;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description
 * @author XMT
 * @version V1.0
 */
@Service
public class ScsService {

	@Autowired
	private ScsDeskDao scsDeskDao;

	@Autowired
	private ScsAccessoryDAO scsAccessoryDAO;

	@Autowired
	private ScsDishDAO scsDishDAO;

	@Autowired
	private ScsDishTypeDAO scsDishTypeDAO;

	@Autowired
	private SysAttachmentDao sysAttachmentDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ScsDishLinkDao scsDishLinkDao;

	@Autowired
	private SysDataDictionaryDao sysDicDao;

	@Autowired
	private ScsShopDao scsShopDao;

	/*
	 * ScsDishLink
	 */
	public int insertDishSN(ScsDishLink scsDishSN) {
		return scsDishLinkDao.insert(scsDishSN);
	}

	public int countType(String sn, String dish_name) {
		return scsDishLinkDao.countType(sn, dish_name);
	}

	public ScsDishLink getDish(String id) {
		return scsDishLinkDao.getDish(id);
	}

	public ScsDishLink selectByPK(String id) {
		return scsDishLinkDao.selectByPK(id);
	}

	public List<ScsDishLink> selectBySN(String sn) {
		return scsDishLinkDao.selectBySN(sn);
	}

	public List<ScsDishLink> selectDishSNAll() {
		return scsDishLinkDao.selectAll();
	}

	public List<SysDataDictionary> findDictionaryByCode(String code) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("code", code);
		return sysDicDao.findDictionaryByCode(params);
	}

	public List<Map<String, Object>> listDish(Map<String, Object> params) {
		return scsDishLinkDao.listDish(params);
	}

	public int countDishLinkSize(String dishId) {
		return scsDishLinkDao.countDishLinkSize(dishId);
	}

	public void saveOrUpdate(ScsDishLink scsDishLink) {
		if (scsDishLinkDao.update(scsDishLink) == 0) {
			if (StringUtil.isEmpty(scsDishLink.getId())) {
				scsDishLink.setId(StringUtil.createUUID());
			}
			scsDishLinkDao.insert(scsDishLink);
		}
	}

	public List<String> selectPKBySN(String sn) {
		return scsDishLinkDao.selectPKBySN(sn);
	}

	public <E, K, V> Page<E> pageDishSN(Map<K, V> params, int current, int pagesize, String sortString) {
		PageBounds pager = new PageBounds();
		pager.setLimit(pagesize);
		pager.setPage(current);
		pager.setOrders(Order.formString(sortString));
		PageList<E> result = scsDishLinkDao.page(params, pager);
		Paginator paginator = result.getPaginator();
		return new PageContainer<E, K, V>(paginator.getTotalCount(), paginator.getLimit(), paginator.getPage(), result,
				params);
	}

	/*
	 * ScsDesk
	 */
	public int insert(ScsDesk scsDesk) {
		return scsDeskDao.insert(scsDesk);
	}

	public int delete(String id) {
		return scsDeskDao.delete(id);
	}

	public ScsDesk selectByPrimaryKey(String id) {
		return scsDeskDao.selectByPrimaryKey(id);
	}

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize, String sortString) {
		PageBounds pager = new PageBounds();
		pager.setLimit(pagesize);
		pager.setPage(current);
		pager.setOrders(Order.formString(sortString));
		PageList<E> result = scsDeskDao.page(params, pager);
		Paginator paginator = result.getPaginator();
		return new PageContainer<E, K, V>(paginator.getTotalCount(), paginator.getLimit(), paginator.getPage(), result,
				params);
	}

	/*
	 * ScsDish
	 */
	public int insertDish(ScsDish scsDish) {
		return scsDishDAO.insert(scsDish);
	}

	public int updateDish(ScsDish sortdDish) {
		return scsDishDAO.updateByPrimaryKeySelective(sortdDish);
	}

	public ScsDish selectDisByName(String name) {
		return scsDishDAO.selectByName(name);
	}

	public int deleteDish(String id) {
		return scsDishDAO.delete(id);
	}

	public ScsDish selectDisByPrimaryKey(String id) {
		return scsDishDAO.selectByPrimaryKey(id);
	}

	public <E, K, V> Page<E> pageDish(Map<K, V> params, int current, int pagesize, String sortString) {
		PageBounds pager = new PageBounds();
		pager.setLimit(pagesize);
		pager.setPage(current);
		pager.setOrders(Order.formString(sortString));
		PageList<E> result = scsDishDAO.page(params, pager);
		Paginator paginator = result.getPaginator();
		return new PageContainer<E, K, V>(paginator.getTotalCount(), paginator.getLimit(), paginator.getPage(), result,
				params);
	}

	/*
	 * ScsDishType
	 */
	public int insertDishType(ScsDishType scsDishType) {
		return scsDishTypeDAO.insert(scsDishType);
	}

	public int updateDishType(ScsDishType scsDishType) {
		return scsDishTypeDAO.updateByPrimaryKeySelective(scsDishType);
	}

	public List<ScsDishType> selectAll() {
		return scsDishTypeDAO.selectAll();
	}

	public int deleteDishType(Integer id) {
		return scsDishTypeDAO.delete(id);
	}

	public ScsDishType selectDishTypeByPrimaryKey(Integer id) {
		return scsDishTypeDAO.selectByPrimaryKey(id);
	}

	public <E, K, V> Page<E> pageDishType(Map<K, V> params, int current, int pagesize, String sortString) {
		PageBounds pager = new PageBounds();
		pager.setLimit(pagesize);
		pager.setPage(current);
		pager.setOrders(Order.formString(sortString));
		PageList<E> result = scsDishTypeDAO.page(params, pager);
		Paginator paginator = result.getPaginator();
		return new PageContainer<E, K, V>(paginator.getTotalCount(), paginator.getLimit(), paginator.getPage(), result,
				params);
	}

	/*
	 * ScsAccessory
	 */
	public int insertAccessory(ScsAccessory scsAccessory) {
		return scsAccessoryDAO.insert(scsAccessory);
	}

	public List<ScsAccessory> selectAcceAll() {
		return scsAccessoryDAO.selectAll();
	}

	public int deleteAccessory(String id) {
		return scsAccessoryDAO.deleteByPrimaryKey(id);
	}

	public ScsAccessory selectAccessoryByPrimaryKey(String id) {
		return scsAccessoryDAO.selectByPrimaryKey(id);
	}

	public <E, K, V> Page<E> pageAccessory(Map<K, V> params, int current, int pagesize, String sortString) {
		PageBounds pager = new PageBounds();
		pager.setLimit(pagesize);
		pager.setPage(current);
		pager.setOrders(Order.formString(sortString));
		PageList<E> result = scsAccessoryDAO.page(params, pager);
		Paginator paginator = result.getPaginator();
		return new PageContainer<E, K, V>(paginator.getTotalCount(), paginator.getLimit(), paginator.getPage(), result,
				params);
	}
/**
 * api接口部分
 */

	/**
	 * 按SN查询菜品
	 *
	 * @param request
	 * @return
	 */
	public JSONObject findDishInfoBySn(HttpServletRequest request) {
		JsonUtil jsonUtil = getJsonUtilEntity(true);
		String sn = request.getParameter("sn");
		List<Object> dishes = new ArrayList<>();
		List<ScsDish> list = scsDishDAO.selectBySn(sn);

		for (ScsDish sdi : list) {
			Map<String, Object> dish = new HashMap<>();
			ScsDishType sdt = scsDishTypeDAO.selectByPrimaryKey(sdi.getDish_type());
			SysAttachment saList = sysAttachmentDao.getAttachByTransInfo(sdi.getId().toString(),
					Constants.Attachment.Type.DISH_PIC.toString());// getSystByProId(sdi.getId().toString());
			if (sdt != null) {
				dish.put("dishTypeId", sdt.getId());
				dish.put("dishTypeName", sdt.getType_name().toString());
			} else {
				dish.put("dishTypeId", "");
				dish.put("dishTypeName", "未知类型");
			}
			if (saList != null) {
				dish.put("dishImageName", saList.getAlias_name());
				dish.put("dishImageUrl",
						request.getServerName() + ":" + request.getServerPort() + "/api/scs/downFile?filePath="
								+ saList.getLocation().toString() + "&fileName=" + saList.getAlias_name().toString());
			} else {
				dish.put("dishImageName", "");
				dish.put("dishImageUrl", "");
			}
			dish.put("dishId", sdi.getId().toString());
			dish.put("dishName", sdi.getName().toString());
			dish.put("dishPrice", sdi.getPrice());
			dish.put("dishMemo", sdi.getMemo().toString());
			dishes.add(dish);
		}
		Map<String, Object> result = new HashMap<>();
		result.put("dishes", dishes);
		jsonUtil.setResult(result);
		return JsonUtil.toJson(jsonUtil);

	}

	/**
	 * 按SN查询DESK
	 *
	 * @param request
	 * @return
	 */
	public JSONObject findDeskInfoBySn(HttpServletRequest request) {
		JsonUtil jsonUtil = getJsonUtilEntity(true);
		String sn = request.getParameter("sn");
		List<ScsDesk> list = scsDeskDao.selectBySn(sn);
		Map<String, Object> result = new HashMap<>();
		result.put("desks", list);
		jsonUtil.setResult(result);
		return JsonUtil.toJson(jsonUtil);
	}

	/**
	 * 按SN 查询附件信息
	 *
	 * @param request
	 * @return
	 */

	/*public JSONObject findScsAccessoryBySn(HttpServletRequest request){
		 JsonUtil jsonUtil = getJsonUtilEntity(true);
		 String sn=request.getParameter("sn");
		 Map<String,String> params= new HashMap<String,String>();
		 params.put("sn", sn);
		 params.put("type", Constants.Attachment.Type.SC_ACCESSORY);
		 List<ScsAccessory> list= scsAccessoryDAO.selectBySn(params);

		 Map<String, Object> result = new HashMap<>();
		 result.put("accessories", list);
		 jsonUtil.setResult(result);
		 return JsonUtil.toJson(jsonUtil);
	}*/

	public JSONObject findScsAccessoryBySn(HttpServletRequest request) {
		JsonUtil jsonUtil = getJsonUtilEntity(true);
	    List<Object> ace = new ArrayList<>();
	    String sn=request.getParameter("sn");
	    List<SysAttachment> list = sysAttachmentDao.getAttachBySn(sn);
		for (SysAttachment sa : list) {
			Map<String, Object> demo = new HashMap<>();
			demo.put("fileName", sa.getAlias_name().toString());
			demo.put("fileType", sa.getFile_type().toString());
			demo.put("fileUrl", request.getServerName() + ":" + request.getServerPort() + "/api/scs/downFile?filePath="
					+ sa.getLocation().toString() + "&fileName=" + sa.getAlias_name().toString());
			ace.add(demo);
		}
	jsonUtil.setResult(ace);
	return JsonUtil.toJson(jsonUtil);}
	/*
	 * 查询所有菜品信息
	 */
	public JSONObject findAllDishInfo(HttpServletRequest request) {
		JsonUtil jsonUtil = getJsonUtilEntity(true);
		List<Object> dishes = new ArrayList<>();
		Map<String, Object> result = new HashMap<>();
		List<ScsDish> list = scsDishDAO.selectAll();

		for (ScsDish sdi : list) {
			Map<String, Object> dish = new HashMap<>();
			ScsDishType sdt = scsDishTypeDAO.selectByPrimaryKey(sdi.getDish_type());
			SysAttachment saList = sysAttachmentDao.getAttachByTransInfo(sdi.getId().toString(),
					Constants.Attachment.Type.DISH_PIC.toString());// getSystByProId(sdi.getId().toString());
			if (sdt != null) {
				dish.put("dishTypeId", sdt.getId());
				dish.put("dishTypeName", sdt.getType_name().toString());
			} else {
				dish.put("dishTypeId", "");
				dish.put("dishTypeName", "未知类型");
			}
			if (saList != null) {
				dish.put("dishImageName", saList.getAlias_name());
				dish.put("dishImageUrl",
						request.getServerName() + ":" + request.getServerPort() + "/api/scs/downFile?filePath="
								+ saList.getLocation().toString() + "&fileName=" + saList.getAlias_name().toString());
			} else {
				dish.put("dishImageName", "");
				dish.put("dishImageUrl", "");
			}
			dish.put("dishId", sdi.getId().toString());
			dish.put("dishName", sdi.getName().toString());
			dish.put("dishPrice", sdi.getPrice());
			dish.put("dishMemo", sdi.getMemo().toString());
			dishes.add(dish);
		}
		result.put("dishes", dishes);
		jsonUtil.setResult(result);
		return JsonUtil.toJson(jsonUtil);
	}

	/*
	 * 查询所有菜品类型信息
	 * 
	 * 
	 */
	public JSONObject findAllDishType(HttpServletRequest request) {
		String sn = request.getParameter("sn");

		System.out.println(sn);
		JsonUtil jsonUtil = getJsonUtilEntity(true);
		List<Object> dishes = new ArrayList<>();
		List<ScsDishType> list = new ArrayList<>();
		if (StringUtil.isEmpty(sn)) {
			list = scsDishTypeDAO.selectAll();
		} else {
			list = scsDishTypeDAO.selectBySn(sn);
		}

		for (ScsDishType sdt : list) {
			Map<String, Object> dish_type = new HashMap<>();
			dish_type.put("dishTypeId", 2000 + sdt.getId());
			dish_type.put("dishTypeName", sdt.getType_name().toString());
			dishes.add(dish_type);
		}
		jsonUtil.setResult(dishes);
		return JsonUtil.toJson(jsonUtil);
	}

	/*
	 * 查询所有附件信息
	 */
	public JSONObject showAccessoryS(HttpServletRequest request) {
		JsonUtil jsonUtil = getJsonUtilEntity(true);
		List<Object> ace = new ArrayList<>();
		List<SysAttachment> list = sysAttachmentDao.getAttachByType(Constants.Attachment.Type.SC_ACCESSORY);
		for (SysAttachment sa : list) {
			Map<String, Object> demo = new HashMap<>();
			demo.put("fileName", sa.getAlias_name().toString());
			demo.put("fileType", sa.getFile_type().toString());
			demo.put("fileUrl", request.getServerName() + ":" + request.getServerPort() + "/api/scs/downFile?filePath="
					+ sa.getLocation().toString() + "&fileName=" + sa.getAlias_name().toString());
			ace.add(demo);
		}
		jsonUtil.setResult(ace);
		return JsonUtil.toJson(jsonUtil);
	}

	/*
	 * 添加桌位
	 */
	public JSONObject addDeskInfo(JSONObject json) {
		JsonUtil jsonUtil = getJsonUtilEntity(true);
		String[] key = { "userName", "deskNumber", "deskAlias", "deskMemo", "desk_x", "desk_y", "desk_z", "desk_w",
				"desk_v", "desk_q", "deskValid", "deskSerialNumber" };
		if (CharacterUtil.judgeJsonFormat(key, json)) {
			List<ScsDesk> sbn =scsDeskDao.selectByNumberAndType(json.getString("deskNumber"), json.getString("deskSerialNumber"));
			Map<String, Object> map = new HashMap<>();
			map.put("username", json.getString("userName"));
			map.put("status", 1);
			User ums_user = userDao.getByUsername(map);
			if(ums_user!=null){
				if(sbn.size()==0){
					ScsDesk sdi = new ScsDesk();
					sdi.setId(RandomUtil.generateString(32));
					sdi.setNumber(json.getString("deskNumber"));
					sdi.setAlias(json.getString("deskAlias"));
					sdi.setCreator_fk(ums_user.getId().toString());
					sdi.setDate_create(RandomUtil.getTimeStampFor());
					sdi.setDate_update(RandomUtil.getTimeStampFor());
					sdi.setDeskq(json.getDouble("desk_q"));
					sdi.setDeskv(json.getDouble("desk_v"));
					sdi.setDeskw(json.getDouble("desk_w"));
					sdi.setDeskx(json.getDouble("desk_x"));
					sdi.setDesky(json.getDouble("desk_y"));
					sdi.setDeskz(json.getDouble("desk_z"));
					sdi.setMemo(json.getString("deskMemo"));
					sdi.setValid(json.getByte("deskValid"));
					sdi.setDesk_type(json.getByte("deskSerialNumber"));
					sdi.setUpdater_fk(ums_user.getId().toString());
					int n = scsDeskDao.insert(sdi);
					if (n != 1) {
						jsonUtil = getJsonUtilEntity(false);
						jsonUtil.setMessage("Error from Database operations!");
					}
				}else{
					jsonUtil = getJsonUtilEntity(false);
					jsonUtil.setMessage("The deskNumber already exists!");
				}
			}else{
				jsonUtil = getJsonUtilEntity(false);
				jsonUtil.setMessage("NO User");
			}
			
			} else {
			jsonUtil = getJsonUtilEntity(false);
			jsonUtil.setMessage("Error from json format!");
		}
		return JsonUtil.toJson(jsonUtil);
	}
	/**
	 *
	 * @discription 添加机器人与DESK关联数据
	 * @author SJZ
	 * @created 2017年6月1日 下午2:44:26
	 */

	public int addDeskRobotFef(String sn,String deskId,String uid){
		Map<String,Object> params= new HashMap<String,Object>();
		params.put("id", RandomUtil.generateString(32));
		params.put("deskId", deskId);
		params.put("sn", sn);
		params.put("updaterFk", uid);
		params.put("creatorFk", uid);
		params.put("dateUpdate", RandomUtil.getTimeStampFor());
		params.put("dateCreate", RandomUtil.getTimeStampFor());
		return scsDeskDao.insertRobotDeskRef(params);

	}


	/**
	 *
	 * @discription  按SN 删除DESK
	 * @author CJay
	 * @created 2017年6月1日 下午3:32:42
	 */
	public JSONObject  deleteDeskInfoBySn(String  sn){
		JsonUtil jsonUtil = getJsonUtilEntity(true);
		scsDeskDao.deleteBySn(sn);
		scsDeskDao.deleteRobotDeskRefBySn(sn);
		return JsonUtil.toJson(jsonUtil);

	}
	/*
	 * 删除桌位信息
	 */
	public JSONObject deleteDeskInfo(JSONObject json) {
		JsonUtil jsonUtil = getJsonUtilEntity(true);
		String[] key = { "deskNumbers", "type" };

		if (CharacterUtil.judgeJsonFormat(key, json)) {
			String[] str = json.getString("deskNumbers").split("&");
			// JSONArray idArray = json.getJSONArray("deskNumbers");
			for (String number : str) {
				List<ScsDesk> sdiList = scsDeskDao.selectByNumber(number.toString());
				for (ScsDesk sdi : sdiList) {
					if (json.getInteger("type") == -1) {
						if (sdi.getDesk_type() == -1) {
							scsDeskDao.delete(sdi.getId().toString());
						}
					} else {
						if (sdi.getDesk_type() != -1) {
							scsDeskDao.delete(sdi.getId().toString());
						}
					}
				}
			}
		} else {
			jsonUtil = getJsonUtilEntity(false);
			jsonUtil.setMessage("Error from json format!");
		}
		return JsonUtil.toJson(jsonUtil);
	}

	/*
	 * 查看所有桌位信息
	 */
	public JSONObject showAllDeskInfo(Integer desk_type) {
		JsonUtil jsonUtil = getJsonUtilEntity(true);
		List<ScsDesk> list = scsDeskDao.selectAll();
		List<Object> result = new ArrayList<>();
		for (ScsDesk sdi : list) {
			if (desk_type == -1) {
				if (sdi.getDesk_type() == -1) {
					Map<String, Object> map = new HashMap<>();
					map.put("deskId", sdi.getId().toString());
					map.put("deskNumber", sdi.getNumber().toString());
					map.put("deskAlias", sdi.getAlias().toString());
					map.put("deskMemo", sdi.getMemo().toString());
					map.put("desk_x", sdi.getDeskx());
					map.put("desk_y", sdi.getDesky());
					map.put("desk_z", sdi.getDeskz());
					map.put("desk_w", sdi.getDeskw());
					map.put("desk_v", sdi.getDeskv());
					map.put("desk_q", sdi.getDeskq());
					map.put("deskValid", sdi.getValid());
					map.put("deskSerialNumber", sdi.getDesk_type());
					result.add(map);
				}
			} else {
				if (sdi.getDesk_type() != -1) {
					Map<String, Object> map = new HashMap<>();
					map.put("deskId", sdi.getId().toString());
					map.put("deskNumber", sdi.getNumber().toString());
					map.put("deskAlias", sdi.getAlias().toString());
					map.put("deskMemo", sdi.getMemo().toString());
					map.put("desk_x", sdi.getDeskx());
					map.put("desk_y", sdi.getDesky());
					map.put("desk_z", sdi.getDeskz());
					map.put("desk_w", sdi.getDeskw());
					map.put("desk_v", sdi.getDeskv());
					map.put("deskValid", sdi.getValid());
					map.put("desk_q", sdi.getDeskq());
					map.put("deskSerialNumber", sdi.getDesk_type());
					result.add(map);
				}
			}
		}
		jsonUtil.setResult(result);
		return JsonUtil.toJson(jsonUtil);
	}

	/*
	 * 获得返回json
	 */
	public JsonUtil getJsonUtilEntity(boolean flag) {
		JsonUtil jsonUtil;
		if (flag) {
			jsonUtil = new JsonUtil("200", "ok", null);
		} else {
			jsonUtil = new JsonUtil("500", "", null);
		}
		return jsonUtil;
	}

	/*
	 * ScsShop
	 */
	public ScsShop selectByShopPK(String id) {
		return scsShopDao.selectByPK(id);
	}

	public List<ScsShop> selectShopAll() {
		return scsShopDao.selectAll();
	}
	
	public int insertShopSelective(ScsShop shop){
		return scsShopDao.insertSelective(shop);
	}
	
	public int insertShop(ScsShop shop){
		return scsShopDao.insert(shop);
	}
	
	public int updateShopSelective(ScsShop shop){
		return scsShopDao.updateSelective(shop);
	}
	
	public int deleteByShopPK(String id){
		return scsShopDao.delete(id);
	}

	public <E, K, V> Page<E> shopPage(Map<K, V> params, int current, int pagesize, String sortString) {
		PageBounds pager = new PageBounds();
		pager.setLimit(pagesize);
		pager.setPage(current);
		pager.setOrders(Order.formString(sortString));
		PageList<E> result = scsShopDao.page(params, pager);
		Paginator paginator = result.getPaginator();
		return new PageContainer<E, K, V>(paginator.getTotalCount(), paginator.getLimit(), paginator.getPage(), result,
				params);
	}
}
