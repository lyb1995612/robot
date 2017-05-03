/**   
 * @Title: User.java 
 * @Package: com.csjbot.admin.data.ums.model 
 * @Description: TODO
 * @author DCJ  
 * @date 2015-4-14 下午5:05:33 
 * @version 1.0 
 */

package com.csjbot.robot.biz.ums.model;

import java.io.Serializable;
import java.util.Date;

import com.csjbot.robot.base.model.AtomModel;

/**
 * @Description
 * @author DCJ
 * @date 2015-4-14 下午5:05:33
 * @version V1.0
 */
public class User extends AtomModel implements Serializable {
	private static final long serialVersionUID = -6501616217033845894L;

	private String id; // 主键(UUID)
	private String username; // 用户名
	private String password; // 密码
	private String salt; // 盐
	private String phone; // 手机号码
	private String email; // 邮箱
	private String realname; // 真实姓名
	private int sex;// 性别 0：男 1：女
	private String idCard; // 身份证号码
	private int isSuperAdmin;// 0 否 1：是
	private int passwordChanged = 0; //0 否 1 是
	private Date lastLoginTime;// 最后登录时间
	private int status;// 0 否 1：是
	private Date dateEffect;// 生效时间
	private Date dateExpire;// 失效时间

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getIsSuperAdmin() {
		return isSuperAdmin;
	}
	public void setIsSuperAdmin(int isSuperAdmin) {
		this.isSuperAdmin = isSuperAdmin;
	}
	public int getPasswordChanged() {
		return passwordChanged;
	}
	public void setPasswordChanged(int passwordChanged) {
		this.passwordChanged = passwordChanged;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getDateEffect() {
		return dateEffect;
	}
	public void setDateEffect(Date dateEffect) {
		this.dateEffect = dateEffect;
	}
	public Date getDateExpire() {
		return dateExpire;
	}
	public void setDateExpire(Date dateExpire) {
		this.dateExpire = dateExpire;
	}
	public String getCreator_fk() {
		return creator_fk;
	}
	public void setCreator_fk(String creator_fk) {
		this.creator_fk = creator_fk;
	}
	public String getUpdater_fk() {
		return updater_fk;
	}
	public void setUpdater_fk(String updater_fk) {
		this.updater_fk = updater_fk;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	
}
