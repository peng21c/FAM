package cn.fam.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.fam.entity.AssetsRent;
import cn.fam.entity.Category;
import cn.fam.entity.FixedAssets;
import cn.fam.entity.Manager;
import cn.fam.entity.Name;
import cn.fam.entity.Staff;
/**
 * 数据库连接与关闭工具类
 * @author zch
 * create 2016-7-11
 * modify by yzy 2016-7-15
 */
public class BaseDao {
	
	private String driver = 
			"com.microsoft.sqlserver.jdbc.SQLServerDriver";// 数据库驱动字符串
	private String url = 
		"jdbc:sqlserver://localhost:1433;DatabaseName=System";// 连接URL字符串
	private  String user = "zch"; // 数据库用户名
	private  String password = "123456"; // 用户密码
	Connection conn = null;// 数据连接对象
	/**
	 * 获取数据库连接对象。
	 */
	public Connection getConnection() {
		if(conn==null){
			// 获取连接并捕获异常
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				e.printStackTrace();// 异常处理
			}
		}	
		return conn;// 返回连接对象
	}
	/**
	 * 关闭数据库连接。
	 * @param conn 数据库连接
	 * @param stmt Statement对象
	 * @param rs 结果集
	 */
	public void closeAll(Connection conn, Statement stmt,ResultSet rs) {
		// 若结果集对象不为空，则关闭
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 若Statement对象不为空，则关闭
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 若数据库连接对象不为空，则关闭
		if (conn != null) {
			try {
				conn.close();
				conn=null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 六个数据表的增、删、改的操作
	 * @param sql 预编译的 SQL 语句          
	 * @param param 预编译的 SQL 语句中的‘？’参数的字符串数组          
	 * @return 受影响的行数
	 */
	public int exceuteUpdate(String preparedSql, Object[] param) {
		PreparedStatement pstmt = null;
		int num = 0;
		conn =  getConnection(); 
		try {
			pstmt = conn.prepareStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			num = pstmt.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			closeAll(conn, pstmt, null);
		}
		return num;
	}
	/**
	 * 六个数据表的增、删、改的操作
	 * @param preparedSql 预编译的 SQL 语句  
	 * @param param 预编译的 SQL 语句中的‘？’参数的字符串数组  
	 * @return 是否有符合标准的对象
	 */
	public boolean exceute(String preparedSql, Object[] param) {
		PreparedStatement pstmt = null;
		boolean rs=false;
		conn =  getConnection(); 
		try {
			pstmt = conn.prepareStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			rs = pstmt.execute(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} /*finally{
			closeAll(conn, pstmt, null);
		}*/
		return rs;
	}
	/**
	 *根据条件，查询固定资产数据库
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<FixedAssets> exceuteQueueFromfixedAssets(String preparedSql, Object[] param)
	{
		List<FixedAssets> list=new ArrayList<FixedAssets>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn =  getConnection(); 
		try {
			pstmt = conn.prepareStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			rs = pstmt.executeQuery(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		try {
			while(rs.next()){
				FixedAssets fixedAssets=new FixedAssets();
				fixedAssets.setId(rs.getInt(1));
				fixedAssets.setName(rs.getString(2));
				fixedAssets.setCategory(rs.getString(3));
				fixedAssets.setModel(rs.getString(4));
				fixedAssets.setPrice(rs.getInt(5));
				fixedAssets.setBuyDate(rs.getDate(6));
				fixedAssets.setState(rs.getString(7));
				fixedAssets.setUserId(rs.getInt(8));
				fixedAssets.setRemark(rs.getString(9));
				list.add(fixedAssets);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	/**
	 *根据条件，查询小类数据库,添加新的固定资产时使用
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<Name> exceuteQueueFromName(String preparedSql, Object[] param) 
	{
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		List<Name> Names =null;
		conn=this.getConnection();
		try {
			psmt = conn.prepareStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					psmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			rs = psmt.executeQuery(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		try {
			Names =new ArrayList<Name>();
			while(rs.next())
			{
				 Name name = new Name();
				 name.setName(rs.getString("name"));
				 name.setBelongedCategoryId(rs.getInt("BelongedCategoryId"));
				 Names.add(name);
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		finally
		{
			this.closeAll(conn, psmt, rs);
		}
		
		return Names;
		
	}
	/**
	 * * @author jiajia
	 * create 2016-7-17
	 *根据条件，查询领用信息数据库，归还时使用
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public AssetsRent exceuteQueueFromRent(String preparedSql, Object[] param) throws Exception
	{
		AssetsRent list=null;
		//添加代码
		PreparedStatement psmt=null;
		ResultSet rs=null;
		conn=getConnection();
		try
		{
			psmt=conn.prepareStatement(preparedSql);
			if(param!=null)
			{
				for(int i=0;i<param.length;i++)
				{
					psmt.setObject(i+1, param[i]);
				}
			}
			rs=psmt.executeQuery();
			while(rs.next())
			{
				list.setId(rs.getInt("id"));
				list.setFixedAssetsId(rs.getInt("fixedAssetsId"));
				list.setRentDate(rs.getDate("rentDate"));
				list.setUserId(rs.getInt("userId"));
				list.setRentManagerName(rs.getString("rentManagerName"));
				list.setUseTo(rs.getString("useTo"));
				list.setRemark(rs.getString("remark"));
				list.setRetDate(rs.getDate("retDate"));
				list.setRetManagerName(rs.getString("retManagerName"));
				
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		finally
		{
			this.closeAll(conn, psmt, rs);
		}
		return list;
		
	}
	




	
	/**
	 * 管理员登录时，查找是否登录合法，，查找数据库，如果有，返回数据库查询到的登录员
	 * @param preparedSql
	 * @author yzy
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Manager exceuteQueueFromManager(String preparedSql, Object[] param) 
	{
		Manager manager=new Manager();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn =  getConnection(); 
		try {
			pstmt = conn.prepareStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			rs = pstmt.executeQuery(); 
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		try {
			while(rs.next()){
				manager.setId(rs.getInt(1));
				manager.setName(rs.getString(2));
				manager.setPassPwd(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return manager;
		
	}
	/**
	 * 收到领用财务时，查询员工数据表，看看这个员工是不是公司公司员工，如果是，返回员工实例
	 * * * @author jiajia
	 * create 2016-7-17
	 * @param preparedSql
	 * @param param
	 * @return
	 * @throws Exception
	 */
	public Staff exceuteQueueFromStaff(String preparedSql, Object[] param)
	{
		PreparedStatement psmt=null;
		ResultSet rs=null;
		Staff staff=null;
		conn=getConnection();
		try
		{
			psmt=conn.prepareStatement(preparedSql);
			if(param!=null)
			{
				for(int i=0;i<param.length;i++)
				{
					psmt.setObject(i+1, param[i]);
				}
			}
			rs=psmt.executeQuery();
			while(rs.next())
			{
				staff=new Staff();
				staff.setId(rs.getInt("id"));
				staff.setName(rs.getString("name"));
				staff.setJob(rs.getString("job"));
				staff.setRemark(rs.getString("remark"));
			}
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		finally
		{
			this.closeAll(conn, psmt, rs);
		}
		if(staff==null)
		{
			System.out.println("没找到");
		}
		return staff;
	}

	/**
	 *根据条件，查询大类数据库,添加新的固定资产时使用，返回所有大类，selete*,所以不加查找条件
	 * @param sql
	 * @return
	 * @throws Exception
	 * 
	 * 查找所有的大类，添加新资产时使用
	 * 邓李
	 */
	public List<Category> exceuteQueueFromCatedory(String Sql) 
	{
		List<Category> list=new ArrayList<Category>();
		Connection conn=null;
		PreparedStatement psmt=null;
		ResultSet rs=null;
		
		try
		{
			conn=this.getConnection();
			
			psmt=conn.prepareStatement(Sql);
			rs=psmt.executeQuery();
			if(rs!=null)
			{
				while(rs.next())
				{
					Category cate=new Category();
					cate.setId(rs.getInt("id"));
					cate.setName(rs.getString("name"));
					list.add(cate);
				}
			}
			
		}
		catch(SQLException ex)
		{
			ex.printStackTrace();
		}
		
		finally
		{
			this.closeAll(conn, psmt, rs);
		}
		
		return list;
		
	}
	
	
}
