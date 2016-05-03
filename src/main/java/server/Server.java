package server;

import data.Database;
import endpoints.AppHandler;
import endpoints.MessageHandler;
import endpoints.PlatesHandler;
import endpoints.XmlHandler;

import java.io.IOException;

/**
 * Created by germangb on 03/05/16.
 */
public class Server {

    public static void main (String[] args) throws IOException {
        // shared resources
        Database data = new Database();

        // Set up end points
        EndPoint pro = new EndPoint(3332, new PlatesHandler(data));
        EndPoint xml = new EndPoint(3333, new XmlHandler(data));
        EndPoint app = new EndPoint(3334, new AppHandler(data));
        EndPoint msg = new EndPoint(3335, new MessageHandler(data));

        // start threads
        pro.start();
        xml.start();
        app.start();
        msg.start();
    }
}
