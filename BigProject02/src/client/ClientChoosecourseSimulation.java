package client;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ClientChoosecourseSimulation extends Thread{ 
	private String studentId;
	private String courseId;
	private ClientRemote remote;
	ClientChoosecourseSimulation(){
		
	}
	ClientChoosecourseSimulation(String host, int post, String sid, String cid) throws Exception{
		remote = new ClientRemote(host, post); //ͨ���ͻ��˴�������������������
		//����ѡ����Ϣ
		studentId=sid;
		courseId=cid;
	}
	@Override
	public void run() {
			String info = remote.chooseCourse(studentId, courseId); //info��¼ѡ�ν����Ϣ
			remote.exitConnection(); //ѡ�ν�����ر�����
			switch(info.charAt(0)){
			case '0':
				String s = info.substring(1); //��ȥ��һλ���Ӵ�����ʾʧ��ԭ��
				System.out.println("ѧ��"+studentId+"ѡ��"+courseId+"ʧ�ܣ�ʧ��ԭ��"+s);
				break;
			case '1':
				System.out.println("ѧ��"+studentId+"ѡ��"+courseId+"�ɹ�");
				break;
			}
	}
	public static void main(String[] args) throws Exception {
		//���ļ���Ϣ�г�ʼ��������200��ѡ������ѧ�Ŵ�9999000-9999199��һ��ѡ001�γ̣�һ��ѡ002�γ�
		FileInputStream fis = new FileInputStream("ChooseCourse200.txt");
		DataInputStream dis = new DataInputStream(fis);
		ClientChoosecourseSimulation[] client = new ClientChoosecourseSimulation[200];
		String studentId, CourseId;
		for(int i=0; i<200; i++) {
			studentId = dis.readUTF();
			CourseId = dis.readUTF();
			client[i] = new ClientChoosecourseSimulation("localhost", 4444, studentId, CourseId);
		}
		dis.close();
		fis.close();
		//����200��ѡ���߳�
		for(int i=0; i<200; i++) {
			client[i].start();
		}	
		//�����߳̽���������������͸���ѡ�μ�¼�ļ���ָ��(-1)
		while(Thread.activeCount() != 1);
			//System.out.println("�����߳�������");//����debug��
		new Client("localhost",4444).exitChoosecourseManage();
	}
}
