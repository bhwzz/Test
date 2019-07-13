package ProjectServer;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import EntityClass.Course;
import EntityClass.Stu_Course;

//ѡ�νӿڵ�ʵ����
//���������ṩ����ʱ����ͨ�����ø���ĺ�������
public class ChooseCourseImple implements ChooseCourse{
	Socket DBsocket;
	
	public ChooseCourseImple(Socket dBsocket) {
			super();
			DBsocket = dBsocket;
	}	
	public ChooseCourseImple() {
			super();
			// TODO Auto-generated constructor stub
	}
//	static ArrayList<Course> C_array=null;	
//	
//	public static void Init() {//��ʼ��C_array
//		//ʵ��Ӧ�õ��ú������ж��ļ�
//		C_array=
//		//��������
//		if(C_array.size()==0) {
//			Course c1=new Course("001","Ӣ��",2,1,1);
//			Course c2=new Course("002","��ѧ",2,1,1);
//			Course c3=new Course("003","����",2,1,1);
//			C_array.add(c1);
//			C_array.add(c2);
//			C_array.add(c3);
//		}		
//	}
	
	//ѡ�κ�����ѧ��id��Ҫѡ�γ�id��
	//����1��ѡ�γɹ�������0�����������㣻����-1����ѧ���Ѿ�ѡ�����ſβ����ظ�ѡ��
	public int chooseCourse(String StuId,String CouId)
	{
		//�жϸ�ѧ���ܷ�ѡ�����ſΣ�δʵ�֣�
//		boolean choose=IsChoose(stuId,couId);
//		if(choose) {//�Ѿ�ѡ�����ſ�
//			return -1;//����-1��ʾ���ſθ�ѧ���Ѿ�ѡ��
//		}
//		
		//���ڿ���ѡ�ε������
		int i;
		for(i=0;i<C_array.size();i++) {
			if(C_array.get(i).getCourse_id().equals(CouId)) {
				System.out.println("�ҵ��γ��ˣ�"+CouId);
				break;
			}
		}
		if(i<C_array.size()) {
			Course c=C_array.get(i);		
			synchronized(c) {
				if(c.getLeft_num()>0) {
					c.setLeft_num(c.getLeft_num()-1);//�޸ĸÿγ̵�ʣ������
					c.setStu_num(c.getStu_num()+1);
					
					Calendar cal=Calendar.getInstance();//����ѡ�μ�¼
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
					Stu_Course temp=new Stu_Course(StuId,CouId,sdf.format(cal.getTime()));
					SC_array.add(temp);
					temp.print();
					addStu_Course(temp);
					//��ѡ����Ϣд���ļ�
					//��ʱֻ��Ҫ���Ӽ�¼����
					//����д�ļ���������׷�Ӵ򿪣���append					
					return 1;//ѡ�γɹ�
				}
				else
					return 0;//��ʾ����������
			}
		}
		else
			return -1;
		
	}
	//��ѡ������ѧ��id��Ҫ�˵Ŀγ�id��
	public int dropCourse(String stuId,String couId) {
		boolean bool=IsChoose(stuId,couId);
		if(bool) {
			boolean suc=deleteCourse(stuId,couId);
			return suc?1:0;
		}
		else {
			return 
		}
	}
}
