package itsz.aprecor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;



public class Sec2Nivel2Juego extends AppCompatActivity {

    private TextView tvPalabraAleatoria,tv_score,tv_vidas,tvPutSec2Nivel2;
    private ImageView iv_score,iv_aleatorio;
    private TextInputEditText edtRespuesta;
    private MediaPlayer mp_correcto, mp_incorrecto;
    int score, numAleatorio, vidas = 3;
    //Variable para recibir el parametro que viene de la clase anterior;
    private String string_score;

    private String []vector1= {"Platano","Naranja","Manzana","Frijol","Tomate","Elote","Gato","Vibora"};
    private String []vector2= {"Tzapotl","Xocotl","Tzatza","Etl","Tomatl","Elotl","Miston","Kowatl"};

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Metodo que quita action bar sin descomponer el color de status bar and navigationbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sec2_nivel2_juego);

        tvPalabraAleatoria = findViewById(R.id.tvPalabraAleatoria);
        edtRespuesta = findViewById(R.id.edtRespuesta);
        tv_score = findViewById(R.id.tv_score);
        tv_vidas = findViewById(R.id.tv_vidas);
        iv_score=findViewById(R.id.iv_score);
        iv_aleatorio = findViewById(R.id.iv_aleatorio);
        tvPutSec2Nivel2 = findViewById(R.id.tvPutSec2Nivel2);

        //Metodo para boton comprobar
        findViewById(R.id.btnComprobarNivel2Sec2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnComprobar();
            }
        });
        PalabraAleatoria();


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

        //Recibimos el parametro que viene de la clase anterior
        string_score =getIntent().getStringExtra("score");
        score = 30;
        tv_score.setText(score + "/40");

        tvPutSec2Nivel2.setText("Tu score actual es: " + score);
        mp_correcto = MediaPlayer.create(this,R.raw.audiocorrecto);
        mp_incorrecto = MediaPlayer.create(this,R.raw.erroraprecor);
    }//Fin del oncreate

    @SuppressLint("SetTextI18n")
    public void btnComprobar(){
        if(score>= 24 && score < 40) {
            boolean bandera = true;
            String traduccion = vector2[numAleatorio];
            if (!edtRespuesta.getText().toString().equals("")) {
                if (edtRespuesta.getText().toString().trim().equalsIgnoreCase(traduccion)) {
                    score++;
                    tv_score.setText(score + "/40");
                    edtRespuesta.setText("");
                    mp_correcto.start();
                    Score();
                    PalabraAleatoria();
                    BaseDeDatos();
                } else {
                    vidas--;
                    bandera = false;
                    mp_incorrecto.start();
                    edtRespuesta.setText("");
                    PalabraAleatoria();
                }
            } else {
                Toast.makeText(this, "Ingresa la traduccion", Toast.LENGTH_SHORT).show();
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
                        Intent intent = new Intent(Sec2Nivel2Juego.this, LevelsSection2.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        }else{
            //Metodo que nos lleva al siguiente nivel
            Intent intent = new Intent(this,Sec2Nivel3Juego.class);
            Toast.makeText(this, "Felicidades desbloqueaste el nivel practica-2", Toast.LENGTH_SHORT).show();
            string_score=String.valueOf(score);
            intent.putExtra("score",string_score);
            startActivity(intent);
            finish();
        }
    }
    public void PalabraAleatoria(){

        numAleatorio =(int)(Math.random()*8);

        for(int i=0; i<vector1.length;i++) {
            tvPalabraAleatoria.setText(vector1[numAleatorio]);
            break;
        }

        if(numAleatorio==0){
            iv_aleatorio.setImageResource(R.drawable.ic_banana);
        }else if(numAleatorio==1){
            iv_aleatorio.setImageResource(R.drawable.ic_orangesvg);
        }else if(numAleatorio==2){
            iv_aleatorio.setImageResource(R.drawable.ic_applesvg);
        }else if(numAleatorio==3){
            iv_aleatorio.setImageResource(R.drawable.ic_beanssvg);
        }else if(numAleatorio==4){
            iv_aleatorio.setImageResource(R.drawable.tomate128);
        }else if(numAleatorio==5){
            iv_aleatorio.setImageResource(R.drawable.ic_cornsvg);
        }else if(numAleatorio==6){
            iv_aleatorio.setImageResource(R.drawable.ic_catsvg);
        }else if(numAleatorio==7){
            iv_aleatorio.setImageResource(R.drawable.ic_sbake);
        }
        //{"Platano","Naranja","Manzana","Frijol","Tomate","Elote","Gato","Vibora"};
    }
    public void Score(){
        switch (score){
            //Iniciamos el numero de case en 31 por que el score de la clase anterior es de 30, es el numero que llego
            case 31:
                iv_score.setImageResource(R.drawable.score2_uno);
                break;
            case 32:
                iv_score.setImageResource(R.drawable.score2_dos);
                break;
            case 33:
                iv_score.setImageResource(R.drawable.score2_tres);
                break;
            case 34:
                iv_score.setImageResource(R.drawable.score2_cuatro);
                break;
            case 35:
                iv_score.setImageResource(R.drawable.score2_cinco);
                break;
            case 36:
                iv_score.setImageResource(R.drawable.score2_seis);
                break;
            case 37:
                iv_score.setImageResource(R.drawable.score2_siete);
                break;
            case 38:
                iv_score.setImageResource(R.drawable.score2_ocho);
                break;
            case 39:
                iv_score.setImageResource(R.drawable.score2_nueve);
                break;
            case 40:
                iv_score.setImageResource(R.drawable.score2_diez);
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
                        startActivity(new Intent(Sec2Nivel2Juego.this,LevelsSection2.class));
                        finish();
                    }
                });
                btnSigNivel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Sec2Nivel2Juego.this,Sec2Nivel3Juego.class));
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
    public void onBackPressed(){
        MostrarAlerta();
    }
    @SuppressLint("UseCompatLoadingForDrawables")
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
                Intent intent = new Intent(Sec2Nivel2Juego.this, LevelsSection2.class);
                startActivity(intent);
                BaseDeDatos();
                finish();
            }
        });
    }
}