package cn.fam.dao.Impl;
import java.util.List;

import cn.fam.dao.BaseDao;
import cn.fam.dao.FixedAssetsDao;
import cn.fam.entity.FixedAssets;
import cn.fam.entity.Staff;
public class FixedAssetsImpl extends BaseDao implements FixedAssetsDao {
	/**
	 * 继承BaseDao,实现FixedAssetsDao接口中声明的方法
	 * @author 邓李
	 */
	@Override
	public int addNew(FixedAssets fxaset) {
		String sql = "insert into assets(id,name,category,model,price,buyDate,state,userId,remark) values (?,?,?,?,?,?,?,?,?)";
		Object[] param={fxaset.getId(),fxaset.getName(),fxaset.getCategory(),fxaset.getModel(),fxaset.getPrice(),fxaset.getBuyDate(),fxaset.getState(),fxaset.getUserId(),fxaset.getRemark()};
		int result = this.exceuteUpdate(sql, param);
		return result;
	}

	/**
	 * 更新资产表
	 * @author 邓李
	 */
	public int update(FixedAssets fxaset) {
		String sql="update assets set userid=?  where id =?";
		Object[] param={fxaset.getUserId(),fxaset.getId()};
		int result = this.exceuteUpdate(sql, param);
		return result;

	}


	/**
	 * 根据资产id删除资产
	 * @author 邓李
	 */
	public int delete(FixedAssets fxaset) {
		String sql="delete from assets where  id=?";
		Object[] param={fxaset.getId()};
		int result = this.exceuteUpdate(sql, param);
		return result;
	}

	/**
	 * @author jiajia
	 * create 2016-7-17
	 */
	//根据资产编号查找资产信息的函数
	
	public FixedAssets findByID(FixedAssets fxaset) {
		String sql = "select * from assets where id=? ";
		Object[] param={fxaset.getId()};
		List<FixedAssets> list= exceuteQueueFromfixedAssets(sql, param);
		FixedAssets fixedAssets=null;
		if(list.size()!=0)
		{
			fixedAssets=list.get(0);
		}
		return fixedAssets;
	}


	@Override
	/**
	* @author 孟祥蕊
	* create 2016-7-17
	* modify by 孟祥蕊 2016-7-17
	*根据大类查找
	*/
	public List<FixedAssets> findByCatory(FixedAssets fxaset) {
		String sql = "select *  from assets where category=?";
		Object[] param={fxaset.getCategory()};
		return exceuteQueueFromfixedAssets(sql,param);
	}

	@Override
	/**
	*
	*
	* @author 孟祥蕊
	* create 2016-7-17
	* modify by 孟祥蕊 2016-7-17
	*根据小类查找
	*/
	public List<FixedAssets> findByName(FixedAssets fxaset) {
		String sql = "select * from assets where name=?";
		Object[] param={fxaset.getName()};
		return exceuteQueueFromfixedAssets(sql,param);
	}

	@Override
	public List<FixedAssets> findByuserID(Staff staff) {
		// TODO Auto-generated method stub
		String sql="select * from  assets where userId=?";
		Object[] param ={staff.getId()};
		List<FixedAssets> fixedAssetsList=this.exceuteQueueFromfixedAssets(sql, param);
		return fixedAssetsList;
	}
	
	/**
	 * 根据小类（姓名）查找正常的，未被借出的资产
	 * @author 贾孟歌
	 * create 2016-7-18
	 */
	public List<FixedAssets> findByNameCanBeRented(FixedAssets fxaset) {
		String sql="select * from assets where name=? and state='正常' and userId=0";
		Object[] param ={fxaset.getName()};
		List<FixedAssets> fixedAssetsList=this.exceuteQueueFromfixedAssets(sql, param);
		return fixedAssetsList;
	}

	
	

}
