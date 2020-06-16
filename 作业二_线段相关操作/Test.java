/*2018/10/10  ��ΰ  1711350
*��ҵ3����д�߶���MyLine,Ҫ�����£�
*��Ҫ���ԣ��˵㡢����ΪPoint
*��д���췽��
*��д��Ա������
*	����߶��Ƿ�λ�ڵ�һ����
*	���߶γ���
*	�ж�����ֱ���Ƿ��ཻ����һ�߶���Ϊ������
*	�ж������߶��Ƿ��ཻ
*	��һ�㵽�߶εľ���
*	��������
*/

class Point{
	//�˵����
	public int x,y;
	Point(){x=0;y=0;}
	Point(int a,int b){x=a;y=b;}
}

class MyLine{
	public Point e1,e2;
	public double k,b;//��¼�߶�����ֱ�ߵķ��̲���
	MyLine(){e1=new Point();e2=new Point();k=b=0;}
	MyLine(int a1,int b1,int a2,int b2){
		e1=new Point(a1,b1); e2=new Point(a2,b2);
		k=(b1-b2)/(a1-a2);b=b1-k*a1;
	}
	public boolean cheek_One_Area(){			//����Ƿ��ڵ�һ����
		if((e1.x>0&&e1.y>0)&&(e2.x>0&&e2.y>0))
			return true;
		else
			return false;
	}
	public double length(){			//���߶γ���
		return Math.sqrt(Math.pow(e1.x-e2.x,2)+Math.pow(e2.y-e1.y,2));
	}
	public double distance_to_Plot(Point p){				//��㵽ֱ�߾���
		return Math.abs(p.y-k*p.x-b)/Math.sqrt(Math.pow(k,2)+1);
	}
	public boolean is_Cross(MyLine ln){			//�ж������߶�����ֱ���Ƿ��ཻ
		if(ln.k!=this.k)  
			return true;
		else
			return false;
	}
	public boolean is_Line_Cross(MyLine ln){			//�������߶��Ƿ��ཻ
		if(((ln.k*(this.e1.x)+ln.b-(this.e1.y))*(ln.k*(this.e2.x)+ln.b-(this.e2.y))<=0)
			&&((this.k*(ln.e1.x)+this.b-(ln.e1.y))*(this.k*(ln.e2.x)+this.b-(ln.e2.y))<=0))
			return true;
		else
			return false;
	}
}

public class Test{
	public static void main(String[]args)
	{
		Point plot=new Point(5,6);
		MyLine line=new MyLine(1,-1,2,3);
		MyLine line1=new MyLine(-1,-1,2,0);
		MyLine line2=new MyLine(-1,-1,0,1);
		System.out.println(line.cheek_One_Area());
		System.out.println(line.length());
		System.out.println(line.is_Cross(line1));
		System.out.println(line.is_Line_Cross(line1));
		System.out.println(line.is_Cross(line2));
		System.out.println(line.is_Line_Cross(line2));
		System.out.println(line.distance_to_Plot(plot));
	}
}