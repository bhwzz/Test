package filetool;
//编写一个学生文件
//writeback前两行要改一下
import java.io.*;
import java.util.*;

import EntityClass.Student;



//Student s1=new Student("0000001","王丽安",18,'女');
public class StuFileTool implements tool {
	int LINELENGTH = 24;
	File f1;
	public Map<String , String> bufferMap;
	public StuFileTool(String s) throws Exception {
		// TODO Auto-generated constructor stub
		f1 = new File(s);
		this.getbook();
	}

	
	
	public void writeback(Student s) throws Exception {
		if(bufferMap.get(s.getId())!=null) {
			String pathString = bufferMap.get(s.getId());
	//	
	//	
	//	public void writeback(String s) throws Exception {
	//		String pathString = bufferMap.get(s);
			RandomAccessFile raf = new RandomAccessFile(f1, "rw");
			raf.seek(Integer.parseInt(pathString)*LINELENGTH);
			String tempString=new StuQuality(s).get()+",0"+"\r\n";	
			raf.write(tempString.getBytes());
			raf.close();
		}
		else if(bufferMap.get(s.getId())==null) {
			add(s.toString());
		}
	}
	
	public void delete(String s) throws Exception {
//		String s=ss[0];
		if( bufferMap.get(s)!=null) {
			String pathString = bufferMap.get(s);
			RandomAccessFile raf = new RandomAccessFile(f1, "rw");
			raf.seek(Integer.parseInt(pathString)*LINELENGTH+21);
		//	String tempString=new StuQuality(s).get()+",1"+"\r\n";
			String tempString = "1";
			bufferMap.remove(s);
			raf.write(tempString.getBytes());
			raf.close();
		}
	}
	
	public void add(Object k) throws Exception {
//	public void add(String s) throws Exception {
	//String pathString = bufferMap.get(s);
		String s=(String)k;
		RandomAccessFile raf=new RandomAccessFile(f1, "rw");
	//	String pathString = bufferMap.get(s.getId());
	//	
	//	
//		public void writeback(String s) throws Exception {
//			String pathString = bufferMap.get(s);
			//Rando(mAccessFile raf = new RandomAccessFile(f1, "rw");
			raf.seek(raf.length());
			String tempString=new StuQuality(s).get()+",0"+"\r\n";	
			raf.write(tempString.getBytes());
			
			bufferMap.put(new String(tempString.getBytes(), 0, 7), (raf.length()/LINELENGTH)-1+"");
			raf.close();
	}
	public void write() throws Exception {
//		for(int i=0;i<9999999;i++)
//		{
//			int m=(int)(Math.random()*9+1)*1000000;
//		}
		Map<String, StuQuality> map = new HashMap<String, StuQuality>();
		//Map<Integer> hashset=new HashSet();
	    for(int i=0;;i++)
	    {

//	          
//	          
//	          
//	    	String s1=ret;
	    	String s1 = "";
	    	String str = null; 
	    	
	    	int q1=(int)(Math.random()*10);
//	    	System.out.println(q);
	    	int f1=q1%2;
	    	if (f1==1) {
	    	
	    	for(int j=0;j<3;j++) {
//	   
	    	int hightPos, lowPos; // 定义高低位 
	    	Random random = new Random(); 
	    	hightPos = (176 + Math.abs(random.nextInt(39)));//获取高位值 
	    	lowPos = (161 + Math.abs(random.nextInt(93)));//获取低位值 
	    	byte[] b = new byte[2]; 
	    	b[0] = (new Integer(hightPos).byteValue()); 
	    	b[1] = (new Integer(lowPos).byteValue()); 
	    	str = new String(b, "GBk");//转成中文 
	    	s1=s1+str;
	    	}
	    	}
	    	else {
	    		s1 = "  ";
	    		for(int j=0;j<2;j++) {
//	    			   
	    			    	int hightPos, lowPos; // 定义高低位 
	    			    	Random random = new Random(); 
	    			    	hightPos = (176 + Math.abs(random.nextInt(39)));//获取高位值 
	    			    	lowPos = (161 + Math.abs(random.nextInt(93)));//获取低位值 
	    			    	byte[] b = new byte[2]; 
	    			    	b[0] = (new Integer(hightPos).byteValue()); 
	    			    	b[1] = (new Integer(lowPos).byteValue()); 
	    			    	str = new String(b, "GBk");//转成中文 
	    			    	s1=s1+str;
	    			    	}
	    	}
	    //	s1=str;
	    	//String s1=str;
	    	
	 
	    	int d=(int)((Math.random()*9+1)*10%16+1);
	    	
	    	int q=(int)(Math.random()*10);
//	    	System.out.println(q);
	    	int f=q%2;
	    	char g;
	    	if (f==1) {
	    		g='男';
	    	}
	    	else g='女';
	    	int id1=(1+(int)(Math.random()*10000000));
	    	String id = String.format("%7d", id1).replace(" ", "0"); 
	    	map.put(id, new StuQuality(id,s1,d,g));
	        //map.add(id,new quality(id));
	        //如果容量等于100  跳出循环
	        if(map.size()==10)
	        {
	            break;
	        }
	    }
	    //遍历
	    
	    PrintWriter fw = new PrintWriter(f1.getAbsolutePath());
		BufferedWriter bw = new BufferedWriter(fw);
		

	    for (Map.Entry<String, StuQuality> entry : map.entrySet()) {
	
	    		String m=entry.getValue().get()+",0"+"\r\n";
	    		bw.write(m);
	   // 		System.out.println(m.length());
	    //		System.out.println(m.getBytes().length);
	    	
	   // 	  System.out.println(entry.getValue().get());
	    }

		bw.flush();
		bw.close();
	}

	public void getbook(String s) throws Exception
	{
		bufferMap = new TreeMap<String, String>();
	//	FileReader r1= new FileReader(s);
		FileInputStream in = new FileInputStream(s);
		BufferedInputStream br=new BufferedInputStream(in);
		//BufferedReader br = new BufferedReader(new FileReader(s));  
        
       // char[] buff = new char[20];  
		byte[] buff=new byte[24];
        int len = -1;  
        int i=0;
        while( (len = br.read(buff)) != -1 ){  
        //    System.out.print(new String(buff, 0, len));  
        	if(new String(buff, 21, 1).equals("0"))
            {
            	 bufferMap.put(new String(buff, 0, 7), i+"");
            	
            }
            else {
            	System.out.println("已被删除");
			}
            i++;
        } 
        in.close();
        br.close();
     //   System.out.println(bufferMap);
	}
	public void getbook() throws Exception   //生成索引
	{
		bufferMap = new TreeMap<String, String>();
		//FileReader r1=new FileReader(f1.getAbsolutePath());
		//bufferMap = new TreeMap<String, String>();
		//	FileReader r1= new FileReader(s);
			FileInputStream in = new FileInputStream(f1.getAbsolutePath());
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
	            	 bufferMap.put(new String(buff, 0, 7), i+"");
	            	
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
	
	//随机读取一条学生信息测试
	public String findinfile ( String findid) throws Exception
	{
		String placeString = bufferMap.get(findid);
		if(placeString == null)
			return null;
		else {
			RandomAccessFile raf=new RandomAccessFile(f1, "r");  
        //获取RandomAccessFile对象文件指针的位置，初始位置是0  
			System.out.println("RandomAccessFile文件指针的初始位置:"+raf.getFilePointer());  
			System.out.println(placeString);
			raf.seek(Integer.parseInt(placeString)*LINELENGTH);//移动文件指针位置  
			//char[] buff = new char[20]; 
//			String mString = new String(raf.readline().getBytes("ISO-8859-1"), "utf-8");
			byte[] b = new byte[24];
			raf.read(b);
		//	char c[] = new char[20];
		//	for(int i=0;i<20;i++) {
		//		c[i]=raf.readChar();
		//	}
			String mString=new String(b);
			System.out.println(mString+"");
			//System.out.println(mString.toCharArray());
			//int i=mString()
			mString = mString.replaceAll("[\\t\\n\\r]", "");
			mString = mString.replaceAll(" ", "");
			String lastString = mString.substring(mString.length()-1, mString.length());//取最后一位
			if(lastString.equals("1"))
			{
				return null;
			}
	//		System.out.println(mString+"");
			mString =  mString.substring(0,mString.length()-2);
			System.out.println(mString);
			//Student student=Student.toStudent(mString);
			return mString;
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		
//		StuFileTool test1 = new StuFileTool("d:\\test3.txt");
//	//	test1.write();
//		// TODO Auto-generated method stub
//		test1.getbuffer();
//		test1.findinfile("1722981");
		
		
		StuFileTool test1 = new StuFileTool("d:\\test3.txt");
			test1.write();
			// TODO Auto-generated method stub
//			test1.getbuffer();
			System.out.println("ok");
		//	test1.findinfile("1722981");
//			 Scanner sc = new Scanner(System.in);
		        //利用hasNextXXX()判断是否还有下一输入项
//		    while(true) {
//		            String str = sc.next();
//		            if(str.equals("n"))
//		            	break;
//		            test1.findinfile(str);
//		    }
	//		 test1.writeback("2964715");
			test1.add("2272176,双卿扑,03,男");
			System.out.println(test1.bufferMap);
	}


	@Override
	public void delete(String s, String s2) throws Exception {
		// TODO Auto-generated method stub
		
	}



	@Override
	public Object get(String stuid) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



	



	

}
