package cn.fam.entity;

public class Name {
/**
 * С���ʵ����
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
