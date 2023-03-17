package itsz.aprecor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

public class Sec2Nivel1Lec1 extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec2_nivel1_lec1);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.statusBar)));

        findViewById(R.id.btnSec2Nivel1Jugar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Sec2Nivel1Lec1.this, Sec2Nivel1Lec1Juego.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent= new Intent(Sec2Nivel1Lec1.this,LevelsSection2.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}