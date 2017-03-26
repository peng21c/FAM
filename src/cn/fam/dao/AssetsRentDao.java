package cn.fam.dao;
import cn.fam.entity.AssetsRent;
public interface AssetsRentDao {
/**
 * 接口，提供对固定资产领用数据表操作的抽象方法。
 */
	int addNew(AssetsRent asr);//有人借用时，增加一条记录
	int update(AssetsRent asr); //归还时，修改借用信息数据表
}
