/**Bank.java
ʵ������ϵͳģ�⣬���߳�ʵ�ֹ˿ʹ�ȡ�ֽ�Ĳ���Ҫ��
2018/12/13  wrote by ��ΰ

�������룺�������߳��е�run()���������ù˿��̣߳�ͬʱ����ʱ�������ͨ��random�����������
*/

import java.lang.Thread;		//�߳�
import java.util.Random;		//���������
import java.util.Formatter;		//�����ʽ����

class Data			//�˿�������
{
		String name;int rest;int check_in;int check_out;
		Data(){};
		void set(String _name,int in,int out){name=_name; check_in=in;check_out=out;rest=in-out;}
		void save(int in){ check_in+=in;rest+=in;}
		void withDraw(int out){ check_out+=out;rest-=out;}
		synchronized void print1(){System.out.println("�ͻ��˻���Ϣ�� ����"+name+" �����ֽ�����"+check_in+" ֧ȡ�ֽ�����"+check_out+" �˻����"+rest);}
		java.util.Formatter formatter=new  Formatter(System.out);
		synchronized void print(){formatter.format("%-10s %-10s %-10s %-10s \n",name,check_in,check_out,rest);}
}

public class Bank extends Thread{
	//����һЩ�û���
	String nameArray[]={"����","����","����","����","Ǯ��","�ﲩ","���","��ʱ","��ʷ��","���̶�","���ζ�","¹��","���׷�","����","����ͮ"
	};
	//�������пͻ���Ϣ�������ݿ�
    Data [] dataOfConsumer=new Data[15];
	int numOfConsumer;		//��¼�����˻����еĹ˿�����
	Random random=new Random((int)(System.currentTimeMillis()));	//��random������������
	Bank()
	{
		for(int i=0;i<15;i++)
			dataOfConsumer[i]=new Data() ;
		for(int i=0;i<10;i++)
		{
			int out=random.nextInt(10000);
			dataOfConsumer[i].set(nameArray[i],out+random.nextInt(10000),out); //���ó�ʼ���˻���Ϣ
		}
		numOfConsumer=10;		//�ȴ���ʮ�˵�����
	}
	public  synchronized void  saveMoney(String name,int num)		//ʹ�ùؼ���synchronized����ֹ���ʺ����ĳ�ͻ����withdraw������ͬ����
	{
		boolean exist=false;		//���ñ�����־�˿��Ƿ��Ѿ������п���
		for(int i=0;i<numOfConsumer;i++)
		{
			Data d=dataOfConsumer[i];
			if(d.name.equals(name))
			{
				d.check_in+=num;
				d.rest+=num;
				System.out.println(new java.util.Date().toString()+"��"+name+"�����ֽ�"+num+"Ԫ");
				d.print1();
				exist=true;
				break;
			}
		}
		if(exist==false)		//��ͻ�δ�����������е�dataOfconsumer���黹�пռ䣬��Ϊ֮����������ҵ���ܽ���
		{
				if(numOfConsumer<15)
				{
					dataOfConsumer[numOfConsumer++].set(name,num,0);
					System.out.println(new java.util.Date().toString()+"��"+name+"�ڱ�����δ��������Ϊ֮�����������ֽ�"+num+"Ԫ");
					dataOfConsumer[numOfConsumer-1].print1();
				}
				else
				{
					System.out.println(new java.util.Date().toString()+"��"+name+"�޷��ڱ��п���,�����ֽ�ҵ��δ�����");
				}
		}
		return ;
	}

	public synchronized void  withdrawMoney(String name,int num)
	{
		boolean exist=false;
		for(int i=0;i<numOfConsumer;i++)
		{
			Data d=dataOfConsumer[i];
			if(d.name.equals(name))
			{
				exist=true;
				if(d.rest>num)
				{
					d.check_out+=num;
					d.rest-=num;
					System.out.println(new java.util.Date().toString()+"��"+name+"ȡ���ֽ�"+num+"Ԫ");
					d.print1();
					break;
				}
				else		//���㣬��������
				{
					System.out.println(new java.util.Date().toString()+"��"+name+"�˻����㣬֧ȡ�ֽ�ҵ��δ�����");	
					d.print1();
					break;
				}
			}
		}
		if(exist==false)		//�ͻ�δ���������ܽ���ҵ��
		{
			System.out.println(new java.util.Date().toString()+"��"+name+"�û��ڱ������˻���Ϣ��");	
		}
		return;
	}
	public void run()			//ʵ������Ӫҵ��س���
	{
		int time=30;//������������ʱ��Ϊ30��
		System.out.println(new java.util.Date().toString()+": ���п���Ӫҵ��");
		while(true)
		{
			int gap=random.nextInt(10);			//�˿����������ҵ�����ʱ������Ϊ���ֵ
			time-=gap;
			if(time<0)		//������ʱ�䵽�˵�ʱ��׼ʱЪҵ
			{
				try{
					System.out.println("...\n"+new java.util.Date().toString()+" Waiting consumers��\n ...");
					sleep((gap+time)*1000);
				}catch(InterruptedException e){
					System.out.println("Interrupted!");
				}
				break;
			}
			try{			//������û�пͻ��ڼ���еȴ�
				if(gap!=0)
				System.out.println("...\n"+new java.util.Date().toString()+":  Waiting consumers��\n ...");	//��ʾ��Ϣ
				sleep(gap*1000);
			}catch(InterruptedException e){
				System.out.println("Interrupted!");
			}
			String nameTemp=nameArray[random.nextInt(15)];	//����õ�һλ�ͻ�������
			int command=random.nextInt(2)+1;	//���������ȡҵ��
			int money=random.nextInt(10000);		//ҵ�����������
			Thread t=new Thread(new Consumer(this,nameTemp,command,money),nameTemp);		//������Ϣ�����ͻ��߳�
			t.start();	//��ʼ�߳�
			try {		//ÿ��ҵ���ʱһ�룬�߳���ͣһ��
				sleep(1000);
			}catch(InterruptedException e){
			System.out.println("Interrupted!");
			}
			time--;				//ÿ��ҵ���ʱһ��
			if(time<0) break;
		}
		System.out.println(new java.util.Date().toString()+": ���й���Ъҵ��");
		System.out.println("���пͻ�����ȡ��¼���£�");		//����Ъҵ�󱨸�Ӫҵ״��
		java.util.Formatter formatter=new  Formatter(System.out);
		formatter.format("%-10s %-10s %-10s %-10s \n","���� ","����","֧ȡ "," �˻���� ");
		for(int i=0;i<numOfConsumer;i++)
			dataOfConsumer[i].print();
	}
	public static void main(String [] args)			//���������
	{
		Bank bank=new Bank();
		bank.start();
	}
}

class Consumer implements Runnable{
	//�˿�����
	String name;
	//���пͷ�
	Bank b;
	//��־�ù˿��Ǵ�Ǯ����ȡǮ,1��ʾ��Ǯ��2��ʾȡǮ
	int mark;
	//ҵ���漰���
	int numOfMoney;
	Consumer(Bank b,String name,int mark,int n){		//��ʼ��
		this.b=b;
		this.name=name;
		this.mark=mark;
		numOfMoney=n;
	}
	public void run(){		//�ͻ�����ҵ��
		if(mark==1)
		{
			b.saveMoney(name,numOfMoney);
		}
		if(mark==2)
		{
			b.withdrawMoney(name,numOfMoney);
		}
	}
}
