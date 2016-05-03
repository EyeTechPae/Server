package endpoints;

import data.Database;
import server.ConnectionHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * Created by germangb on 03/05/16.
 */
public class XmlHandler implements ConnectionHandler {

    /** Database handle */
    private Database data;

    public XmlHandler(Database data) {
        this.data = data;
    }

    public void onConnected(Socket socket) throws IOException {
        System.out.println("[XML] "+socket.getInetAddress()+":"+socket.getPort()+" connected.");

        // open output stream
        OutputStream os = socket.getOutputStream();
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

        // write xml file
        System.out.println("[XML] Sending data");
        data.writeXmlData(writer);
        writer.flush();

        // close on finished
        socket.close();
        System.out.println("[XML] Connection closed");
    }
}
