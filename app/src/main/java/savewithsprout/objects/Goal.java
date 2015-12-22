package savewithsprout.objects;

/**
 * Created by menushkaweeratunga on 15-12-06.
 */
public class Goal {
    public String name;

    float amount;
    int term;
    float withdrawFee;

    Reminder[] reminders;

    public Goal(String name, float amount, int term, float withdrawFee, Reminder[] reminders){
        this.name = name;
        this.amount = amount;
        this.term = term;
        this.withdrawFee = withdrawFee;
        this.reminders = reminders;
    }

    public float getAmount(){
        return amount;
    }

    public int getTerm(){
        return term;
    }
}