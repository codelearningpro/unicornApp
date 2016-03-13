package savewithsprout.messaging;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import savewithsprout.unicornapp.R;

public class MessagingActivity extends Activity {

    ArrayList<Message> messages = new ArrayList<Message>();

    ListView messageList;
    MessagingAdapter messagingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messaging);

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        try {
            messages.add(new Message("Adam", "Yo whats up?", format.parse("2016/02/01 17:48:52:10")));
            messages.add(new Message("Adam", "I have a questions to ask", format.parse("2016/02/01 15:48:52:10")));
            messages.add(new Message("Micheal", "Same, we need to have a meeting soon", format.parse("2016/03/12 09:47:52:10")));
            messages.add(new Message("Andrew", "Alright how about Saturday at 8", format.parse("2016/03/13 10:47:52:10")));
            messages.add(new Message("Micheal", "At night? Over skype?", format.parse("2016/03/13 12:47:52:10")));
            messages.add(new Message("Andrew", "Yes and yes sound good?", format.parse("2016/03/13 13:47:52:10")));
            messages.add(new Message("Adam", "Perfect see you then", format.parse("2016/03/13 14:47:52:10")));
        } catch (ParseException e){

        }

        messageList = ((ListView) findViewById(R.id.messageList));

        messagingAdapter = new MessagingAdapter(getBaseContext(), messages);
        messageList.setAdapter(messagingAdapter);

        ((EditText) findViewById(R.id.messageInput)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    sendMessage(v.getText().toString());
                    handled = true;
                    scrollMyListViewToBottom();
                }
                return handled;
            }
        });

        ((EditText) findViewById(R.id.messageInput)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollMyListViewToBottom();
            }
        });

        scrollMyListViewToBottom();
    }

    private void scrollMyListViewToBottom() {
        messageList.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                messageList.setSelection(messagingAdapter.getCount() - 1);
            }
        });
    }

    public void sendMessage(String message){
        messages.add(new Message("Andrew", message, new Date()));
        messagingAdapter.notifyDataSetChanged();
    }
}
