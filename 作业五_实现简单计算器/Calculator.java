//~Calculator.java demo

/**2018/11/28 1711350 liwei
~Calculator.java  
users :
	���������ܹ�ʵ�ֿ������ȼ����������㡢ȡģ�����Ǻ������㣬���ܹ�����ʮ���Ƶ�����ʽ��
	ͬʱʵ�������ݴ洢���洢�������Լ���ȡ�ڴ����ݵĹ��ܣ�
	�ڼ������л��ܹ�ʵ�ָ��ŵĸı䣬������������ѣ��޸Ĺ��ܣ�ͬʱ�ܹ�����˲�ͬ��ɾ�����ܡ�
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;
import java.lang.Number.*;
import java.text.DecimalFormat;

class MyFrame extends Frame{
	//����������ݵĸ�ʽ
	DecimalFormat df=new DecimalFormat("###.########");
	//��������ջ�����������Լ������ʵ�����ȼ��Ŀ���
	Stack<Double> n_stack=new Stack<Double>();
	Stack<String> c_stack=new Stack<String>();
	//���水����keyֵ
		String[] str={
			"Mod","10^x","��","CE","C","Del","sin","MC","1","2","3","+","cos","MR","4","5","6","-","tan","MS","7","8","9","x","+/-","M+","0",".","=","/"
		};
		//�����ı����Լ���ť
	JTextField mt=new JTextField(10),
		it1=new JTextField(20),
		it2=new JTextField(20);
	JButton [] keys=new JButton[30];
	//��¼�������쳣״̬
	boolean worse=false;
	//�Ӽ��˳��Լ�ȡģ����
	double func(double a,double b,String character)
	{
		if(character.equals("+"))
			return a+b;
		else if(character.equals("-"))
			return a-b;
		else if(character.equals("x"))
			return a*b;
		else if(character.equals("/"))
			return a/b;
		else if(character.equals("Mod"))
			return a%b;
		else return 0;
	}
	//���Ǻ����������غ���
	double func(String character,double a)
	{
		if(character.equals("cos"))
			return Math.cos(Math.PI*(a/180));
		else if(character.equals("sin"))
			return Math.sin(Math.PI*(a/180));
		else if(character.equals("tan"))
			return Math.tan(Math.PI*(a/180));
		else if(character.equals("10^x"))
			return Math.pow(10,a);
		else return 0;
	}
	//ע�������
	class depress implements ActionListener{
		public void actionPerformed(ActionEvent e)
		{
			//��ȡ��Ӧ��ť��keyֵ
			String keyName=((JButton)e.getSource()).getText();
			//�����ֵĴ���
			for(int i=0;i<10;i++)
				if(keyName.equals(Integer.toString(i)))
				{
					if(it2.getText().equals("")||it2.getText().equals("0")||it2.getText().substring(0,1).equals("="))
						it2.setText(keyName);
					else
						it2.setText(it2.getText()+keyName);
				}
				//��С����Ĵ���
			if(keyName.equals("."))
			{
				if(it2.getText().indexOf(".",0)==-1)		//������ʹ����һ������������ֻ�ܹ����һ��С���㣬�����������
					it2.setText(it2.getText()+keyName);
				return;
			}
			//�ԼӼ��˳��Լ�ȡģ������Ĵ���
			if(keyName.equals("+")||keyName.equals("-")||keyName.equals("x")||keyName.equals("/")||keyName.equals("Mod"))
			{
				Double gD;
				if(it2.getText().substring(0,1).equals("="))
					gD=Double.valueOf(it2.getText().substring(1,it2.getText().length()));
				else
					gD=Double.valueOf(it2.getText());
				if(!c_stack.isEmpty()&&(c_stack.peek().equals("/")||c_stack.peek().equals("Mod"))&&Double.valueOf(it2.getText())==0)
				{
					it2.setText("������ģ������Ϊ0,�밴CE�������������");
					for(int i=0;i<30;i++)
						if(i!=3)
						keys[i].setEnabled(false);
					worse=true;
					return;
				}
				n_stack.push(gD);
				if(!c_stack.isEmpty())
					if(c_stack.peek().equals("cos")||c_stack.peek().equals("sin")||c_stack.peek().equals("tan")||c_stack.peek().equals("10^x"))
							n_stack.push(func(c_stack.pop(),n_stack.pop()));
					else
					if(c_stack.peek().equals("x")||c_stack.peek().equals("/")||c_stack.peek().equals("Mod"))
						{
							double b=n_stack.pop(),a=n_stack.pop();
							n_stack.push(func(a,b,c_stack.pop()));
						}
				c_stack.push(keyName);		
				it1.setText(it1.getText()+" "+df.format(gD)+" "+keyName);
				it2.setText("0");
				return;
			}
			//�������ť�Ĵ��������������
			if(keyName.equals("C"))
			{
				it1.setText("");
				it2.setText("0");
				while(!c_stack.isEmpty())
					c_stack.pop();
				while(!n_stack.isEmpty())
					n_stack.pop();
				return;
			}
			//��������
			if(keyName.equals("CE"))
			{
				it2.setText("0");
				if(worse==true)
				{
					for(int i=0;i<30;i++)
						keys[i].setEnabled(true);
					worse=false;
				}
				return;
			}
			//ɾ����
			if(keyName.equals("Del"))
			{
				if(it2.getText().length()!=0)
					it2.setText(it2.getText().substring(0,it2.getText().length()-1));
				return;
			}
			//Բ���ʦ�
			if(keyName.equals("��"))
			{			
				it2.setText("3.1415926");
				return;
			}
			//���ں���Ӧ
			if(keyName.equals("="))
			{
				if(!c_stack.isEmpty()&&(c_stack.peek().equals("/")||c_stack.peek().equals("Mod"))&&Double.valueOf(it2.getText())==0)
				{
					it2.setText("��������Ϊ0,�밴CE�������������");
					for(int i=0;i<30;i++)
						if(i!=3)
						keys[i].setEnabled(false);
					worse=true;
					return;
				}
				if(!it2.getText().substring(0,1).equals("="))
					n_stack.push(Double.valueOf(it2.getText()));
				else 
					return;
				while(!c_stack.isEmpty()){
					if(c_stack.peek().equals("cos")||c_stack.peek().equals("sin")||c_stack.peek().equals("tan")||c_stack.peek().equals("10^x"))
							n_stack.push(func(c_stack.pop(),n_stack.pop()));
					else
						{
							double b=n_stack.pop(),a=n_stack.pop();
							n_stack.push(func(a,b,c_stack.pop()));
						}
				}
					it2.setText("= "+df.format(Double.valueOf(n_stack.pop())));
				it1.setText("");
				return;
			}
			//����
			if(keyName.equals("+/-"))
			{
				if(Double.valueOf(it2.getText())>0)
				{
				it2.setText("-"+it2.getText());
				}
				else
					it2.setText(it2.getText().substring(1,it2.getText().length()));
				return;
			}
			//���Ǻ���
			if(keyName.equals("cos")||keyName.equals("sin")||keyName.equals("tan")||keyName.equals("10^x"))
			{
				c_stack.push(keyName);
				if(keyName.equals("10^x"))
					it1.setText(it1.getText()+"10^");
				else
					it1.setText(it1.getText()+keyName);
				return ;
			}
			//���浱ǰ������������ݲ���洢��������ӣ���ʾ���ڴ���ʾ��
			if(keyName.equals("M+"))
			{
				String res=it2.getText().substring(0,1).equals("=")?it2.getText().substring(1,it2.getText().length())
				:it2.getText();
				if(mt.getText().equals(""))
					mt.setText(res);
				else
					mt.setText(df.format(func(Double.valueOf(mt.getText()),Double.valueOf(res),"+")));
				return;
			}
			//���
			if(keyName.equals("M-"))
			{
				if(mt.getText().equals(""))
					mt.setText(it2.getText());
				else
					mt.setText(df.format(func(Double.valueOf(mt.getText()),Double.valueOf(mt.getText()),"-")));
				return;
			}
			//�ڴ����
			if(keyName.equals("MC"))
			{
				mt.setText("");
				return;
			}
			//�洢����������
			if(keyName.equals("MS"))
			{
				mt.setText(it2.getText().substring(0,1).equals("=")?it2.getText().substring(1,it2.getText().length())
				:it2.getText());
				return;
			}
			//���ڴ���ʾ�������
			if(keyName.equals("MR"))
			{
				it2.setText(mt.getText().equals("")?"= 0":mt.getText());
				return;
			}
		}
	}
		
	public MyFrame()
	{
		super("Calculator");
		//���ô��ڹرն���
	addWindowListener(new WindowAdapter(){
      public void windowClosing(WindowEvent e)
		{
        System.exit(0);
		}
	});//�رմ���
	//�����壬�������ð�ť
	JPanel kp=new JPanel(new GridLayout(5,6));
	int i=0;
	//��Ӱ�ť
	for(String s:str)
	{
		keys[i++]=new JButton(s);
	}
	//Ϊ��ťע�������
	for(int j=0;j<30;j++)
	{
		keys[j].addActionListener(new depress());
		kp.add(keys[j]);
	}
	//����
		Box bh=Box.createHorizontalBox();
		Box iv=Box.createVerticalBox();
		mt.setEditable(false);
		it1.setEditable(false);
		it2.setEditable(false);
		it1.setHorizontalAlignment(JTextField.RIGHT);
		it2.setHorizontalAlignment(JTextField.RIGHT);
		it2.setText("0");		//����������ʼ��Ϊ0
		iv.add(it1);iv.add(it2);
		bh.add(mt);
		bh.add(Box.createHorizontalStrut(10));
		bh.add(iv);
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		add(bh);
		add(kp);
		super.pack();
		setResizable(false);		//���ô��ڴ�С���ɱ�
		setVisible(true);//���ô��ڿɼ�
	}
}

public class Calculator{
	public  static void main(String[]args)
	{
		MyFrame calculator=new MyFrame();//���ô��ڽ��ж���
	}	
	
}