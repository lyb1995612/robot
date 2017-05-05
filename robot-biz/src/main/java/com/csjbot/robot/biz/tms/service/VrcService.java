package com.csjbot.robot.biz.tms.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.biz.sys.dao.SysVersionRobotDao;
import com.csjbot.robot.biz.sys.model.SysVersionRobot;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

@Service
public class VrcService {

	@Autowired
	private SysVersionRobotDao sysVersionRobotDao;

	/*public Page<Map<String, Object>> pageAndSort(Map<String, Object> params, int current, int pagesize,
			String sortString) {
		return sysVersionRobotDao.pageAndSort(params, current, pagesize, sortString);
	}*/

	public boolean insert(SysVersionRobot sysVersionRobot) {
		return sysVersionRobotDao.insert(sysVersionRobot) > 0;
	}

	public SysVersionRobot selectByPrimaryKey(String id) {
		return sysVersionRobotDao.selectByPrimaryKey(id);
	}

	public boolean updateVersionRobot(SysVersionRobot sysVersionRobot) {
		return sysVersionRobotDao.updateByPrimaryKeySelective(sysVersionRobot) > 0;
	}

	public boolean deleteVersionByPrimaryKey(String id) {
		return sysVersionRobotDao.deleteByPrimaryKey(id) > 0;
	}
	public PageList<SysVersionRobot> versPage(Map<String, Object> params,int current, int pagesize, String sortString) {
        PageBounds pager = new PageBounds();
        pager.setLimit(pagesize);
        pager.setPage(current);
        pager.setOrders(Order.formString(sortString));
        return sysVersionRobotDao.page(params, pager);
    }
}