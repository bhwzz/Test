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
			System.out.println("û���ҵ������ļ�");
		}
	}
		
	StuFileNode(Student s){
				
	}
	
}
