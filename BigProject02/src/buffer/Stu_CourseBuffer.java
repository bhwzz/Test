package buffer;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import quality.*;
import quality.StuCouQuality;

//
//import com.sun.jndi.rmi.registry.ReferenceWrapper;
//import com.sun.tools.classfile.StackMapTable_attribute.same_frame;
import java.util.Map.Entry;

import EntityClass.*;
import filetool.*;
//import filetool.StuCouQuality;

public class Stu_CourseBuffer {

	private StudentBuffer stubuffer;
	private CourseBuffer coubuffer;
//	public Map<String, Set> stu_couBook;
	public Map<String, Map> stu_couBuffer;
	public tool tool2;
	public File rootfile;
	int LINELENGTH = 1;
	int STUIDSIZE = 7;
	int COUIDSIZE = 3;
	int TIMESIZE = 0;
	int SIZE = 10;
	public Stu_CourseBuffer(StudentBuffer s,CourseBuffer c,String filename) throws Exception {
		stubuffer = s;
		coubuffer = c;
		rootfile = new File(filename);
		tool2 = new StuCouTool(rootfile);
		stu_couBuffer = new HashMap<String, Map>();
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean Check() throws Exception {
		synchronized(stu_couBuffer) {
			System.out.println("目前选课缓存大小为"+stu_couBuffer.size());
			if(stu_couBuffer.size()<=SIZE)
				return false;
			//如果现在的缓存没有被装满 返回false
			else {
				if(stu_couBuffer.size()<6) {
		//			studentMap.remove(key)
					Map.Entry<String, Map> item = (Entry<String, Map>)stu_couBuffer.entrySet().iterator().next();
					//获取第一个键值对
					Map<String, StuCouQuality> m2= item.getValue();
					//选课信息貌似不会被修改？只有增加和删除？？？
											
					for (StuCouQuality value : m2.values()) {
						if(value.flag1==1)
							tool2.add(value.stu_Course);;
						if(value.flag1==-1)
							tool2.delete(value.stu_Course.getstuId(), value.stu_Course.getcouId());
							//add(value.stu_Course.getstuId(), value.stu_Course.getcouId());
					}
					stu_couBuffer.remove(item.getKey());	
					return true;
				}
				else{
					for(int i=0;i<5;i++) {
					//			studentMap.remove(key)
								Map.Entry<String, Map> item = (Entry<String, Map>)stu_couBuffer.entrySet().iterator().next();
								//获取第一个键值对
								Map<String, StuCouQuality> m2= item.getValue();
								//选课信息貌似不会被修改？只有增加和删除？？？
														
								for (StuCouQuality value : m2.values()) {
									if(value.flag1==1)
										tool2.add(value.stu_Course);;
									if(value.flag1==-1)
										tool2.delete(value.stu_Course.getstuId(), value.stu_Course.getcouId());
										//add(value.stu_Course.getstuId(), value.stu_Course.getcouId());
								}
								
								stu_couBuffer.remove(item.getKey());	
								
							}
					return true;
				}
			}
		}
	}
	
//	public Stu_CourseBuffer(StudentBuffer stu,String s) {
//		this.stubuffer = stu;
//		stu_couBuffer = new LinkedHashMap<String, Map>();
//		rootfile = new File(s);
//		// TODO Auto-generated constructor stub
//	}
	public Map find(String Stuid) throws Exception {
		Student student=null;
		synchronized(stubuffer) {
			student=(stubuffer.Find(Stuid));
		}
		
		Map<String,StuCouQuality>mainMap =null;
		synchronized(stu_couBuffer) {
			mainMap=stu_couBuffer.get(Stuid);
		}
		
		if(student==null)
		{
			System.out.println("该学生不存在");
			return null;
		}
		else if(stu_couBuffer.get(Stuid)!=null)
		{
			Map<String,StuCouQuality> map=mainMap;
			synchronized(stu_couBuffer) {
				stu_couBuffer.remove(Stuid);
				stu_couBuffer.put(Stuid, map);
			}
			Map<String, Stu_Course> map2 = new HashMap<String, Stu_Course>();
		
			for (StuCouQuality value : map.values()) {
				if(value.flag1!=-1)
					map2.put(value.stu_Course.getcouId(), value.stu_Course);

			}
			
				return map2;
		}
		else {
			Map<String,Stu_Course> map=null;
			synchronized(stu_couBuffer) {
				 map = (Map<String,Stu_Course>)tool2.get(Stuid);
			}
			if(map==null||map.size()==0)
			{
				System.out.println("该学生未选过课");
				Map <String ,Stu_Course> map2=new HashMap<String, Stu_Course>();
				return map2;
			}
			Map<String, StuCouQuality> map3=new HashMap<String, StuCouQuality>();
			for (Stu_Course value : map.values()) {
				
				map3.put(value.getcouId(), new StuCouQuality(value));

			}
			synchronized(stu_couBuffer) {
				stu_couBuffer.put(Stuid, map3);
			}
			return map;
		}
	}
	public int find(String Stuid,String Couid) throws Exception {
		Student student=null;
		synchronized(stubuffer) {
			student=stubuffer.Find(Stuid);
		}
		Map<String,StuCouQuality> mainmap=null;
		synchronized(stu_couBuffer) {
			mainmap=stu_couBuffer.get(Stuid);
			}
		if(student==null)
		{
			System.out.println("该学生不存在");
			return -1;
		}
		else if(coubuffer.Find(Couid)==null) {
			System.out.println("该课程不存在");
			return -2;
		}
//		else if(stu_couBook.get(Stuid)==null)
//		{
//			System.out.println("该学生未选过课");
//			return false;
//		}
		else if(mainmap!=null){
			
			Map<String,StuCouQuality> mapp=mainmap;
			if(mapp.get(Couid)==null||mapp.size()==0) {
				System.out.println("该学生未选过这节课");
				return -3;
			}
			Map<String, Stu_Course> map3=new HashMap<String, Stu_Course>();
			for (StuCouQuality value : mapp.values()) {
				if(value.flag1!=-1)
					map3.put(value.stu_Course.getcouId(), value.stu_Course);

			}
			if(map3.size()==0) {
				System.out.println("该学生未选过这节课");
				return -3;
			}
			synchronized(stu_couBuffer) {
				stu_couBuffer.remove(Stuid);
				stu_couBuffer.put(Stuid, mapp);
			}
			return 1;
		}
	
		else {
			Map<String,Stu_Course> map=null;
			synchronized(stu_couBuffer) {
				map=(Map<String, Stu_Course>)tool2.get(Stuid);
			}
			if(map==null||map.size()==0) {
				System.out.println("该学生未选过课");
				return -3;
			}
			Map<String, StuCouQuality> map3=new HashMap<String, StuCouQuality>();
			for (Stu_Course value : map.values()) {
				map3.put(value.getcouId(), new StuCouQuality(value));

			}
			synchronized(stu_couBuffer) {
				stu_couBuffer.put(Stuid, map3);
			
				Check();
			}
			if(map.get(Couid)==null) {
				System.out.println("该学生未选过这节课");
				return -3;
			}
			return 1;
		}
		
	}
			
	public int add(String Stuid,String Couid) throws Exception			
	{
		//String Stuid = s.getstuId();
		//String Couid = s.getcouId();
		Student student=null;
		synchronized(stubuffer) {
			student=stubuffer.Find(Stuid);
		}
		
		
		Map mainMap = null;
		synchronized(stu_couBuffer) {
			mainMap=stu_couBuffer.get(Stuid);
		}
		if(stubuffer.Find(Stuid)==null)
		{
			System.out.println("该学生不存在");
			return -1;
		}
		else if(coubuffer.Find(Couid)==null)
		{
			System.out.println("该课程不存在");
			return -2;
		}
		else if(mainMap!=null)
		{//在缓存中能找到这个学生
			Map map = mainMap;
			if(map.get(Couid)==null||((StuCouQuality)map.get(Couid)).flag1==-1) {  //这个学生没有选过这节课
				if(!coubuffer.AddCourse(Couid)) {
					System.out.println("该课程已满课");
					return -3;
				}
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
				String time= sdf.format( new Date());
				Stu_Course s=new Stu_Course(Stuid,Couid,time);
				StuCouQuality quality=new StuCouQuality(s);
				quality.changeflag();
				map.put(Couid, quality);
			//	tool2.add(s);
				synchronized(stu_couBuffer) {
					Check();
				}
				return 1;
			}
			//在文件结尾添加一行 一会根据格式写
			
			else {
				System.out.println("已经选过这节课了");
				return -4;
			}
			
		}
		else {
			Map<String,Stu_Course> map=null;
			synchronized(stu_couBuffer) {
				map=(Map<String, Stu_Course>)tool2.get(Stuid);
			}
			if(map==null||map.size()==0) {
				System.out.println("这个学生没有选过任何课");
				if(!coubuffer.AddCourse(Couid)) {
					System.out.println("该课程已满课");
					return -3;
				}
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
				String time= sdf.format( new Date());
				Stu_Course s=new Stu_Course(Stuid,Couid,time);
				//tool2.add(s);//往文件里面加入一条记录
				Map<String, StuCouQuality> map2 = new HashMap<String, StuCouQuality>();
				StuCouQuality quality=new StuCouQuality(s);
				quality.changeflag();
				map2.put(Couid, quality);
				synchronized(stu_couBuffer) {
					stu_couBuffer.put(Stuid, map2);
				
					Check();
				}
				return 1;
			}
			else if(map.get(Couid)!=null){
				System.out.println("这个学生已经选过这节课了");
				return -4;
			}
			else {
				if(!coubuffer.AddCourse(Couid)) {
					System.out.println("该课程已满课");
					return -3;
				}
				Map<String, StuCouQuality> map3=new HashMap<String, StuCouQuality>();
				for (Stu_Course value : map.values()) {
					map3.put(value.getcouId(), new StuCouQuality(value));

				}
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
				String time= sdf.format( new Date());
				Stu_Course s=new Stu_Course(Stuid,Couid,time);
				StuCouQuality quality=new StuCouQuality(s);
				quality.changeflag();
			//	tool2.add(s);//往文件里面加入一条记录
				map3.put(Couid, quality);
				synchronized(stu_couBuffer) {
					stu_couBuffer.put(Stuid, map3);
				
					Check();
				}
				return 1;
			}
		}
	}
//			Map <String,String> map=new HashMap<String, String>();

	public int delete(String Stuid,String Couid) throws Exception {
		Student student=null;
		synchronized(stubuffer) {
			student=stubuffer.Find(Stuid);
		}
		Map mainMap=null;
		synchronized(stu_couBuffer) {
			mainMap=stu_couBuffer.get(Stuid);
		}
			if(student==null)
			{
				System.out.println("该学生不存在");
				return -1;
			}
			else if(coubuffer.Find(Couid)==null)
			{
				System.out.println("该课程不存在");
				return -2;
			}
			else if(mainMap!=null) {
				Map map=mainMap;
				if(map.get(Couid)==null||((StuCouQuality)(mainMap.get(Couid))).flag1==-1) {
					System.out.println("该学生选过课 但是没选过这节课");
					return -3;
				}
				else {
					coubuffer.DropCourse(Couid);
					((StuCouQuality)map.get(Couid)).delflag();
					//map.remove(Couid);
					//tool2.delete( Stuid, Couid);
					return 1;
				}
			}
			else {
				
				Map<String,Stu_Course> map=null;
				synchronized(stubuffer) {
						map=(Map<String, Stu_Course>)tool2.get(Stuid);
				}
				if(map==null||map.size()==0) {
					System.out.println("该学生从未选过课");
					return -3;
				}
				else if(map.get(Couid)==null)
				{
					System.out.println("该学生没选过这节课");
					return -3;
				}
				else if(map.get(Couid)!=null) {
					coubuffer.DropCourse(Couid);
					//tool2.delete(Stuid, Couid);
					//map.remove(Couid);
					Map<String, StuCouQuality> map3=new HashMap<String, StuCouQuality>();
					for (Stu_Course value : map.values()) {
						map3.put(value.getcouId(), new StuCouQuality(value));

					}
					map3.get(Couid).delflag();
					synchronized(stubuffer) {
						stu_couBuffer.put(Stuid, map3);
					
						Check();
					}
					return 1;
				}
			}
			return 0;
		}
			
	public void clear() throws Exception {
		synchronized(stubuffer) {
			Iterator<Entry<String, Map>> entries =stu_couBuffer.entrySet().iterator();
			while(entries.hasNext()){
			    Entry<String, Map> item = entries.next();
	//		    if(entry.getValue().flag==0)
	//			{
	//				studentMap.remove(entry.getKey());
	//				//没有被修改过 直接丢弃
	//			}
				
			//    Map.Entry<String, Map> item = (Entry<String, Map>)stu_couBuffer.entrySet().iterator().next();
				//获取第一个键值对
				Map<String, StuCouQuality> m2= item.getValue();
				//选课信息貌似不会被修改？只有增加和删除？？？
										
				for (StuCouQuality value : m2.values()) {
					if(value.flag1==1)
						tool2.add(value.stu_Course);
					else if(value.flag1==-1)
						tool2.delete(value.stu_Course.getstuId(), value.stu_Course.getcouId());
						//add(value.stu_Course.getstuId(), value.stu_Course.getcouId());
				}
			
			//stu_couBuffer.remove(item.getKey());	
		    
			    
				
			}
			stu_couBuffer.clear();
		}
	}	
	
	
	public void fresh() throws Exception {
		synchronized(stubuffer) {
			System.out.println("正在刷新文件");
			Iterator<Entry<String, Map>> entries =stu_couBuffer.entrySet().iterator();
			while(entries.hasNext()){
			    Entry<String, Map> item = entries.next();
			//获取第一个键值对
				Map<String, StuCouQuality> m2= item.getValue();
				List<String> list = new ArrayList<String>();
				//选课信息貌似不会被修改？只有增加和删除？？？
										
				for (StuCouQuality value : m2.values()) {
					if(value.flag1==1) {
						tool2.add(value.stu_Course);
						value.flag1=0;
					}
						
					else if(value.flag1==-1) {
						tool2.delete(value.stu_Course.getstuId(), value.stu_Course.getcouId());
						//add(value.stu_Course.getstuId(), value.stu_Course.getcouId());
						list.add( value.stu_Course.getcouId());
					}
				}
				for(String value:list) {
					m2.remove(value);
				}
			}
			Iterator<Entry<String, Map>> entries2 =stu_couBuffer.entrySet().iterator();
			List<String> list2 = new ArrayList<String>();
			while(entries2.hasNext()) {
				 Entry<String, Map> item2 = entries2.next();
				 if(item2.getValue().size()==0) {
					 list2.add(item2.getKey());
				 }
			}
			for(String value:list2) {
				stu_couBuffer.remove(value);
			}
		}
		
	}	
	
	public static void main(String[] args) {
		//Stu_CourseBuffer mBuffer = new Stu_CourseBuffer(, c, filename)
		//System.out.println("lalalalala");
	}
	
	
	

}
