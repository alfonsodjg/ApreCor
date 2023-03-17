package itsz.aprecor;

import static itsz.aprecor.R.drawable.bien64;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Sec2Nivel1Lec1Juego extends AppCompatActivity {

    private EditText edtUno,edtCuatro,edtCinco,edtSiete,edtOcho,edtNueve;
    private ImageView iv_score;
    private TextView tv_score, tv_vidas,tvIntermedio;
    private MediaPlayer mp_correcto, mp_incorecto;
    private String string_score;
    private int best_score,score=24, vidas = 3;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Metodo que quita action bar sin descomponer el color de status bar and navigationbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sec2_nivel1_lec1_juego);

        edtUno = findViewById(R.id.edtUno);
        edtCuatro = findViewById(R.id.edtCuatro);
        edtCinco = findViewById(R.id.edtCinco);
        edtSiete = findViewById(R.id.edtSiete);
        edtOcho = findViewById(R.id.edtOcho);
        edtNueve = findViewById(R.id.edtNueve);
        iv_score = findViewById(R.id.iv_score);
        tv_score = findViewById(R.id.tv_score);
        tv_vidas = findViewById(R.id.tv_vidas);

        edtCuatro.setVisibility(View.INVISIBLE);
        edtCinco.setVisibility(View.INVISIBLE);
        edtSiete.setVisibility(View.INVISIBLE);
        edtOcho.setVisibility(View.INVISIBLE);
        edtNueve.setVisibility(View.INVISIBLE);

        findViewById(R.id.btnComprobarNivel1Sec2).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                Comprobar();
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

        mp_correcto = MediaPlayer.create(this,R.raw.audiocorrecto);
        mp_incorecto = MediaPlayer.create(this,R.raw.erroraprecor);

    }//Fin del oncreate

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    private void Comprobar(){
        if(score>=24 && score <30) {
            String edt_uno = edtUno.getText().toString().trim();
            String edt_cuatro = edtCuatro.getText().toString().trim();
            String edt_cinco = edtCinco.getText().toString().trim();
            String edt_siete = edtSiete.getText().toString().trim();
            String edt_ocho = edtOcho.getText().toString().trim();
            String edt_nueve = edtNueve.getText().toString().trim();

            boolean bandera = false;
            if (!edt_uno.isEmpty()) {
                if (edt_uno.equalsIgnoreCase("se")) {
                    score++;
                    tv_score.setText(score + "/6");
                    bandera = true;
                    edtUno.setEnabled(false);
                    edtUno.setText("");
                    edtUno.setForeground(getDrawable(bien64));
                    Score();
                    BaseDeDatos();
                    mp_correcto.start();
                } else {
                    //Error
                    vidas--;
                    mp_incorecto.start();
                }
            } else if (!edt_cuatro.isEmpty()) {
                if (edt_cuatro.equalsIgnoreCase("Nawi")) {
                    score++;
                    tv_score.setText(score + "/6");
                    bandera = true;
                    edtCuatro.setEnabled(false);
                    edtCuatro.setText("");
                    edtCuatro.setForeground(getDrawable(bien64));
                    Score();
                    BaseDeDatos();
                    mp_correcto.start();
                } else {
                    vidas--;
                    mp_incorecto.start();
                }
            } else if (!edt_cinco.isEmpty()) {
                if (edt_cinco.equalsIgnoreCase("Makualli")) {
                    score++;
                    tv_score.setText(score + "/6");
                    bandera = true;
                    edtCinco.setEnabled(false);
                    edtCinco.setText("");
                    edtCinco.setForeground(getDrawable(bien64));
                    Score();
                    BaseDeDatos();
                    mp_correcto.start();
                } else {
                    vidas--;
                    mp_incorecto.start();
                }
            } else if (!edt_siete.isEmpty()) {
                if (edt_siete.equalsIgnoreCase("Chikome")) {
                    score++;
                    tv_score.setText(score + "/6");
                    bandera = true;
                    edtSiete.setEnabled(false);
                    edtSiete.setText("");
                    edtSiete.setForeground(getDrawable(bien64));
                    Score();
                    BaseDeDatos();
                    mp_correcto.start();
                } else {
                    vidas--;
                    mp_incorecto.start();
                }
            } else if (!edt_ocho.isEmpty()) {
                if (edt_ocho.equalsIgnoreCase("Chikueyi")) {
                    score++;
                    tv_score.setText(score + "/6");
                    bandera = true;
                    edtOcho.setEnabled(false);
                    edtOcho.setText("");
                    edtOcho.setForeground(getDrawable(bien64));
                    Score();
                    BaseDeDatos();
                    mp_correcto.start();
                } else {
                    vidas--;
                    mp_incorecto.start();
                }
            } else if (!edt_nueve.isEmpty()) {
                if (edt_nueve.equalsIgnoreCase("Chiknawi")) {
                    score++;
                    tv_score.setText(score + "/6");
                    bandera = true;
                    edtNueve.setEnabled(false);
                    edtNueve.setText("");
                    edtNueve.setForeground(getDrawable(bien64));
                    Score();
                    BaseDeDatos();
                    mp_correcto.start();
                } else {
                    vidas--;
                    mp_incorecto.start();
                }
            }

            if (!bandera) {
                switch (vidas) {
                    case 2:
                        Toast.makeText(this, "Te quedan dos vidas", Toast.LENGTH_SHORT).show();
                        tv_vidas.setText("2");
                        break;
                    case 1:
                        Toast.makeText(this, "Te queda una vida", Toast.LENGTH_SHORT).show();
                        tv_vidas.setText("1");
                        break;
                    case 0:
                        Toast.makeText(this, "Haz perdido todas tus vidas", Toast.LENGTH_SHORT).show();
                        tv_vidas.setText("0");
                        Intent intent = new Intent(Sec2Nivel1Lec1Juego.this, LevelsSection2.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }

        }else{
            //Metodo que nos llevara al siguiente nivel
            Intent intent = new Intent(this,Sec2Nivel2Juego.class);
            Toast.makeText(this, "Felicidades, ahora estas en el nivel practica-1", Toast.LENGTH_SHORT).show();
            string_score = String.valueOf(score);
            intent.putExtra("score",string_score);
            startActivity(intent);
            finish();
        }

    }

    public void Score(){

        //El case empieza en 25 por que el score anterior fue de 24, sin ello la bd no registra nuevo puntaje
        switch (score){
            case 25:
                iv_score.setImageResource(R.drawable.scoreuno);
                edtCuatro.setVisibility(View.VISIBLE);
                edtCinco.setVisibility(View.INVISIBLE);
                edtSiete.setVisibility(View.INVISIBLE);
                edtOcho.setVisibility(View.INVISIBLE);
                edtNueve.setVisibility(View.INVISIBLE);
                break;
            case 26:
                iv_score.setImageResource(R.drawable.scoredos);
                edtCuatro.setVisibility(View.VISIBLE);
                edtCuatro.setEnabled(false);
                edtCinco.setVisibility(View.VISIBLE);
                edtSiete.setVisibility(View.INVISIBLE);
                edtOcho.setVisibility(View.INVISIBLE);
                edtNueve.setVisibility(View.INVISIBLE);
                break;
            case 27:
                iv_score.setImageResource(R.drawable.scoretres);
                edtCuatro.setVisibility(View.VISIBLE);
                edtCuatro.setEnabled(false);
                edtCinco.setVisibility(View.VISIBLE);
                edtCinco.setEnabled(false);
                edtSiete.setVisibility(View.VISIBLE);
                edtOcho.setVisibility(View.INVISIBLE);
                edtNueve.setVisibility(View.INVISIBLE);
                break;
            case 28:
                iv_score.setImageResource(R.drawable.scorecuatro);
                edtCuatro.setVisibility(View.VISIBLE);
                edtCuatro.setEnabled(false);
                edtCinco.setVisibility(View.VISIBLE);
                edtCinco.setEnabled(false);
                edtSiete.setVisibility(View.VISIBLE);
                edtSiete.setEnabled(false);
                edtOcho.setVisibility(View.VISIBLE);
                edtNueve.setVisibility(View.INVISIBLE);
                break;
            case 29:
                iv_score.setImageResource(R.drawable.scorecinco);
                edtCuatro.setVisibility(View.VISIBLE);
                edtCuatro.setEnabled(false);
                edtCinco.setVisibility(View.VISIBLE);
                edtCinco.setEnabled(false);
                edtSiete.setVisibility(View.VISIBLE);
                edtSiete.setEnabled(false);
                edtOcho.setVisibility(View.VISIBLE);
                edtOcho.setEnabled(false);
                edtNueve.setVisibility(View.VISIBLE);
                break;
            case 30:
                iv_score.setImageResource(R.drawable.scoreseis);
                edtCuatro.setVisibility(View.VISIBLE);
                edtCuatro.setEnabled(false);
                edtCinco.setVisibility(View.VISIBLE);
                edtCinco.setEnabled(false);
                edtSiete.setVisibility(View.VISIBLE);
                edtSiete.setEnabled(false);
                edtOcho.setVisibility(View.VISIBLE);
                edtOcho.setEnabled(false);
                edtNueve.setVisibility(View.VISIBLE);
                edtNueve.setEnabled(false);
                LayoutInflater inflater=getLayoutInflater();
                View view=inflater.inflate(R.layout.alert_anim,null);
                AlertDialog.Builder builder=new AlertDialog.Builder(this,R.style.alertAnim);
                builder.setView(view);
                builder.create();
                builder.setCancelable(false);
                builder.show();
                TextView tvMessage=view.findViewById(R.id.tvMessage);
                tvMessage.setText("Felicidades completaste este nivel!!");
                ImageButton btnRegresar=view.findViewById(R.id.btnRegresar);
                ImageButton btnSigNivel=view.findViewById(R.id.btnSigNivel);

                btnRegresar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Sec2Nivel1Lec1Juego.this,LevelsSection2.class));
                        finish();
                    }
                });
                btnSigNivel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Sec2Nivel1Lec1Juego.this,Sec2Nivel2Juego.class));
                        finish();
                    }
                });
                break;
        }



    }
    public void BaseDeDatos(){
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
    @Override
    public void onBackPressed() {
        MostrarAlerta();
    }
    @SuppressLint({"UseCompatLoadingForDrawables", "SetTextI18n"})
    private void MostrarAlerta(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialogTheme);
        LayoutInflater inflater = getLayoutInflater();

        //Ruta donde se encuentra la alerta personalizada
        View view = inflater.inflate(R.layout.alert_custom,null);
        builder.setView(view);

        //Variable constante que crea la alerta
        final AlertDialog dialogo = builder.create();
        dialogo.setCancelable(false);
        //Metodo que cambia color de la alerta
        dialogo.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background_alert_dialog_custom));
        //dialogo.setTitle("Alerta");
        dialogo.show();
        TextView txt = view.findViewById(R.id.tvAlert);
        txt.setText("¿Esta seguro que desea salir del juego?");

        //Metodo para boton no, al mostrar la alerta
        Button btnNo = view.findViewById(R.id.btnAlertNo);
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo.cancel();
            }
        });
        //Metodo para boton si al mostrar la alerta
        Button btnSi = view.findViewById(R.id.btnAlertSi);
        btnSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sec2Nivel1Lec1Juego.this, LevelsSection2.class);
                startActivity(intent);
                BaseDeDatos();
                finish();
            }
        });
    }
}