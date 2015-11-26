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
public class CreateAccountFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_createaccount, container, false);

        Typeface regular = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Montserrat-Regular.ttf");
        Typeface light = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Montserrat-Light.ttf");
        Typeface hairline = Typeface.createFromAsset(getActivity().getAssets(),  "fonts/Montserrat-Hairline.ttf");

        ((TextView)view.findViewById(R.id.createAccountText)).setTypeface(light);
        ((TextView)view.findViewById(R.id.createAccountFirstName)).setTypeface(light);
        ((TextView)view.findViewById(R.id.createAccountLastName)).setTypeface(light);
        ((TextView)view.findViewById(R.id.createAccountEmail)).setTypeface(light);
        ((TextView)view.findViewById(R.id.createAccountPhone)).setTypeface(light);
        ((TextView)view.findViewById(R.id.createAccountPassword)).setTypeface(light);
        ((TextView)view.findViewById(R.id.createAccountPasswordConfirm)).setTypeface(light);
        ((TextView)view.findViewById(R.id.createAccountButton)).setTypeface(regular);

        return view;
    }
}
