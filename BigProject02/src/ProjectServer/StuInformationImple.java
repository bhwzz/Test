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
	public boolean Add(Object o) {//����ѧ����
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
	//����ѧ����ѡ�γ�
	public String FindCourse(String id) {
		
		return "";		
	}
	//����ѧ����ѡ�γ���Ŀ���ú�����������һ����������˼�����ͬ�������Լ������ؽ������
	public int FindCourseNum(String id) {
		
		return 0;
	}
}
