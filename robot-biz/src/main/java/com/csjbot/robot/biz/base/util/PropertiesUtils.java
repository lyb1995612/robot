package com.csjbot.robot.biz.base.util;

import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Properties宸ュ叿绫?
 * 
 * @version 2012-9-11 涓嬪崍01:10:22
 */
@Component
public class PropertiesUtils {
	@Autowired
	@Qualifier("appconfig")
	private Properties properties;
	private static PropertiesUtils piu;

	/**
	 * 鍒濆鍖栧伐鍏风被榛樿灞炴?
	 */
	@PostConstruct
	public void init() {
		piu = this;
		piu.properties = this.properties;
	}

	public static String getValue(String key) {
		return piu.properties.getProperty(key, null);
	}

}
