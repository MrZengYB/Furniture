package com.tlb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//系统角色
@Entity
@Table(name = "t_tlb_js")
public class TTlbJs extends BaseEntity {

	private static final long serialVersionUID = 2046536868684412519L;

	private String jsid; // 角色ID

	private String mc; // 名称

	private Boolean sfxs; // 是否显示

	@Id
	@Column(length = 32, nullable = true)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getJsid() {
		return jsid;
	}

	public void setJsid(String jsid) {
		this.jsid = jsid;
	}

	@Column(length = 32, nullable = false)
	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public Boolean getSfxs() {
		return sfxs;
	}

	public void setSfxs(Boolean sfxs) {
		this.sfxs = sfxs;
	}
}
