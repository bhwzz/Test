package buffer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import EntityClass.Student;

public class DBProtocol {
	StudentBuffer sb=new StudentBuffer();//����ѧ������
	
	public void Service(Socket serverSocket) throws ClassNotFoundException, Exception {
		try {
			DataInputStream dis=new DataInputStream(serverSocket.getInputStream());
			ObjectInputStream ois=new ObjectInputStream(serverSocket.getInputStream());
			DataOutputStream dos=new DataOutputStream(serverSocket.getOutputStream());
			ObjectOutputStream oos=new ObjectOutputStream(serverSocket.getOutputStream());
			PrintWriter pw=new PrintWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
			int i=dis.readInt();
			while(true) {
				switch(i) {
				case 1://ѡ��
					break;
				case 2://��ѡ	
					break;
					
				case 3://����ѧ����Ϣ������һ��ѧ������
					dos.writeBoolean(sb.Add((Student)ois.readObject()));
					dos.flush();
					break;
				case 4://ɾ��ѧ����Ϣ��������һ��ѧ��id
					dos.writeBoolean(sb.Delete(dis.readUTF()));
					dos.flush();
					break;
				case 5://�޸�ѧ����Ϣ������һ��ѧ������
					dos.writeBoolean(sb.Change((Student)ois.readObject()));
					dos.flush();
					break;
				case 6://����ѧ����Ϣ��������ѧ��id
					oos.writeObject(sb.Find(dis.readUTF()));
					oos.flush();
					break;
					
				case 7://���ӿγ�
					break;
				case 8://ɾ���γ̣�Ҫ��ÿγ̵�ѡ������Ϊ0
					break;
				case 9://�޸Ŀγ���Ϣ
					break;
				case 10://���ҿγ���Ϣ
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("û�����ӵ��ͻ���");
		}
	}
	
	public static void main(String[] args) throws ClassNotFoundException, Exception {
		Properties p=new Properties();
		p.load(new FileInputStream("file.properties"));
		String Serverport=p.getProperty("Serverport");
		ServerSocket ss=new ServerSocket(Integer.parseInt(Serverport));
		DBProtocol dbp=new DBProtocol();
		while(true) {
			Socket s=ss.accept();
			dbp.Service(s);
		}
	}
	
}
