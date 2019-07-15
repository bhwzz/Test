package ProjectServer;

import java.util.Scanner;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.*;

abstract public class ClientUI {
	static Client client;
	public static void main(String[] args) throws Exception {		
		ClientUI.client = new Client("localhost",4444);
		ClientUI.mainInterface();
	}
	//主界面
	public static void mainInterface(){
		
		while(true) {
			clear();
			System.out.println("----------------------------");
			System.out.println("    1.选课管理");
			System.out.println("    2.学生信息管理");
			System.out.println("    3.课程信息管理");
			System.out.println("    4.退出");
			System.out.print("请输入你要进行的操作编号：  ");
			Scanner sc=new Scanner(System.in);
			int i=sc.nextInt();
			switch(i) {
			case 1:
				choosecourseInformationInterface();
				break;
			case 2:
				studentInformationInterface();
				break;
			case 3:
				courseInformationInterface();
				break;
			case 4:
				System.out.println("谢谢您的使用！");
				System.exit(0);
			default:
				System.out.println("请输入正确编号：");
				delay(2000);
				break;
			}
		}		
	}
	//选课管理界面
	public static void choosecourseInformationInterface(){
		while(true) {
			clear();
			System.out.println("----------选课管理--------------");
			System.out.println("    1.选课");
			System.out.println("    2.退课");
			System.out.println("    3.返回");
			System.out.print("请输入你要进行的操作编号：  ");
			Scanner sc=new Scanner(System.in);
			int i=sc.nextInt();
			switch(i) {
			case 1:
				client.chooseCourse();
				break;
			case 2:
				client.dropCourse();
				break;
			case 3:
				return;
			default:
				System.out.println("请输入正确编号：");
				delay(2000);
				break;
			}
		}
	}
	public static void studentInformationInterface(){

		while(true) {
			clear();
			System.out.println("----------学生信息管理--------------");
			System.out.println("    1.增加学生信息");
			System.out.println("    2.删除学生信息");
			System.out.println("    3.修改学生信息");
			System.out.println("    4.查找学生信息");
			System.out.println("    5.返回");
			System.out.print("请输入你要进行的操作编号：  ");
			Scanner sc=new Scanner(System.in);
			int i=sc.nextInt();
			switch(i) {
			case 1:
				client.addStudent();
				break;
			case 2:
				client.deleteStudent();
				break;
			case 3:
				client.changeStudent();
				break;
			case 4:
				client.findStudent();
				break;
			case 5:
				return;
			default:
				System.out.println("请输入正确编号：");
				delay(2000);
				break;
			}
		}
	}
	public static void courseInformationInterface(){

		while(true) {
			clear();
			System.out.println("--------------课程信息管理--------------");
			System.out.println("    1.增加课程信息");
			System.out.println("    2.删除课程信息");
			System.out.println("    3.修改课程信息");
			System.out.println("    4.查询所有课程信息");
			System.out.println("    5.增加课程容量");
			System.out.println("    6.返回");
			System.out.print("请输入你要进行的操作编号：  ");
			Scanner sc=new Scanner(System.in);
			int i=sc.nextInt();
			switch(i) {
			case 1:
				client.addCourse();
				break;
			case 2:
				client.deleteCourse();
				break;
			case 3:
				client.changeCourse();
				break;
			case 4:
				client.findAllCourse();
				break;
			case 5:
				client.addCourseCapacity();
				break;
			case 6:
				return;
			default:
				System.out.println("请输入正确编号：");
				delay(2000);
				break;
			}
		}
	}
    public static void clear() {
    	Robot r;
		try {
			r = new Robot();
	        r.mousePress(InputEvent.BUTTON3_MASK);       // 按下鼠标右键
	        r.mouseRelease(InputEvent.BUTTON3_MASK);    // 释放鼠标右键
	        r.keyPress(KeyEvent.VK_CONTROL);             // 按下Ctrl键
	        r.keyPress(KeyEvent.VK_R);                    // 按下R键
	        r.keyRelease(KeyEvent.VK_R);                  // 释放R键
	        r.keyRelease(KeyEvent.VK_CONTROL);            // 释放Ctrl键
	        r.delay(500);    
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /*--------------------- 
    	作者：Demon530 
    	来源：CSDN 
    	原文：https://blog.csdn.net/qq_18144681/article/details/51222405 
    	版权声明：本文为博主原创文章，转载请附上博文链接！*/


    }
    public static void delay(int time) {
    	Robot r;
		try {
			r = new Robot();
	        r.delay(time);    
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
