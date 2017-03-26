package cn.fam.dao.Impl;
import cn.fam.dao.AssetsRentDao;
import cn.fam.dao.BaseDao;
import cn.fam.entity.AssetsRent;
/** 
 * @author jiajia
 * * create 2016-7-17
 * �޸� 2016-7-18
 */
//������Ϣ������ʲ�ʱ������һ��ļ�¼
public class AssetsRentImpl extends BaseDao implements AssetsRentDao{

	@Override
	public int addNew(AssetsRent asr) {
		// TODO Auto-generated method stub
		//��������Ϣ���еĶ�Ӧ��Ϣ����ţ��ʲ���ţ�������ڣ������߱�ţ��������Ա��������;����ע�����
		String sql = "insert into rentInfo(fixedAssetsId,rentDate,userId,rentManagerName,useTo,remark) values (?,?,?,?,?,?)";
		Object[] param={asr.getFixedAssetsId(),asr.getRentDate(),asr.getUserId(),asr.getRentManagerName(),asr.getUseTo(),asr.getRemark()};
		int result = this.exceuteUpdate(sql, param);
		
		return result;
	}
	@Override
	//���������ʲ���Ϣ���黹ʱ��
	public int update(AssetsRent asr) {
		// TODO Auto-generated method stub
		String sql="update rentInfo set retDate=?,retManagerName=?  where fixedAssetsId =?";
		Object[] param={asr.getRetDate(),asr.getRetManagerName(),asr.getFixedAssetsId()};
		int result = this.exceuteUpdate(sql, param);
		return result;
	}
/**
 * �̳�BaseDao,ʵ��AssectsRentDao�ӿ��еķ���
 */
}
