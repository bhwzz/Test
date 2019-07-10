package ProjectServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ChooseProtocol implements IOStrategy{
	ChooseCourseImple cci=new ChooseCourseImple();
	
	public void Init() {
		cci.Init();
	}
	public void Service(Socket socket) {
		String sid=null;
		String cid=null; 
		int bool;
		try {//�û������кܶ�����
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//PrintWriter bw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			int i=br.read();
			while(true) {
				switch(i) {
				case '1'://ѡ��
					sid=br.readLine();
					cid=br.readLine();
					bool=cci.chooseCourse(sid,cid);
					bw.write(bool);
					bw.flush();
					break;
				case '2'://��ѡ
					sid=br.readLine();
					cid=br.readLine();
					bool=cci.dropCourse(sid,cid);
					bw.write(bool);
					bw.flush();
				default:
					System.out.println("�޷�����ͻ�������");
					//����-1��˵���������
					bw.write(-1);
					bw.flush();
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("û�����ӵ��ͻ���");
		}
	}
}
