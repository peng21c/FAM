package cn.fam.dao;
import java.util.List;

import cn.fam.entity.Category;
public interface CategoryDao {
/**
 * 对大类数据表的操作方法
 */
	public int addCategory(Category category);//添加一个大类
	public List<Category> findAll(); //展示所有的大类，添加新资产时使用
}
