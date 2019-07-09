package student;

public class Student{
	public String stuID, stuName;
	
	public String get_stuID() {
		return stuID;
	}
	public String get_name() {
		return stuName;
	}
	public void setStu(String s)   //从字符串读取学生信息
	//假设字符串为201792417-赵思洋/.
	{
		stuID=s.substring(0, 9); 
		stuName=s.substring(s.indexOf("-")+1,s.indexOf("/"));
		
	}
	public static void main(String[] args) 
	{
		Student s1 = new Student();
		s1.setStu("201792417-赵思洋/.");
		System.out.println(s1.stuID);
		System.out.println(s1.stuName);
		
	}
	
}