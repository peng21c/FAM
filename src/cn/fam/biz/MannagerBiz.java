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
	 * ����Ա��¼�������λ���
	 * @author yzy
	 * @return ��½�ɹ��򷵻سɹ���ʵ��,���ɹ��򷵻�null
	 */

	public static Manager login()
	{
		Scanner input=new Scanner(System.in);
		int i=0;
		ManagerImpl MI=new ManagerImpl();
		for(i=0;i<3;i++){
			Manager manager=new Manager();
			System.out.println("�������û�����");
			String inputname=input.next();
			System.out.println("���������룺");
			String inputpwd=input.next();
			manager.setName(inputname);
			manager.setPassPwd(inputpwd);
			manager.setId(MI.login(manager).getId());
			if(manager.getId()!=0){
				System.out.println("��¼�ɹ�����ӭ     "+manager.getName());
				return manager;
			}else{
				System.out.println("�û������������!������ "+(2-i)+"�λ���");
			}
		}
		if(i==3){
			System.out.println("��¼ʧ�ܣ������");
			System.exit(0);
		}
		return null;	
	}
	/**
	 * ����Ա��������,���������룬Ȼ��������ݿ⣬��mng���������
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
			System.out.println("���������:");
			String oldpwd=input.next();
			System.out.println("����������:");
			String newpwd=input.next();
			System.out.println("ȷ��������:");
			String checkpwd=input.next();
			if(!oldpwd.equals(manager.getPassPwd())){
				System.out.println("����������������������룺");
				continue;
			}else{
				if(!newpwd.equals(checkpwd)){
					System.out.println("�����������벻һ�£����������룺");
					continue;
				}else{
					manager.setPassPwd(newpwd);
					if(MI.resetPwd(manager)!=1){
						System.out.println("�����޸�ʧ��");
						return;
					}else{
						System.out.println("�����޸ĳɹ�");
						Test.manager.setPassPwd(newpwd);
						break;
					}
				}
			}	
		}while(true);
		return;
	}

	/**
	 * ��ӹ̶��ʲ�������ʾ���еĴ��࣬�ù���Աѡ��Ȼ����ʾ��������µ�С�࣬�ù���Աѡ��������������Ϣ
	 * @author ����
	 */
	public static void addFixedAssets()
	{
		FixedAssetsImpl fixdao=new FixedAssetsImpl();
		FixedAssets fixaset=new FixedAssets();
		Scanner input = new Scanner(System.in);
		System.out.println("�������еĴ�����ѡ��һ�������id");
		CategoryImpl cated=new CategoryImpl();
		List<Category> list=cated.findAll();
		for(int i=0;i<list.size();i++)
		{
			System.out.println(list.get(i).getId()+"��   "+list.get(i).getName());
		}
		int cate=input.nextInt();
		fixaset.setCategory(list.get(cate).getName());
		System.out.println("���ڴ����µĵ�С����ѡ��һ��");
		Name name= new Name();
		name.setBelongedCategoryId(cate);
		NameImpl named=new NameImpl();
		List<Name> listname=named.findByCagetoryId(name);
		for(int i=0;i<listname.size();i++)
		{
			System.out.println(listname.get(i).getBelongedCategoryId()+"��   "+listname.get(i).getName());
		}
		int nameid=input.nextInt();
		fixaset.setName(listname.get(nameid).getName());
		System.out.println("����Ҫ�������ʲ�ID");
		int id=input.nextInt();
		fixaset.setId(id);
		System.out.println("����Ҫ�������ʲ��ͺ�");
		String model=input.next();
		fixaset.setModel(model);
		System.out.println("����Ҫ�������ʲ��۸�");
		int price=input.nextInt();
		fixaset.setPrice(price);
		System.out.println("����Ҫ�������ʲ��������ڣ���-��-�գ�");
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
		System.out.println("����Ҫ�������ʲ�״̬");
		String state=input.next();
		fixaset.setState(state);
		System.out.println("����Ҫ�������ʲ�ʹ����ID");
		int userid=input.nextInt();
		fixaset.setUserId(userid);
		System.out.println("����Ҫ�������ʲ���ע");
		String remark=input.next();
		fixaset.setRemark(remark);
		fixdao.addNew(fixaset);
		System.out.println("�����ɹ�");
	}
	/**
	 * ɾ���̶��ʲ�
	 * @author ����
	 */
	public static void deleteFixedAssets()
	{
		System.out.println("����Ҫɾ�����ʲ���ID");
		Scanner input = new Scanner(System.in);
		int id=input.nextInt();
		FixedAssets fixaset= new FixedAssets();
		fixaset.setId(id);
		FixedAssetsImpl fixdao=new FixedAssetsImpl();
		fixdao.delete(fixaset);
		System.out.println("ɾ���ɹ�");

	}
	/**
	 * @author jiajia
	 * create 2016-7-17
	 * �޸� 2016-7-18
	 */
	//�����������һ����Ա���ı�ź��������ж��ǲ��Ǳ���˾��Ա�����ٸ��������С�����ƣ��������ݿ⣬����ɽ���ʲ���
	//����һ���ɽ��ʲ��ı�ţ�Ȼ������ʲ����ݱ��ʹ���ˣ��Լ����������ݱ����һ�����ü�¼�������鷳��ľ��
	public static void rentFixedAssets()
	{
		//��Ӵ���
		
		Scanner input = new Scanner(System.in);
		//�����ж��Ƿ��ǹ�˾Ա���ĺ���
		System.out.println("������Ա����ţ�");
	    int id=input.nextInt();
	    System.out.println("������Ա��������");
		String name=input.next();
		//����һ��Ա�������ո�����ı����������ֵ����Ա������ΪԱ�����Һ����Ĳ���Ϊһ��Ա����
		Staff s=new Staff();
		s.setId(id);
		s.setName(name);
		Staff s1=null;
		StaffImpl si= new StaffImpl();//Ϊ��ʹ��StaffImpl�еĺ����������
		//�����ж�Ա���Ƿ���ڵĺ���
		s1=si.isExist(s);
		if(s1!=null)
		{
			//���ð���С�����δ����ĺ���
			System.out.println("�������ʲ���С�����ƣ�");
			String xiaoname=input.next();
			FixedAssetsImpl ffi=new FixedAssetsImpl();//Ϊ��ʹ��FixedAssetsImpl�еĺ����������
			FixedAssets ff=new FixedAssets();//����һ���µ�FixedAssets��С������д��
			ff.setName(xiaoname);
			List<FixedAssets> ff1=null;//����С�����Ʋ��Һ���
			ff1=ffi.findByNameCanBeRented(ff);//����С�����Ʋ��ҿɽ��ʲ�����
			for(int i=0;i<ff1.size();i++)//ѭ������ɽ��ʲ�
			{
				FixedAssets ff2=ff1.get(i);
				System.out.println(ff2.getId()+"\t"+ff2.getName()+"\t\t"+ff2.getCategory()+"\t\t"+ff2.getModel()+"     "+ff2.getPrice()+"  \t"+ff2.getBuyDate()+"\t"+ff2.getState()+"\t"+ff2.getUserId()+"\t"+ff2.getRemark());
			}
			if(ff1.size()==0)
			{
				System.out.println("��Ǹ��û�пɹ�������ʲ�");
				return;
			}
			
			//���ø� ���ʲ����ݱ�ĺ���
			System.out.println("��ɽ��ʲ��ı�ţ�");
			int kejieid=input.nextInt();
			FixedAssetsImpl fi=new FixedAssetsImpl();//Ϊ��ʹ��FixedAssetsImpl�еĺ����������
			FixedAssets f=new FixedAssets();//����һ���µ�FixedAssets���ʲ����д��
			f.setId(kejieid);
			FixedAssets f1=null;
			f1=fi.findByID(f);//���ð��ձ�Ų��Һ����ҵ��ñ�ŵ��ʲ���Ϣ
			f1.setUserId(id);//д��ʹ���߱��
			fi.update(f1);//�����ʲ���Ϣ
			
			//���ø����������ݱ�ĺ���
			AssetsRentImpl ai=new AssetsRentImpl();//Ϊ��ʹ��AssetsRentImpl�еĺ����������
			AssetsRent a=new AssetsRent();//����һ���µ�AssetsRent��һϵ��������Ϣд��
			System.out.println("�������ʲ����ڣ���-��-�գ�");
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

			a.setUserId(id);//�ʼ�����Ա�����
			a.setRentManagerName(Test.manager.getName());//д����ʱ����
			ai.addNew(a);
		}
	
	}
	/**
	 * @author jiajia
	 * create 2016-7-17
	 * �޸� 2016-7-18
	 */
	//�黹�ʲ��������ѽ���Ĺ̶��ʲ��ı�ţ�Ȼ����ݱ���޸��ʲ����ݱ��޸��������ݱ�
	public static void retFixedAssets()
	{
		//��Ӵ���
		//���ø����ʲ����ݱ�ĺ���
		Scanner input = new Scanner(System.in);
		System.out.println("���ѽ���ʲ��ı�ţ�");
	    int yijieid=input.nextInt();
	    FixedAssetsImpl fi=new FixedAssetsImpl();//Ϊ��ʹ��FixedAssetsImpl�еĺ����������
		FixedAssets f=new FixedAssets();//����һ���µ�FixedAssets���ʲ����д��
		f.setId(yijieid);
		FixedAssets f1=null;
		f1=fi.findByID(f);//���ð��ձ�Ų��Һ����ҵ��ñ�ŵ��ʲ���Ϣ
		f1.setUserId(0);//��ʹ���߱�Ÿ�Ϊ0
		fi.update(f1);//�����ʲ���Ϣ
		
		//���ø����������ݱ�ĺ���
		AssetsRentImpl ai=new AssetsRentImpl();//Ϊ��ʹ��AssetsRentImpl�еĺ����������
		AssetsRent a=new AssetsRent();//����һ���µ�AssetsRent��һϵ��������Ϣд��
		a.setFixedAssetsId(yijieid);
		System.out.println("����黹�ʲ����ڣ���-��-�գ�");
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
		a.setRetManagerName(Test.manager.getName());//д��黹ʱ����Ա����
		ai.update(a);
	}
	/**
	* @author ������
	* create 2016-7-17
	* modify by ������ 2016-7-17
	 * ͨ����������̶��ʲ���Ϣ
	 */
	public static void showByCategory()
	{
		System.out.println("��������Ҫ���ҵĴ�������");
		Scanner input = new Scanner(System.in);
		String category = input.next();
		FixedAssets fxaset=new FixedAssets();
		fxaset.setCategory(category);
		FixedAssetsImpl fixedassetsimpl=new FixedAssetsImpl();
		List<FixedAssets> fixedassetss = fixedassetsimpl.findByCatory(fxaset);
		if(fixedassetss == null)
			System.out.println("û���ҵ��ô���");
		else{
			System.out.println("���\tС���� \t \t������\t\t�ͺ� \t\t�۸�\t��������\t״̬\tʹ����\t��ע");
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
	*@author ������
	* create 2016-7-17
	* modify by ������ 2016-7-17
	 * ͨ��С�ࣨname������̶��ʲ���Ϣ
	 */
	public static void showByName()
	{
		System.out.println("��������Ҫ���ҵ�С������");
		Scanner input = new Scanner(System.in);
		String name= input.next();
		FixedAssets fxaset=new FixedAssets();
		fxaset.setName(name);
		FixedAssetsImpl fixedassetsimpl=new FixedAssetsImpl();
		List<FixedAssets> fixedassetss = fixedassetsimpl.findByName(fxaset);
		if(fixedassetss == null)
			System.out.println("û���ҵ���Ϣ");
		else{
			System.out.println("���\tС����\t\t������\t\t�ͺ�\t\t�۸�\t��������\t״̬\tʹ����\t��ע");
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
	//ͨ���ʲ�id����̶��ʲ���Ϣ
	public static void showByFixedAssetsId()
	{
		FixedAssets fa=new FixedAssets();
		System.out.println("������Ҫ��ѯ���ʲ�id");
		int Id=new Scanner(System.in).nextInt();
		fa.setId(Id);
		FixedAssetsImpl fai=new FixedAssetsImpl();
		fa=fai.findByID(fa);
		System.out.println("��ѯ�����");
		System.out.println("���\tС����\t\t������\t\t�ͺ�\t\t�۸�\t��������\t״̬\tʹ����\t��ע");
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
	
	* @author ������
	* create 2016-7-17
	* modify by ������ 2016-7-17
	 * ͨ��ʹ����id����̶��ʲ���Ϣ,���������id������ȷ��
	 */
	public static void showByuserId()
	{
		List<FixedAssets> list=new ArrayList();
		System.out.println("������Ҫ��ѯ��ʹ����id");
		int userId=new Scanner(System.in).nextInt();
		Staff staff=new Staff(); //���ڷ�װuserid
		staff.setId(userId);     //��װuserid
		FixedAssetsImpl fai=new FixedAssetsImpl();
		list=fai.findByuserID(staff);
		int i;
		System.out.println("��ѯ�����");
		System.out.println("�ʲ����      ����      ���       �ͺ�       �۸�    ״̬         ʹ����      ��ע ");
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
	* @author ������
	* create 2016-7-17
	* modify by ������ 2016-7-17
	 * ��Ӵ��࣬���費����ظ���
	 */
	public static void addCategory()
	{
		CategoryImpl cgdao=new CategoryImpl();
		String category; //��������Ĵ�������
		System.out.println("������Ҫ��ӵĴ�������:");
		category=new Scanner(System.in).next();
		Category newCategory= new Category();
		newCategory.setName(category);
		int i=cgdao.addCategory(newCategory);
		if(i==1)
		{
			System.out.println("��Ӵ���"+newCategory.getName()+"�ɹ�");
		}
	}
	/**
	* @author ������
	* create 2016-7-17
	* modify by ������ 2016-7-17
	 * ���С�࣬��ѡ����Ҫ��ӵ�С�����ڵĴ���
	 */
	public static void addName()
	{
		CategoryImpl cgdao=new CategoryImpl();
		List<cn.fam.entity.Category> list=cgdao.findAll();
		String cagory;
		int i;
		System.out.println("��ѡ��Ҫ�����С�������Ĵ������:");
		for(i=0;i<list.size();i++)
		{
			System.out.println(i+"��   "+list.get(i).getName());
		}
		int input=new Scanner(System.in).nextInt();
		while(input<0||input>=list.size())
		{
			System.out.println("���벻�ڷ�Χ������������:");
			input=new Scanner(System.in).nextInt();
		}
		i=input;
		System.out.println("��������ҪΪ����"+list.get(i).getName()+"��ӵ�С�����ƣ�");
		String mingCheng=new Scanner(System.in).next();
		Name name=new Name();
		name.setBelongedCategoryId(list.get(i).getId());
		name.setName(mingCheng);
		if(new NameImpl().addNew(name)==1)
		{
			System.out.println("���С��"+name.getName()+"�ɹ�");
		}
	}
}
