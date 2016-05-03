package endpoints;

import data.Database;
import server.ConnectionHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by germangb on 03/05/16.
 */
public class AppHandler implements ConnectionHandler {

    /** Database handle */
    private Database data;

    public AppHandler (Database data) {
        this.data = data;
    }

    public void onConnected(Socket socket) throws IOException {
        System.out.println("[APP] "+socket.getInetAddress()+":"+socket.getPort()+" connected.");

        // open output stream
        OutputStream os = socket.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

        // write json file
        System.out.println("[APP] Sending data");
        data.writeAppData(writer);
        writer.flush();

        // close on finished
        socket.close();
        System.out.println("[APP] Connection closed");
    }
}
