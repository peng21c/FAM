package cn.fam.view;
import java.util.Scanner;
import cn.fam.test.Test;
import cn.fam.biz.MannagerBiz;
public class mainView {
/**
 * 控制程序运行流程
 * @throws Exception 
 */
	/*登录界面，有两个选项，登录和退出系统，选择登陆后，输入管理员姓名和密码三次，成功，将用户名密码set到test的manager，调到MainView,不成功结束程序
	选择退出，退出程序*/
	public static void showLoginView()
	{
		System.out.println("-------------------------------------------");
		System.out.println("----------欢迎使用固定资产管理系统-----------------");
		System.out.println("-------------------------------------------");
		System.out.println();
		System.out.println("            1、            登       录");
		System.out.println("            2、           退出系统");
		System.out.println();
		System.out.println("-------------------------------------------");
		System.out.println("请选择要进行操作的序号:");
		int input=new Scanner(System.in).nextInt();
		while(input!=1&&input!=2)
		{
			System.out.println("输入有误，请重新输入:");
			input=new Scanner(System.in).nextInt();
		}
		switch(input)
		{
		case 1:
			
			Test.manager=MannagerBiz.login();
			System.out.println("登录成功！");
			System.out.println("欢迎"+Test.manager.getName());
			System.out.println("============================我是华丽的分割线=====================================================");
			showMainView();
			break;
		case 2:
			System.out.println("程序结束运行！");
			System.exit(0);
		}
	}
	/*可选项：查看资产信息，删除资产信息，添加资产信息，修改资产信息，添加类别，领用资产，归还资产，修改密码，退出
	 * 查看资产信息，跳转到showFixedAssetsBaseView()
	 * 删除资产信息，调用biz的方法
	 * 添加资产信息，不跳转，先展示大类，然后输入一个大类的id,展示下面的小类，选择一个小类的名称，再输入资产其他信息
	 * 修改资产信息，调用biz的方法
	 * 添加类别，跳转到addclazzBaseView()
	 * 领用资产，调用biz方法
	 * 归还资产，同上
	 * 修改密码，不跳转，输入新，修改test中静态变量manager的密码，更新数据库中的密码值
	 * 退出，结束程序
	 */
	public static void showMainView()
	{
		System.out.println("-------------------------------------------");
		System.out.println("----------欢迎使用固定资产管理系统-----------------");
		System.out.println("-------------------------------------------");
		System.out.println();
		System.out.println("          1、                     查看资产信息");
		System.out.println("          2、                     删除资产信息");
		System.out.println("          3、                     添加资产信息");
		System.out.println("          4、                     添   加  类  别");
		System.out.println("          5、                     领   用  资  产");
		System.out.println("          6、                     归   还  资  产");
		System.out.println("          7、                     修   改  密  码");
		System.out.println("          8、                     注              销");
		System.out.println("          9、                     退              出");
		System.out.println();
		System.out.println("*******************************************");
		System.out.println("请选择要进行操作的序号:");
		int input=new Scanner(System.in).nextInt();
		while(input<-1||input>9)
		{
			System.out.println("输入有误，请重新输入:");
			input=new Scanner(System.in).nextInt();
		}
		switch(input)
		{
		case 1://查看资产
			System.out.println("============================我是华丽的分割线=====================================================");
			showFixedAssetsBaseView();
			break;
		case 2: //删除资产
			//System.out.println("删除资产成功");
			MannagerBiz.deleteFixedAssets();
			System.out.println("0:返回主菜单********************************");
			input=new Scanner(System.in).nextInt();
			while(input!=0)
			{
				System.out.println("输入有误，请重新输入:");
				input=new Scanner(System.in).nextInt();
			}
			System.out.println("============================我是华丽的分割线=====================================================");
			showMainView();
			break;
		case 3://添加资产
			//System.out.println("添加资产成功");
			MannagerBiz.addFixedAssets();
			System.out.println("0:返回主菜单********************************");
			input=new Scanner(System.in).nextInt();
			while(input!=0)
			{
				System.out.println("输入有误，请重新输入:");
				input=new Scanner(System.in).nextInt();
			}
			System.out.println("============================我是华丽的分割线=====================================================");
			showMainView();
			break;
		
		case 4://添加类别
			System.out.println("============================我是华丽的分割线=====================================================");;
			showAddclazzBaseView();
			break;
		case 5://领用资产
			//System.out.println("领用资产成功");
			MannagerBiz.rentFixedAssets();
			System.out.println("0:返回主菜单********************************");
			input=new Scanner(System.in).nextInt();
			while(input!=0)
			{
				System.out.println("输入有误，请重新输入:");
				input=new Scanner(System.in).nextInt();
			}
			System.out.println("============================我是华丽的分割线=====================================================");
			showMainView();
			break;
		case 6://归还资产
			//System.out.println("归还资产成功");
			MannagerBiz.retFixedAssets();
			System.out.println("0:返回主菜单********************************");
			input=new Scanner(System.in).nextInt();
			while(input!=0)
			{
				System.out.println("输入有误，请重新输入:");
				input=new Scanner(System.in).nextInt();
			}
			System.out.println("============================我是华丽的分割线=====================================================");
			showMainView();
			break;
		case 7:
			MannagerBiz.resetPwd();
			System.out.println("0:返回主菜单********************************");
			input=new Scanner(System.in).nextInt();
			while(input!=0)
			{
				System.out.println("输入有误，请重新输入:");
				input=new Scanner(System.in).nextInt();
			}
			System.out.println("============================我是华丽的分割线=====================================================");
			showMainView();
			break;
		case 8:
			String name=Test.manager.getName();
			System.out.println("确定要注销[y/n]");
			String input1=new Scanner(System.in).next();
			while(!input1.equals("y")&&!input1.equals("n"))
			{
				System.out.println("输入有误，请重新输入:");
				input1=new Scanner(System.in).next();
			}
			if(input1.equals("y"))
			{
				Test.manager=null;
				System.out.println("管理员"+name+"注销成功");
				System.out.println("============================我是华丽的分割线=====================================================");
				showLoginView();
			}
			else 
			{
				System.out.println("管理员"+name+"取消注销");
				System.out.println("============================我是华丽的分割线=====================================================");
				showMainView();
			}
			break;
		case 9:
			name=Test.manager.getName();
			System.out.println("确定要退出[y/n]");
			input1=new Scanner(System.in).next();
			while(!input1.equals("y")&&!input1.equals("n"))
			{
				System.out.println("输入有误，请重新输入:");
				input1=new Scanner(System.in).next();
			}
			if(input1.equals("y"))
			{
				Test.manager=null;
				System.out.println("管理员"+name+"退出成功");
				System.exit(0);
			}
			else 
			{
				System.out.println("管理员"+name+"取消退出");
				System.out.println("============================我是华丽的分割线=====================================================");
				showMainView();
			}
			
		}
	}
	/*
	 *可选项，类别，资产信息， 
	 *类别，跳转到showChooseClassView()
	 *资产信息，跳转到showChooseInfoView()
	 */
	public static void showFixedAssetsBaseView()
	{
		System.out.println("-------------------------------------------");
		System.out.println("----------欢迎使用固定资产管理系统-----------------");
		System.out.println("-------------------------------------------");
		System.out.println();
		System.out.println("           1、        按类别查看资产");
		System.out.println("           2、         按信息查看资产");
		System.out.println();
		System.out.println("0:返回上一级***********************************");
		System.out.println("请选择要进行操作的序号:");
		int input=new Scanner(System.in).nextInt();
		while(input<0||input>2)
		{
			System.out.println("输入有误，请重新输入:");
			input=new Scanner(System.in).nextInt();
		}
		switch(input)
		{
		case 1:
			System.out.println("============================我是华丽的分割线=====================================================");
			showChooseClassView();
			break;
		case 2:
			System.out.println("============================我是华丽的分割线=====================================================");
			showChooseInfoView();
			break;
		case 0:
			System.out.println("============================我是华丽的分割线=====================================================");
			showMainView();
			break;
		}
	}
	
	
	/*可选项，大类，小类
	 * 都调用biz方法
	 */
	public static void showAddclazzBaseView()
	{
		System.out.println("-------------------------------------------");
		System.out.println("----------欢迎使用固定资产管理系统-----------------");
		System.out.println("-------------------------------------------");
		System.out.println();
		System.out.println("            1、                 增加大类");
		System.out.println("            2、                 增加小类");
		System.out.println();
		System.out.println("0:返回上一级*********************************");
		System.out.println("请选择要进行操作的序号:");
		int input=new Scanner(System.in).nextInt();
		while(input<0||input>2)
		{
			System.out.println("输入有误，请重新输入:");
			input=new Scanner(System.in).nextInt();
		}
		switch(input)
		{
		case 1:
			//System.out.println("此处是按大类增加");
			MannagerBiz.addCategory();
			System.out.println("0:返回目录    1:返回主菜单**********************");
			input=new Scanner(System.in).nextInt();
			while(input!=0&&input!=1)
			{
				System.out.println("输入有误，请重新输入:");
				input=new Scanner(System.in).nextInt();
			}
			switch(input)
			{
			case 0:
				System.out.println("============================我是华丽的分割线=====================================================");
				showAddclazzBaseView();
				break;
			case 1:
				System.out.println("============================我是华丽的分割线=====================================================");
				showMainView();
			}
			break;
		case 2:
			//System.out.println("此处是按大类增加");
			MannagerBiz.addName();
			System.out.println("0:目录    1:返回主菜单************************");
			input=new Scanner(System.in).nextInt();
			while(input!=0&&input!=1)
			{
				System.out.println("输入有误，请重新输入:");
				input=new Scanner(System.in).nextInt();
			}
			switch(input)
			{
			case 0:
				System.out.println("============================我是华丽的分割线=====================================================");
				showAddclazzBaseView();
				break;
			case 1:
				System.out.println("============================我是华丽的分割线=====================================================");
				showMainView();
			}
			break;
		case 0:
			System.out.println("============================我是华丽的分割线=====================================================");
			showFixedAssetsBaseView();
		}
	}
	
	/*可选项，大类，小类
	 * 大类，调用biz方法
	 * 小类，调用biz方法
	 */
	public static void showChooseClassView()
	{
		System.out.println("-------------------------------------------");
		System.out.println("----------欢迎使用固定资产管理系统-----------------");
		System.out.println("-------------------------------------------");
		System.out.println();
		System.out.println("          1、                    按大类查看资产");
		System.out.println("          2、                    按小类查看资产");
		System.out.println();
		System.out.println("0:返回上一级***********************************");
		System.out.println("请选择要进行操作的序号:");
		int input=new Scanner(System.in).nextInt();
		while(input<0||input>2)
		{
			System.out.println("输入有误，请重新输入:");
			input=new Scanner(System.in).nextInt();
		}
		switch(input)
		{
		case 1:
			//System.out.println("此处输出按大类查找到的资产信息");
			MannagerBiz.showByCategory();
			System.out.println("0:返回目录    1:返回主菜单**********************");
			input=new Scanner(System.in).nextInt();
			while(input!=0&&input!=1)
			{
				System.out.println("输入有误，请重新输入:");
				input=new Scanner(System.in).nextInt();
			}
			switch(input)
			{
			case 0:
				System.out.println("============================我是华丽的分割线=====================================================");
				showChooseClassView();
				break;
			case 1:
				System.out.println("============================我是华丽的分割线=====================================================");
				showMainView();
			}
			break;
		case 2:
			//System.out.println("此处输出按小类查找到的资产信息");
			MannagerBiz.showByName();
			System.out.println("0:目录    1:返回主菜单************************");
			input=new Scanner(System.in).nextInt();
			while(input!=0&&input!=1)
			{
				System.out.println("输入有误，请重新输入:");
				input=new Scanner(System.in).nextInt();
			}
			switch(input)
			{
			case 0:
				System.out.println("============================我是华丽的分割线=====================================================");
				showChooseClassView();
				break;
			case 1:
				System.out.println("============================我是华丽的分割线=====================================================");
				showMainView();
			}
			break;
		case 0:
			System.out.println("============================我是华丽的分割线=====================================================");
			showFixedAssetsBaseView();
		}
	}
	/*可选项，资产编号、资产类别、使用者
	 * 都调用biz方法
	 */
	public static void showChooseInfoView()
	{
		System.out.println("-------------------------------------------");
		System.out.println("----------欢迎使用固定资产管理系统-----------------");
		System.out.println("-------------------------------------------");
		System.out.println();
		System.out.println("         1、            按资产编号查看资产");
		System.out.println("         2、           按使用者编号查看资产");
		System.out.println();
		System.out.println("0:返回上一级************************************");
		System.out.println("请选择要进行操作的序号:");
		int input=new Scanner(System.in).nextInt();
		while(input<0||input>2)
		{
			System.out.println("输入有误，请重新输入:");
			input=new Scanner(System.in).nextInt();
		}
		switch(input)
		{
		case 1:
			//System.out.println("此处输出按资产编号查找到的资产信息");
			MannagerBiz.showByFixedAssetsId();
			System.out.println("0:返回目录    1:返回主菜单***************************************");
			input=new Scanner(System.in).nextInt();
			while(input!=0&&input!=1)
			{
				System.out.println("输入有误，请重新输入:");
				input=new Scanner(System.in).nextInt();
			}
			switch(input)
			{
			case 0:
				System.out.println("============================我是华丽的分割线=====================================================");
				showChooseInfoView();
				break;
			case 1:
				System.out.println("============================我是华丽的分割线=====================================================");
				showMainView();
			}
			break;
		case 2:
			System.out.println("此处输出按使用者编号查找到的资产信息");
			//MannagerBiz.showByuserId();
			System.out.println("0:返回目录    1:返回主菜单-------------------------------------------");
			input=new Scanner(System.in).nextInt();
			while(input!=0&&input!=1)
			{
				System.out.println("输入有误，请重新输入:");
				input=new Scanner(System.in).nextInt();
			}
			switch(input)
			{
			case 0:
				System.out.println("============================我是华丽的分割线=====================================================");
				showChooseInfoView();
				break;
			case 1:
				System.out.println("============================我是华丽的分割线=====================================================");
				showMainView();
			}
		}
	}
	public static void main(String[] args)
	{
		showLoginView();
	}
}
