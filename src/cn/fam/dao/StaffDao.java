package cn.fam.dao;
import cn.fam.entity.Staff;
public interface StaffDao {
/**
 * 接口，声明对员工数据表操作的抽象方法
 */
	public Staff isExist(Staff temp);
}
