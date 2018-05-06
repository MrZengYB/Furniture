package com.tlb.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("unused")
@Entity
@Table(name = "t_tlb_yh")
public class TTlbYh extends BaseEntity implements UserDetails {

	private static final long serialVersionUID = -2863154140909150791L;

	private String yhid; // 自身表的id
	private String zsxm; // 真实姓名
	private Boolean xb; // 性别
	private String yhsr; // 用户生日
	private String yhdz; // 用户地址
	private String sfzh; // 身份证号
	private String yhxl; // 用户学历
	private String yhtx; // 用户头像
	private String yhjj; // 用户简介
	private String yhyx; // 用户邮箱
	private String yhzwid; // 用户职位id
	private String yhbmid; // 用户部门id
	private String username; // 用户名、账号(手机号码)
	private String password; // 密码
	private Boolean yhsfsc; // 用户是否删除
	private Date sdrq;// 账号锁定日期
	private Boolean isAccountEnabled; // 账号是否启用
	private Boolean isAccountLocked; // 账号是否锁定
	private Boolean isAccountExpired; // 账号是否过期
	private Boolean isCredentialsExpired; // 凭证是否过期
	private Integer loginFailureCount; // 连续登录失败的次数

	/**
	 * 辅助字段.
	 */
	private Set<GrantedAuthority> authorities;// 权限信息

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(length = 32, nullable = false)
	public String getYhid() {
		return yhid;
	}

	public void setYhid(String yhid) {
		this.yhid = yhid;
	}

	public String getYhtx() {
		return yhtx;
	}

	public void setYhtx(String yhtx) {
		this.yhtx = yhtx;
	}

	public String getYhjj() {
		return yhjj;
	}

	public void setYhjj(String yhjj) {
		this.yhjj = yhjj;
	}

	public Boolean getYhsfsc() {
		return yhsfsc;
	}

	public void setYhsfsc(Boolean yhsfsc) {
		this.yhsfsc = yhsfsc;
	}

	@Column(nullable = false, unique = true, name = "yhmc")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "yhmm")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getZsxm() {
		return zsxm;
	}

	public void setZsxm(String zsxm) {
		this.zsxm = zsxm;
	}

	public Boolean getXb() {
		return xb;
	}

	public void setXb(Boolean xb) {
		this.xb = xb;
	}

	public String getYhsr() {
		return yhsr;
	}

	public void setYhsr(String yhsr) {
		this.yhsr = yhsr;
	}

	public String getYhdz() {
		return yhdz;
	}

	public void setYhdz(String yhdz) {
		this.yhdz = yhdz;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getYhxl() {
		return yhxl;
	}

	public void setYhxl(String yhxl) {
		this.yhxl = yhxl;
	}

	public String getYhyx() {
		return yhyx;
	}

	public void setYhyx(String yhyx) {
		this.yhyx = yhyx;
	}

	public String getYhzwid() {
		return yhzwid;
	}

	public void setYhzwid(String yhzwid) {
		this.yhzwid = yhzwid;
	}

	public String getYhbmid() {
		return yhbmid;
	}

	public void setYhbmid(String yhbmid) {
		this.yhbmid = yhbmid;
	}

	public Date getSdrq() {
		return sdrq;
	}

	public void setSdrq(Date sdrq) {
		this.sdrq = sdrq;
	}

	@Column(name = "sfqy")
	public Boolean getIsAccountEnabled() {
		return isAccountEnabled;
	}

	public void setIsAccountEnabled(Boolean isAccountEnabled) {
		this.isAccountEnabled = isAccountEnabled;
	}

	@Column(name = "sfsd")
	public Boolean getIsAccountLocked() {
		return isAccountLocked;
	}

	public void setIsAccountLocked(Boolean isAccountLocked) {
		this.isAccountLocked = isAccountLocked;
	}

	@Column(name = "sfgq")
	public Boolean getIsAccountExpired() {
		return isAccountExpired;
	}

	public void setIsAccountExpired(Boolean isAccountExpired) {
		this.isAccountExpired = isAccountExpired;
	}

	@Column(name = "pzsfgq")
	public Boolean getIsCredentialsExpired() {
		return isCredentialsExpired;
	}

	public void setIsCredentialsExpired(Boolean isCredentialsExpired) {
		this.isCredentialsExpired = isCredentialsExpired;
	}

	@Column(nullable = false, name = "dlsbcs")
	public Integer getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(Integer loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	@Transient
	public Set<GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	/* 实体类中使用了@Table注解后，想要添加表中不存在字段，就要使用@Transient这个注解了. */
	@Transient
	public boolean isEnabled() {
		return this.isAccountEnabled;
	}

	@Transient
	public boolean isAccountNonLocked() {
		return !this.isAccountLocked;
	}

	@Transient
	public boolean isAccountNonExpired() {
		return !this.isAccountExpired;
	}

	@Transient
	public boolean isCredentialsNonExpired() {
		return !this.isCredentialsExpired;
	}
}
