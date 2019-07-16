package filetool;

import java.io.*;
import java.util.*;

//import org.graalvm.compiler.lir.alloc.lsra.ssa.SSALinearScan;

//import javax.sql.rowset.serial.SerialArray;

import EntityClass.*;

public class StuCouTool implements tool {

	File rootfile;
	Map <String,Map> bookMap;
	int LINELENGTH=35;
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
	        	if(new String(buff, 32, 1).equals("0")) {
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


	public Map get(String id) throws Exception {
		Map <String,Stu_Course> map = new HashMap<String,Stu_Course>();
		RandomAccessFile raf=new RandomAccessFile(rootfile, "r");  
        //获取RandomAccessFile对象文件指针的位置，初始位置是0
		if(bookMap.get(id)==null||bookMap.get(id).size()==0) {
			return null;
		}
		Map<String,String> map2=bookMap.get(id);
		for(String value : map2.values()){
			raf.seek(LINELENGTH*Integer.valueOf(value).intValue());
			byte[] b = new byte[LINELENGTH];
			raf.read(b);
		//	char c[] = new char[20];
		//	for(int i=0;i<20;i++) {
		//		c[i]=raf.readChar();
		//	}
			String mString=new String(b);
			System.out.println(mString+"");
			Stu_Course s = Stu_Course.toStuCourse(mString);
			map.put(s.getcouId(), s);
		}
	//	raf.seek(LINELENGTH*);
//		if(bookMap.get(s.getstuId())==null) {
//			Map<String, String> map=new HashMap<String, String>();
//			map.put(s.getcouId(), ""+(rootfile.length()/LINELENGTH) );
//			bookMap.put(s.getstuId(), map);
		return map;
	}
	public void add(Object ss) throws Exception {
		Stu_Course s=(Stu_Course)ss;
		RandomAccessFile raf=new RandomAccessFile(rootfile, "rw");  
        //获取RandomAccessFile对象文件指针的位置，初始位置是0  
		if(bookMap.get((s).getstuId())==null) {
			Map<String, String> map=new HashMap<String, String>();
			map.put(( s).getcouId(), ""+(rootfile.length()/LINELENGTH) );
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
	public void delete(String []s) throws Exception {
		String stuid=s[0];
		String couid=s[1];
		RandomAccessFile raf=new RandomAccessFile(rootfile, "rw");
		System.out.println("RandomAccessFile文件指针的初始位置:"+raf.getFilePointer()); 
		raf.seek(Integer.valueOf((String)bookMap.get(stuid).get(couid)).intValue()*LINELENGTH+32);
		raf.write("1\r\n".getBytes());
		bookMap.get(stuid).remove(couid);
		if(bookMap.get(stuid).size()==0)
			bookMap.remove(stuid);
		raf.close();
	}
	public static void main(String[] args) throws Exception {
		File file = new File("d:\\testK.txt");
		StuCouTool tool =new StuCouTool(file);
		System.out.println(tool.bookMap);
		//Stu_Course stu_Course = new Stu_Course("0000004", "004", "2017-03-04");
		//tool.add(stu_Course);
	//	tool.delete("0000002", "001");
		System.out.println(tool.bookMap);
		System.out.println(tool.get("1234569"));
		
	}
	
	@Override
	public void writeback(Student s) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String findinfile(String f) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

}
