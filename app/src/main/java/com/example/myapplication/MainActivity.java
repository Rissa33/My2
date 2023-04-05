package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView coordinatesOut;
    private float x;
    private float y;
    private String sMove;
    private String sUp;
    private String sDown;
    private final float xCat = 300;
    private final float yCat = 500;
    private final float deltaCat = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        coordinatesOut = findViewById(R.id.coordinatesOut);
        coordinatesOut.setOnTouchListener(listener);
    }
    private View.OnTouchListener listener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            x = motionEvent.getX();
            y = motionEvent.getY();

            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    sDown = "Нажатие координат: x = " + x + ", y = " + y;
                    sMove = "";
                    sUp = "";
                    break;
                case MotionEvent.ACTION_MOVE:
                    sMove = "Движение координат: x = " + x + ", y = " + y;
                    if (x < (xCat + deltaCat) && x > (xCat - deltaCat) && y < (xCat + deltaCat) && y > (xCat - deltaCat)) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Ты нашел кота!", Toast.LENGTH_SHORT );
                        toast.setGravity(Gravity.LEFT, (int) xCat, (int) yCat);
                        LinearLayout toastContainer = (LinearLayout) toast.getView();
                        ImageView cat = new ImageView(getApplicationContext());
                        cat.setImageResource(R.drawable.cat);
                        toastContainer.addView(cat, 0);
                        toast.show();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    sMove = "";
                    sUp = "Отпускание координат: x = " + x + ", y = " + y;
                    break;
            }

            coordinatesOut.setText(sDown + "\n" + sMove + "\n" + sUp);
            return true;
        }
    };
}