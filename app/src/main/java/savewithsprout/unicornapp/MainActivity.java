package savewithsprout.unicornapp;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;

import savewithsprout.fragments.CreateAccountFragment;
import savewithsprout.fragments.LoginFragment;
import savewithsprout.fragments.MainAdvisorFragment;
import savewithsprout.fragments.MainBlankFragment;
import savewithsprout.fragments.MainNewGoalFragment;
import savewithsprout.fragments.MainNewReminderFragment;
import savewithsprout.fragments.MainWithdrawlFragment;
import savewithsprout.mchart.DepositChart;
import savewithsprout.objects.Transaction;
import savewithsprout.objects.Goal;
import savewithsprout.objects.Reminder;

public class MainActivity extends FragmentActivity {

    Handler handler = new Handler();
    Runnable holdIncrease = new Runnable() {
        public void run() {
            deposit += 1;
            updateDeposit();
            handler.post(this);
        }
    };

    Runnable holdDecrease = new Runnable() {
        public void run() {
            deposit -= 1;
            updateDeposit();
            handler.post(this);
        }
    };

    DepositChart depositChart;

    private int deposit = 100;

    ArrayList<Goal> goals = new ArrayList<Goal>() {{
        add(new Goal("Trip to Disneyland", 1200, new GregorianCalendar(2016, 0, 24).getTime(), new GregorianCalendar(2016, 1, 16).getTime(), 100, new ArrayList<Transaction>(), new Reminder[]{}));
        add(new Goal("Collage Fund Year 1", 5000, new GregorianCalendar(2016, 0, 13).getTime(), new GregorianCalendar(2016, 1, 29).getTime(), 100, new ArrayList<Transaction>(), new Reminder[]{}));
        add(new Goal("New Car", 14500, new GregorianCalendar(2016, 0, 02).getTime(), new GregorianCalendar(2016, 3, 25).getTime(), 200, new ArrayList<Transaction>(), new Reminder[]{}));
    }};
    int openedGoal = 0;

    int page = 0;

    RelativeLayout profileImage;

    int bottomMargin = 850;

    Animation moveProfileImage = new Animation() {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) profileImage.getLayoutParams();
            params.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10 * interpolatedTime, getResources().getDisplayMetrics());
            params.bottomMargin = (int)(bottomMargin * interpolatedTime);

            params.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70 + 30 * interpolatedTime, getResources().getDisplayMetrics());
            params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 70 + 30 * interpolatedTime, getResources().getDisplayMetrics());

            profileImage.setLayoutParams(params);
        }
    };

    Animation moveBackProfileImage = new Animation() {
        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) profileImage.getLayoutParams();
            params.rightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10 - 10 * interpolatedTime, getResources().getDisplayMetrics());
            params.bottomMargin = (int)(bottomMargin - bottomMargin * interpolatedTime);

            params.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100 - 30 * interpolatedTime, getResources().getDisplayMetrics());
            params.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100 - 30 * interpolatedTime, getResources().getDisplayMetrics());

            profileImage.setLayoutParams(params);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21){
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.green));
        }

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        ((TextView) findViewById(R.id.mainGoalName)).setText(goals.get(openedGoal).name);
        ((DepositChart)(findViewById(R.id.mainGraph))).setGoal(goals.get(openedGoal));

        LinearLayout holder = (LinearLayout) findViewById(R.id.info_holder);
        RelativeLayout view = (RelativeLayout) getLayoutInflater().inflate(R.layout.goal_info, null);
        holder.addView(view);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.mainFragment, new MainBlankFragment());
        ft.commit();

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

        profileImage = (RelativeLayout) findViewById(R.id.mainProfileImage);
        depositChart = (DepositChart) findViewById(R.id.mainGraph);

        updateDeposit();

        ((TextView) findViewById(R.id.mainButtonAdd)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                    handler.postDelayed(holdIncrease, 500);
                if((event.getAction() == MotionEvent.ACTION_MOVE)||(event.getAction() == MotionEvent.ACTION_UP))
                    handler.removeCallbacks(holdIncrease);
                return false;
            }
        });

        ((TextView) findViewById(R.id.mainButtonSub)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                    handler.postDelayed(holdDecrease, 500);
                if((event.getAction() == MotionEvent.ACTION_MOVE)||(event.getAction() == MotionEvent.ACTION_UP))
                    handler.removeCallbacks(holdDecrease);
                return false;
            }
        });
        //((TextView) findViewById(R.id.mainButtonSub)).setOnTouchListener(listener);


    }

    public void changeGoalLeft(View view){
        if (openedGoal > 0){
            openedGoal -= 1;
        }
        ((TextView) findViewById(R.id.mainGoalName)).setText(goals.get(openedGoal).name);
        ((DepositChart)(findViewById(R.id.mainGraph))).setGoal(goals.get(openedGoal));
        ((DepositChart)(findViewById(R.id.mainGraph))).invalidate();
    }

    public void changeGoalRight(View view){
        if (openedGoal < goals.size() - 1){
            openedGoal += 1;
        }
        ((TextView) findViewById(R.id.mainGoalName)).setText(goals.get(openedGoal).name);
        ((DepositChart)(findViewById(R.id.mainGraph))).setGoal(goals.get(openedGoal));
        ((DepositChart)(findViewById(R.id.mainGraph))).invalidate();
    }

    public void increaseDeposit(View view){
        deposit += 1;
        updateDeposit();
    }

    public void decreaseDeposit(View view) {
        deposit -= 1;
        updateDeposit();
    }

    public void deposit(View view){
        goals.get(openedGoal).transactions.add(new Transaction(deposit, new Date()));
        depositChart.setGoal(goals.get(openedGoal));
        depositChart.invalidate();
    }

    private void updateDeposit(){
        TextView despositView = (TextView)findViewById(R.id.mainButtonDeposit);
        despositView.setText("Deposit $" + deposit);
    }

    public void openSettings(View view){
        System.out.println("Test");
    }

    public void openNewGoal(View view){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.mainFragment, new MainNewGoalFragment());
        ft.commit();

        page = 2;
    }

    public void openWidthdrawlGoal(View view){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.mainFragment, new MainWithdrawlFragment());
        ft.commit();

        page = 3;
    }

    public void openNewReminder(View view){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.mainFragment, new MainNewReminderFragment());
        ft.commit();

        page = 4;
    }

    public void openAdvisor(View view){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.mainAdvisorFragment, new MainAdvisorFragment());
        ft.commit();

        moveProfileImage.setDuration(300);
        profileImage.startAnimation(moveProfileImage);

        profileImage.setClickable(false);

        page = 5;
    }

    public void closeMainFragment(View view){
        page = 0;

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.mainFragment, new MainBlankFragment());
        ft.commit();
    }

    public void closeMainAdvisorFragment(View view){
        page = 0;

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
        ft.replace(R.id.mainAdvisorFragment, new MainBlankFragment());
        ft.commit();

        moveBackProfileImage.setDuration(300);
        profileImage.startAnimation(moveBackProfileImage);

        profileImage.setClickable(true);
    }

    @Override
    public void onBackPressed() {
        if (page == 0){
            super.onBackPressed();
        } else {
            page = 0;

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
            ft.replace(R.id.mainFragment, new MainBlankFragment());
            ft.commit();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView = getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }


}
