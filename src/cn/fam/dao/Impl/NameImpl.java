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
* @author 孟祥蕊
* create 2016-7-17
* modify by 孟祥蕊 2016-7-17
*
*/

public class NameImpl extends BaseDao implements NameDao {

	@Override
	/**
	 * 添加一个小类
	 */
	public int addNew(Name name) {
		String sql = " insert into assetsname(name,BelongedCategoryId) values (?,?)";
		Object[] param={name.getName(),name.getBelongedCategoryId()};
		int result = this.exceuteUpdate(sql, param);
		return result;
		
	}

	@Override
	  /**
     * 根据大类id查找小类
     */
	public List<Name> findByCagetoryId(Name name) {
		String sql = "select * from assetsname where BelongedCategoryId=?";
		Object[] param={name.getBelongedCategoryId()};
		return exceuteQueueFromName(sql,param);
	}
}


