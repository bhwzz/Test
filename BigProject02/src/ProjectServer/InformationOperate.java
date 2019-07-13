package ProjectServer;

public interface InformationOperate {
	public boolean Add(Object o);
	public boolean Delete(String id);
	public boolean Change(Object o);
	public Object Find(String id);
}
