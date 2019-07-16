package filetool;

import java.util.Map;

import EntityClass.*;

public interface tool {
	public void writeback(Student s) throws Exception;
	public void getbook() throws Exception;
	public void add(Object k) throws Exception;
	public void delete(String s) throws Exception;
	public void delete(String s,String s2) throws Exception;
	public String findinfile (String f)throws Exception;
	public Object get(String stuid) throws Exception;
}