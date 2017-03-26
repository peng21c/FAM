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
 * ���ݿ�������رչ�����
 * @author zch
 * create 2016-7-11
 * modify by yzy 2016-7-15
 */
public class BaseDao {
	
	private String driver = 
			"com.microsoft.sqlserver.jdbc.SQLServerDriver";// ���ݿ������ַ���
	private String url = 
		"jdbc:sqlserver://localhost:1433;DatabaseName=System";// ����URL�ַ���
	private  String user = "zch"; // ���ݿ��û���
	private  String password = "123456"; // �û�����
	Connection conn = null;// �������Ӷ���
	/**
	 * ��ȡ���ݿ����Ӷ���
	 */
	public Connection getConnection() {
		if(conn==null){
			// ��ȡ���Ӳ������쳣
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				e.printStackTrace();// �쳣����
			}
		}	
		return conn;// �������Ӷ���
	}
	/**
	 * �ر����ݿ����ӡ�
	 * @param conn ���ݿ�����
	 * @param stmt Statement����
	 * @param rs �����
	 */
	public void closeAll(Connection conn, Statement stmt,ResultSet rs) {
		// �����������Ϊ�գ���ر�
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// ��Statement����Ϊ�գ���ر�
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// �����ݿ����Ӷ���Ϊ�գ���ر�
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
	 * �������ݱ������ɾ���ĵĲ���
	 * @param sql Ԥ����� SQL ���          
	 * @param param Ԥ����� SQL ����еġ������������ַ�������          
	 * @return ��Ӱ�������
	 */
	public int exceuteUpdate(String preparedSql, Object[] param) {
		PreparedStatement pstmt = null;
		int num = 0;
		conn =  getConnection(); 
		try {
			pstmt = conn.prepareStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
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
	 * �������ݱ������ɾ���ĵĲ���
	 * @param preparedSql Ԥ����� SQL ���  
	 * @param param Ԥ����� SQL ����еġ������������ַ�������  
	 * @return �Ƿ��з��ϱ�׼�Ķ���
	 */
	public boolean exceute(String preparedSql, Object[] param) {
		PreparedStatement pstmt = null;
		boolean rs=false;
		conn =  getConnection(); 
		try {
			pstmt = conn.prepareStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
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
	 *������������ѯ�̶��ʲ����ݿ�
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
					pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
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
	 *������������ѯС�����ݿ�,����µĹ̶��ʲ�ʱʹ��
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
					psmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
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
	 *������������ѯ������Ϣ���ݿ⣬�黹ʱʹ��
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public AssetsRent exceuteQueueFromRent(String preparedSql, Object[] param) throws Exception
	{
		AssetsRent list=null;
		//��Ӵ���
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
	 * ����Ա��¼ʱ�������Ƿ��¼�Ϸ������������ݿ⣬����У��������ݿ��ѯ���ĵ�¼Ա
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
					pstmt.setObject(i + 1, param[i]); // ΪԤ����sql���ò���
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
	 * �յ����ò���ʱ����ѯԱ�����ݱ��������Ա���ǲ��ǹ�˾��˾Ա��������ǣ�����Ա��ʵ��
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
			System.out.println("û�ҵ�");
		}
		return staff;
	}

	/**
	 *������������ѯ�������ݿ�,����µĹ̶��ʲ�ʱʹ�ã��������д��࣬selete*,���Բ��Ӳ�������
	 * @param sql
	 * @return
	 * @throws Exception
	 * 
	 * �������еĴ��࣬������ʲ�ʱʹ��
	 * ����
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
