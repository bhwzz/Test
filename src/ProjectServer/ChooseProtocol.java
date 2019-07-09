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
	public void ChooseService(Socket socket) {
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//PrintWriter bw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			int i=br.read();
			String sid=null;
			String cid=null; 
			while(true) {
				switch(i) {
				case '1'://选课
					sid=br.readLine();
					cid=br.readLine();
					int bool=cci.chooseCourse(sid,cid);
//					bw.println(sid);
//					bw.println(cid);
//					bw.println(bool);    //返回选课结果,1成功，0失败
					bw.write(bool);
					bw.flush();
					break;
				default:
					System.out.println("无法处理客户端请求！");
//					bw.println(sid);
//					bw.println(cid);
//					bw.println(-1);     //返回-1，说明请求错误！
					bw.write(-1);
					bw.flush();
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("没有连接到客户端");
		}
	}
}
