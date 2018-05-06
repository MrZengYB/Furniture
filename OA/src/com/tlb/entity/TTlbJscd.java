package com.tlb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

//角色菜单
@Entity
@Table(name = "t_tlb_jscd")
public class TTlbJscd extends BaseEntity {

	private static final long serialVersionUID = -294625814331978827L;

	private String jscdid; // ID

	private String jsid; // 角色ID

	private String xtcdid; // 菜单ID

	@Id
	@Column(length = 32, nullable = true)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getJscdid() {
		return jscdid;
	}

	public void setJscdid(String jscdid) {
		this.jscdid = jscdid;
	}

	@Column(length = 32, nullable = false)
	public String getJsid() {
		return jsid;
	}

	public void setJsid(String jsid) {
		this.jsid = jsid;
	}

	@Column(length = 32, nullable = false)
	public String getXtcdid() {
		return xtcdid;
	}

	public void setXtcdid(String xtcdid) {
		this.xtcdid = xtcdid;
	}

}
