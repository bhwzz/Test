package buffer;

public interface MyBuffer
{
	public Object Find(String s) ;   //�ҵ�����true û�ҵ�����false
	//public boolean change(Object s) ;   //�ҵ�����true û�ҵ�����false
	
	public Object Change(Object s);  
	
	public boolean Add(Object s);
	
	public boolean Delete(String s);  
		
	
}