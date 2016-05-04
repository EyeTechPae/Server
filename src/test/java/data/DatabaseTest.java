package data;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by germangb on 04/05/16.
 */
public class DatabaseTest {
    @Test
    public void setMessage() throws Exception {
        Database data = new Database();

        // test some message
        Message msg = new Message(Message.Type.Info, "Information message");
        data.setMessage(msg);

        // test data
        Message que = data.getMessage();
        assert que == msg;
    }

    @Test
    public void getMessage() throws Exception {
        Database data = new Database();

        // test some message
        Message msg = new Message(Message.Type.Alert, "Test message");
        data.setMessage(msg);

        // test data
        Message que = data.getMessage();
        assert que.date == msg.date;
        assert que.text.equals(msg.text);
        assert que.type == msg.type;


        // no msg
        data.setMessage(null);
        assert data.getMessage() == null;
    }

    @Test
    public void addPlateEntry() throws Exception {
        Database data = new Database();
        assert data.getInside() == 0;

        data.addPlateEntry(new PlateEntry("0000FGH", PlateEntry.Event.In));
        assert data.getInside() == 1;

        data.addPlateEntry(new PlateEntry("1432THC", PlateEntry.Event.In));
        assert data.getInside() == 2;

        data.addPlateEntry(new PlateEntry("1432THC", PlateEntry.Event.Out));
        assert data.getInside() == 1;

        data.addPlateEntry(new PlateEntry("0000FGH", PlateEntry.Event.Out));
        assert data.getInside() == 0;
    }

    @Test
    public void writeXmlData() throws Exception {

    }

    @Test
    public void writeAppData() throws Exception {

    }

}