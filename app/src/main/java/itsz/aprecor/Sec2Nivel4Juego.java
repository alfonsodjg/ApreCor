package itsz.aprecor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class Sec2Nivel4Juego extends AppCompatActivity {

    private TextView tvAleatorio,tv_score,tv_vidas;
    private ImageView iv1, iv2, iv_score;
    private RadioGroup radioGroup, rGroupCortos;
    private RadioButton rb1,rb2,rb3, rbCorto1, rbCorto2;
    private int score, numAleatorio, vidas=3;
    private MediaPlayer mp_correcto, mp_incorrecto;
    private String string_score;

    private String [] oracionesEspañol={"Tu perro", "Cuatro manzanas", "Mis dos casas", "Quiero tres naranjas", "Como te llamas", "Siete tomates", "Un gato", "Dos gatos", "Mi casa", "Una gallina"};
    private String [] oracionesNahuatl={"Mo itszkuintli", "Nawi tzatzas", "No ome kallis", "Nikneki eyi xocotl", "Tlen motoka", "Chickome tomatl", "se miston", "Ome miston", "No kalli", "Se piolama"};

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Metodo que quita action bar sin descomponer el color de status bar and navigationbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sec2_nivel4_juego);

        tvAleatorio = findViewById(R.id.tvAleatorioSec2Nivel4);
        tv_score = findViewById(R.id.tv_score);
        tv_vidas = findViewById(R.id.tv_vidas);
        radioGroup = findViewById(R.id.radios);
        rGroupCortos = findViewById(R.id.rgroupCortos);
        rb1 = findViewById(R.id.rb1Sec2Nivel4);
        rb2 = findViewById(R.id.rb2Sec2Nivel4);
        rb3 = findViewById(R.id.rb3Sec2Nivel4);
        rbCorto1 = findViewById(R.id.rbCorto1);
        rbCorto2 = findViewById(R.id.rbCorot2);
        iv_score = findViewById(R.id.iv_score);
        iv1 = findViewById(R.id.iv1);
        iv2 = findViewById(R.id.iv2);
        PAleatorio();

        findViewById(R.id.btnComprobarNivel4Sec2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comprobar();
            }
        });
        //Recuperamos el score que viene de la clase anterior
        string_score=getIntent().getStringExtra("score");
        score=50;
        tv_score.setText(score + "/60");
        mp_correcto = MediaPlayer.create(this, R.raw.audiocorrecto);
        mp_incorrecto = MediaPlayer.create(this, R.raw.erroraprecor);
    }//Fin del oncreate

    @SuppressLint("SetTextI18n")
    private void Comprobar(){
        if(score >=50 && score < 60){
            boolean bandera = false;
            if(numAleatorio >=0 && numAleatorio <=4){
                if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                    if(numAleatorio==0){
                        if(rb1.isChecked()){
                            score++;
                            tv_score.setText(score + "/10");
                            rb1.setChecked(false);
                            radioGroup.clearCheck();
                            bandera = true;
                            Score();
                            PAleatorio();
                            BaseDeDatos();
                            mp_correcto.start();
                        }else{
                            vidas--;
                            PAleatorio();
                            radioGroup.clearCheck();
                            mp_incorrecto.start();
                        }
                    }else if(numAleatorio==1){
                        if(rb2.isChecked()){
                            score++;
                            tv_score.setText(score + "/10");
                            rb2.setChecked(false);
                            rb2.setChecked(false);
                            radioGroup.clearCheck();
                            bandera = true;
                            Score();
                            PAleatorio();
                            BaseDeDatos();
                            mp_correcto.start();
                        }else {
                            vidas--;
                            PAleatorio();
                            mp_incorrecto.start();
                        }
                    }else if(numAleatorio ==2){
                        if (rb2.isChecked()){
                            score++;
                            tv_score.setText(score + "/10");
                            rb2.setChecked(false);
                            bandera = true;
                            Score();
                            PAleatorio();
                            BaseDeDatos();
                            radioGroup.clearCheck();
                            mp_correcto.start();
                        }else {
                            vidas--;
                            PAleatorio();
                            mp_incorrecto.start();
                        }
                    }else if(numAleatorio ==3){
                        if(rb2.isChecked()){
                            score++;
                            tv_score.setText(score + "/10");
                            rb2.setChecked(false);
                            radioGroup.clearCheck();
                            bandera = true;
                            Score();
                            PAleatorio();
                            BaseDeDatos();
                            mp_correcto.start();
                        }else{
                            vidas--;
                            PAleatorio();
                            radioGroup.clearCheck();
                            mp_incorrecto.start();
                        }
                    }else if(numAleatorio ==4){
                        if (rb3.isChecked()) {
                            score++;
                            tv_score.setText(score + "/10");
                            rb3.setChecked(false);
                            bandera = true;
                            Score();
                            PAleatorio();
                            radioGroup.clearCheck();
                            BaseDeDatos();
                            mp_correcto.start();
                        }else {
                            vidas--;
                            PAleatorio();
                            radioGroup.clearCheck();
                            mp_incorrecto.start();
                        }
                    }
                }else {
                    Toast.makeText(this, "Selecciona un boton", Toast.LENGTH_SHORT).show();
                }
            }else if(numAleatorio >=5 && numAleatorio <=9){
                if(rbCorto1.isChecked() || rbCorto2.isChecked()){
                    if(numAleatorio ==5){
                        if(rbCorto2.isChecked()){
                            score++;
                            tv_score.setText(score + "/10");
                            rbCorto2.setChecked(false);
                            rGroupCortos.clearCheck();
                            bandera = true;
                            Score();
                            PAleatorio();
                            BaseDeDatos();
                            mp_correcto.start();
                        }else{
                            vidas--;
                            PAleatorio();
                            rGroupCortos.clearCheck();
                            mp_incorrecto.start();
                        }
                    }else if(numAleatorio ==6){
                        if(rbCorto1.isChecked()){
                            score++;
                            tv_score.setText(score + "/10");
                            rbCorto1.setChecked(false);
                            rGroupCortos.clearCheck();
                            bandera = true;
                            Score();
                            PAleatorio();
                            BaseDeDatos();
                            mp_correcto.start();
                        }else{
                            vidas--;
                            PAleatorio();
                            rGroupCortos.clearCheck();
                            mp_incorrecto.start();
                        }
                    }else if(numAleatorio==7){
                        if (rbCorto2.isChecked()){
                            score++;
                            tv_score.setText(score + "/10");
                            rbCorto2.setChecked(false);
                            rGroupCortos.clearCheck();
                            bandera = true;
                            Score();
                            PAleatorio();
                            BaseDeDatos();
                            mp_correcto.start();
                        }else {
                            vidas--;
                            PAleatorio();
                            rGroupCortos.clearCheck();
                            mp_incorrecto.start();
                        }
                    }else if(numAleatorio==8){
                        if(rbCorto1.isChecked()){
                            score++;
                            tv_score.setText(score + "/10");
                            rbCorto1.setChecked(false);
                            rGroupCortos.clearCheck();
                            bandera = true;
                            Score();
                            PAleatorio();
                            BaseDeDatos();
                            mp_correcto.start();
                        }else {
                            vidas--;
                            PAleatorio();
                            rGroupCortos.clearCheck();
                            mp_incorrecto.start();
                        }
                    }else if(numAleatorio==9){
                        if(rbCorto2.isChecked()){
                            score++;
                            tv_score.setText(score + "/10");
                            rbCorto2.setChecked(false);
                            rGroupCortos.clearCheck();
                            bandera = true;
                            Score();
                            PAleatorio();
                            BaseDeDatos();
                            mp_correcto.start();
                        }else{
                            vidas--;
                            PAleatorio();
                            rGroupCortos.clearCheck();
                            mp_incorrecto.start();
                        }
                    }
                }else{
                    Toast.makeText(this, "Selecciona un boton", Toast.LENGTH_SHORT).show();
                }
            }

            if (!bandera){
                switch (vidas){
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
                        Intent intent = new Intent(Sec2Nivel4Juego.this, LevelsSection2.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        }else {
            //Metodo que nos lleva al siguiente nivel
            Intent intent=new Intent(this,LevelsSection2.class);
            Toast.makeText(this, "Haz terminado los niveles del nivel intermedio", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }
    }
    private void Score(){
        switch (score){
            case 51:
                iv_score.setImageResource(R.drawable.score2_uno);
                break;
            case 52:
                iv_score.setImageResource(R.drawable.score2_dos);
                break;
            case 53:
                iv_score.setImageResource(R.drawable.score2_tres);
                break;
            case 54:
                iv_score.setImageResource(R.drawable.score2_cuatro);
                break;
            case 55:
                iv_score.setImageResource(R.drawable.score2_cinco);
                break;
            case 56:
                iv_score.setImageResource(R.drawable.score2_seis);
                break;
            case 57:
                iv_score.setImageResource(R.drawable.score2_siete);
                break;
            case 58:
                iv_score.setImageResource(R.drawable.score2_ocho);
                break;
            case 59:
                iv_score.setImageResource(R.drawable.score2_nueve);
                break;
            case 60:
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
                        startActivity(new Intent(Sec2Nivel4Juego.this,LevelsSection2.class));
                        finish();
                    }
                });
                btnSigNivel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Sec2Nivel4Juego.this,MainActivity3HomeLevels.class));
                        Toast.makeText(Sec2Nivel4Juego.this, "Haz desbloqueado el nivel experto", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                break;
        }
    }
    @SuppressLint("SetTextI18n")
    public  void PAleatorio(){
        numAleatorio = (int)(Math.random() * 10);
        tvAleatorio.setText("Traduce la oracion ' " + oracionesEspañol[numAleatorio] + " '");

        if(numAleatorio >=0 && numAleatorio <=4){
            rGroupCortos.setVisibility(View.INVISIBLE);
            iv1.setVisibility(View.INVISIBLE);
            iv2.setVisibility(View.INVISIBLE);
            radioGroup.setVisibility(View.VISIBLE);
            switch (numAleatorio){
                case 0:
                    rb1.setText(oracionesNahuatl[0]);
                    rb2.setText(oracionesNahuatl[2]);
                    rb3.setText(oracionesNahuatl[1]);
                    break;
                case 1:
                    rb1.setText(oracionesNahuatl[2]);
                    rb2.setText(oracionesNahuatl[1]);
                    rb3.setText(oracionesNahuatl[3]);
                    break;
                case 2:
                    rb1.setText(oracionesNahuatl[4]);
                    rb2.setText(oracionesNahuatl[2]);
                    rb3.setText(oracionesNahuatl[0]);
                    break;
                case 3:
                    rb1.setText(oracionesNahuatl[2]);
                    rb2.setText(oracionesNahuatl[3]);
                    rb3.setText(oracionesNahuatl[1]);
                    break;
                case 4:
                    rb1.setText(oracionesNahuatl[1]);
                    rb2.setText(oracionesNahuatl[0]);
                    rb3.setText(oracionesNahuatl[4]);
                    break;
            }
        }else if(numAleatorio >=5 && numAleatorio <=9){
            radioGroup.setVisibility(View.INVISIBLE);
            rGroupCortos.setVisibility(View.VISIBLE);
            switch (numAleatorio){
                case 5:
                    rbCorto1.setText(oracionesNahuatl[6]);
                    rbCorto2.setText(oracionesNahuatl[5]);
                    iv1.setVisibility(View.VISIBLE);
                    iv2.setVisibility(View.VISIBLE);
                    iv1.setImageResource(R.drawable.seven);
                    iv2.setImageResource(R.drawable.tomate128);
                    break;
                case 6:
                    rbCorto1.setText(oracionesNahuatl[6]);
                    rbCorto2.setText(oracionesNahuatl[7]);
                    iv2.setVisibility(View.VISIBLE);
                    iv1.setVisibility(View.GONE);
                    iv2.setImageResource(R.drawable.gato);
                    break;
                case 7:
                    rbCorto1.setText(oracionesNahuatl[8]);
                    rbCorto2.setText(oracionesNahuatl[7]);
                    iv1.setVisibility(View.VISIBLE);
                    iv2.setVisibility(View.VISIBLE);
                    iv1.setImageResource(R.drawable.gato);
                    iv2.setImageResource(R.drawable.gato);
                    break;
                case 8:
                    rbCorto1.setText(oracionesNahuatl[8]);
                    rbCorto2.setText(oracionesNahuatl[6]);
                    iv2.setVisibility(View.VISIBLE);
                    iv1.setVisibility(View.GONE);
                    iv2.setImageResource(R.drawable.ic_casasvg);
                    break;
                case 9:
                    rbCorto1.setText(oracionesNahuatl[7]);
                    rbCorto2.setText(oracionesNahuatl[9]);
                    iv2.setVisibility(View.VISIBLE);
                    iv1.setVisibility(View.GONE);
                    iv2.setImageResource(R.drawable.gallina);
                    break;
            }
        }
    }
    //Base de datos
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
    @Override
    public void onBackPressed() {
        MostrarAlerta();
    }
    @SuppressLint("SetTextI18n")
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
                Intent intent = new Intent(Sec2Nivel4Juego.this, LevelsSection2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}