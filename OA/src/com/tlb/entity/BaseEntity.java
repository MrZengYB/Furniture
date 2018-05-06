package com.tlb.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;


@MappedSuperclass
public class BaseEntity implements Serializable{

	private static final long serialVersionUID = 102377703772331912L;

	private Date cjsj; // 创建时间

	private Date xgsj; // 修改时间

	public Date getCjsj() {
		return cjsj;
	}

	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	public Date getXgsj() {
		return xgsj;
	}

	public void setXgsj(Date xgsj) {
		this.xgsj = xgsj;
	}
	
}
