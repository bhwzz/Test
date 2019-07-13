package ProjectServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
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
		try {//�û������кܶ�����
			BufferedReader br=new BufferedReader(new InputStreamReader(Clientsocket.getInputStream()));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(Clientsocket.getOutputStream()));
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
				case '3'://����ѧ����Ϣ��������һ�������ַ���
					s=br.readLine();//������һ�������ַ���
					Student stu=Student.toStudent(s);
					bool=sii.Add(stu)?1:0;
					bw.write(bool);//����1�����ӳɹ�������0��ѧ���Ѵ���
					bw.flush();
				case '4'://ɾ��ѧ����Ϣ��������һ��ѧ��id
					sid=br.readLine();
					bool=sii.Delete(sid)?1:0;//ɾ��ѧ���ĺ���Ҫ�жϸ�ѧ����û��ѡ��
					bw.write(bool);//����1��ɾ���ɹ�������0����ѧ���Ѿ�ѡ�β���ɾ��
					bw.flush();
				case '5'://�޸�ѧ����Ϣ��������һ����ѧ����Ϣ�ַ���
					s=br.readLine();
					Student stu2=Student.toStudent(s);
					bool=sii.Change(stu2)?1:0;
					bw.write(bool);//����1���޸ĳɹ�������0���޸�ʧ��
					bw.flush();
				case '6'://����ѧ����Ϣ��������ѧ��id
					sid=br.readLine();
					//����ѧ��������Ϣ�Լ�ѡ����Ϣ
					Student stu3=(Student)sii.Find(sid);
					if(stu3==null) {//Ҫ���ҵ�ѧ��������
						bw.write("not found!");//���������д��not found��
						bw.flush();
					}
					else {
						s=stu3.toString();//ѧ����Ϣ
						bw.write(s);//����ѧ��������Ϣ
						int num=sii.FindCourseNum(sid);//ѧ����ѡ�γ���Ŀ
						bw.write(num);
						if(num!=0) {//��ѡ�γ�����Ŀ��Ϊ0
							s=sii.FindCourse(sid);//�������е���ѡ�γ̣�һ���ַ���ƴ�ӵģ�
							bw.write(s);
						}
						bw.flush();
					}
				case '7'://���ӿγ�
				case '8'://ɾ���γ̣�Ҫ��ÿγ̵�ѡ������Ϊ0
				case '9'://�޸Ŀγ���Ϣ
				case 10://���ҿγ���Ϣ
					
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
