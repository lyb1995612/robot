package com.csjbot.robot.biz.scs.service;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.base.exception.ServiceException;
import com.csjbot.robot.biz.cms.model.Customer;
import com.csjbot.robot.biz.scs.dao.ScsDeskDao;
import com.csjbot.robot.biz.scs.model.ScsDesk;
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
	/**
	 * 
	 * 
	 */
	/*public boolean insertDish(ScsDish scsDish);
	public boolean updateDish(ScsDish sortdDish);
	
	 ScsDish selectDisByName(String name);

	public boolean deleteDish(String id);

     ScsDish selectDisByPrimaryKey(String id);

	public <E, K, V> Page<E> pageDishAndSort(Map<String, Object> params, int current, int pagesize, String sortString);

	*//**
	 * 
	 * @param scsDesk
	 * @return
	 *//*
	public boolean insertDishType(ScsDishType scsDishType);
	public boolean updateDishType(ScsDishType scsDishType);
	public List<ScsDishType> selectAll();
	public boolean deleteDishType(Integer id);

	ScsDishType selectDishTypeByPrimaryKey(Integer id);

	public <E, K, V> Page<E> pageDishTypeAndSort(Map<String, Object> params, int current, int pagesize, String sortString);

	*//**
	 * 
	 * @param scsDesk
	 * @return
	 *//*
	public boolean insertAccessory(ScsAccessory scsAccessory);
	public List<ScsAccessory> selectAcceAll();
	public boolean deleteAccessory(String id);

	ScsAccessory selectAccessoryByPrimaryKey(String id);

	public <E, K, V> Page<E> pageAccessoryAndSort(Map<String, Object> params, int current, int pagesize, String sortString);*/

}
