package cn.fam.dao;
import java.util.List;

import cn.fam.entity.Category;
public interface CategoryDao {
/**
 * �Դ������ݱ�Ĳ�������
 */
	public int addCategory(Category category);//���һ������
	public List<Category> findAll(); //չʾ���еĴ��࣬������ʲ�ʱʹ��
}
