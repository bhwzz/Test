package ProjectServer;

import java.util.Scanner;

public class Client { //包含一系列响应用户操作和需求的函数，如选课退课查询等

	Remote r;
	Client(String host, int port) throws Exception{
		r = new Remote(host, port);
	}
	public void chooseCourse() //按提示进行选课操作
	{
		System.out.println("--------------选课操作--------------");
		//根据id列出学生信息和所有课程信息
		String stuId = findStudent().charAt(0);
		if(stuId == null) { //该学生不存在
			return;
		}
		findAllCourse();
		System.out.print("请输入你要选择的课程课序号：");
		Scanner sc=new Scanner(System.in);
		String courId = sc.next(); //读入课序号
		System.out.println( r.chooseCourse(stuId, courId) ); //输出选课结果
	}
	public void dropCourse() //按提示进行退课操作
	{
		System.out.println("--------------退课操作--------------");
		//根据id列出学生信息
		String stuId = findStudent();
		System.out.print("请输入你要退课的课程课序号：");
		Scanner sc=new Scanner(System.in);
		String courId = sc.nextLine(); //读入课序号
		System.out.println( r.chooseCourse(stuId, courId) ); //输出退课结果
	}
	public void addStudent()
	{
		System.out.println("请输入要添加学生的信息：");
		System.out.print("学号：");
		Scanner sc=new Scanner(System.in);
		String id = sc.next(); //读入学号
		//if已有该学号的学生，则提示错误，return
		if(r.findStudent(id) != null){
			System.out.println("学号重复，添加失败");
			return;
		}
		
		System.out.print("姓名：");
		String name = sc.next(); //读入姓名
		System.out.print("班级：");
		int classroom = sc.nextInt(); //读入班级
		System.out.print("性别：");
		char gender = sc.next().charAt(0); //读入性别
		r.addStudent(id+","+name+","+classroom+","+gender);
		
	}
	public void deleteStudent()
	{
		System.out.print("请输入要删除学生的学号：");
		Scanner sc=new Scanner(System.in);
		String id = sc.next(); //读入学号
		//if没有该学号的学生，则提示错误，return
		if(r.findStudent(id) == null){
			System.out.println("该学生不存在");
			return;
		}
		else {
			r.deleteStudent(id);
		}
	}
	public void changeStudent()//全串
	{
		System.out.println("请输入要修改的学生信息：");
		System.out.print("学号：");
		Scanner sc=new Scanner(System.in);
		String id = sc.next(); //读入学号
		//if没有该学号的学生，则提示错误，return
		if(r.findStudent(id) == null){
			System.out.println("不存在该学生");
			return;
		}
		
		System.out.print("姓名：");
		String name = sc.next(); //读入姓名
		System.out.print("班级：");
		int classroom = sc.nextInt(); //读入班级
		System.out.print("性别：");
		char gender = sc.next().charAt(0); //读入性别
		r.deleteStudent(id+","+name+","+classroom+","+gender);
	}
	public void findStudent() //返回
	{
		
		System.out.print("请输入要查询学生的学号：");
		Scanner sc=new Scanner(System.in);
		String id = sc.next(); //读入学号
		String s = r.findStudent(id);
		switch(r.charAt(0)){
		case '0':
			System.out.println("该学生不存在");
			break;
		case '1':
			System.out.println("学生信息如下：");
			String info[] = s.substring(1).split(",");
			System.out.println("学号："+info[0]+"\n姓名:"+info[1]+"\n班级:"+info[2]+"\n性别"+info[3]);
			int courseNum = info[4];
			break;
		}
		else if( s.charAt(0) == 0)
		System.out.println(r.findStudent(id)); //输出查找结果
		return id;
	}
	public void addCourse(String id, String name, int classroom, char gender)//全串
	{

	}
	public void deleteCourse()//id
	{
		
	}
	public void changeCourse()//全串
	{
		
	}
	public void findAllCourse()//所有课程信息
	{
		
	}
}
