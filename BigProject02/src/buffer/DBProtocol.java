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
import java.util.Properties;

import EntityClass.Student;

public class DBProtocol {
	StudentBuffer sb=null;//用于学生操作
	
	public DBProtocol(String file) throws Exception {
		super();
		this.sb=new StudentBuffer(file);
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
					break;
				case 2://退选	
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
					dos.writeUTF(sb.Find(dis.readUTF()).toString());
					System.out.println("数据库端查找学生成功！");
					break;
					
				case 7://增加课程
					break;
				case 8://删除课程，要求该课程的选课人数为0
					break;
				case 9://修改课程信息
					break;
				case 10://查找课程信息
					break;
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
		DBProtocol dbp=new DBProtocol("D:\\test2.txt");
		while(true) {
			Socket s=ss.accept();
			dbp.Service(s);
		}
	}
	
}
