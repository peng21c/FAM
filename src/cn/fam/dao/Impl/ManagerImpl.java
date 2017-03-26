package cn.fam.dao.Impl;

import cn.fam.dao.BaseDao;
import cn.fam.dao.ManagerDao;
import cn.fam.entity.Manager;

public class ManagerImpl extends BaseDao implements ManagerDao{

	@Override
	public Manager login(Manager m)  {
		// TODO Auto-generated method stub
		String sql="select * from manager where name =? and password=?";
		Object[] param={m.getName(),m.getPassPwd()};
		return exceuteQueueFromManager(sql,param);
	}

	@Override
	public int resetPwd(Manager m) {
		// TODO Auto-generated method stub
		String sql="update Manager set password=? where name =? and id=?";
		Object[] param={m.getPassPwd(),m.getName(),m.getId()};
		return this.exceuteUpdate(sql, param);
	}

}
