package itsz.aprecor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Sec1Nivel2Lec2 extends AppCompatActivity {
    private String string_score;
    private int score;
    private TextView tvScorePut;
    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec1_nivel2_lec2);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.statusBar)));

        tvScorePut= findViewById(R.id.tvScorePut);
        score=6;

        string_score = getIntent().getStringExtra("score");
        tvScorePut.setText("Tu score actual es: " + score);
        findViewById(R.id.btnSec1Nivel1Jugar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sec1Nivel2Lec2.this,Sec1Nivel2Lec2Juego.class);
                string_score = String.valueOf(score);
                intent.putExtra("score",string_score);
                startActivity(intent);
                finish();
            }
        });
    }
    public void onBackPressed(){
        Intent intent=new Intent(Sec1Nivel2Lec2.this,LevelsSection1.class);
        startActivity(intent);
        finish();
    }
}