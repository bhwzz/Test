package ProjectServer;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Properties;

public class ServerTest {
	public static void main(String[] args) throws IOException {
		//��ȡ�����ļ�
		Properties p=new Properties();
		p.load(new FileInputStream("file.properties"));
//		String DBserver=p.getProperty("DBserver");
//		int DBport=Integer.parseInt(p.getProperty("DBport"));
		//�����ݿ�ϵͳ��������
//		Socket DBsocket=new Socket("LocalHost",DBport);
		
		//�ȴ���ͻ��˵�����
		IOStrategy ios=new ThreadPoolSupport(new ServerProtocol());
		int Clientport=Integer.parseInt(p.getProperty("Clientport"));
		NwServer ns=new NwServer(Clientport,ios);
	}
}
