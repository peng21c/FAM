package cn.fam.dao;
import cn.fam.entity.*;
import java.sql.SQLException;

import cn.fam.entity.Manager;
/**
 * ����Ա��¼�ӿ�
 * @author yzy
 * create 2016-7-16
 * modify by yzy 2016-7-17
 */
public interface ManagerDao {
	
	/**
	 * ����Ա��¼
	 * @param m һ������Ա����
	 * @return ��½�ɹ��򷵻�true,��¼ʧ�ܷ���false
	 * @throws SQLException 
	 * @throws Exception 
	 */
	Manager login(Manager m) throws SQLException, Exception;
	/**
	 * ����Ա��������
	 * @param m �ѵ�¼�Ĺ���Ա����
	 * @return ���ĳɹ�����1,���ɹ�����-1
	 */
	int resetPwd(Manager m);
}
