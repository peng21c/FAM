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
	 * �޸� 2016-7-18
	 */
	//�ж�Ա���Ƿ���ڵĺ�����
	public Staff isExist(Staff temp) {
		String sql="select * from staff where id =? and name=?";
		//���ձ�����������ң�����BaseDao�еĲ���Ա��������
		Object[] param ={temp.getId(),temp.getName()};
		Staff s=exceuteQueueFromStaff(sql, param);
		if(s==null)
		{
			System.out.println("haiû�ҵ�");
		}
			return s;
		
	
		
	}

	

}
