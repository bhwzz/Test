package ProjectServer;

import java.util.Scanner;

public class Client {
	public static void main(String[] args) {		
		mainInterface();		
		}
			
	//������
	public static void mainInterface(){
		
		while(true) {
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
				chooseCourseInterface();
				break;
			case 2:
				studentInterface();
				break;
			case 3:
				courseInterface();
				break;
			case 4:
				System.out.println("лл����ʹ�ã�");
				System.exit(0);
			default:
				System.out.println("��������ȷ��ţ�");
				break;
				
			}
		}		
	}
	//ѡ�ι������
	public static void chooseCourseInterface(){
		System.out.println("----------ѡ�ι���--------------");
		System.out.println("    1.ѡ��");
		System.out.println("    2.�˿�");
		System.out.println("    3.����");
		System.out.print("��������Ҫ���еĲ�����ţ�  ");
		while(true) {
			Scanner sc=new Scanner(System.in);
			int i=sc.nextInt();
			switch(i) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				return;
			default:
				System.out.println("��������ȷ��ţ�");
				break;
			}
		}
	}
	public static void studentInterface(){
		System.out.println("----------ѧ����Ϣ����--------------");
		System.out.println("    1.����ѧ����Ϣ");
		System.out.println("    2.ɾ��ѧ����Ϣ");
		System.out.println("    3.�޸�ѧ����Ϣ");
		System.out.println("    4.ɾ��ѧ����Ϣ");
		System.out.println("    5.����");
		System.out.print("��������Ҫ���еĲ�����ţ�  ");
		while(true) {
			Scanner sc=new Scanner(System.in);
			int i=sc.nextInt();
			switch(i) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				return;
			default:
				System.out.println("��������ȷ��ţ�");
				break;
			}
		}
	}
	public static void courseInterface(){
		System.out.println("--------------�γ���Ϣ����--------------");
		System.out.println("    1.���ӿγ���Ϣ");
		System.out.println("    2.ɾ���γ���Ϣ");
		System.out.println("    3.�޸Ŀγ���Ϣ");
		System.out.println("    4.��ѯ�γ���Ϣ");
		System.out.println("    5.����");
		System.out.print("��������Ҫ���еĲ�����ţ�  ");
		while(true) {
			Scanner sc=new Scanner(System.in);
			int i=sc.nextInt();
			switch(i) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				return;
			default:
				System.out.println("��������ȷ��ţ�");
				break;
			}
		}
	}

}
