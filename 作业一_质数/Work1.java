/*author����ΰ time�� 18/10/5 
*ѧ�ţ�1711350  �����ѧԺ�ƿ�һ��
*��ҵһ����ʾ2-100������������ÿ�����ʾ��һ����
*/

public class Work1
{
	public static void main(String[] args)
	{
		int b=0;
		for(int i=2;i<=100;i++)
		{
			int a=2;
			
			for(int j=2;j<i;j++)
				if((i%j)!=0)
					a++;
			if(a==i)
			{
				b++;
				System.out.print(i+"\t");
				
				if((b%5)==0)
					System.out.println();
			}
		}
	}
}