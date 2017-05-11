/**
 * 
 */
package com.csjbot.robot.biz.pms.dao;


import com.csjbot.robot.biz.base.BaseDAO;
import com.csjbot.robot.biz.pms.model.Pms_product;

import java.util.List;

/**
 * @author 作者：Zhangyangyang
 * @version 创建时间：2017年3月21日 上午9:45:25
 * 类说明
 */
public interface Pms_productDAO extends BaseDAO<Pms_product> {
    @Override
    List<Pms_product> selectAll();
}
