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
		//读取配置文件
		Properties p=new Properties();
		p.load(new FileInputStream("file.properties"));
			//读数据库端口号
		int DBport=Integer.parseInt(p.getProperty("DBport"));
		System.out.println(DBport);
			//读取文件数据
		String StuFile=p.getProperty("StuFile");
		String CouFile=p.getProperty("CouFile");
		String StuCouFile=p.getProperty("StuCouFile");
			//创建NwServer对象，创建数据库socket循环等待服务器的请求
		IOStrategy ios=new DBThreadPoolSupport(new DBProtocol(StuFile,CouFile,StuCouFile));
		NwServer ns=new NwServer(DBport,ios);
	}

}
