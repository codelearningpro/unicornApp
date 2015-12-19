package savewithsprout.helpers;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by menushkaweeratunga on 15-12-18.
 */
public class NetworkHelper {
    public static final int SUCCESS = 0;
    public static final int FAILED = 1;
    public static final int ERROR = 2;

    public static boolean checkSignIn(String email, String phone, String password){
        return true;
    }

    public static boolean checkAccountCreate(String first, String last, String email, String phone, String password){
        return true;
    }
}
