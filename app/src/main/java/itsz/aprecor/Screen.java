package itsz.aprecor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.FileUtils;
import android.widget.ProgressBar;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class Screen extends AppCompatActivity {
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.statusBar)));

        ProgressBar pB=findViewById(R.id.pB);
        //Tiempo del ProgresBar
        Timer timerProgres=new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                count++;
                pB.setProgress(count);
                if(count==30){
                    timerProgres.cancel();
                    Intent intent = new Intent(Screen.this, MainActivity3HomeLevels.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        //-ADJG
        Timer time = new Timer();
        time.schedule(timerTask, 0,50);
    }
}
