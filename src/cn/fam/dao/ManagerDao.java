package cn.fam.dao;
import cn.fam.entity.*;
import java.sql.SQLException;

import cn.fam.entity.Manager;
/**
 * 管理员登录接口
 * @author yzy
 * create 2016-7-16
 * modify by yzy 2016-7-17
 */
public interface ManagerDao {
	
	/**
	 * 管理员登录
	 * @param m 一个管理员对象
	 * @return 登陆成功则返回true,登录失败返回false
	 * @throws SQLException 
	 * @throws Exception 
	 */
	Manager login(Manager m) throws SQLException, Exception;
	/**
	 * 管理员重置密码
	 * @param m 已登录的管理员对象
	 * @return 更改成功返回1,不成功返回-1
	 */
	int resetPwd(Manager m);
}
