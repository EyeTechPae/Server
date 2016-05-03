package endpoints;

import data.Database;
import data.Message;
import server.ConnectionHandler;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * Created by germangb on 03/05/16.
 */
public class MessageHandler implements ConnectionHandler {

    private Database data;

    public MessageHandler (Database data) {
        this.data = data;
    }

    public void onConnected(Socket socket) throws IOException {
        // set timeout
        socket.setSoTimeout(1000);

        try {
            // open input stream
            InputStream is = socket.getInputStream();

            // read message type
            int type = is.read();

            if (type == 1 || type == 2 || type == 3) {
                // read message
                byte[] buffer = new byte[1024];
                int read = is.read(buffer);
                String str = new String(buffer, 0, read);

                // update message
                Message.Type tp = Message.Type.values()[type];
                Message msg = new Message(tp, str);
                data.setMessage(msg);
            } else {
                // delete message
                data.setMessage(null);
            }
        } catch (SocketTimeoutException e) {
            System.out.println("[MESSAGE] Connection timed out");
            //e.printStackTrace();
        } finally {
            // close connection
            socket.close();
        }
    }
}
