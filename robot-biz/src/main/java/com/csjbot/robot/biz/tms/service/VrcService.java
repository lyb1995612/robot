package com.csjbot.robot.biz.tms.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.base.page.Page;
import com.csjbot.robot.biz.sys.dao.SysVersionRobotDao;
import com.csjbot.robot.biz.sys.model.SysVersionRobot;

@Service
public class VrcService {

	@Autowired
	private SysVersionRobotDao sysVersionRobotDao;

	public Page<Map<String, Object>> pageAndSort(Map<String, Object> params, int current, int pagesize,
			String sortString) {
		return sysVersionRobotDao.pageAndSort(params, current, pagesize, sortString);
	}

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

}