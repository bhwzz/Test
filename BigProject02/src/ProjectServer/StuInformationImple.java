package ProjectServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import EntityClass.Stu_Course;
import EntityClass.Student;

public class StuInformationImple implements InformationOperate{
	//Socket DBsocket;
	DataInputStream dis=null;
	DataOutputStream dos=null;
	public StuInformationImple(Socket DBsocket) {
		super();
		//DBsocket = dBsocket;
		try {
			dis=new DataInputStream(DBsocket.getInputStream());
			dos=new DataOutputStream(DBsocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public StuInformationImple() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean Add(Object o) {//增加学生，不需要判断该学生是否已经存在,如果该学生已经存在会返回false
		Student s=(Student)o;
		boolean bool=false;
		try {
			dos.writeInt(3);
			dos.writeUTF(s.toString());//直接给数据库端传学生信息
			dos.flush();
			bool = dis.readBoolean();
			System.out.println("客户端向服务器请求增加学生成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public int Delete(String id) {//删除学生,删除成功返回1，学生不存在返回-1，学生有选课记录返回-2???????(待修改）
		int bool=0;
		try {
			dos.writeInt(4);
			dos.writeUTF(id);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bool=dis.readInt();
			System.out.println("客户端向服务器请求删除学生成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public boolean Change(String s) {//修改学生信息，无需判断该学生是否已经存在，不存在返回false
		boolean bool=false;
		try {
			dos.writeInt(5);
			dos.writeUTF(s);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bool=dis.readBoolean();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public Object Find(String id) {//查找学生信息，找到返回student没找到返回null
		Student stu=null;
		try {
			dos.writeInt(6);
			dos.writeUTF(id);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			String s=dis.readUTF();
			System.out.println(s);
			if(!s.equals("null"))
				stu=Student.toStudent(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return stu;
	}
	//查找学生已选课程
	public List FindCourse(String id) {//传stuid返回该学生选的所有的课,该学生一定存在
		try {
			dos.writeInt(12);
			dos.writeUTF(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int num;
		String s;
		List<Stu_Course> list=new ArrayList<Stu_Course>();
		try {
			num = dis.readInt();
			for(int i=0;i<num;i++) {
				s=dis.readUTF();
				list.add(Stu_Course.toStuCourse(s));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return list;//如果没有选课记录，就使用list.size()判断		
	}
	public void flushFile() {
		try {
			dos.writeInt(-2);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
