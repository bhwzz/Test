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
		while(id.length()!=7){
			System.out.println("长度有误，请输入7位数字学号：");
			id = sc.next();
		}
		System.out.print("姓名：");
		String name = sc.next(); //读入姓名
		System.out.print("班级：");
		int classroom = sc.nextInt(); //读入班级
		System.out.print("性别：");
		char gender = sc.next().charAt(0); //读入性别
		String student = new String(id+","+name+","+classroom+","+gender);//向服务器传递的待增加的学生信息
		//若添加失败，则说明已存在该学号，提示错误
		if(r.addStudent(student)){
			System.out.println("添加成功");	
		}
		else {
			System.out.println("学号重复，添加失败");
		}
		
		ClientUI.delay(2000);
		return;
	}
	public void deleteStudent()
	{
		System.out.print("请输入要删除学生的学号：");
		Scanner sc=new Scanner(System.in);
		String id = sc.next(); //读入学号
		while(id.length()!=7){
			System.out.println("长度有误，请输入7位数字学号：");
			id = sc.next();
		}
		String s = r.findStudent(id);
		switch(s.charAt(0)){
		case '0':
			String info = s.substring(1); //除去第一位的子串，表示错误信息
			System.out.println("删除失败，失败原因："+info);
			break;
		case '1':
			System.out.println("删除成功");
			break;
		}
		
		ClientUI.delay(2000);
		return;
	}
	public void changeStudent()//全串
	{
		System.out.println("请输入要修改的学生信息：");
		System.out.print("学号：");
		Scanner sc=new Scanner(System.in);
		String id = sc.next(); //读入学号
		while(id.length()!=7){
			System.out.println("长度有误，请输入7位数字学号：");
			id = sc.next();
		}
		System.out.print("姓名：");
		String name = sc.next(); //读入姓名
		System.out.print("班级：");
		int classroom = sc.nextInt(); //读入班级
		System.out.print("性别：");
		char gender = sc.next().charAt(0); //读入性别
		String student = new String(id+","+name+","+classroom+","+gender);//向服务器传递的待修改的学生信息
		if(r.changeStudent(student)) {
			System.out.println("删除成功");
		}
		else {
			System.out.println("不存在该学号，删除失败");
		}
		
		ClientUI.delay(2000);
		return;
	}
	public void findStudent() //返回
	{
		
		System.out.print("请输入要查询学生的学号：");
		Scanner sc=new Scanner(System.in);
		String id = sc.next(); //读入学号
		while(id.length()!=7){
			System.out.println("长度有误，请输入7位数字学号：");
			id = sc.next();
		}
		String s = r.findStudent(id);
		switch(s.charAt(0)){
		case '0':
			System.out.println("该学生不存在");
			break;
		case '1':
			System.out.println("学生信息如下：");
			String info[] = s.substring(1).split(",");
			System.out.println("学号："+info[0]+"\n姓名:"+info[1]+"\n班级:"+info[2]+"\n性别"+info[3]);
			//int courseNum = Integer.parseInt(info[4]);
			break;
		}
		
		ClientUI.delay(2000);
		return;
	
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
