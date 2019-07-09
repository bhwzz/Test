package ProjectServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChooseCourseRP implements ReqResultProcess{//定义选课请求的处理类

	public void ResultProcess(InputStreamReader isr){
		BufferedReader br=new BufferedReader(isr);		
		try {
			String sid=br.readLine();
			String cid=br.readLine();
			String bool=br.readLine();//记录了选课是否成功
			switch(bool) {
			case "1"://成功
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
