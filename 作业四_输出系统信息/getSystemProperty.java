//��getSystemProperty.java

import java.util.Properties;
/**
���Ĵ�Java��ҵ����ȡϵͳ��Ϣ���������в��������������ϵͳ����������������������������Ӧ����Ϣ
*@author 1711350  ��ΰ
*@version 2018/11/08
*/
public class getSystemProperty{
	/**������ʵ��ϵͳ��Ϣ��������������в������������Ҫ�����Ϣ������ȫ�����ϵͳ��Ϣ
	*@param  args �����в�����ȡ
	*@return NO returns
	*/
	 public static void main(String[] args)
	 {
		 System.out.println("�������ϵͳ������Ϣ��");
		if(args.length>0)
			for(int i=0;i<args.length;i++)
				System.out.println(System.getProperty(args[i]));
		else
		{	
			Properties properties=System.getProperties();
			for(String key:properties.stringPropertyNames())
				System.out.println(key+"="+properties.getProperty(key));
		}
	 }
}