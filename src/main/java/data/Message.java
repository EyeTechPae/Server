package data;

import java.util.Date;

/**
 * Created by germangb on 03/05/16.
 */
public class Message {

    /** data.Message types */
    public enum Type {
        Info("Info"), Alert("Alert"), Server("Server");

        /** value in the json file */
        public final String  jsonField;

        Type (String field) {
            jsonField = field;
        }
    }

    /** data.Message type */
    public final Type type;

    /** data.Message text */
    public final String text;

    /** MEssage date */
    public final Date date;

    public Message (Type type, String text) {
        this(type, text, new Date());
    }

    public Message (Type type, String text, Date date) {
        this.type = type;
        this.text = text;
        this.date = date;
    }
}
