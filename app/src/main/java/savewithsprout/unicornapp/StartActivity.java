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
import savewithsprout.helpers.MessageHelper;
import savewithsprout.helpers.ValidationHelper;

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
        String phone = ((TextView) findViewById(R.id.phoneInput)).getText().toString();
        String password = ((TextView) findViewById(R.id.passwordInput)).getText().toString();

        int status = ValidationHelper.validateLogin(phone, password);

        if (status == 0){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        } else if (status == 1){
            ((TextView) findViewById(R.id.loginFeedback)).setText(MessageHelper.BLANK_PHONE);
        } else if (status == 2){
            ((TextView) findViewById(R.id.loginFeedback)).setText(MessageHelper.BLANK_PASSWORD);
        } else if (status == 3){
            ((TextView) findViewById(R.id.loginFeedback)).setText(MessageHelper.INVALID_PHONE_NUMBER);
        }
    }

    public void createAccount(View view){
        String firstName = ((TextView) findViewById(R.id.createAccountFirstName)).getText().toString();
        String lastName = ((TextView) findViewById(R.id.createAccountLastName)).getText().toString();
        String email = ((TextView) findViewById(R.id.createAccountEmail)).getText().toString();
        String phone = ((TextView) findViewById(R.id.createAccountPhone)).getText().toString();
        String password = ((TextView) findViewById(R.id.createAccountPassword)).getText().toString();
        String passwordConfirmation = ((TextView) findViewById(R.id.createAccountPasswordConfirm)).getText().toString();

        int status = ValidationHelper.validateCreateAccount(firstName, lastName, email, phone, password, passwordConfirmation);

        if (status == 0){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        } else if (status == 1){
            ((TextView) findViewById(R.id.createAccountfeedback)).setText(MessageHelper.BLANK_FIRST_NAME);
        } else if (status == 2){
            ((TextView) findViewById(R.id.createAccountfeedback)).setText(MessageHelper.BLANK_LAST_NAME);
        } else if (status == 3){
            ((TextView) findViewById(R.id.createAccountfeedback)).setText(MessageHelper.BLANK_EMAIL);
        } else if (status == 4){
            ((TextView) findViewById(R.id.createAccountfeedback)).setText(MessageHelper.BLANK_PHONE);
        } else if (status == 5){
            ((TextView) findViewById(R.id.createAccountfeedback)).setText(MessageHelper.BLANK_PASSWORD);
        } else if (status == 6){
            ((TextView) findViewById(R.id.createAccountfeedback)).setText(MessageHelper.BLANK_PASSWORD_CONFIRM);
        } else if (status == 7){
            ((TextView) findViewById(R.id.createAccountfeedback)).setText(MessageHelper.INVALID_EMAIL_ID);
        } else if (status == 8){
            ((TextView) findViewById(R.id.createAccountfeedback)).setText(MessageHelper.INVALID_PHONE_NUMBER);
        } else if (status == 9){
            int passStatus = ValidationHelper.isPasswordValid(password);

            if (passStatus == ValidationHelper.PASSWORD_INVALID_LENGTH){
                ((TextView) findViewById(R.id.createAccountfeedback)).setText(MessageHelper.INVALID_PASSWORD_LENGTH);
            } else if (passStatus == ValidationHelper.PASSWORD_INVALID_CASE){
                ((TextView) findViewById(R.id.createAccountfeedback)).setText(MessageHelper.INVALID_PASSWORD_CASE);
            } else if (passStatus == ValidationHelper.PASSWORD_INVALID_SYMBOL){
                ((TextView) findViewById(R.id.createAccountfeedback)).setText(MessageHelper.INVALID_PASSWORD_SYMBOL);
            } else if (passStatus == ValidationHelper.PASSWORD_INVALID_DIGIT){
                ((TextView) findViewById(R.id.createAccountfeedback)).setText(MessageHelper.INVALID_PASSWORD_DIGIT);
            }
        } else if (status == 10){
            ((TextView) findViewById(R.id.createAccountfeedback)).setText(MessageHelper.INVALID_PASSWORD_CONFIRM);
        }
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
