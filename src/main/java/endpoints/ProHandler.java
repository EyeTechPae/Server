package endpoints;

import server.ConnectionHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by germangb on 05/05/16.
 */
public class ProHandler implements ConnectionHandler {

    public void onConnected(Socket socket) throws IOException {
        // read command
        int cmd = socket.getInputStream().read();
        System.out.println("[PRO] Client connected");

        if (cmd == 0) {
            System.out.println("[PRO] Client requests data");
            OutputStream os = socket.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < 43; ++i)
                str.append(Math.random() > 0.5 ? '0' : '1');
            str.append('\0');
            writer.write(str.toString());
            writer.flush();
        }

        socket.close();
        System.out.println("[PRO] Disconnected client");
    }
}
