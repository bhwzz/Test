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
	StudentBuffer sb=null;//用于学生操作
	CourseBuffer cb=null;//用于课程操作
	Stu_CourseBuffer scb=null;
	
	public DBProtocol(String stufile,String coufile) throws Exception {//构造函数还需要修改！！！！
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
				System.out.println("服务器请求："+i);
				switch(i) {
				case 1://选课
					dos.writeInt(scb.add(dis.readUTF(),dis.readUTF()));
					dos.flush();
					break;
				case 2://退选	
					dos.writeInt(scb.delete(dis.readUTF(),dis.readUTF()));
					dos.flush();
					break;
					
				case 3://增加学生信息，传来一个学生对象
					dos.writeBoolean(sb.Add(Student.toStudent(dis.readUTF())));
					System.out.println("数据库端增加学生成功！");
					dos.flush();
					break;
				case 4://删除学生信息，传给我一个学生id
					dos.writeBoolean(sb.Delete(dis.readUTF()));
					System.out.println("数据库删除学生成功！");
					dos.flush();
					break;
				case 5://修改学生信息，传来一个学生对象
					dos.writeBoolean(sb.Change(Student.toStudent(dis.readUTF())));
					System.out.println("数据库端修改学生成功！");
					dos.flush();
					break;
				case 6://查找学生信息，传给我学生id
					Student s=sb.Find(dis.readUTF());
					if(s==null) {
						dos.writeUTF("null");
						System.out.println("null");
					}
					else {
						dos.writeUTF(s.toString());
					System.out.println("数据库端查找学生成功！");
					}
					break;					
				case 7://增加课程
					dos.writeBoolean(cb.Add(Course.toCourse(dis.readUTF())));
					dos.flush();
					break;
				case 8://删除课程，要求该课程的选课人数为0
					dos.writeInt(cb.Delete(dis.readUTF()));
					dos.flush();
					break;
				case 9://修改课程信息
					dos.writeBoolean(cb.Change(dis.readUTF()));
					dos.flush();
					break;
				case 10://查找所有课程信息
					Map map=cb.FindAll();
					dos.writeInt(map.size());//先写回个数
					Iterator it=map.entrySet().iterator();
					while(it.hasNext()) {
						Map.Entry<String,Course> entry=(Map.Entry<String, Course>)it.next();
						dos.writeUTF(entry.getValue().toString());
					}
					dos.flush();
					break;
				case 11://增加课程容量
					dos.writeBoolean(cb.AddCapcity(dis.readUTF(), dis.readInt()));
					dos.flush();
					break;
				case 12://查找学生已选课程信息,该学生一定存在
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
			System.out.println("没有连接到客户端");
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, Exception {
		Properties p=new Properties();
		p.load(new FileInputStream("file.properties"));
		String DBport=p.getProperty("DBport");
		System.out.println(DBport);
		ServerSocket ss=new ServerSocket(Integer.parseInt(DBport));
		System.out.println("数据库服务程序已经准备好了！");
		DBProtocol dbp=new DBProtocol("D:\\test3.txt","D:\\Test4.txt");
		while(true) {
			Socket s=ss.accept();
			dbp.Service(s);
		}
	}
	
}
