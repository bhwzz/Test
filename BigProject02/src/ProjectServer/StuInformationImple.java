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
	public boolean Add(Object o) {//����ѧ��������Ҫ�жϸ�ѧ���Ƿ��Ѿ�����,�����ѧ���Ѿ����ڻ᷵��false
		Student s=(Student)o;
		boolean bool=false;
		try {
			dos.writeInt(3);
			dos.writeUTF(s.toString());//ֱ�Ӹ����ݿ�˴�ѧ����Ϣ
			dos.flush();
			bool = dis.readBoolean();
			System.out.println("�ͻ������������������ѧ���ɹ���");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public int Delete(String id) {//ɾ��ѧ��,ɾ���ɹ�����1��ѧ�������ڷ���-1��ѧ����ѡ�μ�¼����-2???????(���޸ģ�
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
			System.out.println("�ͻ��������������ɾ��ѧ���ɹ���");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public boolean Change(String s) {//�޸�ѧ����Ϣ�������жϸ�ѧ���Ƿ��Ѿ����ڣ������ڷ���false
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
	public Object Find(String id) {//����ѧ����Ϣ���ҵ�����studentû�ҵ�����null
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
	//����ѧ����ѡ�γ�
	public String FindCourse(String id) {
		
		return "";		
	}
	//����ѧ����ѡ�γ���Ŀ���ú�����������һ����������˼�����ͬ�������Լ������ؽ������
	public int FindCourseNum(String id) {
		
		return 0;
	}
}
