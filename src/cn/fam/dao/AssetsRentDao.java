package cn.fam.dao;
import cn.fam.entity.AssetsRent;
public interface AssetsRentDao {
/**
 * �ӿڣ��ṩ�Թ̶��ʲ��������ݱ�����ĳ��󷽷���
 */
	int addNew(AssetsRent asr);//���˽���ʱ������һ����¼
	int update(AssetsRent asr); //�黹ʱ���޸Ľ�����Ϣ���ݱ�
}
