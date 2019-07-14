package ProjectServer;

import java.net.Socket;

public class CouInformationImple implements InformationOperate {
	Socket DBsocket;
	
	public CouInformationImple() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CouInformationImple(Socket dBsocket) {
		super();
		DBsocket = dBsocket;
	}

	@Override
	public boolean Add(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean Change(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object Find(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean AddCapacity(String id,int num) {
		return false;
	}
}
