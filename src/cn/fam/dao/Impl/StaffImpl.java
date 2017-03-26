package cn.fam.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.fam.dao.BaseDao;
import cn.fam.dao.StaffDao;
import cn.fam.entity.Staff;

public class StaffImpl extends BaseDao implements StaffDao{

	@Override
	/**
	 * @author jiajia
	 * create 2016-7-17
	 * 修改 2016-7-18
	 */
	//判断员工是否存在的函数）
	public Staff isExist(Staff temp) {
		String sql="select * from staff where id =? and name=?";
		//按照编号与姓名查找（调用BaseDao中的查找员工函数）
		Object[] param ={temp.getId(),temp.getName()};
		Staff s=exceuteQueueFromStaff(sql, param);
		if(s==null)
		{
			System.out.println("hai没找到");
		}
			return s;
		
	
		
	}

	

}
