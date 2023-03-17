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

public class Sec1Nivel3Lec3 extends AppCompatActivity {

    //Creamos dos variables donde recibimos el score que viene de la clase Sec1Nivel2Lec2Juego, este score no es el de la bd
    private String string_score;
    private int score;
    private TextView tvScorePutLec3;
    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec1_nivel3_lec3);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.statusBar)));

        tvScorePutLec3 = findViewById(R.id.tvScorePutLec3);
        //Recibos los parametros enviados de la clase anterior
        string_score =getIntent().getStringExtra("score");
        score=12;
        tvScorePutLec3.setText("Tu score actual es: " + score);
        findViewById(R.id.btnSec1Nivel3Jugar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sec1Nivel3Lec3.this,Sec1Nivel3Lec3Juego.class);
                string_score=String.valueOf(score);
                intent.putExtra("score",string_score);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent intent=new Intent(Sec1Nivel3Lec3.this,LevelsSection1.class);
        startActivity(intent);
        finish();
    }
}