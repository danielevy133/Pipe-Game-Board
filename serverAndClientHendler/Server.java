package serverAndClientHendler;

import java.io.IOException;
public interface Server {
	
	public void start (ClientHandler CH) throws IOException;
	public void stop ();
	
}
