package cn.fam.dao.Impl;
import cn.fam.dao.AssetsRentDao;
import cn.fam.dao.BaseDao;
import cn.fam.entity.AssetsRent;
/** 
 * @author jiajia
 * * create 2016-7-17
 * 修改 2016-7-18
 */
//领用信息表，借出资产时，增加一天的记录
public class AssetsRentImpl extends BaseDao implements AssetsRentDao{

	@Override
	public int addNew(AssetsRent asr) {
		// TODO Auto-generated method stub
		//将领用信息表中的对应信息（编号，资产编号，借出日期，领用者编号，借出管理员姓名，用途，备注）添加
		String sql = "insert into rentInfo(fixedAssetsId,rentDate,userId,rentManagerName,useTo,remark) values (?,?,?,?,?,?)";
		Object[] param={asr.getFixedAssetsId(),asr.getRentDate(),asr.getUserId(),asr.getRentManagerName(),asr.getUseTo(),asr.getRemark()};
		int result = this.exceuteUpdate(sql, param);
		
		return result;
	}
	@Override
	//更新领用资产信息表，归还时的
	public int update(AssetsRent asr) {
		// TODO Auto-generated method stub
		String sql="update rentInfo set retDate=?,retManagerName=?  where fixedAssetsId =?";
		Object[] param={asr.getRetDate(),asr.getRetManagerName(),asr.getFixedAssetsId()};
		int result = this.exceuteUpdate(sql, param);
		return result;
	}
/**
 * 继承BaseDao,实现AssectsRentDao接口中的方法
 */
}
