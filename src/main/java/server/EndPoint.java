package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by germangb on 03/05/16.
 */
public class EndPoint  extends Thread {

    /** server.Server */
    private ServerSocket server;

    /** connection handler */
    private ConnectionHandler handler;

    /** End point name */
    private String name;

    /**
     *
     * @param port Listening port
     * @param handler Socket connection handler
     * @throws IOException error initializing server.Server
     */
    public EndPoint (String name, int port, ConnectionHandler handler) throws IOException {
        this.server = new ServerSocket(port);
        this.handler = handler;
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println("["+name+"] Listening on "+server.getLocalSocketAddress());
        while (true) {
            try {
                Socket socket = server.accept();
                handler.onConnected(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
