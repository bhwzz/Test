import java.io.*;

import java.util.*;


import EntityClass.Stu_Course;
import EntityClass.Student;
import quality.StudentQuality;

public class Stu_CourseBuffer {

	private StudentBuffer stubuffer;
	private CourseBuffer coubuffer;
	public Map<String, Map> stu_coubuffer;
	public File rootfile;
	int LINELENGTH = 0;
	int STUIDSIZE = 7;
	int COUIDSIZE = 3;
	int TIMESIZE = 0;
	
	
	public Stu_CourseBuffer(StudentBuffer stu,String s) {
		this.stubuffer = stu;
		rootfile = new File(s);
		// TODO Auto-generated constructor stub
	}
	public Set find(String Stuid) {
		if(stubuffer.Find(Stuid)==null)
		{
			System.out.println("��ѧ��������");
			return null;
		}
		return stu_coubuffer.get(Stuid).keySet();
	}
	public boolean find(String Stuid,String Couid) {
		if(stubuffer.Find(Stuid)==null)
		{
			System.out.println("��ѧ��������");
			return false;
		}
		else if(coubuffer.Find(Couid)==null) {
			System.out.println("�ÿγ̲�����");
			return false;
		}
		else if(stu_coubuffer.get(Stuid)==null)
		{
			System.out.println("��ѧ��δѡ����");
			return false;
		}
		else {
			Map<String,String> map=stu_coubuffer.get(Stuid);
			if(map.get(Couid)==null) {
				System.out.println("��ѧ��δѡ����ڿ�");
				return false;
			}
			return true;
		}
		
			
			
//			RandomAccessFile raf=new RandomAccessFile(rootfile, "r");  
//	        //��ȡRandomAccessFile�����ļ�ָ���λ�ã���ʼλ����0  
//			System.out.println("RandomAccessFile�ļ�ָ��ĳ�ʼλ��:"+raf.getFilePointer()); 
//			for(int i = 0 ; i < list.size() ; i++) {
//					  //system.out.println(list.get(i));
//				String placeString = list.get(i);
//				System.out.println(placeString);
//				raf.seek(Integer.parseInt(placeString)*LINELENGTH);//�ƶ��ļ�ָ��λ��
//				byte[] b = new byte[LINELENGTH];
//				raf.read(b);
//				String mString=new String(b);
//				System.out.println(mString+"");
//				mString = mString.replaceAll("[\\t\\n\\r]", "");
//				mString = mString.replaceAll(" ", "");
//				String lastString = mString.substring(mString.length()-1, mString.length());//ȡ���һλ
//				String courseIDString = mString.substring(8, 11);
//				if(lastString.equals("1"))
//				{
//					continue;
//				}
//				else if(courseIDString.equals(Couid)) {
//					return true;
//				}
//					
//			}
			return false;
		}
	}
				
	public boolean add(Stu_Course s)			
,	{
		String Stuid = s.getstuID();
		String Couid = s.getcouID();
		if(stubuffer.Find(Stuid)==null)
		{
			System.out.println("��ѧ��������");
			return false;
		}
		else if(stu_coubuffer.get(Stuid)==null)
		{
			System.out.println("��ѧ��δѡ����");
			//���ļ���β���һ�� һ����ݸ�ʽд
			return true;
		}
		else {
			List<String> list=stu_coubuffer.get(Stuid);
			
			RandomAccessFile raf=new RandomAccessFile(rootfile, "r");  
	        //��ȡRandomAccessFile�����ļ�ָ���λ�ã���ʼλ����0  
			System.out.println("RandomAccessFile�ļ�ָ��ĳ�ʼλ��:"+raf.getFilePointer()); 
			for(int i = 0 ; i < list.size() ; i++) {
					  //system.out.println(list.get(i));
				String placeString = list.get(i);
				System.out.println(placeString);
				raf.seek(Integer.parseInt(placeString)*LINELENGTH);//�ƶ��ļ�ָ��λ��
				byte[] b = new byte[LINELENGTH];
				raf.read(b);
				String mString=new String(b);
				System.out.println(mString+"");
				mString = mString.replaceAll("[\\t\\n\\r]", "");
				mString = mString.replaceAll(" ", "");
				String lastString = mString.substring(mString.length()-1, mString.length());//ȡ���һλ
				String courseIDString = mString.substring(8, 11);
				if(courseIDString.equals(Couid)&&lastString.equals("0")) {
					System.out.println("���ѧ���Ѿ�ѡ����ڿ���");
					return false;
				}
					
			}
			//���ļ���β���һ�� һ����ݸ�ʽд
			return true;
		}
	}
				//char[] buff = new char[20]; 
//				String mString = new String(raf.readline().getBytes("ISO-8859-1"), "utf-8");
			
			//	raf.read(b);
			//	char c[] = new char[20];
			//	for(int i=0;i<20;i++) {
			//		c[i]=raf.readChar();
			//	}
				
				
				//System.out.println(mString.toCharArray());
				//int i=mString()
				
			
			
		
	
	
	
	public void getbuffer() {
		stu_coubuffer = new TreeMap<String, List>();
		//FileReader r1=new FileReader(f1.getAbsolutePath());
		//bufferMap = new TreeMap<String, String>();
		//	FileReader r1= new FileReader(s);
			FileInputStream in = new FileInputStream(rootfile.getAbsolutePath());
			BufferedInputStream br=new BufferedInputStream(in);
			//BufferedReader br = new BufferedReader(new FileReader(s));  
	        
	       // char[] buff = new char[20];  
			byte[] buff=new byte[LINELENGTH];
	        int len = -1;  
	        int i=0;
	        while( (len = br.read(buff)) != -1 ){  
	            System.out.print(new String(buff, 0, len));  
	            System.out.println(new String(buff, 0, len).getBytes().length);
	            //System.out.println(new String(buff, 0, len).get);
	            System.out.println(new String(buff, 0, 7));
	            
	            if(new String(buff, 21, 1).equals("0"))
	            {
	            	// bufferMap.put(new String(buff, 0, 7), i+"");
	            	String stuidString = new String(buff, 0, 7);
	            	if(stu_coubuffer.get(stuidString)!=null)
	            	{
	            		stu_coubuffer.get(stuidString).add(i+"");
	            	}
	            	if(stu_coubuffer.get(stuidString)==null)
	            	{
	            		List<String> list=new ArrayList<String>();
	            		list.add(i+"");
	            		stu_coubuffer.put(stuidString,list);
	            	}
	            }
	          
	            else {
	            	System.out.println("�ѱ�ɾ��");
				}
	         //   bufferMap.put(new String(buff, 0, 7), i+"");
	            i++;
	        } 
	        in.close();
	        br.close();
	        System.out.println(bufferMap);
	}
	
}
