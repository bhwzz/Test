package filetool;
//��дһ��ѧ���ļ�

import java.io.*;
import java.util.*;


//Student s1=new Student("0000001","������",18,'Ů');
public class StuFileTool {

	File f1;
	Map<String , String> bufferMap;
	public StuFileTool(String s) {
		// TODO Auto-generated constructor stub
		f1 = new File(s);
		
	}
	public void write() throws Exception {
//		for(int i=0;i<9999999;i++)
//		{
//			int m=(int)(Math.random()*9+1)*1000000;
//		}
		Map<String, quality> map = new HashMap<String, quality>();
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
	    	int hightPos, lowPos; // ����ߵ�λ 
	    	Random random = new Random(); 
	    	hightPos = (176 + Math.abs(random.nextInt(39)));//��ȡ��λֵ 
	    	lowPos = (161 + Math.abs(random.nextInt(93)));//��ȡ��λֵ 
	    	byte[] b = new byte[2]; 
	    	b[0] = (new Integer(hightPos).byteValue()); 
	    	b[1] = (new Integer(lowPos).byteValue()); 
	    	str = new String(b, "GBk");//ת������ 
	    	s1=s1+str;
	    	}
	    	}
	    	else {
	    		s1 = "  ";
	    		for(int j=0;j<2;j++) {
//	    			   
	    			    	int hightPos, lowPos; // ����ߵ�λ 
	    			    	Random random = new Random(); 
	    			    	hightPos = (176 + Math.abs(random.nextInt(39)));//��ȡ��λֵ 
	    			    	lowPos = (161 + Math.abs(random.nextInt(93)));//��ȡ��λֵ 
	    			    	byte[] b = new byte[2]; 
	    			    	b[0] = (new Integer(hightPos).byteValue()); 
	    			    	b[1] = (new Integer(lowPos).byteValue()); 
	    			    	str = new String(b, "GBk");//ת������ 
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
	    		g='��';
	    	}
	    	else g='Ů';
	    	int id1=(1+(int)(Math.random()*10000000));
	    	String id = String.format("%7d", id1).replace(" ", "0"); 
	    	map.put(id, new quality(id,s1,d,g));
	        //map.add(id,new quality(id));
	        //�����������100  ����ѭ��
	        if(map.size()==10)
	        {
	            break;
	        }
	    }
	    //����
	    
	    PrintWriter fw = new PrintWriter("d:\\test3.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		

	    for (Map.Entry<String, quality> entry : map.entrySet()) {
	
	    		String m=entry.getValue().get()+",0"+"\r\n";
	    		bw.write(m);
	    		System.out.println(m.length());
	    		System.out.println(m.getBytes().length);
	    	
	    	  System.out.println(entry.getValue().get());
	    }

		bw.flush();
		bw.close();
	}

	public void getbuffer(String s) throws Exception
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
            bufferMap.put(new String(buff, 0, 7), i+"");
            i++;
        } 
        in.close();
        br.close();
     //   System.out.println(bufferMap);
	}
	public void getbuffer() throws Exception   //��������
	{
		bufferMap = new TreeMap<String, String>();
		//FileReader r1=new FileReader(f1.getAbsolutePath());
		//bufferMap = new TreeMap<String, String>();
		//	FileReader r1= new FileReader(s);
			FileInputStream in = new FileInputStream(f1.getAbsolutePath());
			BufferedInputStream br=new BufferedInputStream(in);
			//BufferedReader br = new BufferedReader(new FileReader(s));  
	        
	       // char[] buff = new char[20];  
			byte[] buff=new byte[24];
	        int len = -1;  
	        int i=0;
	        while( (len = br.read(buff)) != -1 ){  
	            System.out.print(new String(buff, 0, len));  
	            bufferMap.put(new String(buff, 0, 7), i+"");
	            i++;
	        } 
	        in.close();
	        br.close();
	        System.out.println(bufferMap);
	}
	
	//�����ȡһ��ѧ����Ϣ����
	public String findinfile ( String findid) throws Exception
	{
		String placeString = bufferMap.get(findid);
		if(placeString == null)
			return null;
		else {
			RandomAccessFile raf=new RandomAccessFile(f1, "r");  
        //��ȡRandomAccessFile�����ļ�ָ���λ�ã���ʼλ����0  
			System.out.println("RandomAccessFile�ļ�ָ��ĳ�ʼλ��:"+raf.getFilePointer());  
			System.out.println(placeString);
			raf.seek(Integer.parseInt(placeString)*24);//�ƶ��ļ�ָ��λ��  
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
			String lastString = mString.substring(mString.length()-1, mString.length());//ȡ���һλ
			if(lastString.equals("1"))
			{
				return null;
			}
			System.out.println(mString+"");
			mString =  mString.substring(0,mString.length()-2);
			System.out.println(mString);
			return mString;
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		StuFileTool test1 = new StuFileTool("d:\\test3.txt");
	//	test1.write();
		// TODO Auto-generated method stub
		test1.getbuffer();
		test1.findinfile("1722981");
	}

}
