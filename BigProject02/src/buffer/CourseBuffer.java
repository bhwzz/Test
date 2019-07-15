package buffer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import EntityClass.*;

import EntityClass.Course;
import quality.StudentQuality;

public class CourseBuffer {

	public Map<String, Course> CourseMap;//(cou_id,course)
	public File rootfile;
	public CourseBuffer(String file) throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		CourseMap=new HashMap<String,Course>();
		String line;
		Course cou=null;
		rootfile = new File(file);
		while((line=br.readLine())!=null) {
			cou=Course.toCourse(line);
			CourseMap.put(cou.getCourse_id(), cou);
		}		
	}
	public void writeFile() throws IOException {//��map�еĿγ���Ϣд���ļ�
		PrintWriter pw=new PrintWriter((new FileOutputStream(rootfile.getAbsolutePath())));
		Iterator it=CourseMap.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, Course> entry=(Map.Entry<String, Course>)it.next();
			pw.println(entry.getValue().toString());
		}
		pw.flush();
		pw.close();
	}
	
	protected void finalize() throws Exception {
		writeFile();
	}
	
	public Course Find(String s) {    //�ҵ�����course û�ҵ�����null
		Course c=null;
		synchronized(CourseMap.get(s)) {
			c=CourseMap.get(s);
		}
		if(c==null)
		{
			return null;
		}
		return c;
	}
	public Map FindAll() {//�������пγ���Ϣ
		return CourseMap;
	}
	public boolean Change(String s) {//�޸Ŀγ���Ϣ�����롰id��name��ֻ�����޸Ŀγ���
		//���жϸÿγ��Ƿ����
		String []ss=new String[2];
		ss=s.split(",");
		if(Find(ss[0])==null) {
			System.out.println("����������γ�");
			return false;
		}
		synchronized(CourseMap.get(s)) {
			CourseMap.get(ss[0]).setCourse_name(ss[1]);
			CourseMap.put(ss[0],CourseMap.get(ss[0]));
		}
		return true;
	}
	
	public boolean Add(Course s) {//���ӿγ̣����γ��Ѵ��ڷ���false
		if(Find(s.getCourse_id())!=null) {
			System.out.println("�Ѿ���������γ�");
			return false;
		}	
		synchronized(CourseMap.get(s)) {
			CourseMap.put(s.getCourse_id(), s);
		}
		return true;
		
	}
	
	public int Delete(String s){//ɾ���γ̣��γ̲����ڷ���-1,�γ���ѡ������0����-2���ɹ�����1
		if(Find(s)==null) {
			System.out.println("����������γ�");
			return -1;
		}
		synchronized(CourseMap.get(s)) {
			if(CourseMap.get(s).getStu_num()!=0) {
				System.out.println("�ÿγ��Ѿ���ѡ������ɾ��");
				return -2;
			}
		}
		return 1;
	}
	public boolean AddCapcity(String id,int num) {//���ӿγ��������γ̲����ڷ���false
		if(CourseMap.get(id)==null) {
			System.out.println("�ÿγ̲����ڣ�");
			return false;
		}
		synchronized(CourseMap.get(id)) {
			CourseMap.get(id).setNum(CourseMap.get(id).getNum()+num);
		}
		return true;
	}
	public void DropCourse(String id) {//�˿κ�����ÿ��+1
		synchronized(CourseMap.get(id)) {
			CourseMap.get(id).setLeft_num(CourseMap.get(id).getLeft_num()+1);
			CourseMap.get(id).setStu_num(CourseMap.get(id).getStu_num()-1);
		}
	}
	public boolean AddCourse(String id) {//���û�пγ̲����ټ�������ѡ��
		synchronized(CourseMap.get(id)) {
			if(CourseMap.get(id).getLeft_num()>0) {
				CourseMap.get(id).setLeft_num(CourseMap.get(id).getLeft_num()-1);
				CourseMap.get(id).setStu_num(CourseMap.get(id).getStu_num()+1);
				return true;
			}
			else
				return false;
		}
	}
//	public static void main(String[] args) throws IOException {
//		CourseBuffer cb=new CourseBuffer("D:\\test4.txt");
//		Iterator it=cb.CourseMap.entrySet().iterator();
//		while(it.hasNext()) {
//			Map.Entry<String, Course> entry=(Map.Entry<String, Course>)it.next();
//			System.out.println(entry.getKey());
//			entry.getValue().print();
//		}
//		cb.Add(Course.toCourse("007,ģ��,100,100,0"));
//		cb.Find("004");
//		cb.Find("001");
//		cb.writeFile();
//	}
}
