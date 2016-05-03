package server;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by germangb on 03/05/16.
 */
public interface ConnectionHandler {

    /**
     * Handle a socket connection
     * @param socket socket connection
     * @throws IOException any IO error from the socket
     */
    void onConnected (Socket socket) throws IOException;
}
