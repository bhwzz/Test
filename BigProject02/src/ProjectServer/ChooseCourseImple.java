package ProjectServer;

import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import EntityClass.Course;
import EntityClass.Stu_Course;

//选课接口的实现类
//服务器端提供服务时可以通过调用该类的函数进行
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
//	public static void Init() {//初始化C_array
//		//实际应该调用函数进行读文件
//		C_array=
//		//测试用例
//		if(C_array.size()==0) {
//			Course c1=new Course("001","英语",2,1,1);
//			Course c2=new Course("002","数学",2,1,1);
//			Course c3=new Course("003","语文",2,1,1);
//			C_array.add(c1);
//			C_array.add(c2);
//			C_array.add(c3);
//		}		
//	}
	
	//选课函数（学生id，要选课程id）
	//返回1：选课成功；返回0：课余量不足；返回-1：该学生已经选过该门课不可重复选课
	public int chooseCourse(String StuId,String CouId)
	{
		//判断该学生能否选过这门课（未实现）
//		boolean choose=IsChoose(stuId,couId);
//		if(choose) {//已经选过该门课
//			return -1;//返回-1表示这门课该学生已经选过
//		}
//		
		//对于可以选课的情况：
		int i;
		for(i=0;i<C_array.size();i++) {
			if(C_array.get(i).getCourse_id().equals(CouId)) {
				System.out.println("找到课程了！"+CouId);
				break;
			}
		}
		if(i<C_array.size()) {
			Course c=C_array.get(i);		
			synchronized(c) {
				if(c.getLeft_num()>0) {
					c.setLeft_num(c.getLeft_num()-1);//修改该课程的剩余数量
					c.setStu_num(c.getStu_num()+1);
					
					Calendar cal=Calendar.getInstance();//增加选课记录
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
					Stu_Course temp=new Stu_Course(StuId,CouId,sdf.format(cal.getTime()));
					SC_array.add(temp);
					temp.print();
					addStu_Course(temp);
					//将选课信息写入文件
					//此时只需要增加记录即可
					//调用写文件函数（可追加打开），append					
					return 1;//选课成功
				}
				else
					return 0;//表示课余量不足
			}
		}
		else
			return -1;
		
	}
	//退选函数（学生id，要退的课程id）
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
