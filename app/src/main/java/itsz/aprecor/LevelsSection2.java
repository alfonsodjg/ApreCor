package itsz.aprecor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

public class LevelsSection2 extends AppCompatActivity {
    private int best_score,score=24;
    private TextView tvIntermedio;
    private ImageButton btnSec2Nivel1,btnSec2Nivel2,btnSec2Nivel3,btnSec2Nivel4;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_section2);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.statusBar)));

        btnSec2Nivel1 = findViewById(R.id.btnSec2Nivel1);
        btnSec2Nivel2 = findViewById(R.id.btnSec2Nivel2);
        btnSec2Nivel3 = findViewById(R.id.btnSec2Nivel3);
        btnSec2Nivel4 = findViewById(R.id.btnSec2Nivel4);
        tvIntermedio= findViewById(R.id.tvBasico);

        //Metodo para boton nivel 1 en seccion 2
        btnSec2Nivel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(LevelsSection2.this, Sec2Nivel1Lec1.class);
                startActivity(intent);
                finish();
            }
        });

        //Metodo para boton nivel 2 en seccion 2
        btnSec2Nivel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LevelsSection2.this,Sec2Nivel2Juego.class);
                String string_score = String.valueOf(score);
                intent.putExtra("score",string_score);
                startActivity(intent);
                finish();
            }
        });
        //Metodo para boton nivel 3 en seccion 2
        btnSec2Nivel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LevelsSection2.this,Sec2Nivel3Juego.class);
                startActivity(intent);
                finish();
            }
        });
        //Metodo para boton 4 en seccion 2
        btnSec2Nivel4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LevelsSection2.this,Sec2Nivel4Juego.class);
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
        //Se llama al metodo que va desbloqueando los niveles
        Enabled();
        //tvIntermedio.setText("El score acuatl que no es de la bd es: " + score); nos muestra el score que no es de la bd
    }//Fin del oncreate

    //Metodo que va desbloqueando los botones de acuerdo al mejor score registrado en la bd
    @SuppressLint("UseCompatLoadingForDrawables")
    private void Enabled(){
        if(best_score==24){
            btnSec2Nivel1.setEnabled(true);
            btnSec2Nivel2.setEnabled(false);
            btnSec2Nivel3.setEnabled(false);
            btnSec2Nivel4.setEnabled(false);
            btnSec2Nivel2.setBackground(getDrawable(R.drawable.ic_padlookyellow));
            btnSec2Nivel3.setBackground(getDrawable(R.drawable.ic_padlookyellow));
            btnSec2Nivel4.setBackground(getDrawable(R.drawable.ic_padlookyellow));
        }
        if(best_score>=24 && best_score <30){
            btnSec2Nivel1.setEnabled(true);
            btnSec2Nivel2.setEnabled(false);
            btnSec2Nivel3.setEnabled(false);
            btnSec2Nivel4.setEnabled(false);
            btnSec2Nivel2.setBackground(getDrawable(R.drawable.ic_padlookyellow));
            btnSec2Nivel3.setBackground(getDrawable(R.drawable.ic_padlookyellow));
            btnSec2Nivel4.setBackground(getDrawable(R.drawable.ic_padlookyellow));
        }
        if(best_score>=30 && best_score <40){
            btnSec2Nivel1.setEnabled(true);
            btnSec2Nivel2.setEnabled(true);
            btnSec2Nivel3.setEnabled(false);
            btnSec2Nivel4.setEnabled(false);
            btnSec2Nivel3.setBackground(getDrawable(R.drawable.ic_padlookyellow));
            btnSec2Nivel4.setBackground(getDrawable(R.drawable.ic_padlookyellow));
        }
        if(best_score>=40 && best_score<50){
            btnSec2Nivel1.setEnabled(true);
            btnSec2Nivel2.setEnabled(true);
            btnSec2Nivel3.setEnabled(true);
            btnSec2Nivel4.setEnabled(false);
            btnSec2Nivel4.setBackground(getDrawable(R.drawable.ic_padlookyellow));
        }
        if(best_score>=50 && best_score<60){
            btnSec2Nivel1.setEnabled(true);
            btnSec2Nivel2.setEnabled(true);
            btnSec2Nivel3.setEnabled(true);
            btnSec2Nivel4.setEnabled(true);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overhomesections,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id =item.getItemId();

        if(id == R.id.itemHomeSections){
            Intent intent = new Intent(LevelsSection2.this,MainActivity3HomeLevels.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void onBackPressed(){
        Intent intent=new Intent(LevelsSection2.this,MainActivity3HomeLevels.class);
        startActivity(intent);
        finish();
    }
}