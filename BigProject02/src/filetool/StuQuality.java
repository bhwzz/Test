package filetool;

import EntityClass.Student;

public class StuQuality {
	private String id;
	private String name;
	private int classroom;
	private char gender;
	StuQuality(String id){
		String [] s=new String[4];
		s=id.split(",");
		//System.out.println(s[0]+s[1]+Integer.parseInt(s[2])+s[3].charAt(0));
	//	return new Student(s[0],s[1],Integer.parseInt(s[2]),s[3].charAt(0));
		
		this.id = s[0];
		this.name=s[1];
		classroom=Integer.parseInt(s[2]);
		gender=s[3].charAt(0);
	}
	StuQuality(String id,String name,int classroom,char gender){
		this.id = id;
		this.name=name;
		this.classroom = classroom;
		this.gender = gender;
	}
	StuQuality(Student s){
		this.id = s.getId();
		this.name= s.getName();
		this.classroom = s.getClassroom();
		this.gender = s.getGender();
	}
	
	public String get(String name,int classroom,char gender) {
		this.name=name;
		this.classroom = classroom;
		this.gender = gender;
		String classString = String.format("%2d", classroom).replace(" ", "0");
		String string = id+","+name+","+classString+","+gender;
		return string;
	}
	public String get() {
		//String string = id+","+name+","+classroom+","+gender;
		String classString = String.format("%2d", classroom).replace(" ", "0");
		if(name.length()==2)
		{
			name="  "+name;
		}
		String string = id+","+name+","+classString+","+gender;
		return string;
	}
}
