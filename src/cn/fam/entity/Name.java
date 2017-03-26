package cn.fam.entity;

public class Name {
/**
 * 小类的实体类
 */
	private String name;
	private int BelongedCategoryId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBelongedCategoryId() {
		return BelongedCategoryId;
	}
	public void setBelongedCategoryId(int belongedCategoryId) {
		BelongedCategoryId = belongedCategoryId;
	}
	
}
