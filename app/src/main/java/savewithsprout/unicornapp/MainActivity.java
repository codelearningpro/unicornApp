package savewithsprout.unicornapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private int deposit = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout holder = (LinearLayout) findViewById(R.id.info_holder);
        String[] titles = {"Last Deposit", "Next Deposit", "Week Remaining"};
        String[] amount = {"$34", "$29", "5"};
        for (int i = 0; i < 3; i++){
            LinearLayout view = (LinearLayout) getLayoutInflater().inflate(R.layout.info, null);
            ((TextView)view.findViewById(R.id.title)).setText(titles[i]);
            ((TextView)view.findViewById(R.id.amount)).setText(amount[i]);
            holder.addView(view);
        }

        Typeface regular = Typeface.createFromAsset(getAssets(),  "fonts/Montserrat-Regular.ttf");
        Typeface light = Typeface.createFromAsset(getAssets(),  "fonts/Montserrat-Light.ttf");
        Typeface hairline = Typeface.createFromAsset(getAssets(),  "fonts/Montserrat-Hairline.ttf");

        ((TextView) findViewById(R.id.mainGoalLeft)).setTypeface(hairline);
        ((TextView) findViewById(R.id.mainGoalName)).setTypeface(light);
        ((TextView) findViewById(R.id.mainGoalRight)).setTypeface(hairline);
        ((TextView) findViewById(R.id.mainButtonAdd)).setTypeface(hairline);
        ((TextView) findViewById(R.id.mainButtonDeposit)).setTypeface(hairline);
        ((TextView) findViewById(R.id.mainButtonSub)).setTypeface(hairline);

    }

    public void increaseDeposit(View view){
        deposit += 1;
        updateDeposit();
    }

    public void decreaseDeposit(View view){
        deposit -= 1;
        updateDeposit();
    }

    private void updateDeposit(){
        TextView despositView = (TextView)findViewById(R.id.mainButtonDeposit);
        despositView.setText("Deposit $" + deposit);
    }

    public void openSettings(View view){
        System.out.println("Test");
    }
}
