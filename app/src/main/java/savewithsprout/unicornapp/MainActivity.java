package savewithsprout.unicornapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import savewithsprout.mchart.DepositChart;
import savewithsprout.objects.Goal;
import savewithsprout.objects.Reminder;

public class MainActivity extends Activity {

    private int deposit = 23;

    Goal goal = new Goal("Trip to Disneyland", 1200, 28, 100, new Reminder[]{});

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Changes Status bar but requires API 21

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.green));

        */

        //((DepositChart)(findViewById(R.id.mainGraph))).setGoal(goal);
        ((TextView) findViewById(R.id.mainGoalName)).setText(goal.name);

        LinearLayout holder = (LinearLayout) findViewById(R.id.info_holder);
        RelativeLayout view = (RelativeLayout) getLayoutInflater().inflate(R.layout.goal_info, null);
        holder.addView(view);

        Typeface regular = Typeface.createFromAsset(getAssets(),  "fonts/Montserrat-Regular.ttf");
        Typeface light = Typeface.createFromAsset(getAssets(),  "fonts/Montserrat-Light.ttf");
        Typeface hairline = Typeface.createFromAsset(getAssets(),  "fonts/Montserrat-Hairline.ttf");

        ((TextView) findViewById(R.id.mainGoalLeft)).setTypeface(hairline);
        ((TextView) findViewById(R.id.mainGoalName)).setTypeface(light);
        ((TextView) findViewById(R.id.mainGoalRight)).setTypeface(hairline);


        ((TextView) findViewById(R.id.mainButtonAdd)).setTypeface(hairline, Typeface.BOLD);
        ((TextView) findViewById(R.id.mainButtonDeposit)).setTypeface(hairline, Typeface.BOLD);
        ((TextView) findViewById(R.id.mainButtonSub)).setTypeface(hairline, Typeface.BOLD);

        ((TextView) findViewById(R.id.mainButtonAdd)).setTypeface(hairline, Typeface.BOLD);
        ((TextView) findViewById(R.id.mainButtonDeposit)).setTypeface(hairline, Typeface.BOLD);
        ((TextView) findViewById(R.id.mainButtonSub)).setTypeface(hairline, Typeface.BOLD);

        ((TextView) findViewById(R.id.infoGoalText)).setTypeface(light);
        ((TextView) findViewById(R.id.infoGoalName)).setTypeface(light, Typeface.BOLD);
        ((TextView) findViewById(R.id.infoTargetText)).setTypeface(light);
        ((TextView) findViewById(R.id.infoTargetDate)).setTypeface(light, Typeface.BOLD);
        ((TextView) findViewById(R.id.infoWithdrawText)).setTypeface(light);
        ((TextView) findViewById(R.id.infoWithdrawAmount)).setTypeface(light, Typeface.BOLD);

        ((TextView) findViewById(R.id.infoLastDepositText)).setTypeface(light);
        ((TextView) findViewById(R.id.infoLastDepositAmount)).setTypeface(light, Typeface.BOLD);
        ((TextView) findViewById(R.id.infoLastDepositSub)).setTypeface(regular, Typeface.ITALIC);
        ((TextView) findViewById(R.id.infoNextDepositText)).setTypeface(light);
        ((TextView) findViewById(R.id.infoNextDepositAmount)).setTypeface(light, Typeface.BOLD);
        ((TextView) findViewById(R.id.infoNextDepositSub)).setTypeface(regular, Typeface.ITALIC);
        ((TextView) findViewById(R.id.infoWeeksRemainingText)).setTypeface(light);
        ((TextView) findViewById(R.id.infoWeeksRemainingAmount)).setTypeface(light, Typeface.BOLD);
        ((TextView) findViewById(R.id.infoWeeksRemainingSub)).setTypeface(regular, Typeface.ITALIC);
        ((TextView) findViewById(R.id.infoToReachGoalText)).setTypeface(light);
        ((TextView) findViewById(R.id.infoToReachGoalAmount)).setTypeface(light, Typeface.BOLD);
        ((TextView) findViewById(R.id.infoToReachGoalSub)).setTypeface(regular, Typeface.ITALIC);
        ((TextView) findViewById(R.id.infoYourPaceText)).setTypeface(light);
        ((TextView) findViewById(R.id.infoYourPaceAmount)).setTypeface(light, Typeface.BOLD);
        ((TextView) findViewById(R.id.infoYourPaceSub)).setTypeface(regular, Typeface.ITALIC);

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
