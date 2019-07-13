package ProjectServer;

import java.net.Socket;

import EntityClass.Student;

public class StuInformationImple implements InformationOperate{
	Socket DBsocket;
	
	public StuInformationImple(Socket dBsocket) {
		super();
		DBsocket = dBsocket;
	}
	public StuInformationImple() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean Add(Object o) {//增加学生，
		Student s=(Student)o;
		
		return false;
	}

	@Override
	public boolean Delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Change(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object Find(String id) {
		// TODO Auto-generated method stub
		return false;
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
