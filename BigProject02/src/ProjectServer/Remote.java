package ProjectServer;

import java.io.*;
import java.net.Socket;


public class Remote { //���أ��ͻ��ˣ������������ͨ�ŷ�ʽ
	String host;
	int port;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	BufferedReader br=null;
	PrintWriter pw=null;

	Remote(String host, int port) throws Exception{
		this.host = host;
		this.port = port;
		Socket socket = new Socket(host, port);
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
		//br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
	public String chooseCourse(String studentId, String CourseId) //���� ��1�ɹ���/��0ʧ��+ʧ��ԭ��
	{
		try {
			dos.writeInt(1);//1��ʾѡ��
			dos.writeUTF(studentId); 
			dos.writeUTF(CourseId); 
			dos.flush();
			return dis.readInt()==1 ? "1" : "0"+dis.readUTF();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public String dropCourse(String studentId, String CourseId) //���� ��1�ɹ���/��0ʧ��+ʧ��ԭ��
	{
		try {
			dos.writeInt(2);//2��ʾ�˿�
			dos.writeUTF(studentId);
			dos.writeUTF(CourseId); 
			dos.flush();
			return dis.readInt()==1 ? "1" : "0"+dis.readUTF();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public boolean addStudent(String student)//id,name,classroom,gendar�ĸ��ϴ����м䶺�ŷָ�
	{
		try {
			dos.writeInt(3);//3��ʾ����ѧ����Ϣ
			dos.writeUTF(student); //�Զ�����Ϊ�ָ����������������
			dos.flush();
			return dis.readInt()== 1;
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public String deleteStudent(String id)//����ѧ��ɾ�������� ��1�ɹ���/��0ʧ��+������Ϣ��
	{
		try {
			dos.writeInt(4);//4��ʾɾ��ѧ����Ϣ
			dos.writeUTF(id);
			dos.flush();
			return dis.readInt()==1 ? "1" : "0"+dis.readUTF();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public boolean changeStudent(String student)//����ͬaddStudent���޸Ķ�Ӧѧ��id��ѧ������Ϣ
	{
		try {
			dos.writeInt(5);//5��ʾ�޸�ѧ����Ϣ
			dos.writeUTF(student); //����Ҫ�޸ĵ�ѧ����Ϣ�ַ���
			dos.flush();
			return dis.readInt()== 1;
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}

	}
	//������Ӧѧ�ŵ�ѧ����Ϣ�������ַ������飬����String[0]Ϊ�Ƿ���ڣ�1����+ѧ����Ϣ��/��0�����ڣ���String[1]-String[num]Ϊѧ����ѡnum�ſγ̵Ŀγ���Ϣ���Զ�����Ϊ�γ���Ϣ�ڲ��ָ���
	public String[] findStudent(String id)
	{
		try {
			dos.writeInt(6);//6��ʾ����ѧ����Ϣ
			dos.writeUTF(id);
			dos.flush();
			String str = dis.readUTF();
			String[] s = str.split(".");
			if(s.length == 0) { //��ʾû�зָ�������ѡ�γ̣���strװ���ַ�������
				String s2[] = new String[1];
				s2[0] = str;
				return s2;
			}			
			else
				return s;
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public boolean addCourse(String id, String name, int num) //���ݿγ̺ţ��γ�����������������(1�ɹ�)/(0ʧ��,ԭ���ǿ���ų�ͻ)
	//�γ�����(String) course_id,course_name, (int) num,left_num,stu_num;
	{
		try {
			dos.writeInt(7);//7��ʾ���ӿγ���Ϣ
			dos.writeUTF(id+","+name+","+num); //�Զ�����Ϊ�ָ��������������Ҫ���ӵĿγ̺ţ��γ�����������
			dos.flush();
			return dis.readInt()== 1;
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public String deleteCourse(String id)//����(1�ɹ�)/(0ʧ��+ʧ��ԭ��)�ٲ����ڸÿγ̢ڸÿγ�����ѡ���޷�ɾ��
	{
		try {
			dos.writeInt(8);//8��ʾɾ���γ���Ϣ
			dos.writeUTF(id);
			dos.flush();
			return dis.readInt()==1 ? "1" : "0"+dis.readUTF();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public boolean changeCourse(String id, String name)	//ֻ���޸Ŀγ����֡�����(1�ɹ�)/(0ʧ��,ԭ���ǲ����ڸÿγ̺ſγ�)
	{
		try {
			dos.writeInt(9);//9��ʾ�޸Ŀγ���Ϣ
			dos.writeUTF(id+","+name); //�Զ�����Ϊ�ָ��������������Ҫ�޸ĵĿγ̺ţ��γ���
			dos.flush();
			return dis.readInt()== 1;
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public String[] findAllCourse()//�������пγ���Ϣ�����ض�Ӧ��Ŀ�Ŀγ̴�(ÿ���γ���Ϣ�ڲ�������Ȼ�Զ��ŷָ�)
	{
		try {
			dos.writeInt(10);//10��ʾ�������пγ���Ϣ
			dos.flush();
			int num = dis.readInt();
			String[] s = new String[num];
			for(int i=0; i<num; i++) {
				s[i] = dis.readUTF();
			}
			return s;
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public boolean addCourseCapacity(String id, int addNum) //���ӿγ�����������(1���ӳɹ�)/(0����ʧ�ܣ�ԭ���ǲ����ڸÿγ�)
	{
		try {
			dos.writeInt(11);//11��ʾ���ӿγ�����
			dos.writeUTF(id);
			dos.writeInt(addNum);
			dos.flush();
			return dis.readInt()== 1;
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public void exitChoosecourseManage() {
		try {
			dos.writeInt(-1); //-1��ʾ���߷������˳�ѡ�ι���
		} catch (IOException e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public void exitStudentManage() {
		try {
			dos.writeInt(-2); //-2��ʾ���߷������˳�ѧ������
		} catch (IOException e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public void exitCourseManage() {
		try {
			dos.writeInt(-3); //-2��ʾ���߷������˳��γ̹���
		} catch (IOException e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
}
