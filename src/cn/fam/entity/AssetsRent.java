package cn.fam.entity;

import java.util.Date;

public class AssetsRent {
/**
 * ������Ϣ��
 */
	private int id; //��ʾ
	private int FixedAssetsId; 
	private Date rentDate;
	private int userId;
	private String rentManagerName;
	private String useTo; //��;
	private String remark; //��ע
	private Date retDate;
	private String retManagerName;
	public int getId() {
		return id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFixedAssetsId() {
		return FixedAssetsId;
	}
	public void setFixedAssetsId(int fixedAssetsId) {
		FixedAssetsId = fixedAssetsId;
	}
	public Date getRentDate() {
		return rentDate;
	}
	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}
	public String getRentManagerName() {
		return rentManagerName;
	}
	public void setRentManagerName(String rentManagerName) {
		this.rentManagerName = rentManagerName;
	}
	public String getUseTo() {
		return useTo;
	}
	public void setUseTo(String useTo) {
		this.useTo = useTo;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getRetDate() {
		return retDate;
	}
	public void setRetDate(Date retDate) {
		this.retDate = retDate;
	}
	public String getRetManagerName() {
		return retManagerName;
	}
	public void setRetManagerName(String retManagerName) {
		this.retManagerName = retManagerName;
	}

}
