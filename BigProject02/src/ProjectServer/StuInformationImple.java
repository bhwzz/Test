package ProjectServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import EntityClass.Student;

public class StuInformationImple implements InformationOperate{
	//Socket DBsocket;
	DataInputStream dis=null;
	DataOutputStream dos=null;
	ObjectOutputStream oos=null;
	ObjectInputStream ois=null;
	public StuInformationImple(Socket DBsocket) {
		super();
		//DBsocket = dBsocket;
		try {
			dis=new DataInputStream(DBsocket.getInputStream());
			dos=new DataOutputStream(DBsocket.getOutputStream());
			oos=new ObjectOutputStream(DBsocket.getOutputStream());
			ois=new ObjectInputStream(DBsocket.getInputStream());
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
			oos.writeObject(s);//直接给数据库端传学生信息
			bool = dis.readBoolean();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public boolean Delete(String id) {//删除学生,删除成功返回1，学生不存在返回-1，学生有选课记录返回-2???????(待修改）
		boolean bool=false;
		try {
			dos.writeUTF(id);;
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
	public boolean Change(Object o) {//修改学生信息，无需判断该学生是否已经存在，不存在返回false
		boolean bool=false;
		try {
			oos.writeObject((Student)o);
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
		return false;
	}

	@Override
	public Object Find(String id) {//查找学生信息，找到返回student没找到返回null
		Student stu=null;
		try {
			dos.writeUTF(id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stu=(Student)ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stu;
	}
	//查找学生已选课程
	public String FindCourse(String id) {
		
		return "";		
	}
	//查找学生已选课程数目，该函数可以与上一个函数调用思洋的相同函数，自己处理返回结果即可
	public int FindCourseNum(String id) {
		
		return 0;
	}
}
