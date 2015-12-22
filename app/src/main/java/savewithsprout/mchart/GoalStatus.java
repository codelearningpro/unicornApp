package savewithsprout.mchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by menushkaweeratunga on 15-12-18.
 */
public class GoalStatus extends View {
    Context context;

    float percentage = 0.68f;

    public GoalStatus(Context context){
        super(context);
        this.context = context;
    }

    public GoalStatus(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
    }

    public GoalStatus(Context context, AttributeSet attrs, int defStyle){
        super(context, attrs, defStyle);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint background = new Paint();
        background.setARGB(255, 187, 232, 212);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), background);

        float angle = getHeight() / 4;
        Path completed = new Path();
        completed.moveTo(0, 0);
        completed.lineTo(getWidth() * percentage + angle, 0);
        completed.lineTo(getWidth() * percentage - angle, getHeight());
        completed.lineTo(0, getHeight());

        Paint gradient = new Paint();
        gradient.setShader(new LinearGradient(0, 0, getWidth() * percentage + angle, 0, Color.rgb(41, 139, 226), Color.rgb(34, 181, 115), Shader.TileMode.MIRROR));
        canvas.drawPath(completed, gradient);

        Typeface regular = Typeface.createFromAsset(context.getAssets(),  "fonts/Montserrat-Regular.ttf");
        Typeface light = Typeface.createFromAsset(context.getAssets(),  "fonts/Montserrat-Light.ttf");

        Paint lightPaint = new Paint();
        lightPaint.setTypeface(light);
        lightPaint.setTextSize(36);
        lightPaint.setColor(Color.WHITE);

        Paint regularPaint = new Paint();
        regularPaint.setTypeface(regular);
        regularPaint.setTextSize(36);
        regularPaint.setColor(Color.WHITE);

        canvas.drawText("Status: ", 10, (int)(getHeight() / 2) - (int)((lightPaint.descent() + lightPaint.ascent()) / 2), lightPaint);
        canvas.drawText("ON SCHEDULE", 10 + lightPaint.measureText("Status: "), (int) (getHeight() / 2) - (int) ((regularPaint.descent() + regularPaint.ascent()) / 2), regularPaint);

        String perText = Math.round(percentage * 100) + "%";
        lightPaint.setTextSize(48);
        lightPaint.setARGB(255, 34, 181, 115);

        canvas.drawText(perText, getWidth() - 10 - lightPaint.measureText(perText), (int)(getHeight() / 2) - (int)((lightPaint.descent() + lightPaint.ascent()) / 2), lightPaint);
    }
}
