package buffer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import EntityClass.Course;
import EntityClass.Stu_Course;
import EntityClass.Student;

public class DBProtocol {
	StudentBuffer sb=null;//����ѧ������
	CourseBuffer cb=null;//���ڿγ̲���
	Stu_CourseBuffer scb=null;
	
	public DBProtocol(String stufile,String coufile) throws Exception {//���캯������Ҫ�޸ģ�������
		super();
		this.sb=new StudentBuffer(stufile);
		this.cb=new CourseBuffer(coufile);
		this.scb=new Stu_CourseBuffer(sb,cb);
	}

	public void Service(Socket serverSocket) throws ClassNotFoundException, Exception {
		try {
			DataInputStream dis=new DataInputStream(serverSocket.getInputStream());
			DataOutputStream dos=new DataOutputStream(serverSocket.getOutputStream());;
//			ObjectInputStream ois;
//			ObjectOutputStream oos=new ObjectOutputStream(serverSocket.getOutputStream());
//			PrintWriter pw=new PrintWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
			int i;
			while(true) {
				i=dis.readInt();
				System.out.println("����������"+i);
				switch(i) {
				case 1://ѡ��
					dos.writeInt(scb.add(dis.readUTF(),dis.readUTF()));
					dos.flush();
					break;
				case 2://��ѡ	
					dos.writeInt(scb.delete(dis.readUTF(),dis.readUTF()));
					dos.flush();
					break;
					
				case 3://����ѧ����Ϣ������һ��ѧ������
					dos.writeBoolean(sb.Add(Student.toStudent(dis.readUTF())));
					System.out.println("���ݿ������ѧ���ɹ���");
					dos.flush();
					break;
				case 4://ɾ��ѧ����Ϣ��������һ��ѧ��id
					dos.writeBoolean(sb.Delete(dis.readUTF()));
					System.out.println("���ݿ�ɾ��ѧ���ɹ���");
					dos.flush();
					break;
				case 5://�޸�ѧ����Ϣ������һ��ѧ������
					dos.writeBoolean(sb.Change(Student.toStudent(dis.readUTF())));
					System.out.println("���ݿ���޸�ѧ���ɹ���");
					dos.flush();
					break;
				case 6://����ѧ����Ϣ��������ѧ��id
					Student s=sb.Find(dis.readUTF());
					if(s==null) {
						dos.writeUTF("null");
						System.out.println("null");
					}
					else {
						dos.writeUTF(s.toString());
					System.out.println("���ݿ�˲���ѧ���ɹ���");
					}
					break;					
				case 7://���ӿγ�
					dos.writeBoolean(cb.Add(Course.toCourse(dis.readUTF())));
					dos.flush();
					break;
				case 8://ɾ���γ̣�Ҫ��ÿγ̵�ѡ������Ϊ0
					dos.writeInt(cb.Delete(dis.readUTF()));
					dos.flush();
					break;
				case 9://�޸Ŀγ���Ϣ
					dos.writeBoolean(cb.Change(dis.readUTF()));
					dos.flush();
					break;
				case 10://�������пγ���Ϣ
					Map map=cb.FindAll();
					dos.writeInt(map.size());//��д�ظ���
					Iterator it=map.entrySet().iterator();
					while(it.hasNext()) {
						Map.Entry<String,Course> entry=(Map.Entry<String, Course>)it.next();
						dos.writeUTF(entry.getValue().toString());
					}
					dos.flush();
					break;
				case 11://���ӿγ�����
					dos.writeBoolean(cb.AddCapcity(dis.readUTF(), dis.readInt()));
					dos.flush();
					break;
				case 12://����ѧ����ѡ�γ���Ϣ,��ѧ��һ������
					Map<String,Stu_Course> map1=scb.find(dis.readUTF());
					int num=map1.size();
					dos.writeInt(num);
					if(num>0) {
						Iterator it1=map1.entrySet().iterator();
						while(it1.hasNext()) {
							Map.Entry<String, Stu_Course> entry=(Map.Entry<String, Stu_Course>)it1.next();
							dos.writeUTF(entry.getValue().toString());
						}
					}
					dos.flush();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("û�����ӵ��ͻ���");
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, Exception {
		Properties p=new Properties();
		p.load(new FileInputStream("file.properties"));
		String DBport=p.getProperty("DBport");
		System.out.println(DBport);
		ServerSocket ss=new ServerSocket(Integer.parseInt(DBport));
		System.out.println("���ݿ��������Ѿ�׼�����ˣ�");
		DBProtocol dbp=new DBProtocol("D:\\test3.txt","D:\\Test4.txt");
		while(true) {
			Socket s=ss.accept();
			dbp.Service(s);
		}
	}
	
}
