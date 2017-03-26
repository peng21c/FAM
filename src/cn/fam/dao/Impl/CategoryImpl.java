package cn.fam.dao.Impl;

import java.util.List;

import cn.fam.dao.BaseDao;
import cn.fam.dao.CategoryDao;
import cn.fam.entity.Category;

public class CategoryImpl extends BaseDao implements CategoryDao {

	@Override
	public int addCategory(Category category) {
		String sql = " SET IDENTITY_INSERT  categoryInfo ON insert into categoryInfo(id,name) values (?,?)";
		Object[] param={category.getId(),category.getName()};
		int result = this.exceuteUpdate(sql, param);
		return result;
	}

	/**
	 * 查找所有的大类，添加新资产时使用
	 * 邓李
	 */
public List<Category> findAll() {
		List<Category> cates=null;
		String sql = "select * from categoryInfo";
		cates=exceuteQueueFromCatedory(sql);
		return cates;
	}


	
}
