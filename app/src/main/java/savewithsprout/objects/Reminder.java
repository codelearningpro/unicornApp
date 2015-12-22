package savewithsprout.objects;

/**
 * Created by menushkaweeratunga on 15-12-06.
 */
public class Reminder {
    public boolean byEmail;
    public boolean byText;

    public int day;
    public int hour, minute;

    public int remindEvery;

    public static final int WEEKLY = 0;
    public static final int BIWEEKLY = 1;
    public static final int MONTHLY = 2;

    public Reminder(boolean byEmail, boolean byText, int day, int hour, int minute, int remindEvery){
        this.byEmail = byEmail;
        this.byText = byText;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.remindEvery = remindEvery;
    }

}
