package buffer;

import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

import ProjectServer.DBThreadPoolSupport;
import ProjectServer.IOStrategy;
import ProjectServer.NwServer;
import ProjectServer.ThreadPoolSupport;

public class DBtest {

	public static void main(String[] args) throws ClassNotFoundException, Exception {
		//��ȡ�����ļ�
		Properties p=new Properties();
		p.load(new FileInputStream("file.properties"));
			//�����ݿ�˿ں�
		int DBport=Integer.parseInt(p.getProperty("DBport"));
		System.out.println(DBport);
			//��ȡ�ļ�����
		String StuFile=p.getProperty("StuFile");
		String CouFile=p.getProperty("CouFile");
		String StuCouFile=p.getProperty("StuCouFile");
			//����NwServer���󣬴������ݿ�socketѭ���ȴ�������������
		IOStrategy ios=new DBThreadPoolSupport(new DBProtocol(StuFile,CouFile,StuCouFile));
		NwServer ns=new NwServer(DBport,ios);
	}

}
