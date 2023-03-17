package itsz.aprecor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class Sec1Nivel4Lec4 extends AppCompatActivity {
    //Creamos dos variables para recibir los parametros que vienen de la clase Sec1Nivel3Lec3Juego
    private String string_score;
    private int score;
    private TextView tvScorePutLec4;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec1_nivel4_lec4);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.statusBar)));

        tvScorePutLec4= findViewById(R.id.tvScorePutLec4);

        string_score = getIntent().getStringExtra("score");
        score = 18;
        tvScorePutLec4.setText("Tu score actual es: " + score);
        findViewById(R.id.btnSec1Nivel4Jugar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Sec1Nivel4Lec4.this,Sec1Nivel4Lec4Juego.class);
                string_score= String.valueOf(score);
                intent.putExtra("score",string_score);
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
            //tvGemas.setText("8");
            BD.close();
        }else{
            BD.close();
        }//Fin de conexion de sqlite
    }


    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Sec1Nivel4Lec4.this,LevelsSection1.class);
        startActivity(intent);
        finish();
    }
}