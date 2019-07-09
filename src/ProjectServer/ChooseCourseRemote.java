package ProjectServer;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

//选课接口的本地实现类
public class ChooseCourseRemote implements ChooseCourse{
	BufferedReader br=null;
	PrintWriter pw=null;
	
	ChooseCourseRemote(String ip,int port) throws IOException{
		Socket socket=new Socket(ip,port);
		br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
	public int chooseCourse(String stuId,String couId) {
		try {
			pw.print(1);//1表示选课
			pw.println(stuId);
			pw.println(couId);
			pw.flush();
			
			return br.read();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	//退选函数
}
