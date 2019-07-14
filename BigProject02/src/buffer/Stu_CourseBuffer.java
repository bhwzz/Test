import java.io.*;

import java.util.*;
//
//import com.sun.jndi.rmi.registry.ReferenceWrapper;
//import com.sun.tools.classfile.StackMapTable_attribute.same_frame;
import java.util.Map.Entry;

import EntityClass.*;
import EntityClass.Student;
import quality.StudentQuality;
import filetool.*;

public class Stu_CourseBuffer {

	private StudentBuffer stubuffer;
	private CourseBuffer coubuffer;
//	public Map<String, Set> stu_couBook;
	public Map<String, Map> stu_couBuffer;
	public StuCouTool tool2;
	public File rootfile;
	int LINELENGTH = 1;
	int STUIDSIZE = 7;
	int COUIDSIZE = 3;
	int TIMESIZE = 0;
	int SIZE = 10;
	
	public boolean Check() throws Exception {
		if(stu_couBuffer.size()<=SIZE)
			return false;
		//������ڵĻ���û�б�װ�� ����false
		else {
//			studentMap.remove(key)
			Map.Entry<String, Map> item = (Entry<String, Map>)stu_couBuffer.entrySet().iterator().next();
			//��ȡ��һ����ֵ��
			//ѡ����Ϣò�Ʋ��ᱻ�޸ģ�ֻ�����Ӻ�ɾ��������
//			if(item.getValue().flag==0)
//			{
				stu_couBuffer.remove(item.getKey());
				//û�б��޸Ĺ� ֱ�Ӷ���
			}
//			
//			else if(item.getValue().flag!=0)
//			{
//				
//				tool1.writeback(item.getValue().getStudent());
//				studentMap.remove(item.getKey());
//				//���޸Ĺ� д��ȥ
//			}
			//TreeMap<String,String>д����
			//RandomAccessFile ��������ļ�
			
			return true;
		}
	
	public Stu_CourseBuffer(StudentBuffer stu,String s) {
		this.stubuffer = stu;
		stu_couBuffer = new LinkedHashMap<String, Map>();
		rootfile = new File(s);
		// TODO Auto-generated constructor stub
	}
	public Map find(String Stuid) {
		if(stubuffer.Find(Stuid)==null)
		{
			System.out.println("��ѧ��������");
			return null;
		}
		else if(stu_couBuffer.get(Stuid)!=null)
		{
			Map map=stu_couBuffer.get(Stuid);
			stu_couBuffer.remove(Stuid);
			stu_couBuffer.put(Stuid, map);
			return map;
		}
		else {
			Map<String,Stu_Course> map = tool2.get(Stuid);
			if(map==null||map.size()==0)
			{
				System.out.println("��ѧ��δѡ����");
				return null;
			}
			stu_couBuffer.put(Stuid, map);
			return map;
		}
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
//		else if(stu_couBook.get(Stuid)==null)
//		{
//			System.out.println("��ѧ��δѡ����");
//			return false;
//		}
		else if(stu_couBuffer.get(Stuid)!=null){
			
			Map<String,Stu_Course> map=(Map)stu_couBuffer.get(Stuid);
			if(map.get(Couid)==null||map.size()==0) {
				System.out.println("��ѧ��δѡ����ڿ�");
				return false;
			}
			return true;
		}
	
		else {
			Map map=tool2.get(Stuid);
			if(map==null||map.size()==0) {
				System.out.println("��ѧ��δѡ����");
				return false;
			}
			stu_couBuffer.put(Stuid, map);
			Check();
			if(map.get(Couid)==null) {
				System.out.println("��ѧ��δѡ����ڿ�");
				return false;
			}
			return true;
		}
		
	}
			
	public boolean add(Stu_Course s)			
	{
		String Stuid = s.getstuId();
		String Couid = s.getcouId();
		if(stubuffer.Find(Stuid)==null)
		{
			System.out.println("��ѧ��������");
			return false;
		}
		else if(coubuffer.Find(Couid)==null)
		{
			System.out.println("�ÿγ̲�����");
			return false;
		}
		else if(stu_couBuffer.get(Stuid)!=null)
		{//�ڻ��������ҵ����ѧ��
			Map map = stu_couBuffer.get(Stuid);
			if(map.get(Couid)==null) {  //���ѧ��û��ѡ����ڿ�
				map.put(Couid, s);
				tool2.add(s);
				Check();
				return true;
			}
			//���ļ���β���һ�� һ����ݸ�ʽд
			
			else {
				System.out.println("�Ѿ�ѡ����ڿ���");
				return false;
			}
			
		}
		else {
			Map map=tool2.get(Stuid);
			if(map==null||map.size()==0) {
				System.out.println("���ѧ��û��ѡ���κο�");
				tool2.add(s);//���ļ��������һ����¼
				Map<String, Stu_Course> map2 = new HashMap<String, Stu_Course>();
				map2.put(Couid, s);
				stu_couBuffer.put(Stuid, map2);
				Check();
				return true;
			}
			else if(map.get(Couid)!=null){
				System.out.println("���ѧ���Ѿ�ѡ����ڿ���");
				return false;
			}
			else {
				tool2.add(s);//���ļ��������һ����¼
				map.put(Couid, s);
				stu_couBuffer.put(Stuid, map);
				Check();
				return true;
			}
		}
	}
//			Map <String,String> map=new HashMap<String, String>();
//			map.put(s.getcouId(),""+(rootfile.length()/LINELENGTH));
//		
//			stu_coubuffer.put(Stuid, map);
//			
//			
//			RandomAccessFile raf=new RandomAccessFile(rootfile, "r");  
//	        //��ȡRandomAccessFile�����ļ�ָ���λ�ã���ʼλ����0  
//			
//			System.out.println("RandomAccessFile�ļ�ָ��ĳ�ʼλ��:"+raf.getFilePointer()); 
//			raf.seek(rootfile.length());
//			raf.writeBytes(s.toString()+",0,\r\n".getBytes());
//			raf.close();
//			
//			
//			return true;
		
//		else if(stu_coubuffer.get(Stuid).get(Couid)==null){//��ѧ��ѡ���� ����ûѡ����ڿ�
//			System.out.println("��ѧ��ѡ���� ����ûѡ����ڿ�");
//			Map <String,String> map=stu_coubuffer.get(Stuid);
//			map.put(s.getcouId(),""+(rootfile.length()/LINELENGTH));
//		
//			stu_coubuffer.put(Stuid, map);
//			
//			
//			RandomAccessFile raf=new RandomAccessFile(rootfile, "r");  
//	        //��ȡRandomAccessFile�����ļ�ָ���λ�ã���ʼλ����0  
//			
//			System.out.println("RandomAccessFile�ļ�ָ��ĳ�ʼλ��:"+raf.getFilePointer()); 
//			raf.seek(rootfile.length());
//			raf.writeBytes(s.toString()+",0,\r\n".getBytes());
//			raf.close();
//			return true;
//		}
//		else {  //��ѧ��ѡ����ڿ�
//			System.out.println("��ѧ��ѡ����ڿ�");
//			return false;
//		}
//	}
				//char[] buff = new char[20]; 
//				String mString = new String(raf.readline().getBytes("ISO-8859-1"), "utf-8");
			
			//	raf.read(b);
			//	char c[] = new char[20];
			//	for(int i=0;i<20;i++) {
			//		c[i]=raf.readChar();
			//	}
				
				
				//System.out.println(mString.toCharArray());
				//int i=mString()
		public boolean delete(String Stuid,String Couid) {
			if(stubuffer.Find(Stuid)==null)
			{
				System.out.println("��ѧ��������");
				return false;
			}
			else if(coubuffer.Find(Couid)==null)
			{
				System.out.println("�ÿγ̲�����");
				return false;
			}
			else if(stu_couBuffer.get(Stuid)!=null) {
				Map map=stu_couBuffer.get(Stuid);
				if(map.get(Couid)==null) {
					System.out.println("��ѧ��ѡ���� ����ûѡ����ڿ�");
					return false;
				}
				else {
					map.remove(Couid);
					tool2.delete( Stuid, Couid);
					return true;
				}
			}
			else {
				Map map=tool2.get(Stuid);
				if(map==null||map.size()==0) {
					System.out.println("��ѧ����δѡ����");
					return false;
				}
				else if(map.get(Couid)==null)
				{
					System.out.println("��ѧ��ûѡ����ڿ�");
					return false;
				}
				else if(map.get(Couid)!=null) {
					tool2.delete(Stuid, Couid);
					map.remove(Couid);
					stu_couBuffer.put(Stuid, map);
					Check();
					return true;
				}
			}
				
			
		}
			
			
		
	
	
	
//	public void getbuffer() {
//		stu_coubuffer = new TreeMap<String, Map>();
//		//FileReader r1=new FileReader(f1.getAbsolutePath());
//		//bufferMap = new TreeMap<String, String>();
//		//	FileReader r1= new FileReader(s);
//			FileInputStream in = new FileInputStream(rootfile.getAbsolutePath());
//			BufferedInputStream br=new BufferedInputStream(in);
//			//BufferedReader br = new BufferedReader(new FileReader(s));  
//	        
//	       // char[] buff = new char[20];  
//			byte[] buff=new byte[LINELENGTH];
//	        int len = -1;  
//	        int i=0;
//	        while( (len = br.read(buff)) != -1 ){  
//	            System.out.print(new String(buff, 0, len));  
//	            System.out.println(new String(buff, 0, len).getBytes().length);
//	            //System.out.println(new String(buff, 0, len).get);
//	            System.out.println(new String(buff, 0, 7));
//	            
//	            if(new String(buff, 21, 1).equals("0"))
//	            {
//	            	// bufferMap.put(new String(buff, 0, 7), i+"");
//	            	String stuidString = new String(buff, 0, 7);
//	            	if(stu_coubuffer.get(stuidString)!=null)
//	            	{
//	            		stu_coubuffer.get(stuidString).add(i+"");
//	            	}
//	            	if(stu_coubuffer.get(stuidString)==null)
//	            	{
//	            		List<String> list=new ArrayList<String>();
//	            		list.add(i+"");
//	            		stu_coubuffer.put(stuidString,list);
//	            	}
//	            }
//	          
//	            else {
//	            	System.out.println("�ѱ�ɾ��");
//				}
//	         //   bufferMap.put(new String(buff, 0, 7), i+"");
//	            i++;
//	        } 
//	        in.close();
//	        br.close();
//	        System.out.println(bufferMap);
//	}
//	
}
