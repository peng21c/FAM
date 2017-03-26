package cn.fam.dao;
import java.util.List;

import cn.fam.entity.FixedAssets;
import cn.fam.entity.Staff;
public interface FixedAssetsDao {
/**
 * 接口，提供对固定资产数据表操作的抽象方法
 */
	int addNew(FixedAssets fxaset); //增加一个固定资产
	int update(FixedAssets fxaset); //更改一个固定资产
	int delete(FixedAssets fxaset); //删除一个固定资产
	FixedAssets findByID(FixedAssets fxaset); //通过资产id在数据库查找
	List<FixedAssets> findByCatory(FixedAssets fxaset); //通过大类查找
	List<FixedAssets> findByName(FixedAssets fxaset); //通过小类查找
	/**
	 * 通过使用者id查找领用信息
	 * @param staff 员工
	 * @return
	 * @throws Exception 
	 */
	List<FixedAssets> findByuserID(Staff staff) throws Exception; 
	List<FixedAssets> findByNameCanBeRented(FixedAssets fxaset);//借用时，根据小类名字查找
}
