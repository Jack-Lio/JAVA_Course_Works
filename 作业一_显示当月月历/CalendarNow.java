/*author����ΰ time��18/10/5
*ѧ�ţ�1711350  �����ѧԺ�ƿ�һ��
*��ҵ������������ʽ�����ǰ�·ݵ�����
*/
import java.util.Calendar;

public class CalendarNow{
	public static void main(String[]args){
		Calendar cal=Calendar.getInstance();
		//��ȡ�ꡢ�¡�����Ϣ
		 int year=cal.get(Calendar.YEAR);
		 int  month=cal.get(Calendar.MONTH);
		 int day=cal.get(Calendar.DAY_OF_MONTH);
		 int week=cal.get(Calendar.DAY_OF_WEEK)-1;//��������ֵΪ1-7����������Ϊ1������Ϊ6��������������-1
		 int week_0=(week-day%7+1)%7;
		 int week0=week_0>0?week_0:7+week_0; //�п��ܳ���week_0Ϊ��������������ڼ����������-3%7=-3������ᱨ��
		 int []Month={31,28,31,30,31,30,31,31,30,31,30,31};
		 if(year%4==0&&year%100!=0||year%400==0)
			 Month[1]=29;
		 System.out.println(year+"��"+(month+1)+"��"+day+"��");
		 //���ø��·ݵ�����
		 System.out.println("Sun\t"+"Mon\t"+"Tue\t"+"Wed\t"+"Thu\t"+"Fri\t"+"Sat\t");
		 
		 int count=0;
		while(week0!=0)
		{
			System.out.print("\t");
			week0--;count++;
		}
		for(int i=0;i<Month[month];i++)
		{
			System.out.print((i+1)+"\t");
			count++;
			if(count%7==0)
				System.out.println();
		}
	}
}