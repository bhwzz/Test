package ProjectServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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
	DataInputStream dis=null;
	DataOutputStream dos=null;
	public ChooseCourseImple(Socket DBsocket) {
			super();
			try {
				dis=new DataInputStream(DBsocket.getInputStream());
				dos=new DataOutputStream(DBsocket.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}	
	public ChooseCourseImple() {
			super();
			// TODO Auto-generated constructor stub
	}

	
	//选课函数（学生id，要选课程id）
	public int chooseCourse(String StuId,String CouId)
	{
		int bool=0;
		//传给思洋：stuid，couid
		try {
			dos.writeInt(1);
			dos.writeUTF(StuId);
			dos.writeUTF(CouId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//要选课
		try {
			bool=dis.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}
	//退选函数（学生id，要退的课程id）
	public int dropCourse(String stuId,String couId) {
		int bool=0;
		try {
			dos.writeInt(2);
			dos.writeUTF(stuId);
			dos.writeUTF(couId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bool=dis.readInt();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;		
	}
	public void flushFile() {
		try {
			dos.writeInt(-1);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
