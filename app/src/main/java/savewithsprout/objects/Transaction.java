package savewithsprout.objects;

/**
 * Created by menushkaweeratunga on 15-12-23.
 */
public class Transaction {

    public float amount;
    public int date;

    public Transaction(float amount, int date){
        this.amount = amount;
        this.date = date;
    }

    public int getTime(){
        return date;
    }
}
