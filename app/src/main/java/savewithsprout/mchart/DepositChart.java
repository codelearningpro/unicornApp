package savewithsprout.mchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Date;

import savewithsprout.objects.Goal;

/**
 * Created by tmwee on 11/16/2015.
 */
public class DepositChart extends View {

    Context context;
    Goal goal;

    float targetAmount = 1000;
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

    public void setGoal(Goal goal){
        this.goal = goal;

        targetAmount = goal.getAmount();
        targetDate = goal.getTerm() * 7;
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

        Path goalPath = new Path();
        goalPath.setFillType(Path.FillType.EVEN_ODD);
        goalPath.moveTo(0, (int) (height + heightMargin));

        int total = 0;
        for (int i = 0; i < transactions.size(); i++){
            Transaction t = transactions.get(i);
            goalPath.lineTo((int) (t.date * dateRatio), (int) (height + heightMargin - t.amount * amountRatio) - total);
            total += t.amount * amountRatio;
        }

        goalPath.lineTo(width, heightMargin);
        goalPath.lineTo(width, (int) (height + heightMargin));
        goalPath.lineTo(0, (int) (height + heightMargin));

        Paint shader = new Paint();
        shader.setARGB(32, 0, 0, 0);
        canvas.drawPath(goalPath, shader);

        white.setStyle(Paint.Style.STROKE);
        white.setStrokeWidth(4);

        canvas.drawPath(goalPath, white);

        //Fill Done

        Path goalDone = new Path();
        goalDone.setFillType(Path.FillType.EVEN_ODD);
        goalDone.moveTo(0, (int) (height + heightMargin));

        total = 0;
        for (int i = 0; i < transactions.size(); i++){
            Transaction t = transactions.get(i);
            goalDone.lineTo((int) (t.date * dateRatio), (int) (height + heightMargin - t.amount * amountRatio) - total);
            total += t.amount * amountRatio;

            if (i == transactions.size() - 1){
                goalDone.lineTo((int) (t.date * dateRatio), (int) (height + heightMargin));
            }
        }

        goalDone.lineTo(0, (int) (height + heightMargin));

        Transaction lastT = transactions.get(transactions.size() - 1);

        Paint gradient = new Paint();
        gradient.setShader(new LinearGradient(0, height + heightMargin, lastT.date * dateRatio, height - lastT.amount * amountRatio, Color.rgb(41, 139, 226), Color.rgb(34, 181, 115), Shader.TileMode.MIRROR));
        canvas.drawPath(goalDone, gradient);

        white.setStyle(Paint.Style.STROKE);
        white.setStrokeWidth(4);
        canvas.drawPath(goalDone, white);

        white.setStrokeWidth(1);
        white.setStyle(Paint.Style.FILL);

        total = 0;
        for (int i = 0; i < transactions.size(); i++){
            Transaction t = transactions.get(i);

            if (i == transactions.size() - 1){
                canvas.drawCircle(t.date * dateRatio, height + heightMargin - t.amount * amountRatio - total, 15, white);
                canvas.drawCircle(t.date * dateRatio, height + heightMargin - t.amount * amountRatio - total, 10, green);
            } else {
                canvas.drawCircle(t.date * dateRatio, height + heightMargin - t.amount * amountRatio - total, 10, white);
                canvas.drawCircle(t.date * dateRatio, height + heightMargin - t.amount * amountRatio - total, 5, green);
            }

            total += t.amount * amountRatio;
        }

        //Outline Around

        white.setStyle(Paint.Style.STROKE);
        white.setStrokeWidth(4);

        //Marker

        int weekRadius = 50;

        Transaction t = transactions.get(transactions.size() - 1);
        canvas.drawCircle(t.date * dateRatio, height + heightMargin, weekRadius, green);

        canvas.drawCircle(t.date * dateRatio, height + heightMargin, weekRadius, white);

        Path textPath = new Path();
        textPath.addArc(new RectF(t.date * dateRatio - weekRadius * 1.2f, height + heightMargin - weekRadius * 1.2f, t.date * dateRatio + weekRadius * 1.2f, height + heightMargin + weekRadius * 1.2f), 185, 265);

        Paint textPaint = new Paint();


        Typeface regular = Typeface.createFromAsset(context.getAssets(),  "fonts/Montserrat-Regular.ttf");
        textPaint.setTypeface(regular);
        textPaint.setTextSize(24);
        textPaint.setColor(Color.WHITE);
        canvas.drawTextOnPath("WEEK", textPath, 0, 0, textPaint);

        int week = 3;

        textPaint.setTextSize(35);
        canvas.drawText(week + "", t.date * dateRatio - textPaint.measureText(week + "") / 2, height + heightMargin - weekRadius / 2 - (textPaint.ascent() + textPaint.descent()) / 2, textPaint);

        canvas.drawRect(2, 2, canvas.getWidth() - 2, canvas.getHeight() - 2, white);
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