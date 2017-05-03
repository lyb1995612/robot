package com.csjbot.robot.biz.tms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csjbot.robot.biz.tms.dao.PkgFileDao;
import com.csjbot.robot.biz.tms.model.FileEntry;
import com.csjbot.robot.biz.tms.model.PkgFile;

@Service
public class PkgFileService {

	@Autowired
	private PkgFileDao dao;

	public PkgFile get(String id) {
		return dao.get(id);
	}

	public List<PkgFile> getAll() {
		return dao.getAll();
	}

	public int insert(PkgFile pojo) {
		return dao.insert(pojo);
	}

	public int update(PkgFile pojo) {
		return dao.update(pojo);
	}

	public int delete(String id) {
		return dao.delete(id);
	}

	public List<String> getDistinctPaths() {
		return dao.getDistinct("path");
	}

	public List<FileEntry> findByName(String ptn, Integer orderBy) {
		String col;
		switch (orderBy) {
		case 2:
			col = "uptime";
			break;
		default:
			col = "path";
		}
		return dao.getLike(ptn, col);
	}

}
