package savewithsprout.helpers;

import android.content.res.Resources;

import savewithsprout.unicornapp.R;

/**
 * Created by menushkaweeratunga on 15-12-21.
 */
public class WebAPIHelper {
    public static final String SIGNUP = "http://ec2-52-90-110-28.compute-1.amazonaws.com/SWS/api/customer/SignIn/1/";

    public static final String CREATEACCOUNT = "http://ec2-52-90-110-28.compute-1.amazonaws.com/SWS/api/customer/CreateCustomer/";

    public static String createSignInURL(String email, String password, Resources resources){
        return SIGNUP + email + "/" + password;
    }

    public static String createCreateAccountURL(String first, String last, String email, String phone, String password, Resources resources){
        return CREATEACCOUNT + first + "/" + last + "/" + email + "/" + phone + "/" + password;
    }
}
