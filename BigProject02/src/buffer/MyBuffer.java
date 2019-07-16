package buffer;

public interface MyBuffer
{
	public Object Find(String s) ;   //找到返回true 没找到返回false
	//public boolean change(Object s) ;   //找到返回true 没找到返回false
	
	public Object Change(Object s);  
	
	public boolean Add(Object s);
	
	public boolean Delete(String s);  
		
	
}