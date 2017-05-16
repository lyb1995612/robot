package com.csjbot.robot.biz.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.base.util.StringUtil;
import com.csjbot.robot.biz.cms.dao.CmsRobotGroupRefDao;
import com.csjbot.robot.biz.cms.model.CmsRobotGroupRef;
import com.csjbot.robot.biz.sys.model.RolePermissionRef;

@Service
public class CmsRobotGroupRefService {
	
	@Autowired
	private CmsRobotGroupRefDao dao;
	
	public int countRobotGroupSize(String groupId){
		return dao.countRobotGroupSize(groupId);
	}
	
	public CmsRobotGroupRef get(String id) {
        return dao.get(id);
    }
	
	public CmsRobotGroupRef delete(String id){
		return dao.delete(id);
	}
	
	public void saveOrUpdate(CmsRobotGroupRef ref) {
        if (dao.update(ref) == 0) {
            if (StringUtil.isEmpty(ref.getId())) {
               ref.setId(StringUtil.createUUID());
            }
            dao.insert(ref);
        }
    }

}
