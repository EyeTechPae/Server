package data;

import java.util.Date;

/**
 * Created by germangb on 03/05/16.
 */
public class PlateEntry {

    /** Plate event */
    public enum Event {
        In, Out
    }

    /** License plate */
    public final String plate;

    /** Event */
    public final Event event;

    /** Event date */
    public final Date date;

    public PlateEntry (String plate, Event event) {
        this(plate, event, new Date());
    }

    public PlateEntry (String plate, Event event, Date date) {
        this.plate = plate;
        this.event = event;
        this.date = date;
    }
}
