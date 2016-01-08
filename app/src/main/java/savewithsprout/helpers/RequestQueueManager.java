package savewithsprout.helpers;

import android.content.Intent;

import com.android.volley.RequestQueue;
import com.android.volley.Response;

import savewithsprout.unicornapp.MainActivity;

/**
 * Created by menushkaweeratunga on 15-12-21.
 */
public class RequestQueueManager {
    RequestQueue queue;

    Response.Listener<String> response = new Response.Listener<String>() {
        @Override
        public void onResponse(String s) {

        }
    };
}
