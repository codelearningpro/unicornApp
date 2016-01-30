package savewithsprout.objects;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by menushkaweeratunga on 15-12-06.
 */
public class Goal {
    public String name;

    float amount;
    int term;
    public Date start, end;
    float withdrawFee;

    public ArrayList<Transaction> transactions;
    Reminder[] reminders;

    //UNUSABLE - WILL REMOVE LATER
    public Goal(String name, float amount, int term, float withdrawFee, Reminder[] reminders){
        this.name = name;
        this.amount = amount;
        this.term = term;
        this.withdrawFee = withdrawFee;
        this.reminders = reminders;
    }

    public Goal(String name, float amount, Date start, Date end, float withdrawFee, ArrayList<Transaction> transactions, Reminder[] reminders){
        this.name = name;
        this.amount = amount;
        this.start = start;
        this.end = end;
        this.withdrawFee = withdrawFee;
        this.transactions = transactions;
        this.reminders = reminders;
    }

    public float getAmount(){
        return amount;
    }

    public int getTerm(){
        return term;
    }
}