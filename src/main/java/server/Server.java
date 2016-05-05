package server;

import data.Database;
import endpoints.*;

import java.io.IOException;

/**
 * Created by germangb on 03/05/16.
 */
public class Server {

    public static void main (String[] args) throws IOException {
        // shared resources
        Database data = new Database();

        // Set up end points
        EndPoint pla = new EndPoint("PLA", 3332, new PlatesHandler(data));
        EndPoint xml = new EndPoint("XML", 3333, new XmlHandler(data));
        EndPoint app = new EndPoint("APP", 3334, new AppHandler(data));
        EndPoint msg = new EndPoint("MSG", 3335, new MessageHandler(data));
        EndPoint pro = new EndPoint("PRO", 3336, new ProHandler());
        EndPoint cen = new EndPoint("CEN", 3337, new CentersHandler(data));

        // start threads
        pla.start();
        xml.start();
        app.start();
        msg.start();
        pro.start();
        cen.start();
    }
}
