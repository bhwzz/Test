import java.io.File;

public class StuFileNode {
	public File MyFile;
	public String Name;
	public void setName(String Name) {
		if(MyFile.exists())
		{
			MyFile.renameTo(Name);
			this.Name = Name;
		}
		else {
			System.out.println("没有找到所属文件");
		}
	}
		
	StuFileNode(Student s){
				
	}
	
}
