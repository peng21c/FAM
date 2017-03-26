package cn.fam.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.fam.dao.BaseDao;
import cn.fam.dao.NameDao;
import cn.fam.entity.Name;
/**
*
*
* @author ������
* create 2016-7-17
* modify by ������ 2016-7-17
*
*/

public class NameImpl extends BaseDao implements NameDao {

	@Override
	/**
	 * ���һ��С��
	 */
	public int addNew(Name name) {
		String sql = " insert into assetsname(name,BelongedCategoryId) values (?,?)";
		Object[] param={name.getName(),name.getBelongedCategoryId()};
		int result = this.exceuteUpdate(sql, param);
		return result;
		
	}

	@Override
	  /**
     * ���ݴ���id����С��
     */
	public List<Name> findByCagetoryId(Name name) {
		String sql = "select * from assetsname where BelongedCategoryId=?";
		Object[] param={name.getBelongedCategoryId()};
		return exceuteQueueFromName(sql,param);
	}
}


