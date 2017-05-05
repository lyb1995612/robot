package com.csjbot.robot.biz.scs.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.biz.scs.dao.ScsAccessoryDAO;
import com.csjbot.robot.biz.scs.dao.ScsDeskDao;
import com.csjbot.robot.biz.scs.dao.ScsDishDAO;
import com.csjbot.robot.biz.scs.dao.ScsDishTypeDAO;
import com.csjbot.robot.biz.scs.model.ScsAccessory;
import com.csjbot.robot.biz.scs.model.ScsDesk;
import com.csjbot.robot.biz.scs.model.ScsDish;
import com.csjbot.robot.biz.scs.model.ScsDishType;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;


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

	public int insert(ScsDesk scsDesk){
		return scsDeskDao.insert(scsDesk);
	}

	public int delete(String id){
		return scsDeskDao.delete(id);
	}

	public ScsDesk selectByPrimaryKey(String id){
		return scsDeskDao.selectByPrimaryKey(id);
	}

	
	public PageList<ScsDesk> page(Map<String, Object> params,int current, int pagesize, String sortString) {
        PageBounds pager = new PageBounds();
        pager.setLimit(pagesize);
        pager.setPage(current);
        pager.setOrders(Order.formString(sortString));
        return scsDeskDao.page(params, pager);
    }
	
	/*
	 * ScsDish
	 */
	public int insertDish(ScsDish scsDish){
		return scsDishDAO.insert(scsDish);
	}
	public int updateDish(ScsDish sortdDish){
		return scsDishDAO.updateByPrimaryKeySelective(sortdDish);
	}
	
	 public ScsDish selectDisByName(String name){
		 return scsDishDAO.selectByName(name);
	 }

	public int deleteDish(String id){
		return scsDishDAO.delete(id);
	}

     public ScsDish selectDisByPrimaryKey(String id){
    	 return scsDishDAO.selectByPrimaryKey(id);
     }

     public PageList<ScsDish> pageDish(Map<String, Object> params,int current, int pagesize, String sortString) {
         PageBounds pager = new PageBounds();
         pager.setLimit(pagesize);
         pager.setPage(current);
         pager.setOrders(Order.formString(sortString));
         return scsDishDAO.page(params, pager);
     }

	/*
	 * ScsDishType
	 */
	public int  insertDishType(ScsDishType scsDishType){
		return scsDishTypeDAO.insert(scsDishType);
	}
	public int updateDishType(ScsDishType scsDishType){
		return scsDishTypeDAO.updateByPrimaryKeySelective(scsDishType);
	}
	public List<ScsDishType> selectAll(){
		return scsDishTypeDAO.selectAll();
	}
	public int deleteDishType(Integer id){
		return scsDishTypeDAO.delete(id);
	}

	public ScsDishType selectDishTypeByPrimaryKey(Integer id){
		return scsDishTypeDAO.selectByPrimaryKey(id);
	}

	public PageList<ScsDishType> pageDishType(Map<String, Object> params,int current, int pagesize, String sortString) {
        PageBounds pager = new PageBounds();
        pager.setLimit(pagesize);
        pager.setPage(current);
        pager.setOrders(Order.formString(sortString));
        return scsDishTypeDAO.page(params, pager);
    }

	/*
	 * ScsAccessory
	 */
	public int insertAccessory(ScsAccessory scsAccessory){
		return scsAccessoryDAO.insert(scsAccessory);
	}
	public List<ScsAccessory> selectAcceAll(){
		return scsAccessoryDAO.selectAll();
	}
	public int deleteAccessory(String id){
		return scsAccessoryDAO.deleteByPrimaryKey(id);
	}

	public ScsAccessory selectAccessoryByPrimaryKey(String id){
		return scsAccessoryDAO.selectByPrimaryKey(id);
	}

	public PageList<ScsAccessory> pageAccessory(Map<String, Object> params,int current, int pagesize, String sortString) {
        PageBounds pager = new PageBounds();
        pager.setLimit(pagesize);
        pager.setPage(current);
        pager.setOrders(Order.formString(sortString));
        return scsAccessoryDAO.page(params, pager);
    }

}
