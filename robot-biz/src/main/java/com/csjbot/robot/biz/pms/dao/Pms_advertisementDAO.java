/**
 * 
 */
package com.csjbot.robot.biz.pms.dao;


import com.csjbot.robot.base.dao.BaseDAO;
import com.csjbot.robot.biz.pms.model.Pms_advertisement;

import java.util.List;

/**
 * @author 作者：Zhangyangyang
 * @version 创建时间：2017年3月27日 下午3:43:16
 * 类说明
 */
public interface Pms_advertisementDAO extends BaseDAO<Pms_advertisement> {
    //获得valid为1的

    @Override
    List<Pms_advertisement> selectAll();
}
