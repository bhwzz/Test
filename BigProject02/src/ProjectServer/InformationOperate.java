package ProjectServer;

public interface InformationOperate {
	public boolean Add(Object o);
	public int Delete(String id);
	public boolean Change(String s);
	public Object Find(String id);
}
