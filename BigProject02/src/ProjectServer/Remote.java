package ProjectServer;

import java.io.*;
import java.net.Socket;


public class Remote { //本地（客户端）代理，与服务器通信方式
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
		//dis = new DataInputStream(s.getInputStream());
		//dos = new DataOutputStream(s.getOutputStream());
		br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
	public String chooseCourse(String studentId, String CourseId) //返回选课结果提示信息
	{
		try {
			pw.print(1);//1表示选课
			pw.println(studentId+","+CourseId); //以逗号作为分隔符向服务器传数据
			pw.flush();
			**return br.readLine();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public String dropCourse(String studentId, String CourseId) //按提示进行退课操作
	{
		try {
			pw.print(2);//2表示退课
			pw.println(studentId+","+CourseId); //以逗号作为分隔符向服务器传数据
			pw.flush();
			**return br.readLine();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public boolean addStudent(String student)//id,name,classroom,gendar的复合串，中间逗号分隔
	{
		try {
			pw.print(3);//3表示增加学生信息
			pw.println(student); //以逗号作为分隔符向服务器传数据
			pw.flush();
			return br.read()=='1';//**读的是字符1
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public String deleteStudent(String id)//根据学号删除，返回 （1成功）/（0失败+错误信息）
	{
		try {
			pw.print(4);//4表示删除学生信息
			pw.println(id);
			pw.flush();
			return br.readLine();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public boolean changeStudent(String student)//参数同addStudent，修改对应学号id的学生的信息
	{
		try {
			pw.print(5);//5表示修改学生信息
			pw.println(student); //传递要修改的学生信息字符串
			pw.flush();
			return br.read() == '1';
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}

	}
	//查找相应学号的学生信息，返回是否存在（1成功+学生信息）/（0失败）的字符串，以逗号作为学生信息分隔符，若不存在学生则返回null
	public String findStudent(String id)
	{
		try {
			pw.print(6);//6表示查找学生信息
			pw.println(id);
			pw.flush();
			return br.readLine();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public String addCourse(String course)
	//String course_id,course_name,int num,left_num,stu_num;
	{
		try {
			pw.print(7);//7表示增加课程信息
			pw.println(course); //传递要增加的课程信息字符串
			pw.flush();
			**return br.readLine();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public String deleteCourse(String id)//id
	{
		try {
			pw.print(8);//8表示删除课程信息
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
			pw.print(9);//9表示修改课程信息
			pw.println(course); //传递要修改的课程信息字符串
			pw.flush();
			**return br.readLine();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public String findCourse()
	{
		try {
			pw.print(10);//10表示查找课程信息

			pw.flush();
			**return br.readLine();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
}
