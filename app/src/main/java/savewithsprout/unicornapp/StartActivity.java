package savewithsprout.unicornapp;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import savewithsprout.fragments.CreateAccountFragment;
import savewithsprout.fragments.LoginFragment;

public class StartActivity extends FragmentActivity {

    int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment, new LoginFragment());
        ft.commit();
    }

    public void moveToCreateAccount(View view){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left);
        ft.replace(R.id.fragment, new CreateAccountFragment());
        ft.commit();

        page = 1;
    }

    public void signIn(View view){
        //Check for login info

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    public void createAccount(View view){
        //Create account with info

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    public void onBackPressed() {
        if (page == 0){
            super.onBackPressed();
        } else if (page == 1){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_right);
            ft.replace(R.id.fragment, new LoginFragment());
            ft.commit();

            page = 0;
        }
    }
}
