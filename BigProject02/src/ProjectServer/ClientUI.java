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
	//������
	public static void mainInterface(){
		
		while(true) {
			clear();
			System.out.println("----------------------------");
			System.out.println("    1.ѡ�ι���");
			System.out.println("    2.ѧ����Ϣ����");
			System.out.println("    3.�γ���Ϣ����");
			System.out.println("    4.�˳�");
			System.out.print("��������Ҫ���еĲ�����ţ�  ");
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
				System.out.println("лл����ʹ�ã�");
				System.exit(0);
			default:
				System.out.println("��������ȷ��ţ�");
				delay(2000);
				break;
			}
		}		
	}
	//ѡ�ι������
	public static void choosecourseInformationInterface(){
		while(true) {
			clear();
			System.out.println("----------ѡ�ι���--------------");
			System.out.println("    1.ѡ��");
			System.out.println("    2.�˿�");
			System.out.println("    3.����");
			System.out.print("��������Ҫ���еĲ�����ţ�  ");
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
				System.out.println("��������ȷ��ţ�");
				delay(2000);
				break;
			}
		}
	}
	public static void studentInformationInterface(){

		while(true) {
			clear();
			System.out.println("----------ѧ����Ϣ����--------------");
			System.out.println("    1.����ѧ����Ϣ");
			System.out.println("    2.ɾ��ѧ����Ϣ");
			System.out.println("    3.�޸�ѧ����Ϣ");
			System.out.println("    4.����ѧ����Ϣ");
			System.out.println("    5.����");
			System.out.print("��������Ҫ���еĲ�����ţ�  ");
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
				System.out.println("��������ȷ��ţ�");
				delay(2000);
				break;
			}
		}
	}
	public static void courseInformationInterface(){

		while(true) {
			clear();
			System.out.println("--------------�γ���Ϣ����--------------");
			System.out.println("    1.���ӿγ���Ϣ");
			System.out.println("    2.ɾ���γ���Ϣ");
			System.out.println("    3.�޸Ŀγ���Ϣ");
			System.out.println("    4.��ѯ���пγ���Ϣ");
			System.out.println("    5.���ӿγ�����");
			System.out.println("    6.����");
			System.out.print("��������Ҫ���еĲ�����ţ�  ");
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
				System.out.println("��������ȷ��ţ�");
				delay(2000);
				break;
			}
		}
	}
    public static void clear() {
    	Robot r;
		try {
			r = new Robot();
	        r.mousePress(InputEvent.BUTTON3_MASK);       // ��������Ҽ�
	        r.mouseRelease(InputEvent.BUTTON3_MASK);    // �ͷ�����Ҽ�
	        r.keyPress(KeyEvent.VK_CONTROL);             // ����Ctrl��
	        r.keyPress(KeyEvent.VK_R);                    // ����R��
	        r.keyRelease(KeyEvent.VK_R);                  // �ͷ�R��
	        r.keyRelease(KeyEvent.VK_CONTROL);            // �ͷ�Ctrl��
	        r.delay(500);    
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        /*--------------------- 
    	���ߣ�Demon530 
    	��Դ��CSDN 
    	ԭ�ģ�https://blog.csdn.net/qq_18144681/article/details/51222405 
    	��Ȩ����������Ϊ����ԭ�����£�ת���븽�ϲ������ӣ�*/


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
