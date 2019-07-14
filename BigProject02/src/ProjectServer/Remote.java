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
	public String chooseCourse(String studentId, String CourseId) //����ѡ�ν����ʾ��Ϣ
	{
		try {
			pw.print(1);//1��ʾѡ��
			pw.println(studentId+","+CourseId); //�Զ�����Ϊ�ָ����������������
			pw.flush();
			**return br.readLine();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public String dropCourse(String studentId, String CourseId) //����ʾ�����˿β���
	{
		try {
			pw.print(2);//2��ʾ�˿�
			pw.println(studentId+","+CourseId); //�Զ�����Ϊ�ָ����������������
			pw.flush();
			**return br.readLine();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public boolean addStudent(String student)//id,name,classroom,gendar�ĸ��ϴ����м䶺�ŷָ�
	{
		try {
			dos.writeInt(3);//3��ʾ����ѧ����Ϣ
			dos.flush();
			System.out.println(student);
			dos.writeUTF(student); //�Զ�����Ϊ�ָ����������������
			dos.flush();
			int bool=dis.readInt();
			System.out.println("�ͻ�����������ѧ���ɹ���");
			return (bool==1)?true:false;
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
	//������Ӧѧ�ŵ�ѧ����Ϣ�������Ƿ���ڣ�1�ɹ�+ѧ����Ϣ��/��0ʧ�ܣ����ַ������Զ�����Ϊѧ����Ϣ�ָ�������������ѧ���򷵻�null
	public String findStudent(String id)
	{
		try {
			dos.writeInt(6);//6��ʾ����ѧ����Ϣ
			dos.writeUTF(id);
			dos.flush();
			return dis.readInt()==1 ? "1"+dis.readUTF() : "0";
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public String addCourse(String course)
	//String course_id,course_name,int num,left_num,stu_num;
	{
		try {
			pw.print(7);//7��ʾ���ӿγ���Ϣ
			pw.println(course); //����Ҫ���ӵĿγ���Ϣ�ַ���
			pw.flush();
			**return br.readLine();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public String deleteCourse(String id)//id
	{
		try {
			pw.print(8);//8��ʾɾ���γ���Ϣ
			pw.println(id);
			pw.flush();
			**return br.readLine();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public String changeCourse(String course)//
	{
		try {
			pw.print(9);//9��ʾ�޸Ŀγ���Ϣ
			pw.println(course); //����Ҫ�޸ĵĿγ���Ϣ�ַ���
			pw.flush();
			**return br.readLine();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public String findCourse()
	{
		try {
			pw.print(10);//10��ʾ���ҿγ���Ϣ

			pw.flush();
			**return br.readLine();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
}
