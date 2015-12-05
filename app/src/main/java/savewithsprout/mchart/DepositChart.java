package savewithsprout.mchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tmwee on 11/16/2015.
 */
public class DepositChart extends View {

    Context context;

    int targetAmount = 1000;
    int targetDate = 5;
    ArrayList<Transaction> transactions = new ArrayList<Transaction>();


    public DepositChart(Context context){
        super(context);
        this.context = context;
        init();
    }

    public DepositChart(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
        init();
    }

    public DepositChart(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        this.context = context;
        init();
    }

    public void init(){
        transactions.add(new Transaction(100, 1));
        transactions.add(new Transaction(100, 2));
        transactions.add(new Transaction(200, 3));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float width = canvas.getWidth();
        float height = canvas.getHeight() * 0.9f;
        float heightMargin = canvas.getHeight() * 0.1f;

        float amountRatio = height / targetAmount;
        float dateRatio = width / targetDate;

        Paint white = new Paint();
        white.setColor(Color.WHITE);

        Paint grey = new Paint();
        grey.setARGB(255, 204, 204, 204);

        Paint green = new Paint();
        green.setARGB(255, 0, 153, 102);

        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), grey);

        //Fill UnDone

        Path p = new Path();
        p.setFillType(Path.FillType.EVEN_ODD);
        p.moveTo(0, (int) (height + heightMargin));

        for (int i = 0; i < transactions.size(); i++){
            Transaction t = transactions.get(i);
            p.lineTo((int) (t.date * dateRatio), (int) (height - t.amount * amountRatio));
        }

        p.lineTo(width, heightMargin);
        p.lineTo(width, (int) (height + heightMargin));
        p.lineTo(0, (int) (height + heightMargin));

        Paint shader = new Paint();
        shader.setARGB(32, 0, 0, 0);
        canvas.drawPath(p, shader);

        //Fill Done

        p = new Path();
        p.setFillType(Path.FillType.EVEN_ODD);
        p.moveTo(0, (int) (height + heightMargin));

        for (int i = 0; i < transactions.size(); i++){
            Transaction t = transactions.get(i);
            p.lineTo((int) (t.date * dateRatio), (int) (height - t.amount * amountRatio));

            if (i == transactions.size() - 1){
                p.lineTo((int) (t.date * dateRatio), (int) (height + heightMargin));
            }
        }

        //Line

        p.lineTo(0, (int) (height + heightMargin));

        Paint gradient = new Paint();
        gradient.setShader(new LinearGradient(0, height + heightMargin, width, heightMargin, Color.rgb(34, 181, 115), Color.rgb(41, 139, 226), Shader.TileMode.MIRROR));
        canvas.drawPath(p, gradient);

        p = new Path();
        p.setFillType(Path.FillType.EVEN_ODD);
        p.moveTo(0, (int) (height + heightMargin));

        for (int i = 0; i < transactions.size(); i++){
            Transaction t = transactions.get(i);
            p.lineTo((int) (t.date * dateRatio), (int) (height - t.amount * amountRatio));

            if (i == transactions.size() - 1){
                p.lineTo((int) (t.date * dateRatio), (int) (height + heightMargin));
            }
        }
        white.setStyle(Paint.Style.STROKE);
        white.setStrokeWidth(4);
        canvas.drawPath(p, white);

        Transaction t = transactions.get(transactions.size() - 1);
        canvas.drawLine(t.date * dateRatio, height - t.amount * amountRatio, width, heightMargin, white);

        white.setStrokeWidth(1);
        white.setStyle(Paint.Style.FILL);



        for (int i = 0; i < transactions.size(); i++){
            t = transactions.get(i);
            canvas.drawCircle(t.date * dateRatio, height - t.amount * amountRatio, 20, white);
            canvas.drawCircle(t.date * dateRatio, height - t.amount * amountRatio, 15, green);
        }

        //Outline Around

        white.setStyle(Paint.Style.STROKE);
        white.setStrokeWidth(4);
        canvas.drawRect(2, 2, canvas.getWidth() - 2, canvas.getHeight() - 2, white);

        white.setStrokeWidth(1);
        white.setStyle(Paint.Style.FILL);
    }
}

class Transaction {

    float amount;
    int date;

    Transaction(float amount, int date){
        this.amount = amount;
        this.date = date;
    }

    public int getTime(){
        return date;
    }
}