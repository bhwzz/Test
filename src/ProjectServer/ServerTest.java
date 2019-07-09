package ProjectServer;

import java.io.IOException;

public class ServerTest {
	public static void main(String[] args) throws IOException {
		IOStrategy ios=new ThreadPoolSupport(new ChooseProtocol());
		NwServer ns=new NwServer(4444,ios);
	}
}
