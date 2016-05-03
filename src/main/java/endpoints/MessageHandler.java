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
        System.out.println("[MSG] "+socket.getInetAddress()+" connected.");

        // set timeout
        socket.setSoTimeout(1000);

        try {
            // open input stream
            InputStream is = socket.getInputStream();

            // read message type
            int type = is.read();

            if (type == 1 || type == 2 || type == 3) {
                // read message
                System.out.println("[MSG] Reading new message");
                byte[] buffer = new byte[1024];
                int read = is.read(buffer);
                String str = new String(buffer, 0, read);

                // update message
                Message.Type tp = Message.Type.values()[type];
                Message msg = new Message(tp, str);
                data.setMessage(msg);
                System.out.println("[MSG] Message set to \""+str+"\" ("+tp+")");
            } else {
                // delete message
                data.setMessage(null);
                System.out.println("[MSG] The message has been deleted");
            }
        } catch (SocketTimeoutException e) {
            System.out.println("[MSG] Connection timed out");
            //e.printStackTrace();
        } finally {
            // close connection
            socket.close();
            System.out.println("[MSG] Connection closed");
        }
    }
}
