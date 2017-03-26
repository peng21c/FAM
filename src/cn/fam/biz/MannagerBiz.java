package cn.fam.biz;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import cn.fam.dao.Impl.AssetsRentImpl;
import cn.fam.dao.Impl.CategoryImpl;
import cn.fam.dao.Impl.FixedAssetsImpl;
import cn.fam.dao.Impl.ManagerImpl;
import cn.fam.dao.Impl.NameImpl;
import cn.fam.dao.Impl.StaffImpl;
//import cn.fam.entity.Category;
import cn.fam.entity.AssetsRent;
import cn.fam.entity.Category;
import cn.fam.entity.FixedAssets;
import cn.fam.entity.Manager;
import cn.fam.entity.Name;
import cn.fam.entity.Staff;
import cn.fam.test.*;
public class MannagerBiz {

	/**
	 * 管理员登录，有三次机会
	 * @author yzy
	 * @return 登陆成功则返回成功的实例,不成功则返回null
	 */

	public static Manager login()
	{
		Scanner input=new Scanner(System.in);
		int i=0;
		ManagerImpl MI=new ManagerImpl();
		for(i=0;i<3;i++){
			Manager manager=new Manager();
			System.out.println("请输入用户名：");
			String inputname=input.next();
			System.out.println("请输入密码：");
			String inputpwd=input.next();
			manager.setName(inputname);
			manager.setPassPwd(inputpwd);
			manager.setId(MI.login(manager).getId());
			if(manager.getId()!=0){
				System.out.println("登录成功，欢迎     "+manager.getName());
				return manager;
			}else{
				System.out.println("用户名或密码错误!您还有 "+(2-i)+"次机会");
			}
		}
		if(i==3){
			System.out.println("登录失败，你疯了");
			System.exit(0);
		}
		return null;	
	}
	/**
	 * 管理员重置密码,输入新密码，然后更新数据库，和mng的密码变量
	 * @author yzy
	 */
	public static void resetPwd()
	{
		Scanner input=new Scanner(System.in);
		Manager manager=new Manager();
		ManagerImpl MI=new ManagerImpl();
		manager.setId(Test.manager.getId());
		manager.setName(Test.manager.getName());
		manager.setPassPwd(Test.manager.getPassPwd());
		do{
			System.out.println("输入旧密码:");
			String oldpwd=input.next();
			System.out.println("输入新密码:");
			String newpwd=input.next();
			System.out.println("确认新密码:");
			String checkpwd=input.next();
			if(!oldpwd.equals(manager.getPassPwd())){
				System.out.println("旧密码输入错误，请重新输入：");
				continue;
			}else{
				if(!newpwd.equals(checkpwd)){
					System.out.println("两次密码输入不一致，请重新输入：");
					continue;
				}else{
					manager.setPassPwd(newpwd);
					if(MI.resetPwd(manager)!=1){
						System.out.println("密码修改失败");
						return;
					}else{
						System.out.println("密码修改成功");
						Test.manager.setPassPwd(newpwd);
						break;
					}
				}
			}	
		}while(true);
		return;
	}

	/**
	 * 添加固定资产，先显示所有的大类，让管理员选择，然后显示这个大类下的小类，让管理员选择，再输入其他信息
	 * @author 邓李
	 */
	public static void addFixedAssets()
	{
		FixedAssetsImpl fixdao=new FixedAssetsImpl();
		FixedAssets fixaset=new FixedAssets();
		Scanner input = new Scanner(System.in);
		System.out.println("请在所有的大类中选择一个大类的id");
		CategoryImpl cated=new CategoryImpl();
		List<Category> list=cated.findAll();
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i).getId()+"、   "+list.get(i).getName());
		}
		int cate=input.nextInt();
		fixaset.setCategory(list.get(cate).getName());
		System.out.println("请在大类下的的小类中选择一个");
		Name name= new Name();
		name.setBelongedCategoryId(cate);
		NameImpl named=new NameImpl();
		List<Name> listname=named.findByCagetoryId(name);
		for(int i=0;i<listname.size();i++)
		{
			System.out.println(listname.get(i).getBelongedCategoryId()+"、   "+listname.get(i).getName());
		}
		int nameid=input.nextInt();
		fixaset.setName(listname.get(nameid).getName());
		System.out.println("输入要新增的资产ID");
		int id=input.nextInt();
		fixaset.setId(id);
		System.out.println("输入要新增的资产型号");
		String model=input.next();
		fixaset.setModel(model);
		System.out.println("输入要新增的资产价格");
		int price=input.nextInt();
		fixaset.setPrice(price);
		System.out.println("输入要新增的资产购买日期（年-月-日）");
		String date=input.next();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date ud=null;
		try {
			ud = sd.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date d = new java.sql.Date(ud.getTime());
		fixaset.setBuyDate(d);
		System.out.println("输入要新增的资产状态");
		String state=input.next();
		fixaset.setState(state);
		System.out.println("输入要新增的资产使用者ID");
		int userid=input.nextInt();
		fixaset.setUserId(userid);
		System.out.println("输入要新增的资产备注");
		String remark=input.next();
		fixaset.setRemark(remark);
		fixdao.addNew(fixaset);
		System.out.println("新增成功");
	}
	/**
	 * 删除固定资产
	 * @author 邓李
	 */
	public static void deleteFixedAssets()
	{
		System.out.println("输入要删除的资产的ID");
		Scanner input = new Scanner(System.in);
		int id=input.nextInt();
		FixedAssets fixaset= new FixedAssets();
		fixaset.setId(id);
		FixedAssetsImpl fixdao=new FixedAssetsImpl();
		fixdao.delete(fixaset);
		System.out.println("删除成功");

	}
	/**
	 * @author jiajia
	 * create 2016-7-17
	 * 修改 2016-7-18
	 */
	//借出，先输入一个“员工的编号和姓名，判断是不是本公司的员工，再根据输入的小类名称，查找数据库，输出可借的资产后
	//输入一个可借资产的编号，然后更新资产数据表的使用人，以及在领用数据表添加一条借用记录”，好麻烦有木有
	public static void rentFixedAssets()
	{
		//添加代码
		
		Scanner input = new Scanner(System.in);
		//调用判断是否是公司员工的函数
		System.out.println("请输入员工编号：");
	    int id=input.nextInt();
	    System.out.println("请输入员工姓名：");
		String name=input.next();
		//定义一个员工，将刚刚输入的编号与姓名赋值给该员工（因为员工查找函数的参数为一个员工）
		Staff s=new Staff();
		s.setId(id);
		s.setName(name);
		Staff s1=null;
		StaffImpl si= new StaffImpl();//为了使用StaffImpl中的函数而定义的
		//调用判断员工是否存在的函数
		s1=si.isExist(s);
		if(s1!=null)
		{
			//调用按照小类查找未借出的函数
			System.out.println("请输入资产的小类名称：");
			String xiaoname=input.next();
			FixedAssetsImpl ffi=new FixedAssetsImpl();//为了使用FixedAssetsImpl中的函数而定义的
			FixedAssets ff=new FixedAssets();//建立一个新的FixedAssets将小类名称写入
			ff.setName(xiaoname);
			List<FixedAssets> ff1=null;//按照小类名称查找函数
			ff1=ffi.findByNameCanBeRented(ff);//按照小类名称查找可借资产函数
			for(int i=0;i<ff1.size();i++)//循环输出可借资产
			{
				FixedAssets ff2=ff1.get(i);
				System.out.println(ff2.getId()+"\t"+ff2.getName()+"\t\t"+ff2.getCategory()+"\t\t"+ff2.getModel()+"     "+ff2.getPrice()+"  \t"+ff2.getBuyDate()+"\t"+ff2.getState()+"\t"+ff2.getUserId()+"\t"+ff2.getRemark());
			}
			if(ff1.size()==0)
			{
				System.out.println("抱歉，没有可供借出的资产");
				return;
			}
			
			//调用更 新资产数据表的函数
			System.out.println("请可借资产的编号：");
			int kejieid=input.nextInt();
			FixedAssetsImpl fi=new FixedAssetsImpl();//为了使用FixedAssetsImpl中的函数而定义的
			FixedAssets f=new FixedAssets();//建立一个新的FixedAssets将资产编号写入
			f.setId(kejieid);
			FixedAssets f1=null;
			f1=fi.findByID(f);//调用按照编号查找函数找到该编号的资产信息
			f1.setUserId(id);//写入使用者编号
			fi.update(f1);//更新资产信息
			
			//调用更新领用数据表的函数
			AssetsRentImpl ai=new AssetsRentImpl();//为了使用AssetsRentImpl中的函数而定义的
			AssetsRent a=new AssetsRent();//建立一个新的AssetsRent将一系列领用信息写入
			System.out.println("输入借出资产日期（年-月-日）");
			String date=input.next();
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
			java.util.Date ud=null;
			try {
				ud = sd.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			java.sql.Date d = new java.sql.Date(ud.getTime());
			a.setRentDate(d);

			
			a.setFixedAssetsId(kejieid);

			a.setUserId(id);//最开始输入的员工编号
			a.setRentManagerName(Test.manager.getName());//写入借出时姓名
			ai.addNew(a);
		}
	
	}
	/**
	 * @author jiajia
	 * create 2016-7-17
	 * 修改 2016-7-18
	 */
	//归还资产，输入已借出的固定资产的编号，然后根据编号修改资产数据表，修改领用数据表
	public static void retFixedAssets()
	{
		//添加代码
		//调用更新资产数据表的函数
		Scanner input = new Scanner(System.in);
		System.out.println("请已借出资产的编号：");
	    int yijieid=input.nextInt();
	    FixedAssetsImpl fi=new FixedAssetsImpl();//为了使用FixedAssetsImpl中的函数而定义的
		FixedAssets f=new FixedAssets();//建立一个新的FixedAssets将资产编号写入
		f.setId(yijieid);
		FixedAssets f1=null;
		f1=fi.findByID(f);//调用按照编号查找函数找到该编号的资产信息
		f1.setUserId(0);//将使用者编号改为0
		fi.update(f1);//更新资产信息
		
		//调用更新领用数据表的函数
		AssetsRentImpl ai=new AssetsRentImpl();//为了使用AssetsRentImpl中的函数而定义的
		AssetsRent a=new AssetsRent();//建立一个新的AssetsRent将一系列领用信息写入
		a.setFixedAssetsId(yijieid);
		System.out.println("输入归还资产日期（年-月-日）");
		String date=input.next();
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date ud=null;
		try {
			ud = sd.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		java.sql.Date d = new java.sql.Date(ud.getTime());
		a.setRetDate(d);
		a.setRetManagerName(Test.manager.getName());//写入归还时管理员姓名
		ai.update(a);
	}
	/**
	* @author 孟祥蕊
	* create 2016-7-17
	* modify by 孟祥蕊 2016-7-17
	 * 通过大类浏览固定资产信息
	 */
	public static void showByCategory()
	{
		System.out.println("请输入想要查找的大类名称");
		Scanner input = new Scanner(System.in);
		String category = input.next();
		FixedAssets fxaset=new FixedAssets();
		fxaset.setCategory(category);
		FixedAssetsImpl fixedassetsimpl=new FixedAssetsImpl();
		List<FixedAssets> fixedassetss = fixedassetsimpl.findByCatory(fxaset);
		if(fixedassetss == null)
			System.out.println("没有找到该大类");
		else{
			System.out.println("序号\t小类名 \t \t大类名\t\t型号 \t\t价格\t购买日期\t状态\t使用者\t备注");
			for(int i =0; i<fixedassetss.size();i++)
			{
				FixedAssets fixedasset2 =fixedassetss.get(i);
				System.out.print(fixedasset2.getId()+"\t");
				System.out.print(fixedasset2.getName()+"\t\t");
				System.out.print(fixedasset2.getCategory()+"\t\t");
				System.out.print(fixedasset2.getModel()+"       ");
				System.out.print(fixedasset2.getPrice()+"  \t");
				System.out.print(fixedasset2.getBuyDate()+"\t");
				System.out.print(fixedasset2.getState()+"\t");
				System.out.print(fixedasset2.getUserId()+"\t");
				System.out.print(fixedasset2.getRemark()+"\t");
				System.out.println();
			}
		}
	}
	/**
	*@author 孟祥蕊
	* create 2016-7-17
	* modify by 孟祥蕊 2016-7-17
	 * 通过小类（name）浏览固定资产信息
	 */
	public static void showByName()
	{
		System.out.println("请输入想要查找的小类名称");
		Scanner input = new Scanner(System.in);
		String name= input.next();
		FixedAssets fxaset=new FixedAssets();
		fxaset.setName(name);
		FixedAssetsImpl fixedassetsimpl=new FixedAssetsImpl();
		List<FixedAssets> fixedassetss = fixedassetsimpl.findByName(fxaset);
		if(fixedassetss == null)
			System.out.println("没有找到信息");
		else{
			System.out.println("序号\t小类名\t\t大类名\t\t型号\t\t价格\t购买日期\t状态\t使用者\t备注");
			for(int i =0; i<fixedassetss.size();i++)
			{
				FixedAssets fixedasset2 =fixedassetss.get(i);
				System.out.print(fixedasset2.getId()+"\t");
				System.out.print(fixedasset2.getName()+"          ");
				System.out.print(fixedasset2.getCategory()+"\t");
				System.out.print(fixedasset2.getModel()+"       ");
				System.out.print(fixedasset2.getPrice()+"\t");
				System.out.print(fixedasset2.getBuyDate()+"\t");
				System.out.print(fixedasset2.getState()+"\t");
				System.out.print(fixedasset2.getUserId()+"\t");
				System.out.print(fixedasset2.getRemark()+"\t");
				System.out.println();
			}
		}
	}
	//通过资产id浏览固定资产信息
	public static void showByFixedAssetsId()
	{
		FixedAssets fa=new FixedAssets();
		System.out.println("请输入要查询的资产id");
		int Id=new Scanner(System.in).nextInt();
		fa.setId(Id);
		FixedAssetsImpl fai=new FixedAssetsImpl();
		fa=fai.findByID(fa);
		System.out.println("查询结果：");
		System.out.println("序号\t小类名\t\t大类名\t\t型号\t\t价格\t购买日期\t状态\t使用者\t备注");
		System.out.print(fa.getId()+"\t");
		System.out.print(fa.getName()+"          ");
		System.out.print(fa.getCategory()+"\t");
		System.out.print(fa.getModel()+"       ");
		System.out.print(fa.getPrice()+"\t");
		System.out.print(fa.getBuyDate()+"\t");
		System.out.print(fa.getState()+"\t");
		System.out.print(fa.getUserId()+"\t");
		System.out.print(fa.getRemark()+"\t");
		System.out.println();
	}
/**
	
	* @author 孟祥蕊
	* create 2016-7-17
	* modify by 孟祥蕊 2016-7-17
	 * 通过使用者id浏览固定资产信息,假设输入的id都是正确的
	 */
	public static void showByuserId()
	{
		List<FixedAssets> list=new ArrayList();
		System.out.println("请输入要查询的使用者id");
		int userId=new Scanner(System.in).nextInt();
		Staff staff=new Staff(); //用于封装userid
		staff.setId(userId);     //封装userid
		FixedAssetsImpl fai=new FixedAssetsImpl();
		list=fai.findByuserID(staff);
		int i;
		System.out.println("查询结果：");
		System.out.println("资产编号      名称      类别       型号       价格    状态         使用者      备注 ");
		for(i=0;i<list.size();i++)
		{
			System.out.print(list.get(i).getId()+"  ");
			System.out.print(list.get(i).getName()+"  ");
			System.out.print(list.get(i).getCategory()+"  ");
			System.out.print(list.get(i).getModel()+"  ");
			System.out.print(list.get(i).getPrice()+"  ");
			System.out.print(list.get(i).getState()+"  ");
			System.out.print(list.get(i).getUserId()+"  ");
			System.out.println(list.get(i).getRemark());
		}
	}
	/**
	* @author 孟祥蕊
	* create 2016-7-17
	* modify by 孟祥蕊 2016-7-17
	 * 添加大类，假设不添加重复的
	 */
	public static void addCategory()
	{
		CategoryImpl cgdao=new CategoryImpl();
		String category; //保存输入的大类名称
		System.out.println("请输入要添加的大类名称:");
		category=new Scanner(System.in).next();
		Category newCategory= new Category();
		newCategory.setName(category);
		int i=cgdao.addCategory(newCategory);
		if(i==1)
		{
			System.out.println("添加大类"+newCategory.getName()+"成功");
		}
	}
	/**
	* @author 孟祥蕊
	* create 2016-7-17
	* modify by 孟祥蕊 2016-7-17
	 * 添加小类，先选择想要添加的小类属于的大类
	 */
	public static void addName()
	{
		CategoryImpl cgdao=new CategoryImpl();
		List<cn.fam.entity.Category> list=cgdao.findAll();
		String cagory;
		int i;
		System.out.println("请选择要加入的小类所属的大类序号:");
		for(i=0;i<list.size();i++)
		{
			System.out.println(i+"、   "+list.get(i).getName());
		}
		int input=new Scanner(System.in).nextInt();
		while(input<0||input>=list.size())
		{
			System.out.println("输入不在范围，请重新输入:");
			input=new Scanner(System.in).nextInt();
		}
		i=input;
		System.out.println("请输入您要为大类"+list.get(i).getName()+"添加的小类名称：");
		String mingCheng=new Scanner(System.in).next();
		Name name=new Name();
		name.setBelongedCategoryId(list.get(i).getId());
		name.setName(mingCheng);
		if(new NameImpl().addNew(name)==1)
		{
			System.out.println("添加小类"+name.getName()+"成功");
		}
	}
}
