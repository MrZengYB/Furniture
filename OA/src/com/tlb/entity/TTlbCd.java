package com.tlb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_tlb_cd")
public class TTlbCd extends BaseEntity {

	private static final long serialVersionUID = -4707214008184773185L;

	private String cdid; // 菜单id
	private String cdmc; // 菜单名称
	private String cdlj; // 菜单路径
	private String cddj; // 菜单等级
	private String sjcdid; // 上级菜单id
	private String kczyhlx; // 可操作用户类型
	private Boolean sfxs; // 是否显示
	private Boolean sfsc; // 是否删除

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(length = 32, nullable = false)
	public String getCdid() {
		return cdid;
	}

	public void setCdid(String cdid) {
		this.cdid = cdid;
	}

	public String getCdmc() {
		return cdmc;
	}

	public void setCdmc(String cdmc) {
		this.cdmc = cdmc;
	}

	public String getCdlj() {
		return cdlj;
	}

	public void setCdlj(String cdlj) {
		this.cdlj = cdlj;
	}

	public String getCddj() {
		return cddj;
	}

	public void setCddj(String cddj) {
		this.cddj = cddj;
	}

	@Column(length = 32, nullable = true)
	public String getSjcdid() {
		return sjcdid;
	}

	public void setSjcdid(String sjcdid) {
		this.sjcdid = sjcdid;
	}

	public String getKczyhlx() {
		return kczyhlx;
	}

	public void setKczyhlx(String kczyhlx) {
		this.kczyhlx = kczyhlx;
	}

	public Boolean getSfxs() {
		return sfxs;
	}

	public void setSfxs(Boolean sfxs) {
		this.sfxs = sfxs;
	}

	public Boolean getSfsc() {
		return sfsc;
	}

	public void setSfsc(Boolean sfsc) {
		this.sfsc = sfsc;
	}

}
