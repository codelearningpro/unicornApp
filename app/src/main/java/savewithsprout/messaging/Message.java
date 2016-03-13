package savewithsprout.messaging;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by menushkaweeratunga on 16-03-13.
 */
public class Message {
    String username;
    String message;
    Date timestamp;

    Message(String username, String message, Date timestamp){
        this.username = username;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getUsername(){
        return username;
    }

    public String getMessage(){
        return message;
    }

    public Date getTimestamp(){
        return timestamp;
    }

    public String getFormatedTimestamp(){
        if (new Date().getTime() - timestamp.getTime() > 86400000){
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            return format.format(timestamp);
        } else {
            SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
            return format.format(timestamp);
        }
    }
}
