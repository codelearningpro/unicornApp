package savewithsprout.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import savewithsprout.unicornapp.R;

/**
 * Created by tmwee on 11/24/2015.
 */
public class LoginFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        Typeface regular = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Montserrat-Regular.ttf");
        Typeface light = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Montserrat-Light.ttf");
        Typeface hairline = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Montserrat-Hairline.ttf");


        ((TextView)view.findViewById(R.id.emailInput)).setTypeface(light);
        ((TextView)view.findViewById(R.id.phoneInput)).setTypeface(light);
        ((TextView)view.findViewById(R.id.passwordInput)).setTypeface(light);
        ((TextView)view.findViewById(R.id.logInButton)).setTypeface(regular);
        ((TextView)view.findViewById(R.id.forgotPassButton)).setTypeface(hairline);
        ((TextView)view.findViewById(R.id.signUpButton)).setTypeface(regular);
        ((TextView)view.findViewById(R.id.loginFeedback)).setTypeface(regular);

        return view;
    }
}
