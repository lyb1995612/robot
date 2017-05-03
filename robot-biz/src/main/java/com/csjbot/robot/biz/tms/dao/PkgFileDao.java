package com.csjbot.robot.biz.tms.dao;

import java.util.List;

import com.csjbot.robot.biz.tms.model.PkgFile;

public interface PkgFileDao {

	PkgFile get(String id);

	List<PkgFile> getAll();

	// todo insert should return fileId not 0 or 1
	int insert(PkgFile pojo);

	int update(PkgFile pojo);

	int delete(String id);

	<V> List<V> getDistinct(String fieldName);

	<V> List<V> getLike(String ptn, String orderBy); // todo
}
