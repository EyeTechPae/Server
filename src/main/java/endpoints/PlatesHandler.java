package endpoints;

import data.Database;
import server.ConnectionHandler;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by germangb on 03/05/16.
 */
public class PlatesHandler implements ConnectionHandler {

    /** Shared database */
    private Database data;

    public PlatesHandler(Database data) {
        this.data = data;
    }

    public void onConnected(Socket socket) throws IOException {
        // TODO read license plates
    }
}
