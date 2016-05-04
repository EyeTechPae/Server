package data;

import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by germangb on 03/05/16.
 */
public class Database {

    /** Total number of parking places */
    private static final int TOTAL_PLACES = 43;

    /** List of database entries */
    private List<PlateEntry> entries;

    /** Total number of cars inside */
    private int inside = 0;

    /** Broadcast message */
    private Message message;

    /**
     * Create a database
     */
    public Database () {
        this.entries = new ArrayList<PlateEntry>();
        this.message = new Message(Message.Type.Info, "Hello world!");
    }

    /**
     * Set or remove broadcast message
     * @param message message to be sent. null to remove
     */
    public synchronized void setMessage (Message message) {
        this.message = message;
    }

    /**
     * Return the message
     * @return current message, null if none is set
     */
    public synchronized Message getMessage () {
        return message;
    }

    /**
     * Number of cars inside
     * @return
     */
    public synchronized int getInside () {
        return inside;
    }

    /**
     * Add a entry to the license plate stuff
     * @param entry
     */
    public synchronized void addPlateEntry (PlateEntry entry) {
        entries.add(entry);

        // update total cars
        if (entry.event == PlateEntry.Event.In) inside++;
        else inside--;
    }

    /**
     * Dump info in XML format
     * @param writer output
     * @throws IOException
     */
    public synchronized void writeXmlData (Writer writer) throws IOException {
        // date format
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        // XML header
        writer.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n");

        // dump entries
        writer.write("<Register>\n");
        for (PlateEntry e : entries) {
            writer.write("<Action>\n");
            writer.write("<LicensePlate>"+e.plate+"</LicensePlate>\n");
            writer.write("<DateTime>"+dateFormat.format(e.date)+"</DateTime>\n");
            writer.write("<INOUT>"+e.event+"</INOUT>\n");
            writer.write("</Action>\n");
        }

        // end XML
        writer.write("</Register>\n");
    }

    /**
     * Output information for the app
     * @param writer where the data will be written to
     * @throws IOException
     */
    public synchronized void writeAppData (Writer writer) throws IOException {
        // begin json
        writer.write("{\n");

        String mType = "";
        String msg = "";

        if (message != null) {
            msg = message.text;
            mType = message.type.jsonField;
        }

        // write message
        writer.write("\"mType\": \""+mType+"\",\n");
        writer.write("\"message\": \""+msg+"\",\n");

        // write places
        writer.write("\"freePlaces\": "+(TOTAL_PLACES - inside)+",\n");
        writer.write("\"totalPlaces\": "+TOTAL_PLACES+"\n");

        // end json
        writer.write("}\n");
    }
}
