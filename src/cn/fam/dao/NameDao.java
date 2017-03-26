package cn.fam.dao;
import java.util.List;

import cn.fam.entity.Name;
public interface NameDao {
/**
 * 小类接口
 */
	public int addNew(Name name);
	public List<Name> findByCagetoryId(Name name);
}
