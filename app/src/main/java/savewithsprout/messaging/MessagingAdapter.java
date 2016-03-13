package savewithsprout.messaging;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import savewithsprout.unicornapp.R;

/**
 * Created by menushkaweeratunga on 16-03-13.
 */
public class MessagingAdapter extends BaseAdapter {

    Context context;
    ArrayList<Message> messages;
    private static LayoutInflater inflater = null;

    MessagingAdapter(Context context, ArrayList<Message> messages){
        this.context = context;
        this.messages = messages;

        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (messages.get(position).getUsername().equals("Andrew")){
            view = inflater.inflate(R.layout.listview_message_personal, null);
        } else {
            view = inflater.inflate(R.layout.listview_message_other, null);
        }
        if (messages.get(position).getUsername().equals("Andrew")){
            ((ImageView) (view.findViewById(R.id.messageBoxProfileImage))).setImageResource(R.drawable.profile_picture);
        } else if (messages.get(position).getUsername().equals("Adam")){
            ((ImageView) (view.findViewById(R.id.messageBoxProfileImage))).setImageResource(R.drawable.profile_picture2);
        } else if (messages.get(position).getUsername().equals("Micheal")){
            ((ImageView) (view.findViewById(R.id.messageBoxProfileImage))).setImageResource(R.drawable.profile_picture3);
        } else {
            ((ImageView) (view.findViewById(R.id.messageBoxProfileImage))).setImageResource(R.drawable.default_profile_picture);
        }

        ((TextView) (view.findViewById(R.id.messageBoxUsername))).setText(messages.get(position).getUsername());
        ((TextView) (view.findViewById(R.id.messageBoxMessage))).setText(messages.get(position).getMessage());
        ((TextView) (view.findViewById(R.id.messageBoxTimestamp))).setText(messages.get(position).getFormatedTimestamp());
        return view;
    }
}
