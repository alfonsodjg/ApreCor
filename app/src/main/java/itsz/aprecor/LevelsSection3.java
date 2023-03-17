package itsz.aprecor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LevelsSection3 extends AppCompatActivity {
    private ImageButton btnSec3Nivel1,btnSec3Nivel2,btnSec3Nivel3,btnSec3Nivel4;
    private int best_score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_section3);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.statusBar)));

        btnSec3Nivel1=findViewById(R.id.btnSec3Nivel1);
        btnSec3Nivel2=findViewById(R.id.btnSec3Nivel2);
        btnSec3Nivel3=findViewById(R.id.btnSec3Nivel3);
        btnSec3Nivel4=findViewById(R.id.btnSec3Nivel4);
        //Metodo para boton nivel 1 en seccion 3
        btnSec3Nivel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LevelsSection3.this, Sec3Nivel1Juego.class);
                startActivity(intent);
                finish();
            }
        });
        //Metodo para boton nivel 2 en seccion 3
        btnSec3Nivel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LevelsSection3.this,Sec3Nivel2Juego.class);
                startActivity(intent);
                finish();
            }
        });
        //Metodo para boton 3 en seccion 3
        btnSec3Nivel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LevelsSection3.this,Sec3Nivel3Juego.class);
                startActivity(intent);
                finish();
            }
        });
        //Metodo para boton 4 en seccion 3
        btnSec3Nivel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LevelsSection3.this, Sec3Nivel4Juego.class);
                startActivity(intent);
                finish();
            }
        });
        //Conexion base de datos sqlite
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"BD", null,1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        Cursor consulta = BD.rawQuery(
                "select * from puntaje where score=(select max(score) from puntaje)", null);

        if (consulta.moveToFirst()){
            String temp_score = consulta.getString(1);
            best_score = Integer.parseInt(temp_score);
            //tvGemas.setText("8");
            BD.close();
        }else{
            BD.close();
        }//Fin de conexion de sqlite
        Enabled();
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    private void Enabled(){
        if(best_score==60){
            btnSec3Nivel1.setEnabled(true);
            btnSec3Nivel2.setEnabled(false);
            btnSec3Nivel3.setEnabled(false);
            btnSec3Nivel4.setEnabled(false);
            btnSec3Nivel2.setBackground(getDrawable(R.drawable.ic_padlookyellow));
            btnSec3Nivel3.setBackground(getDrawable(R.drawable.ic_padlookyellow));
            btnSec3Nivel4.setBackground(getDrawable(R.drawable.ic_padlookyellow));
        }
        if(best_score>=60 && best_score <70){
            btnSec3Nivel1.setEnabled(true);
            btnSec3Nivel2.setEnabled(false);
            btnSec3Nivel3.setEnabled(false);
            btnSec3Nivel4.setEnabled(false);
            btnSec3Nivel2.setBackground(getDrawable(R.drawable.ic_padlookyellow));
            btnSec3Nivel3.setBackground(getDrawable(R.drawable.ic_padlookyellow));
            btnSec3Nivel4.setBackground(getDrawable(R.drawable.ic_padlookyellow));

        }
        if(best_score>=70 && best_score<80){
            btnSec3Nivel1.setEnabled(true);
            btnSec3Nivel2.setEnabled(true);
            btnSec3Nivel3.setEnabled(false);
            btnSec3Nivel4.setEnabled(false);
            btnSec3Nivel3.setBackground(getDrawable(R.drawable.ic_padlookyellow));
            btnSec3Nivel4.setBackground(getDrawable(R.drawable.ic_padlookyellow));
        }
        if(best_score >=80 && best_score<90){
            btnSec3Nivel1.setEnabled(true);
            btnSec3Nivel2.setEnabled(true);
            btnSec3Nivel3.setEnabled(true);
            btnSec3Nivel4.setEnabled(false);
            btnSec3Nivel4.setBackground(getDrawable(R.drawable.ic_padlookyellow));
        }
        if(best_score>=90 && best_score <100){
            btnSec3Nivel1.setEnabled(true);
            btnSec3Nivel2.setEnabled(true);
            btnSec3Nivel3.setEnabled(true);
            btnSec3Nivel4.setEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LevelsSection3.this,MainActivity3HomeLevels.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}