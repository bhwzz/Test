package ProjectServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import EntityClass.Student;

public class ServerProtocol implements IOStrategy{
	ChooseCourseImple cci=null;
	StuInformationImple sii=null;
	CouInformationImple cii=null;
	
	ServerProtocol(Socket DBsocket){
		cci=new ChooseCourseImple(DBsocket);
		sii=new StuInformationImple(DBsocket);
		cii=new CouInformationImple(DBsocket);
	}
	public ServerProtocol() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void Service(Socket Clientsocket) {
		String sid=null;
		String cid=null; 
		String s=null;
		int bool;
		System.out.println("服务器开始service！");
		try {
			DataInputStream dis=new DataInputStream(Clientsocket.getInputStream());
			DataOutputStream dos=new DataOutputStream(Clientsocket.getOutputStream());
//			ObjectInputStream ois=new ObjectInputStream(Clientsocket.getInputStream());
//			ObjectOutputStream oos=new ObjectOutputStream(Clientsocket.getOutputStream());
//			PrintWriter pw=new PrintWriter(new OutputStreamWriter(Clientsocket.getOutputStream()));			
//			BufferedReader br=new BufferedReader(new InputStreamReader(Clientsocket.getInputStream()));
//			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(Clientsocket.getOutputStream()));
			while(true) {
				int i=dis.readInt();
				System.out.println("客户端请求："+i);
				switch(i) {
//				case 1://选课:返回1选课成功；返回0选课失败，接着返回一个字符串（
//					sid=br.readLine();
//					cid=br.readLine();
//					bool=cci.chooseCourse(sid,cid);
//					bw.write(bool);
//					bw.flush();
//					break;
//				case 2://退选
//					sid=br.readLine();
//					cid=br.readLine();
//					bool=cci.dropCourse(sid,cid);
//					bw.write(bool);
//					bw.flush();
//					break;
				case 3://增加学生信息，传给我一个整的字符串
					s=dis.readUTF();//传给我一个整的字符串
					Student stu=Student.toStudent(s);
					bool=sii.Add(stu)?1:0;
//					System.out.println("增加结果"+bool);
					dos.writeInt(bool);//返回1，增加成功；返回0该学生已存在
					dos.flush();
					break;
				case 4://删除学生信息，传给我一个学生id
					sid=dis.readUTF();
					bool=sii.Delete(sid)?1:0;//删除学生的函数要判断该学生有没有选课
					dos.writeInt(bool);//返回1：删除成功；返回0：删除学生失败？？？？？？？（待修改）
					System.out.println("删除结果"+bool);
					if(bool==0) {//删除失败返回一个失败原因字符串
						dos.writeUTF("not found");
					}
					dos.flush();
					break;
				case 5://修改学生信息，传给我一整个学生信息字符串
					s=dis.readUTF();
					Student stu2=Student.toStudent(s);
					bool=sii.Change(stu2)?1:0;
					System.out.println("修改结果"+bool);
					dos.writeInt(bool);;//返回1：修改成功；返回0：修改失败
					dos.flush();
					break;
				case 6://查找学生信息，传给我学生id
					sid=dis.readUTF();
					//返回学生个人信息以及选课信息
					System.out.println("客户端要查找的学生id："+sid);
					Student stu3=(Student)sii.Find(sid);
					if(stu3==null)
						dos.writeInt(0);
					else {
						dos.writeInt(1);
						System.out.println("数据库查找该学生成功！");
						dos.writeUTF(stu3.toString());
						System.out.println(stu3.toString());
					}
					dos.flush();
					break;
				case 7://增加课程:传给我一个字符串（id，name，容量）
					s=dis.readUTF();
					
					break;
				case 8://删除课程，要求该课程的选课人数为0（返回结果同student）
					
					break;
				case 9://修改课程信息(只允许修改课程名字）传课程id+name//返回1，0
					
					break;
				case 10://查找课程信息，返回所有课程信息，有几个课程传几次
					
					break;
				case 11://增加课程容量，传id+addnum//返回1，0
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("。。。。。。没有连接到客户端");
		}
	}

}
