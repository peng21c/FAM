package cn.fam.entity;

public class Manager {
/**
 * 管理员类
 */
	private int id;//编号吧
	private String name;
	private String passPwd;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassPwd() {
		return passPwd;
	}
	public void setPassPwd(String passPwd) {
		this.passPwd = passPwd;
	}
	
}
