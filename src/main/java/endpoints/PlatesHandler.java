package endpoints;

import data.Database;
import data.PlateEntry;
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
        System.out.println("[PLA] License plates connected");

        byte[] buffer = new byte[128];
        while (true) {

            try {
                // get event
                int event = socket.getInputStream().read();

                // read license plate
                int read = socket.getInputStream().read(buffer);

                // update database
                PlateEntry.Event ev = event == 0 ? PlateEntry.Event.In : PlateEntry.Event.Out;
                String plate = new String(buffer, 0, read);

                PlateEntry entry = new PlateEntry(plate, ev);
                data.addPlateEntry(entry);

                System.out.println("[PLA] Updated "+plate+" "+ev);
            } catch (Exception e) {
                //e.printStackTrace();
                break;
            }
        }

        System.out.println("[PLA] License plates disconnected");
    }
}
