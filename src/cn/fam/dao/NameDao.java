package cn.fam.dao;
import java.util.List;

import cn.fam.entity.Name;
public interface NameDao {
/**
 * С��ӿ�
 */
	public int addNew(Name name);
	public List<Name> findByCagetoryId(Name name);
}
