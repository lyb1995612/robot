/**
 * 
 */
package com.csjbot.robot.biz.pms.dao;


import com.csjbot.robot.biz.base.BaseDAO;
import com.csjbot.robot.biz.pms.model.Cms_secret;

import java.util.List;

/**
 * @author 作者：Zhangyangyang
 * @version 创建时间：2017年4月12日 上午11:17:41
 * 类说明
 */
public interface Cms_secretDAO extends BaseDAO<Cms_secret> {
	//根据code_group查询
	public abstract List<Cms_secret> findCmsSecretByGroup(String group);
}
