package cn.fam.view;
import java.util.Scanner;
import cn.fam.test.Test;
import cn.fam.biz.MannagerBiz;
public class mainView {
/**
 * ���Ƴ�����������
 * @throws Exception 
 */
	/*��¼���棬������ѡ���¼���˳�ϵͳ��ѡ���½���������Ա�������������Σ��ɹ������û�������set��test��manager������MainView,���ɹ���������
	ѡ���˳����˳�����*/
	public static void showLoginView()
	{
		System.out.println("-------------------------------------------");
		System.out.println("----------��ӭʹ�ù̶��ʲ�����ϵͳ-----------------");
		System.out.println("-------------------------------------------");
		System.out.println();
		System.out.println("            1��            ��       ¼");
		System.out.println("            2��           �˳�ϵͳ");
		System.out.println();
		System.out.println("-------------------------------------------");
		System.out.println("��ѡ��Ҫ���в��������:");
		int input=new Scanner(System.in).nextInt();
		while(input!=1&&input!=2)
		{
			System.out.println("������������������:");
			input=new Scanner(System.in).nextInt();
		}
		switch(input)
		{
		case 1:
			
			Test.manager=MannagerBiz.login();
			System.out.println("��¼�ɹ���");
			System.out.println("��ӭ"+Test.manager.getName());
			System.out.println("============================���ǻ����ķָ���=====================================================");
			showMainView();
			break;
		case 2:
			System.out.println("����������У�");
			System.exit(0);
		}
	}
	/*��ѡ��鿴�ʲ���Ϣ��ɾ���ʲ���Ϣ������ʲ���Ϣ���޸��ʲ���Ϣ�������������ʲ����黹�ʲ����޸����룬�˳�
	 * �鿴�ʲ���Ϣ����ת��showFixedAssetsBaseView()
	 * ɾ���ʲ���Ϣ������biz�ķ���
	 * ����ʲ���Ϣ������ת����չʾ���࣬Ȼ������һ�������id,չʾ�����С�࣬ѡ��һ��С������ƣ��������ʲ�������Ϣ
	 * �޸��ʲ���Ϣ������biz�ķ���
	 * ��������ת��addclazzBaseView()
	 * �����ʲ�������biz����
	 * �黹�ʲ���ͬ��
	 * �޸����룬����ת�������£��޸�test�о�̬����manager�����룬�������ݿ��е�����ֵ
	 * �˳�����������
	 */
	public static void showMainView()
	{
		System.out.println("-------------------------------------------");
		System.out.println("----------��ӭʹ�ù̶��ʲ�����ϵͳ-----------------");
		System.out.println("-------------------------------------------");
		System.out.println();
		System.out.println("          1��                     �鿴�ʲ���Ϣ");
		System.out.println("          2��                     ɾ���ʲ���Ϣ");
		System.out.println("          3��                     ����ʲ���Ϣ");
		System.out.println("          4��                     ��   ��  ��  ��");
		System.out.println("          5��                     ��   ��  ��  ��");
		System.out.println("          6��                     ��   ��  ��  ��");
		System.out.println("          7��                     ��   ��  ��  ��");
		System.out.println("          8��                     ע              ��");
		System.out.println("          9��                     ��              ��");
		System.out.println();
		System.out.println("*******************************************");
		System.out.println("��ѡ��Ҫ���в��������:");
		int input=new Scanner(System.in).nextInt();
		while(input<-1||input>9)
		{
			System.out.println("������������������:");
			input=new Scanner(System.in).nextInt();
		}
		switch(input)
		{
		case 1://�鿴�ʲ�
			System.out.println("============================���ǻ����ķָ���=====================================================");
			showFixedAssetsBaseView();
			break;
		case 2: //ɾ���ʲ�
			//System.out.println("ɾ���ʲ��ɹ�");
			MannagerBiz.deleteFixedAssets();
			System.out.println("0:�������˵�********************************");
			input=new Scanner(System.in).nextInt();
			while(input!=0)
			{
				System.out.println("������������������:");
				input=new Scanner(System.in).nextInt();
			}
			System.out.println("============================���ǻ����ķָ���=====================================================");
			showMainView();
			break;
		case 3://����ʲ�
			//System.out.println("����ʲ��ɹ�");
			MannagerBiz.addFixedAssets();
			System.out.println("0:�������˵�********************************");
			input=new Scanner(System.in).nextInt();
			while(input!=0)
			{
				System.out.println("������������������:");
				input=new Scanner(System.in).nextInt();
			}
			System.out.println("============================���ǻ����ķָ���=====================================================");
			showMainView();
			break;
		
		case 4://������
			System.out.println("============================���ǻ����ķָ���=====================================================");;
			showAddclazzBaseView();
			break;
		case 5://�����ʲ�
			//System.out.println("�����ʲ��ɹ�");
			MannagerBiz.rentFixedAssets();
			System.out.println("0:�������˵�********************************");
			input=new Scanner(System.in).nextInt();
			while(input!=0)
			{
				System.out.println("������������������:");
				input=new Scanner(System.in).nextInt();
			}
			System.out.println("============================���ǻ����ķָ���=====================================================");
			showMainView();
			break;
		case 6://�黹�ʲ�
			//System.out.println("�黹�ʲ��ɹ�");
			MannagerBiz.retFixedAssets();
			System.out.println("0:�������˵�********************************");
			input=new Scanner(System.in).nextInt();
			while(input!=0)
			{
				System.out.println("������������������:");
				input=new Scanner(System.in).nextInt();
			}
			System.out.println("============================���ǻ����ķָ���=====================================================");
			showMainView();
			break;
		case 7:
			MannagerBiz.resetPwd();
			System.out.println("0:�������˵�********************************");
			input=new Scanner(System.in).nextInt();
			while(input!=0)
			{
				System.out.println("������������������:");
				input=new Scanner(System.in).nextInt();
			}
			System.out.println("============================���ǻ����ķָ���=====================================================");
			showMainView();
			break;
		case 8:
			String name=Test.manager.getName();
			System.out.println("ȷ��Ҫע��[y/n]");
			String input1=new Scanner(System.in).next();
			while(!input1.equals("y")&&!input1.equals("n"))
			{
				System.out.println("������������������:");
				input1=new Scanner(System.in).next();
			}
			if(input1.equals("y"))
			{
				Test.manager=null;
				System.out.println("����Ա"+name+"ע���ɹ�");
				System.out.println("============================���ǻ����ķָ���=====================================================");
				showLoginView();
			}
			else 
			{
				System.out.println("����Ա"+name+"ȡ��ע��");
				System.out.println("============================���ǻ����ķָ���=====================================================");
				showMainView();
			}
			break;
		case 9:
			name=Test.manager.getName();
			System.out.println("ȷ��Ҫ�˳�[y/n]");
			input1=new Scanner(System.in).next();
			while(!input1.equals("y")&&!input1.equals("n"))
			{
				System.out.println("������������������:");
				input1=new Scanner(System.in).next();
			}
			if(input1.equals("y"))
			{
				Test.manager=null;
				System.out.println("����Ա"+name+"�˳��ɹ�");
				System.exit(0);
			}
			else 
			{
				System.out.println("����Ա"+name+"ȡ���˳�");
				System.out.println("============================���ǻ����ķָ���=====================================================");
				showMainView();
			}
			
		}
	}
	/*
	 *��ѡ�����ʲ���Ϣ�� 
	 *�����ת��showChooseClassView()
	 *�ʲ���Ϣ����ת��showChooseInfoView()
	 */
	public static void showFixedAssetsBaseView()
	{
		System.out.println("-------------------------------------------");
		System.out.println("----------��ӭʹ�ù̶��ʲ�����ϵͳ-----------------");
		System.out.println("-------------------------------------------");
		System.out.println();
		System.out.println("           1��        �����鿴�ʲ�");
		System.out.println("           2��         ����Ϣ�鿴�ʲ�");
		System.out.println();
		System.out.println("0:������һ��***********************************");
		System.out.println("��ѡ��Ҫ���в��������:");
		int input=new Scanner(System.in).nextInt();
		while(input<0||input>2)
		{
			System.out.println("������������������:");
			input=new Scanner(System.in).nextInt();
		}
		switch(input)
		{
		case 1:
			System.out.println("============================���ǻ����ķָ���=====================================================");
			showChooseClassView();
			break;
		case 2:
			System.out.println("============================���ǻ����ķָ���=====================================================");
			showChooseInfoView();
			break;
		case 0:
			System.out.println("============================���ǻ����ķָ���=====================================================");
			showMainView();
			break;
		}
	}
	
	
	/*��ѡ����࣬С��
	 * ������biz����
	 */
	public static void showAddclazzBaseView()
	{
		System.out.println("-------------------------------------------");
		System.out.println("----------��ӭʹ�ù̶��ʲ�����ϵͳ-----------------");
		System.out.println("-------------------------------------------");
		System.out.println();
		System.out.println("            1��                 ���Ӵ���");
		System.out.println("            2��                 ����С��");
		System.out.println();
		System.out.println("0:������һ��*********************************");
		System.out.println("��ѡ��Ҫ���в��������:");
		int input=new Scanner(System.in).nextInt();
		while(input<0||input>2)
		{
			System.out.println("������������������:");
			input=new Scanner(System.in).nextInt();
		}
		switch(input)
		{
		case 1:
			//System.out.println("�˴��ǰ���������");
			MannagerBiz.addCategory();
			System.out.println("0:����Ŀ¼    1:�������˵�**********************");
			input=new Scanner(System.in).nextInt();
			while(input!=0&&input!=1)
			{
				System.out.println("������������������:");
				input=new Scanner(System.in).nextInt();
			}
			switch(input)
			{
			case 0:
				System.out.println("============================���ǻ����ķָ���=====================================================");
				showAddclazzBaseView();
				break;
			case 1:
				System.out.println("============================���ǻ����ķָ���=====================================================");
				showMainView();
			}
			break;
		case 2:
			//System.out.println("�˴��ǰ���������");
			MannagerBiz.addName();
			System.out.println("0:Ŀ¼    1:�������˵�************************");
			input=new Scanner(System.in).nextInt();
			while(input!=0&&input!=1)
			{
				System.out.println("������������������:");
				input=new Scanner(System.in).nextInt();
			}
			switch(input)
			{
			case 0:
				System.out.println("============================���ǻ����ķָ���=====================================================");
				showAddclazzBaseView();
				break;
			case 1:
				System.out.println("============================���ǻ����ķָ���=====================================================");
				showMainView();
			}
			break;
		case 0:
			System.out.println("============================���ǻ����ķָ���=====================================================");
			showFixedAssetsBaseView();
		}
	}
	
	/*��ѡ����࣬С��
	 * ���࣬����biz����
	 * С�࣬����biz����
	 */
	public static void showChooseClassView()
	{
		System.out.println("-------------------------------------------");
		System.out.println("----------��ӭʹ�ù̶��ʲ�����ϵͳ-----------------");
		System.out.println("-------------------------------------------");
		System.out.println();
		System.out.println("          1��                    ������鿴�ʲ�");
		System.out.println("          2��                    ��С��鿴�ʲ�");
		System.out.println();
		System.out.println("0:������һ��***********************************");
		System.out.println("��ѡ��Ҫ���в��������:");
		int input=new Scanner(System.in).nextInt();
		while(input<0||input>2)
		{
			System.out.println("������������������:");
			input=new Scanner(System.in).nextInt();
		}
		switch(input)
		{
		case 1:
			//System.out.println("�˴������������ҵ����ʲ���Ϣ");
			MannagerBiz.showByCategory();
			System.out.println("0:����Ŀ¼    1:�������˵�**********************");
			input=new Scanner(System.in).nextInt();
			while(input!=0&&input!=1)
			{
				System.out.println("������������������:");
				input=new Scanner(System.in).nextInt();
			}
			switch(input)
			{
			case 0:
				System.out.println("============================���ǻ����ķָ���=====================================================");
				showChooseClassView();
				break;
			case 1:
				System.out.println("============================���ǻ����ķָ���=====================================================");
				showMainView();
			}
			break;
		case 2:
			//System.out.println("�˴������С����ҵ����ʲ���Ϣ");
			MannagerBiz.showByName();
			System.out.println("0:Ŀ¼    1:�������˵�************************");
			input=new Scanner(System.in).nextInt();
			while(input!=0&&input!=1)
			{
				System.out.println("������������������:");
				input=new Scanner(System.in).nextInt();
			}
			switch(input)
			{
			case 0:
				System.out.println("============================���ǻ����ķָ���=====================================================");
				showChooseClassView();
				break;
			case 1:
				System.out.println("============================���ǻ����ķָ���=====================================================");
				showMainView();
			}
			break;
		case 0:
			System.out.println("============================���ǻ����ķָ���=====================================================");
			showFixedAssetsBaseView();
		}
	}
	/*��ѡ��ʲ���š��ʲ����ʹ����
	 * ������biz����
	 */
	public static void showChooseInfoView()
	{
		System.out.println("-------------------------------------------");
		System.out.println("----------��ӭʹ�ù̶��ʲ�����ϵͳ-----------------");
		System.out.println("-------------------------------------------");
		System.out.println();
		System.out.println("         1��            ���ʲ���Ų鿴�ʲ�");
		System.out.println("         2��           ��ʹ���߱�Ų鿴�ʲ�");
		System.out.println();
		System.out.println("0:������һ��************************************");
		System.out.println("��ѡ��Ҫ���в��������:");
		int input=new Scanner(System.in).nextInt();
		while(input<0||input>2)
		{
			System.out.println("������������������:");
			input=new Scanner(System.in).nextInt();
		}
		switch(input)
		{
		case 1:
			//System.out.println("�˴�������ʲ���Ų��ҵ����ʲ���Ϣ");
			MannagerBiz.showByFixedAssetsId();
			System.out.println("0:����Ŀ¼    1:�������˵�***************************************");
			input=new Scanner(System.in).nextInt();
			while(input!=0&&input!=1)
			{
				System.out.println("������������������:");
				input=new Scanner(System.in).nextInt();
			}
			switch(input)
			{
			case 0:
				System.out.println("============================���ǻ����ķָ���=====================================================");
				showChooseInfoView();
				break;
			case 1:
				System.out.println("============================���ǻ����ķָ���=====================================================");
				showMainView();
			}
			break;
		case 2:
			System.out.println("�˴������ʹ���߱�Ų��ҵ����ʲ���Ϣ");
			//MannagerBiz.showByuserId();
			System.out.println("0:����Ŀ¼    1:�������˵�-------------------------------------------");
			input=new Scanner(System.in).nextInt();
			while(input!=0&&input!=1)
			{
				System.out.println("������������������:");
				input=new Scanner(System.in).nextInt();
			}
			switch(input)
			{
			case 0:
				System.out.println("============================���ǻ����ķָ���=====================================================");
				showChooseInfoView();
				break;
			case 1:
				System.out.println("============================���ǻ����ķָ���=====================================================");
				showMainView();
			}
		}
	}
	public static void main(String[] args)
	{
		showLoginView();
	}
}
