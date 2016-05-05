package endpoints;

import data.Database;
import server.ConnectionHandler;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by germangb on 05/05/16.
 */
public class CentersHandler implements ConnectionHandler {

    private Database data;

    public CentersHandler (Database data) {
        this.data = data;
    }

    public void onConnected(Socket socket) throws IOException {
        System.out.println("[CEN] Client connected (persistant)");
        InputStream is = socket.getInputStream();

        boolean running = true;
        while (running) {
            // read array length
            int length = socket.getInputStream().read();
            if (length > 0) {
                //System.out.println("[CEN] Reading " + length + " length array");

                for (int i = 0; i < length; ++i) {
                    int x = is.read() << 8 | is.read();
                    //System.out.println("[CEN] x = " + x);
                }
            } else {
                running = false;
            }
        }

        socket.close();
        System.out.println("[CEN] Client disconnected");
    }
}
