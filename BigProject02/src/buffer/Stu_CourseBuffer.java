import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.channels.NonReadableChannelException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import EntityClass.Stu_Course;
import EntityClass.Student;
import quality.StudentQuality;

public class Stu_CourseBuffer {

	private StudentBuffer stubuffer;
	public Map<String, List> stu_coubuffer;
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
	public List find(String Stuid) {
		if(stubuffer.Find(Stuid)==null)
		{
			System.out.println("该学生不存在");
			return null;
		}
		return stu_coubuffer.get(Stuid);
	}
	public boolean find(String Stuid,String Couid) {
		if(stubuffer.Find(Stuid)==null)
		{
			System.out.println("该学生不存在");
			return false;
		}
		else if(stu_coubuffer.get(Stuid)==null)
		{
			System.out.println("该学生未选过课");
			return false;
		}
		else {
			List<String> list=stu_coubuffer.get(Stuid);
			
			RandomAccessFile raf=new RandomAccessFile(rootfile, "r");  
	        //获取RandomAccessFile对象文件指针的位置，初始位置是0  
			System.out.println("RandomAccessFile文件指针的初始位置:"+raf.getFilePointer()); 
			for(int i = 0 ; i < list.size() ; i++) {
					  //system.out.println(list.get(i));
				String placeString = list.get(i);
				System.out.println(placeString);
				raf.seek(Integer.parseInt(placeString)*LINELENGTH);//移动文件指针位置
				byte[] b = new byte[LINELENGTH];
				raf.read(b);
				String mString=new String(b);
				System.out.println(mString+"");
				mString = mString.replaceAll("[\\t\\n\\r]", "");
				mString = mString.replaceAll(" ", "");
				String lastString = mString.substring(mString.length()-1, mString.length());//取最后一位
				String courseIDString = mString.substring(8, 11);
				if(lastString.equals("1"))
				{
					continue;
				}
				else if(courseIDString.equals(Couid)) {
					return true;
				}
					
			}
			return false;
		}
	}
				
	public boolean add(Stu_Course s)			
,	{
		String Stuid = s.getstuID();
		String Couid = s.getcouID();
		if(stubuffer.Find(Stuid)==null)
		{
			System.out.println("该学生不存在");
			return false;
		}
		else if(stu_coubuffer.get(Stuid)==null)
		{
			System.out.println("该学生未选过课");
			//在文件结尾添加一行 一会根据格式写
			return true;
		}
		else {
			List<String> list=stu_coubuffer.get(Stuid);
			
			RandomAccessFile raf=new RandomAccessFile(rootfile, "r");  
	        //获取RandomAccessFile对象文件指针的位置，初始位置是0  
			System.out.println("RandomAccessFile文件指针的初始位置:"+raf.getFilePointer()); 
			for(int i = 0 ; i < list.size() ; i++) {
					  //system.out.println(list.get(i));
				String placeString = list.get(i);
				System.out.println(placeString);
				raf.seek(Integer.parseInt(placeString)*LINELENGTH);//移动文件指针位置
				byte[] b = new byte[LINELENGTH];
				raf.read(b);
				String mString=new String(b);
				System.out.println(mString+"");
				mString = mString.replaceAll("[\\t\\n\\r]", "");
				mString = mString.replaceAll(" ", "");
				String lastString = mString.substring(mString.length()-1, mString.length());//取最后一位
				String courseIDString = mString.substring(8, 11);
				if(courseIDString.equals(Couid)&&lastString.equals("0")) {
					System.out.println("这个学生已经选过这节课了");
					return false;
				}
					
			}
			//在文件结尾添加一行 一会根据格式写
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
	            	System.out.println("已被删除");
				}
	         //   bufferMap.put(new String(buff, 0, 7), i+"");
	            i++;
	        } 
	        in.close();
	        br.close();
	        System.out.println(bufferMap);
	}
	
}
