//��CDRentSystem.java
//1711350 ��ΰ  2018/11/04���

import java.io.*;  //�ļ������������Ҫ
import java.util.Scanner;
/**ʵ�ֻ�Ա����Ϣ����*/
class Member{
	/**��Ա���*/
	int id;
	/**��Ա����*/
	String name;
	/**��Ա����*/
	int	age;
	/**��Ա���õ�CD���ƣ�Ĭ��ÿλ��Ա�������10��CD*/
	String rent[]=new String[10];
	/**��Ա�Ѿ����õ�CD����*/
	int rentNumber=0;
	/**������һ���ڵ�*/
	Member nextMember;
	Member(){};
	//��ʼ������
	Member(int _id,String _name,int _age)
	{
		name=_name;
		age=_age;
		id=_id;
	}
	/**�жϻ�Ա����CD�����Ƿ�ﵽ����
	*@return �����Ƿ�ﵽ����
	*/
	boolean isfull(){
		if(rentNumber==10)
			return true;
		else
			return false;
	}
	/**�Ա���Ա������Ϣ���޸�
	*@param  CDname ����һ��CD������
	*@return �����Ƿ��޸ĳɹ�
	*/
	boolean rentCD(String CDname)
	{
		if(!isfull())
		{
			rent[rentNumber++]=CDname;return true;
		}
		else
			return false;
	}
}

/**��Ա��:��������ʽ�����Ա�����Լ����л�Ա��Ϣ*/
class Membership{
	/**ͳ�ƻ�Ա����*/
	private int count=0;
	private int money=0;			//�����Ա�����Ļ�ѣ����Ϊÿ��500Ԫ
	/**head��tail��ʾ��λλ��*/
	Member head,tail;
	/**���뺯��
	*@param a ����һ��Member����
	*@return �Ƿ����id��ͻ
	*/
	boolean insert(Member a){
		if(find(a.id))	return false;//��������ͬ��id
		if(head==null)
		{	
			head=tail=a;
			count++;
			money+=500;
			return true;
		}
		tail.nextMember=a;
		tail=a;
		count++;
		money+=500;
		return true;		//�ɹ����
	}
	/**��ȡͳ������
	*@return ����һ��int����,��ʾ��Ա����
	*/
	int getCount(){return count;}
	/**��ȡ��Ǯͳ������
	*@return ����һ��int����,��ʾ���̵�����
	*/
	int getMoney(){return money;}
	/**ɾ����Ա����
	*@param id ����һ����Ա���
	*@return �����ܷ�ɹ�ɾ��
	*/
	boolean delete(int id){
		if(head.id==id)			//�Ƿ����һ��λ����ͬ
		{
			head=head.nextMember;
			count--;
			return true;
		}
		Member temp=head;
		while(temp.nextMember!=null)
		{
			if(temp.nextMember.id==id)
			{
				temp.nextMember=temp.nextMember.nextMember;
				count--;
				return true;
			}
			temp=temp.nextMember;
		}
		return false;
	}
	/**��ʾ��ǰ��Ա��Ϣ����	
	*ͬʱҲ����ʾ��Ա��������ЩCD
	*/
	 void display ()
	{
		Member temp=head;
		System.out.println("ID"+"\t"+"NAME"+"\t"+"AGE"+"\t"+"RENTNUMBER"+"\t"+"RENTCDNAMES"+"\t");
		while(temp!=null)
		{
				System.out.print(temp.id+"\t"+temp.name+"\t"+temp.age+"\t"+temp.rentNumber+"\t");
				int i=temp.rentNumber;
				for(String str:temp.rent)
				{
					if(i==0) break;
					System.out.print(str);
					i--;	
				}
					System.out.println("");		//����5
			temp=temp.nextMember;
		}				
	}
	/**��ѯ����
	*@param name ����һ����Ա����
	*@return �����Ƿ����Ŀ���ѯ��Ա
	*/
	boolean find (String name)
	{
		Member temp=head;
		while(temp!=null)
		{
			if(temp.name.equals(name))
			{
				return true;
			}
			temp=temp.nextMember;
		}
		return false;
	}
		/**���ز�ѯ����
	*@param id  ����һ����Ա���
	*@return �����Ƿ����Ŀ���ѯ��Ա
	*/
	boolean find (int id)
	{
		Member temp=head;
		while(temp!=null)
		{
			if(temp.id==id)
			{
				return true;
			}
			temp=temp.nextMember;
		}
		return false;
	}
	/**�޸Ļ�Ա����CD��Ϣ
	*@param name ����һ����Ա����
	*@param CDname CD����
	*@return ���ش洢��Ա�Ƿ��ܹ����ô�CD 
	*/
	boolean rent(String name,String CDname)
	{
		Member temp=head;
		while(temp!=null)
		{
			if(temp.name.equals(name))
				if(!temp.isfull())
				{
					return temp.rentCD(CDname);
				}
				else
					return false;
			temp=temp.nextMember;
		}
		return false;
	}
	/**�޸Ļ�Ա����CD��Ϣ--�黹CD
	*@param name ����һ����Ա����
	*@param CDname CD����
	*@return ���ش洢��Ա�Ƿ����ô�CD���Ƿ���true���ҽ�CD������������¼��ɾ��
	*/
	boolean back(String name,String CDname)
	{
		Member temp=head;
		while(temp!=null)
		{
			if(temp.name.equals(name))
			{
				for(int i=0;i<10;i++)
				{
					if(CDname.equals(temp.rent[i]))
					{
						temp.rent[i]=null;//�޸���Ϣ
						temp.rentNumber--;
						return true;
					}
				}
			}
			temp=temp.nextMember;
		}
		return false;
	}
}

/**CD����Ϣ����*/
class CD{
	/**CD�ѳ���������Ĭ��Ϊ0*/
	int rentnum=0;
	/**CD���
	*һ��������ʱ��
	*/   
	int id;
	/**CD�۸�*/
	int price;
	/**CD����*/
	String name;
	/**CD���*/
	int	number;
	/**������һ���ڵ�*/
	CD nextCD;
	CD(){};
	CD(int _id,int _price,String _name,int _number)
	{
		id=_id;
		name=_name;
		price=_price;
		number=_number;
	}
}

/**CD��CD��漰��Ӫ��Ϣ��:��������ʽ����CD��Ϣ*/
class CDstore{
	/**head��tail��ʾ��λλ��*/
	private int count=0;		//cd����
	private int money=0;		//�������̽�Ǯ��Ϊ0����¼����CD������
	CD head,tail;
	/**����CD
	*@param VIPS ��Ա��Ϣϵͳ����
	*@param VIPname ������
	*@param  CDname Ԥ������CD����
	*@return ���ظ�����CD���ܷ�˳���������CD
	*/
	boolean rent(Membership VIPS,String VIPname,String CDname)
	{
		if(!VIPS.find(VIPname)||this.find(CDname)==-1||this.find(CDname)==0)//�����߲��ǻ�Ա���߿����û�п����õ�CD
			return false;
		if(VIPS.rent(VIPname,CDname))		//��Ա������
		{
			CD temp=head;
			while(temp!=null)
			{
				if(temp.name.equals(CDname))
				{
					temp.rentnum++;		//��CD�����������1
					return true;
				}
				temp=temp.nextCD;
			}
		}		
		return false;	//��Ա������������		
	}
	/**�������������ģ���������û�еĴ�������ڽ���CD���ʱʹ��
	*@param a ����һ��CD����
	*/
	void insert(CD a){
		if(head==null)
		{	
			head=tail=a;
			count++;
			return ;
		}
		tail.nextCD=a;
		tail=a;
		count++;
	}
	/**���䲻����ﺯ�������ڹ�������Ѵ��ڵĴ��
	*@param ��name������һ��CD��������
	*@return �����CD�����ڿ�����򷵻�false
	*/
	boolean insert(String name){
		CD temp=head;
		while(temp!=null)
		{
			if(temp.name.equals(name))
			{
				temp.number++;
				return true;
			}
			temp=temp.nextCD;
		}
		return false;
	}
	/**�黹CD����
	*@param VIPS  ����VIP��¼ϵͳ
	*@param VIPname ����һ����Ա��
	*@param CDname ����һ��CD��
	*@return ���ط�ɹ��黹
	*/
	boolean back(Membership VIPS,String VIPname,String CDname){
		if(!VIPS.find(VIPname)||this.find(CDname)==0)//�����߲��ǻ�Ա���߿���в����ڴ���CD
			return false;
		if(VIPS.back(VIPname,CDname))
		{
			CD temp=head;
			while(temp!=null)
			{
				if(temp.name.equals(CDname))
				{
					temp.rentnum--;		//��CD���������-1		
					return true;		//�ɹ��黹
				}
				temp=temp.nextCD;
			}
		}
		return false;	//�黹ʧ��
	}
	/**��ȡͳ������
	*@return ����һ��int���ݣ���ʾ����CD��������
	*/
	int getCount(){return count;}
	/**��ȡ��Ǯͳ������
	*@return ����һ��int����,��ʾ���̵�����
	*/
	int getMoney(){return money;}
	/**����cd
	*@param name ����һ��CD����
	*@param number��������CD ������
	*@return ����һ��int��������ʾ���������Ƿ��ܹ���ɣ�����治������ʧ�ܷ���0�����򷵻��۳���CD������
	*/
	int sell(String name,int number){
		if(find(name)==0|find(name)==-1) return 0;
		CD temp=head;
		while(temp!=null)
		{
			if(temp.nextCD.name.equals(name))
			{
				if((temp.nextCD.number-temp.nextCD.rentnum)>number)
				{
					temp.nextCD.number-=number;
					money+=temp.nextCD.price*number;
					return temp.nextCD.price*number;
				}
				else 
					if((temp.nextCD.number-temp.nextCD.rentnum)==number)//û�����������ֱ���ۿ�
					{	if(temp.rentnum==0)	//û�����������ֱ���ۿ�
						{	
							temp.nextCD=temp.nextCD.nextCD;
							count--;
							money+=temp.nextCD.price*number;
							return temp.nextCD.price*number;
						}
						else
						{
							temp.nextCD.number-=number;
							money+=temp.nextCD.price*number;
							return temp.nextCD.price*number;
						}
					}
				else return 0;//��治��
			}
			temp=temp.nextCD;
		} 
		return 0;
	}
	/**��ѯ����
	*@param name ����һ��CD����
	*@return  �����Ƿ����Ŀ���ѯCD,���ڷ���1�������ڷ���0�������õ��¿�����CD��Ϊ��ķ���-1
	*/
	int find (String name)
	{
		CD temp=head;
		while(temp!=null)
		{
			if(temp.name.equals(name))		//ע��ʹ��equals������==������
				if(temp.number-temp.rentnum>0)
					return 1;
				else
					return -1;
			temp=temp.nextCD;
		}
		return 0;
	}
	/**��ʾ��ǰCD�����Ϣ����*/
	void display ()
	{
		CD temp=head;
		System.out.println("ID"+"\t"+"NAME"+"\t"+"PRICE"+"\t"+"NUMBER"+"\t"+"RENTNUMBER"+"\t");
		while(temp!=null)
		{
			System.out.println(temp.id+"\t"+temp.name+"\t"+temp.price+"\t"+temp.number+"\t"+temp.rentnum+"\t");
			temp=temp.nextCD;
		}
	}
}

/**CDRentSystem����
*@author ��ΰ  ѧ�ţ�1711350
*@version Java�γ̵�������ҵ 
*@version ʵ�ֶ�CD�����CD�Լ���Ա������ز��������
*@version 2018/11/2
*/
public class CDRentSystem {
	/**��������  ������Ա��Ϣ�Լ�CD��ʼ���
	*@param vipImformationPath ���뱣��VIP��Ϣ���ļ�·��
	*@param vips ����Vip��ϵͳ��������
	*@param CDImformationPath Cd��Ϣ�ļ�·��
	*@param cdstore ����CD��Ϣ�����ݽṹ
	*@return boolean �����Ƿ񴴽��ɹ�
	*/
	static boolean openStore(String vipImformationPath,Membership vips,String CDImformationPath,CDstore cdstore)	//���ļ��ж�ȡ������Ϣ���������
	{
		//��ȡ��Ա��Ϣ
		  File file1 = new File(vipImformationPath);
			if(! file1.exists()){
				System.out.println("�Բ��𣬲�����ָ��·�����ļ�");
				return false;
			}else{
			//����ָ��·����File���󴴽�Scanner����
				try {
					Scanner sc1 = new Scanner(file1);
					while(sc1.hasNext()){          //ѭ��ɨ���ȡ�ļ��е�����
						String str1 = sc1.next();         //���ݶ�ȡ�ļ������ݴ���String ����
						String str2= sc1.next();
						String str3=sc1.next();
						Member vip=new Member(Integer.parseInt(str1),str2,Integer.parseInt(str3));
						
						//vip.printImf(); //�����ȡ����
						
						vips.insert(vip);	//��������
						//System.out.println("#");
					}
				}                          //�ر���
				catch (Exception e) {
                // TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//��ȡCD����Ϣ
			 File file2 = new File(CDImformationPath);
			if(! file2.exists()){
				System.out.println("�Բ��𣬲�����ָ��·�����ļ�");
				return false;
			}else{
				//����ָ��·����File���󴴽�Scanner����
				try {
					Scanner sc2 = new Scanner(file2);
					while(sc2.hasNext()){          //ѭ��ɨ���ȡ�ļ��е�����
						String str1 = sc2.next();         //���ݶ�ȡ�ļ������ݴ���String ����
						String str2 = sc2.next();  
						String str3 = sc2.next();  
						String str4 = sc2.next();  
						CD cd=new CD(Integer.parseInt(str1),Integer.parseInt(str2),str3,Integer.parseInt(str4));
						cdstore.insert(cd);		//����CD������
						//cd.printImf();//�����ȡ����
						            
						//System.out.println("#");
					}
				} catch (Exception e) {
                // TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return true;
	}
	/**��ȡ��������*/
	static void help(){
		System.out.println("-----------------------------------------------");//���������ʾ
		System.out.println("0����ȡ��������Ŀ¼��");
		System.out.println("1����ȡ��Ա��Ϣ��");
		System.out.println("2����ȡCD�����Ϣ��");
		System.out.println("3����ӻ�Ա��");
		System.out.println("4��ɾ����Ա��");
		System.out.println("5������CD��ÿλ��Ա����10��CD��");
		System.out.println("6���黹CD��");
		System.out.println("7������CD��");
		System.out.println("8��������Ʒ���CD��");
		System.out.println("9�������Ʒ��CD��档");
		System.out.println("10����ȡ����������Ϣ��");
		System.out.println("11���˳�CD����ϵͳ��");
		System.out.println("��>>�����������ָ�����ţ�");
		System.out.println("-----------------------------------------------");
	}
	/**
	/**CDRentSystem descriptions
	*��Ҫʵ�ֶ�CD��Ա����ϵͳ�Ĺ��ܲ���
	* @param args array of string arguements
	* @exception exceptions No exceptions thrown
	*/
	public static void main(String []args)
	{
		System.out.println("����CD���۵꿪���ˣ�");
		help();
		CDstore cdStore=new CDstore();Membership VIPS=new Membership();			//��������ʼ������
		openStore( "vipImformation.txt", VIPS,"CDImformation.txt", cdStore);			//���꣬���ļ��л�ȡ��Ϣ���������Ļ�Ա��¼��CD���
		System.out.print(">>");
		Scanner sc=new Scanner(System.in);int command;
		exit:while(true){			//����ѭ��
		//---------------�����ж�----------------------------------
			 if(sc.hasNextInt())			//�ж������Ƿ�Ϊ�������
				 command=sc.nextInt();
			else
			{
				System.out.println("�����������������ֲ�����ţ�");
				sc.next();			//������ǰ�������롣
				System.out.print(">>");
				continue;			//������ǰѭ��
			}
		//----------------------------------------------------------
			switch(command)
			{
			case 0:
				help();					//��ȡ��������
				break;
				case 1:
				VIPS.display();			//��ʾ��Ա��Ϣ��Ϣ
				break;
			case 2:
				cdStore.display();		//��ʾCD�����Ϣ
				break;
			case 3:				//��ӻ�Ա
				System.out.print("�����������Ա��ţ�һ��Ϊ7λ���֣�������������:");
				int id=sc.nextInt();String name=sc.next();int age=sc.nextInt();
				if(VIPS.insert(new Member(id,name,age)))
					System.out.println("��ӻ�Ա�ɹ���");
				else
					System.out.println("���ʧ�ܣ���Ա����Ѵ��ڣ�");
				break;
			case 4:					//ɾ����Ա
				System.out.print("��ɾ����Ա��id��");
				int id1=sc.nextInt();
				if(VIPS.delete(id1))
					System.out.println("ɾ����Ա�ɹ���");
				else
					System.out.println("ɾ��ʧ�ܣ���Ա��Ų����ڣ�");
				break;
			case 5:					//����
				System.out.print("�������������������֡�CD����:");
				String VIPname=sc.next(),CDname=sc.next();
				if(cdStore.rent(VIPS,VIPname,CDname))
					System.out.println(VIPname+"�ɹ�����"+CDname);
				else
					System.out.println("����CDʧ�ܣ�����CD����Լ��û�Ա�Ƿ�ﵽ�������ޡ�");
				break;
			case 6:					//�黹CD
				System.out.print("�������������������֡����黹CD����:");
				String VIPname1=sc.next(),CDname1=sc.next();
				if(cdStore.back(VIPS,VIPname1,CDname1))
					System.out.println(VIPname1+"�ɹ��黹"+CDname1);
				else
					System.out.println("�黹CDʧ�ܣ�����CD����Լ������Ƿ�����");
				break;
			case 7:					//����
				System.out.print("����������������CD���ơ���������:");
				String CDname2=sc.next();int number0=sc.nextInt();
				if(cdStore.sell(CDname2,number0)!=0)
					System.out.println("�ɹ�����"+CDname2+number0+"��");
				else
					System.out.println("����CDʧ�ܣ�����CD��档");
				break;
			case 8:						//�����Ʒ��CD
				System.out.print("����������CD��ţ���20181104�������ơ��۸�����:");
				int id2=sc.nextInt();String name2=sc.next();int price2=sc.nextInt();int number2=sc.nextInt();
				cdStore.insert(new CD(id2,price2,name2,number2));
				System.out.println("���CD�ɹ���");
				break;
			case 9:					//������п��
				System.out.print("������Ҫ���CD�����ơ��������:");
				String name3=sc.next();int number =sc.nextInt();
				if(cdStore.find(name3)!=0)
				{
					while(number-->0)
				cdStore.insert(name3);
				System.out.println("���CD�ɹ���");
				}
				else
					System.out.println("���CDʧ�ܣ������ڴ˿��CD��");
				break;
			case 10:					//��ȡ������Ϣ
				System.out.println("����������:"+(cdStore.getMoney()+VIPS.getMoney())+"Ԫ");
				System.out.println("$$��Ա�������:"+VIPS.getMoney()+"Ԫ");
				System.out.println("$$����CD����:"+cdStore.getMoney()+"Ԫ");
				break;
			case 11:
				System.out.println("�˳�CD����ϵͳ��");
				break exit;			//����while()
			default :
				System.out.println("����ָ�����󣬿�������0��Ż�ȡָ�������");
				break;
			}
			System.out.print(">>");
		}
	}
}//:~

