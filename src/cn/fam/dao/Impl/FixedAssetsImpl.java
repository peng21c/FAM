package cn.fam.dao.Impl;
import java.util.List;

import cn.fam.dao.BaseDao;
import cn.fam.dao.FixedAssetsDao;
import cn.fam.entity.FixedAssets;
import cn.fam.entity.Staff;
public class FixedAssetsImpl extends BaseDao implements FixedAssetsDao {
	/**
	 * �̳�BaseDao,ʵ��FixedAssetsDao�ӿ��������ķ���
	 * @author ����
	 */
	@Override
	public int addNew(FixedAssets fxaset) {
		String sql = "insert into assets(id,name,category,model,price,buyDate,state,userId,remark) values (?,?,?,?,?,?,?,?,?)";
		Object[] param={fxaset.getId(),fxaset.getName(),fxaset.getCategory(),fxaset.getModel(),fxaset.getPrice(),fxaset.getBuyDate(),fxaset.getState(),fxaset.getUserId(),fxaset.getRemark()};
		int result = this.exceuteUpdate(sql, param);
		return result;
	}

	/**
	 * �����ʲ���
	 * @author ����
	 */
	public int update(FixedAssets fxaset) {
		String sql="update assets set userid=?  where id =?";
		Object[] param={fxaset.getUserId(),fxaset.getId()};
		int result = this.exceuteUpdate(sql, param);
		return result;

	}


	/**
	 * �����ʲ�idɾ���ʲ�
	 * @author ����
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
	//�����ʲ���Ų����ʲ���Ϣ�ĺ���
	
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
	* @author ������
	* create 2016-7-17
	* modify by ������ 2016-7-17
	*���ݴ������
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
	* @author ������
	* create 2016-7-17
	* modify by ������ 2016-7-17
	*����С�����
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
	 * ����С�ࣨ���������������ģ�δ��������ʲ�
	 * @author ���ϸ�
	 * create 2016-7-18
	 */
	public List<FixedAssets> findByNameCanBeRented(FixedAssets fxaset) {
		String sql="select * from assets where name=? and state='����' and userId=0";
		Object[] param ={fxaset.getName()};
		List<FixedAssets> fixedAssetsList=this.exceuteQueueFromfixedAssets(sql, param);
		return fixedAssetsList;
	}

	
	

}
