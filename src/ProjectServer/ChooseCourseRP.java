package ProjectServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChooseCourseRP implements ReqResultProcess{//����ѡ������Ĵ�����

	public void ResultProcess(InputStreamReader isr){
		BufferedReader br=new BufferedReader(isr);		
		try {
			String sid=br.readLine();
			String cid=br.readLine();
			String bool=br.readLine();//��¼��ѡ���Ƿ�ɹ�
			switch(bool) {
			case "1"://�ɹ�
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
