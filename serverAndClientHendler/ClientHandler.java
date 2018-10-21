package serverAndClientHendler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ClientHandler {
	
	public void handleClient(InputStream inFromServer,OutputStream outToServer)throws IOException;

}
