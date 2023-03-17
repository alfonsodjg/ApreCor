package itsz.aprecor;

import static itsz.aprecor.R.*;
import static itsz.aprecor.R.drawable.ic_padlookyellow;
import static itsz.aprecor.R.drawable.padlookoriginal128;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity3HomeLevels extends AppCompatActivity {
    private CardView cv;
    private ImageView ivBasico,ivIntermedio,ivExperto;
    private ImageButton btnBasico,btnIntermedio,btnExperto;
    private String temp_score;
    private TextView tvConsultaScore,tvScorePutHomeLevels;
    private int best_score,score;
    private String string_score;
    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main_activity3_home_levels);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(getDrawable(drawable.actionbar_custom));
        getSupportActionBar().setDisplayShowTitleEnabled(false); //Metodo que quita el nombre de la app del action bar


        btnBasico = findViewById(id.btnBasico);
        btnIntermedio = findViewById(id.btnIntermedio);
        btnExperto = findViewById(id.btnExperto);
        tvConsultaScore = findViewById(id.tvConsultaScore);
        cv=findViewById(id.cv);
        cv.setBackground(getDrawable(drawable.custom_cardview)); //Recupera el color personalizado del cardview en Drawable
        ivBasico=findViewById(id.iVBasico);
        ivIntermedio=findViewById(id.ivIntermedio);
        ivExperto=findViewById(id.ivExperto);



        //string_score = getIntent().getStringExtra("score");
        score=0;
        //tvScorePutHomeLevels.setText(score);
        BaseDeDatos();
        //Metodo para boton Nivel basico
        btnBasico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //Se instancia si ya hay un usuario registrado
                if (user!=null) { //if que verifica si ya existe un usuario, si ya existe se pasa a los niveles de lo contrario nos envia al login
                    Intent intent = new Intent(MainActivity3HomeLevels.this, LevelsSection1.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(MainActivity3HomeLevels.this, "Inicia sesion para poder jugar", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity3HomeLevels.this,MainActivity.class));
                    finish();
                }
            }
        });

        //Metodo para boton Nivel intermedio
        btnIntermedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3HomeLevels.this,LevelsSection2.class);
                startActivity(intent);
                finish();
            }
        });
        //Metodo para boton nivel Experto
        btnExperto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3HomeLevels.this, LevelsSection3.class);
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

        //tvConsultaScore.setText("El mejor score es de: " + temp_score); nos muestra el score actual de la bd
        Enabled();

    }//Fin del oncreate

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user==null){
            btnIntermedio.setEnabled(false);
            btnExperto.setEnabled(false);
            btnIntermedio.setBackground(getDrawable(ic_padlookyellow));
            ivIntermedio.setImageResource(drawable.ic_circlegris);
            btnExperto.setBackground(getDrawable(ic_padlookyellow));
            ivExperto.setImageResource(drawable.ic_circlegris);
        }
    }

    //Metodo para ir desbloqueando los niveles, basico, intermedio y experto
    @SuppressLint("UseCompatLoadingForDrawables")
    private void Enabled(){
        if(best_score==0 || best_score < 6 || best_score < 12 || best_score < 18 || best_score< 24){
            btnBasico.setEnabled(true);
            ivBasico.setImageResource(drawable.ic_circleyellow);
            btnIntermedio.setEnabled(false);
            btnExperto.setEnabled(false);
            btnIntermedio.setBackground(getDrawable(ic_padlookyellow));
            btnExperto.setBackground(getDrawable(ic_padlookyellow));
        }
        if(best_score >= 24){
            btnBasico.setEnabled(true);
            btnIntermedio.setEnabled(true);
            btnExperto.setEnabled(false);
            ivBasico.setImageResource(drawable.ic_circleyellow);
            ivIntermedio.setImageResource(drawable.ic_circleyellow);
            btnExperto.setBackground(getDrawable(ic_padlookyellow));
        }
        if(best_score>=60){
            btnBasico.setEnabled(true);
            btnIntermedio.setEnabled(true);
            btnExperto.setEnabled(true);
            btnExperto.setBackground(getDrawable(drawable.animation_boton_experto));
            ivBasico.setImageResource(drawable.ic_circleyellow);
            ivIntermedio.setImageResource(drawable.ic_circleyellow);
            ivExperto.setImageResource(drawable.ic_circleyellow);
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

    //Metodo para menu overflow para que sea desplegable
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overflowpantallaprincipal,menu);
        return true;
    }
    //Metodo de menu para opcion seleccionada
    @SuppressLint("UseCompatLoadingForDrawables")
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.itemUser){
        }
        if(id == R.id.itemCerrarSesion){
            Salir();
        }
        if(id ==R.id.itemAcercaDe){
            //Creacion de alertdialogAcercade
            AlertDialog.Builder alert=new AlertDialog.Builder(this);
            alert.setMessage("Aplicacion creada por itsz ").setCancelable(false).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).setNegativeButton("", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            }).setIcon(drawable.brain);
            AlertDialog dialog=alert.create();
            dialog.setTitle("Hola que tal");
            dialog.show();

        }
        return super.onOptionsItemSelected(item);
    }
    //Metodo para cerrar sesion
    public void Salir(){
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity3HomeLevels.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}