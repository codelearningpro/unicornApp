package savewithsprout.objects;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by menushkaweeratunga on 15-12-23.
 */
public class Transaction {

    public float amount;
    public Date date;
    public Calendar calendar;

    public Transaction(float amount, Date date){
        this.amount = amount;
        this.date = date;
        this.calendar = Calendar.getInstance();
        this.calendar.setTime(this.date);
    }

    public Date getTime(){
        return date;
    }

    public long getMiliseconds(){
        return calendar.getTimeInMillis();
    }
}
