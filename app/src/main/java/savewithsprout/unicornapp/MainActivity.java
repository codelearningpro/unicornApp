package savewithsprout.unicornapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;


import java.util.ArrayList;

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
        TextView despositView = (TextView)findViewById(R.id.deposit);
        despositView.setText("Deposit $" + deposit);
    }

    public void openSettings(View view){
        System.out.println("Test");
    }
}
