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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Sec3Nivel2Juego extends AppCompatActivity {

    private ImageView iv_score, iv_aleatorio;
    private TextView tv_score, tv_vidas,tvPalabra;
    private TextInputLayout TIlayout;
    private TextInputEditText TiEdt;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2;
    private Button btntAleatorio;
    private MediaPlayer mp_xocotl, mp_tomatl, mp_tlasohkamati,mp_piolama,mp_nikan,mp_gracias, mp_casa,mp_no, mp_bien,mp_vibora,mp_correcto,mp_incorrecto;
    private int numAleatorio, score, vidas=3;
    //{"Nueve", "Siete","Seis", "Ocho", "Elotl","Tres","Etl","Se","Ome","Tzatza"};
    private String [] vectorPalabras = {"Naranja", "Tomate","Gracias","gallina","aqui","Tlasohkamati","Kalli", "amo","Kualli","kowatl"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Metodo que quita action bar sin descomponer el color de status bar and navigationbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_sec3_nivel2_juego);

        iv_score = findViewById(R.id.iv_score);
        iv_aleatorio = findViewById(R.id.iv_aleatoriosec3);
        tv_score = findViewById(R.id.tv_score);
        tv_vidas = findViewById(R.id.tv_vidas);
        tvPalabra = findViewById(R.id.tvPalabraSec3Nivel2);
        TIlayout = findViewById(R.id.TiLayoutSec3);
        TiEdt = findViewById(R.id.TiEdit);
        radioGroup = findViewById(R.id.radiogroup);
        rb1 = findViewById(R.id.rbP1);
        rb2 = findViewById(R.id.rbP2);
        btntAleatorio = findViewById(R.id.btnAleatorioSec3Nivel2);
        Aleatorio();

        score=70;
        findViewById(R.id.btnComprobarSec3Nivel3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comprobar();
            }
        });

        mp_xocotl = MediaPlayer.create(this, R.raw.xocotl);
        mp_tomatl = MediaPlayer.create(this, R.raw.tomatl);
        mp_tlasohkamati = MediaPlayer.create(this,R.raw.tlasohkamati);
        mp_piolama = MediaPlayer.create(this, R.raw.piolama);
        mp_nikan = MediaPlayer.create(this, R.raw.nikan);
        mp_gracias = MediaPlayer.create(this,R.raw.gracias);
        mp_casa= MediaPlayer.create(this,R.raw.casa);
        mp_no = MediaPlayer.create(this, R.raw.no);
        mp_bien = MediaPlayer.create(this, R.raw.bien);
        mp_vibora = MediaPlayer.create(this, R.raw.vibora);
        mp_correcto = MediaPlayer.create(this,R.raw.audiocorrecto);
        mp_incorrecto = MediaPlayer.create(this,R.raw.erroraprecor);
    }
    @SuppressLint("SetTextI18n")
    private void Comprobar(){
        if(score >=70 && score <80){
            boolean bandera = false;
            if(numAleatorio >=0 && numAleatorio <=4){
                String respuesta = TiEdt.getText().toString().trim();
                if(!respuesta.isEmpty()){
                    if(vectorPalabras[numAleatorio].equalsIgnoreCase(respuesta)){
                        score++;
                        tv_score.setText(score + "/10");
                        TiEdt.setText("");
                        Aleatorio();
                        Score();
                        BaseDeDatos();
                        bandera = true;
                        mp_correcto.start();
                    }else {
                        vidas--;
                        TiEdt.setText("");
                        mp_incorrecto.start();
                        Aleatorio();
                    }
                }else {
                    Toast.makeText(this, "Escribe una respuesta", Toast.LENGTH_SHORT).show();
                }
            }else if(numAleatorio==5){
                if(rb1.isChecked() || rb2.isChecked()){
                if(rb1.isChecked()){
                    score++;
                    tv_score.setText(score + "/10");
                    rb1.setChecked(false);
                    Aleatorio();
                    Score();
                    BaseDeDatos();
                    bandera = true;
                    mp_correcto.start();
                }else {
                    vidas--;
                    rb2.setChecked(false);
                    Aleatorio();
                    mp_incorrecto.start();
                }
                }else{
                    Toast.makeText(this, "Selecciona un boton", Toast.LENGTH_SHORT).show();
                }
            }else if(numAleatorio==6){
                if(rb1.isChecked() || rb2.isChecked()){
                    if(rb2.isChecked()){
                        score++;
                        tv_score.setText(score + "/10");
                        bandera=true;
                        rb2.setChecked(false);
                        mp_correcto.start();
                        Aleatorio();
                        BaseDeDatos();
                        Score();
                    }else{
                        vidas--;
                        rb1.setChecked(false);
                        mp_incorrecto.start();
                        Aleatorio();
                    }
                }else{
                    Toast.makeText(this, "Selecciona un boton", Toast.LENGTH_SHORT).show();
                }
            }else if(numAleatorio==7){
                if(rb1.isChecked() || rb2.isChecked()){
                    if(rb1.isChecked()){
                        score++;
                        tv_score.setText(score + "/10");
                        rb1.setChecked(false);
                        bandera=true;
                        mp_correcto.start();
                        Aleatorio();
                        Score();
                        BaseDeDatos();
                    }else {
                        vidas--;
                        mp_incorrecto.start();
                        rb2.setChecked(false);
                        Aleatorio();
                    }
                }else {
                    Toast.makeText(this, "Selecciona un boton", Toast.LENGTH_SHORT).show();
                }
            }else if(numAleatorio==8){
                if(rb1.isChecked() || rb2.isChecked()){
                    if(rb1.isChecked()){
                        score++;
                        tv_score.setText(score + "/10");
                        rb1.setChecked(false);
                        bandera=true;
                        mp_correcto.start();
                        Aleatorio();
                        Score();
                        BaseDeDatos();
                    }else {
                        vidas--;
                        mp_incorrecto.start();
                        rb2.setChecked(false);
                        Aleatorio();
                    }
                }else {
                    Toast.makeText(this, "Selecciona un boton", Toast.LENGTH_SHORT).show();
                }
            }else if(numAleatorio==9){
                if(rb1.isChecked() || rb2.isChecked()){
                    if(rb1.isChecked()){
                        score++;
                        tv_score.setText(score + "/10");
                        rb1.setChecked(false);
                        bandera=true;
                        mp_correcto.start();
                        Aleatorio();
                        Score();
                        BaseDeDatos();
                    }else {
                        vidas--;
                        mp_incorrecto.start();
                        rb1.setChecked(false);
                        Aleatorio();
                    }
                }else {
                    Toast.makeText(this, "Selecciona un boton", Toast.LENGTH_SHORT).show();
                }
            }

            if(!bandera){
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
                        Intent intent = new Intent(Sec3Nivel2Juego.this,LevelsSection3.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        }else {
            //Metodo que nos llevara al siguiente nivel
            Intent intent=new Intent(this,Sec3Nivel3Juego.class);
            Toast.makeText(this, "Felicidades haz pasado al nivel-practica 6", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
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
    @SuppressLint("SetTextI18n")
    private void Aleatorio(){
        numAleatorio = (int)(Math.random() *10);
        switch (numAleatorio) {
            case 0:
                TIlayout.setVisibility(View.VISIBLE);
                iv_aleatorio.setVisibility(View.INVISIBLE);
                tvPalabra.setText("Escucha la palabra y escribe su traduccion");
                radioGroup.setVisibility(View.INVISIBLE);
                btntAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_xocotl.start();
                    }
                });
                break;
            case 1:
                TIlayout.setVisibility(View.VISIBLE);
                iv_aleatorio.setVisibility(View.INVISIBLE);
                tvPalabra.setText("Escucha la palabra y escribe su traduccion");
                radioGroup.setVisibility(View.INVISIBLE);
                btntAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_tomatl.start();
                    }
                });
                break;
            case 2:
                TIlayout.setVisibility(View.VISIBLE);
                iv_aleatorio.setVisibility(View.INVISIBLE);
                tvPalabra.setText("Escucha la palabra y escribe su traduccion");
                radioGroup.setVisibility(View.INVISIBLE);
                btntAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_tlasohkamati.start();
                    }
                });
                break;
            case 3:
                TIlayout.setVisibility(View.VISIBLE);
                iv_aleatorio.setVisibility(View.INVISIBLE);
                tvPalabra.setText("Escucha la palabra y escribe su traduccion");
                radioGroup.setVisibility(View.INVISIBLE);
                btntAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_piolama.start();
                    }
                });
                break;
            case 4:
                TIlayout.setVisibility(View.VISIBLE);
                iv_aleatorio.setVisibility(View.INVISIBLE);
                tvPalabra.setText("Escucha la palabra y escribe su traduccion");
                radioGroup.setVisibility(View.INVISIBLE);
                btntAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_nikan.start();
                    }
                });
                break;
            case 5:
                TIlayout.setVisibility(View.INVISIBLE);
                iv_aleatorio.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.VISIBLE);
                rb1.setText("Tlasohkamati");
                rb2.setText("Itszkuintli");
                tvPalabra.setText("Escucha la palabra y selecciona el boton con su traduccion a nahuatl de manera correcta");
                iv_aleatorio.setImageResource(R.drawable.ic_graciassvg);
                btntAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_gracias.start();
                    }
                });
                break;
            case 6:
                TIlayout.setVisibility(View.INVISIBLE);
                iv_aleatorio.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.VISIBLE);
                rb1.setText("Kualli");
                rb2.setText("Kalli");
                tvPalabra.setText("Escucha la palabra y selecciona el boton con su traduccion a nahuatl de manera correcta");
                iv_aleatorio.setImageResource(R.drawable.ic_casasvg);
                btntAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_casa.start();
                    }
                });
                break;
            case 7:
                TIlayout.setVisibility(View.INVISIBLE);
                iv_aleatorio.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.VISIBLE);
                rb1.setText("Amo");
                rb2.setText("Eyi");
                tvPalabra.setText("Escucha la palabra y selecciona el boton con su traduccion a nahuatl de manera correcta");
                iv_aleatorio.setImageResource(R.drawable.ic_nosvg);
                btntAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_no.start();
                    }
                });
                break;
            case 8:
                TIlayout.setVisibility(View.INVISIBLE);
                iv_aleatorio.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.VISIBLE);
                rb1.setText("Kualli");
                rb2.setText("Tzatza");
                tvPalabra.setText("Escucha la palabra y selecciona el boton con su traduccion a nahuatl de manera correcta");
                iv_aleatorio.setImageResource(R.drawable.ic_biensvg);
                btntAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_bien.start();
                    }
                });
                break;
            case 9:
                TIlayout.setVisibility(View.INVISIBLE);
                iv_aleatorio.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.VISIBLE);
                rb1.setText("Kowatl");
                rb2.setText("Piolama");
                tvPalabra.setText("Escucha la palabra y selecciona el boton con su traduccion a nahuatl de manera correcta");
                iv_aleatorio.setImageResource(R.drawable.snake2);
                btntAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_vibora.start();
                    }
                });
                break;
        }
    }
    private void Score(){
        switch (score){
            case 71:
                iv_score.setImageResource(R.drawable.score2_uno);
                break;
            case 72:
                iv_score.setImageResource(R.drawable.score2_dos);
                break;
            case 73:
                iv_score.setImageResource(R.drawable.score2_tres);
                break;
            case 74:
                iv_score.setImageResource(R.drawable.score2_cuatro);
                break;
            case 75:
                iv_score.setImageResource(R.drawable.score2_cinco);
                break;
            case 76:
                iv_score.setImageResource(R.drawable.score2_seis);
                break;
            case 77:
                iv_score.setImageResource(R.drawable.score2_siete);
                break;
            case 78:
                iv_score.setImageResource(R.drawable.score2_ocho);
                break;
            case 79:
                iv_score.setImageResource(R.drawable.score2_nueve);
                break;
            case 80:
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
                        startActivity(new Intent(Sec3Nivel2Juego.this,LevelsSection3.class));
                        finish();
                    }
                });
                btnSigNivel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Sec3Nivel2Juego.this,Sec3Nivel3Juego.class));
                        finish();
                    }
                });
                break;
        }
    }

    @Override
    public void onBackPressed() {
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
                Intent intent = new Intent(Sec3Nivel2Juego.this, LevelsSection3.class);
                startActivity(intent);
                finish();
            }
        });
    }
}