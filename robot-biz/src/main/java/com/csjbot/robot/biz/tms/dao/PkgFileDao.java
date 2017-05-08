package com.csjbot.robot.biz.tms.dao;

import java.util.List;

import com.csjbot.robot.biz.tms.model.PkgFile;
import com.csjbot.robot.biz.tms.model.param.PkgFileParam;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

public interface PkgFileDao {

	public PkgFile get(String id);

	public List<PkgFile> getAll();

	// todo insert should return fileId not 0 or 1
	public int insert(PkgFile pojo);

	public int update(PkgFile pojo);

	public int delete(String id);

	public <V> List<V> getDistinct(String fieldName);

	public <V> List<V> getLike(String ptn, String orderBy); // todo

	public PageList<PkgFile> page(PkgFileParam param, PageBounds bounds);

}
