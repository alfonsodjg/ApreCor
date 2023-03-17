package itsz.aprecor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Objects;


public class LevelsSection1 extends AppCompatActivity {

    private ImageButton btnSec1Nivel1,btnSec1Nivel2,btnSec1Nivel3,btnSec1Nivel4;
    private String temp_score;
    private TextView tvBasico;
    private int score,best_score;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_section1);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.statusBar)));
        //getSupportActionBar().setDisplayShowTitleEnabled(false);

        btnSec1Nivel1 = findViewById(R.id.btnSec1Nivel1);
        btnSec1Nivel2 = findViewById(R.id.btnSec1Nivel2);
        btnSec1Nivel3 = findViewById(R.id.btnSec1Nivel3);
        btnSec1Nivel4 = findViewById(R.id.btnSec1Nivel4);
        tvBasico = findViewById(R.id.tvBasico);

        //Metodo para boton nivel 1 en seccion 1
        btnSec1Nivel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Metodo que infla un fragment
                //getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer,new FragmentSec1Lec1()).commit();
                Intent intent=new Intent(LevelsSection1.this,Sec1Nivel1Lec1.class);
                startActivity(intent);
                finish();

            }
        });

        //Metodo para boton 2 en seccion 1
        btnSec1Nivel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getSupportFragmentManager().beginTransaction().replace(R.id.framecontainer, new FragmentSec1Lec2()).commit();
                Intent intent=new Intent(LevelsSection1.this,Sec1Nivel2Lec2.class);
                startActivity(intent);
                finish();

            }
        });

        //Metodo para boton 3 en seccion 1
        btnSec1Nivel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LevelsSection1.this,Sec1Nivel3Lec3.class);
                startActivity(intent);
                finish();
            }
        });

        //Metodo para nivel 4 boton 4 en seccion 1
        btnSec1Nivel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LevelsSection1.this,Sec1Nivel4Lec4.class);
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
            temp_score = consulta.getString(1);
            best_score = Integer.parseInt(temp_score);
            //tvGemas.setText("8");
            BD.close();
        }else{
            BD.close();
        }//Fin de conexion de sqlite

        //tvBasico.setText("El score actual es: " + best_score); nos muestra el score actual de la bd
        Enabled();

    }//Fin del oncreate

    @SuppressLint("UseCompatLoadingForDrawables")
    private void Enabled(){
        if(best_score <6){
            btnSec1Nivel1.setEnabled(true);
            btnSec1Nivel2.setEnabled(false);
            btnSec1Nivel3.setEnabled(false);
            btnSec1Nivel4.setEnabled(false);
            btnSec1Nivel2.setBackground(getDrawable(R.drawable.ic_padlookyellow));
            btnSec1Nivel3.setBackground(getDrawable(R.drawable.ic_padlookyellow));
            btnSec1Nivel4.setBackground(getDrawable(R.drawable.ic_padlookyellow));
        }
        if(best_score>=6 && best_score<12){
            btnSec1Nivel1.setEnabled(true);
            btnSec1Nivel2.setEnabled(true);
            btnSec1Nivel3.setEnabled(false);
            btnSec1Nivel4.setEnabled(false);
            btnSec1Nivel3.setBackground(getDrawable(R.drawable.ic_padlookyellow));
            btnSec1Nivel4.setBackground(getDrawable(R.drawable.ic_padlookyellow));
        }
        if(best_score>=12 && best_score<18){
            btnSec1Nivel1.setEnabled(true);
            btnSec1Nivel2.setEnabled(true);
            btnSec1Nivel3.setEnabled(true);
            btnSec1Nivel4.setEnabled(false);
            btnSec1Nivel4.setBackground(getDrawable(R.drawable.ic_padlookyellow));
        }
        if(best_score>=18 && best_score<24){
            btnSec1Nivel1.setEnabled(true);
            btnSec1Nivel2.setEnabled(true);
            btnSec1Nivel3.setEnabled(true);
            btnSec1Nivel4.setEnabled(true);
        }
    }

    private void BaseDeDatos(){
        AdminSQLiteOpenHelper admin=new AdminSQLiteOpenHelper(this,"BD",null,1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        Cursor consulta = BD.rawQuery("select * from puntaje where score = (select max(score) from puntaje)",null);
        if(consulta.moveToFirst()){
            String tem_nombre = consulta.getString(0);
            String temp_score = consulta.getString(1);

            int bet_score = Integer.parseInt(temp_score);

            if(score > bet_score){
                ContentValues modificacion= new ContentValues();
                modificacion.put("score", score);

                BD.update("puntaje", modificacion,"score=" + bet_score,null);
            }
            BD.close();

        }else{
            ContentValues insertar = new ContentValues();
            insertar.put("score",score);
            BD.insert("puntaje",null,insertar);
            BD.close();
        }
    }

    //Metodo que crea el menu
    public boolean onCreateOptionsMenu(@NonNull Menu menu){
        getMenuInflater().inflate(R.menu.overhomesections,menu);
        return true;
    }
    //Metodo para la seleccion del item
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.itemHomeSections){
            Intent intent = new Intent(LevelsSection1.this,MainActivity3HomeLevels.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(LevelsSection1.this,MainActivity3HomeLevels.class);
        startActivity(intent);
        finish();
    }
}