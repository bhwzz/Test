package filetool;

import java.io.*;
import java.util.*;

//import javax.sql.rowset.serial.SerialArray;

import EntityClass.*;

public class StuCouTool {

	File rootfile;
	Map <String,Map> bookMap;
	int LINELENGTH=26;
	public StuCouTool(File f) throws Exception {
		this.rootfile = f;
		getbook();
		// TODO Auto-generated constructor stub
	}
	public void getbook() throws Exception {
		bookMap = new TreeMap<String, Map>();
		//	FileReader r1= new FileReader(s);
			FileInputStream in = new FileInputStream(rootfile);
			BufferedInputStream br=new BufferedInputStream(in);
			//BufferedReader br = new BufferedReader(new FileReader(s));  
	        
	       // char[] buff = new char[20];  
			byte[] buff=new byte[LINELENGTH];
	        int len = -1;  
	        int i=0;
	        while( (len = br.read(buff)) != -1 ){
	        	String string=new String(buff, 0, len);
	        	if(new String(buff, 23, 1).equals("0")) {
	        		String stuid = new String(buff, 0, 7);
	        		if(bookMap.get(stuid)==null) {
	        			Map<String, String> map=new HashMap<String, String>();
	        			map.put(new String(buff, 8, 3), i+"");
	        			bookMap.put(stuid, map);
	        		}
	        		else {
	        			Map<String, String> map=bookMap.get(stuid);
	        			map.put(new String(buff, 8, 3), i+"");
	        			bookMap.put(stuid, map);
	        		}
	        		
	        	}
	        	i++;
	        }
	        in.close();
	        br.close();
	}

	        //    System.out.print(new String(buff, 0, len));  
//	        	if(new String(buff, 24, 1).equals("0"))
//	            {
//	            	 bookMap.put(new String(buff, 0, 7), i+"");
//	            	
//	            }
//	            else {
//	            	System.out.println("已被删除");
//				}
//	            i++;
//	        } 
//	        in.close();
//	        br.close();
//	     //   System.out.println(bufferMap);
//		
//	}
	public Map get(String id) {
		Map <String,Stu_Course> map = new HashMap<String,Stu_Course>();
		
		return map;
	}
	public void add(Stu_Course s) throws Exception {
		RandomAccessFile raf=new RandomAccessFile(rootfile, "rw");  
        //获取RandomAccessFile对象文件指针的位置，初始位置是0  
		if(bookMap.get(s.getstuId())==null) {
			Map<String, String> map=new HashMap<String, String>();
			map.put(s.getcouId(), ""+(rootfile.length()/LINELENGTH) );
			bookMap.put(s.getstuId(), map);
		}
		else {
			Map<String, String> map=bookMap.get(s.getstuId());
			map.put(s.getcouId(), ""+(rootfile.length()/LINELENGTH) );
			bookMap.put(s.getstuId(), map);
		}
		System.out.println("RandomAccessFile文件指针的初始位置:"+raf.getFilePointer()); 
//		raf.seek(raf.length());
//		String tempString=new StuQuality(s).get()+",0"+"\r\n";	
//		raf.write(tempString.getBytes());
		
		raf.seek(raf.length());
		String string = s.toString()+",0\r\n";
		raf.write(string.getBytes());
		raf.close();
		
	//	bookMap.put(s.getstuId(), )
	}
	public void delete(String stuid,String couid) throws Exception {
		RandomAccessFile raf=new RandomAccessFile(rootfile, "rw");
		System.out.println("RandomAccessFile文件指针的初始位置:"+raf.getFilePointer()); 
		raf.seek(Integer.valueOf((String)bookMap.get(stuid).get(couid)).intValue()*LINELENGTH+23);
		raf.write("1\r\n".getBytes());
		bookMap.get(stuid).remove(couid);
		if(bookMap.get(stuid).size()==0)
			bookMap.remove(stuid);
		raf.close();
	}
	public static void main(String[] args) throws Exception {
		File file = new File("d:\\StuCou1.txt");
		StuCouTool tool =new StuCouTool(file);
		System.out.println(tool.bookMap);
		//Stu_Course stu_Course = new Stu_Course("0000004", "004", "2017-03-04");
		//tool.add(stu_Course);
		tool.delete("0000002", "001");
		System.out.println(tool.bookMap);
		
	}
}
