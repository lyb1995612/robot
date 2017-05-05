/**
 * 
 */
package com.csjbot.robot.biz.pms.dao;


import com.csjbot.robot.base.dao.BaseDAO;
import com.csjbot.robot.biz.pms.model.Pms_order_item;

/**
 * @author 作者：Zhangyangyang
 * @version 创建时间：2017年4月5日 下午2:20:36
 * 类说明
 */
public interface Pms_order_detailDAO extends BaseDAO<Pms_order_item> {
	
	public abstract Pms_order_item findDetailByOrderId(String order_id);

}
