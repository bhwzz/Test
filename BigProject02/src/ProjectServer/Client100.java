package ProjectServer;

import java.io.IOException;

public class Client100 extends Thread{  //δ���
	private String studentId;
	private String courseId;
	private Remote remote;
	Client100(){
		
	}
	Client100(String host, int post, String sid, String cid) throws Exception{
		remote = new Remote(host, post); //ͨ���ͻ��˴�������������������
		//����ѡ����Ϣ
		studentId=sid;
		courseId=cid;
	}
	@Override
	public void run() {
			String info = remote.chooseCourse(studentId, courseId); //info��¼ѡ�ν����Ϣ
			switch(info.charAt(0)){
			case '0':
				String s = info.substring(1); //��ȥ��һλ���Ӵ�����ʾʧ��ԭ��
				System.out.println("ѡ��ʧ�ܣ�ʧ��ԭ��"+s);
				break;
			case '1':
				System.out.println("ѡ�γɹ�");
				break;
			}

	}
	public static void main(String[] args) throws Exception {
		Client100[] client = new Client100[10];
		for(int i=0; i<10; i++) {
			client[i] = new Client100("localhost", 4444, i+1234500+"", "001");
		}
		for(int i=0; i<10; i++) {
			client[i].start();
		}
	}
}
