package cn.fam.dao;
import java.util.List;

import cn.fam.entity.FixedAssets;
import cn.fam.entity.Staff;
public interface FixedAssetsDao {
/**
 * �ӿڣ��ṩ�Թ̶��ʲ����ݱ�����ĳ��󷽷�
 */
	int addNew(FixedAssets fxaset); //����һ���̶��ʲ�
	int update(FixedAssets fxaset); //����һ���̶��ʲ�
	int delete(FixedAssets fxaset); //ɾ��һ���̶��ʲ�
	FixedAssets findByID(FixedAssets fxaset); //ͨ���ʲ�id�����ݿ����
	List<FixedAssets> findByCatory(FixedAssets fxaset); //ͨ���������
	List<FixedAssets> findByName(FixedAssets fxaset); //ͨ��С�����
	/**
	 * ͨ��ʹ����id����������Ϣ
	 * @param staff Ա��
	 * @return
	 * @throws Exception 
	 */
	List<FixedAssets> findByuserID(Staff staff) throws Exception; 
	List<FixedAssets> findByNameCanBeRented(FixedAssets fxaset);//����ʱ������С�����ֲ���
}
