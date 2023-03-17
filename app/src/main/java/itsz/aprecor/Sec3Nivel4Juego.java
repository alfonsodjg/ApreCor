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

public class Sec3Nivel4Juego extends AppCompatActivity {
    private ImageView iv_score, iv_aleatorio;
    private TextView tv_score, tv_vidas,tvPalabra;
    private TextInputLayout TIlayout;
    private TextInputEditText TiEdt;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2;
    private Button btntAleatorio;
    private MediaPlayer mo_itzkuinli, mp_nawi_taztzas, mp_tlasohkamati,mp_nikpia_ome_kalli,mp_amo_tzopelik_atl,mp_como_te_llamas, mp_mi_mama_tiene_un_gato,mp_no, mp_naranja,mp_frijol,mp_correcto,mp_incorrecto;
    private int numAleatorio, score, vidas=3;
    //{"Nueve", "Siete","Seis", "Ocho", "Elotl","Tres","Etl","Se","Ome","Tzatza"};
    private String [] vectorPalabras = {"Tu perro", "Cuatro manzanas","Gracias","Tengo dos casas","El agua no esta dulce","Tlen motoka","no mama kikpia se miston", "amo","xocotl","etl"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Metodo que quita action bar sin descomponer el color de status bar and navigationbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_sec3_nivel4_juego);

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

        score=90;

        findViewById(R.id.btnComprobarSec3Nivel4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comprobar();
            }
        });

        mo_itzkuinli = MediaPlayer.create(this, R.raw.mo_itzkuintli);
        mp_nawi_taztzas = MediaPlayer.create(this, R.raw.nawi_tzatza);
        mp_tlasohkamati = MediaPlayer.create(this,R.raw.tlasohkamati);
        mp_nikpia_ome_kalli = MediaPlayer.create(this, R.raw.nikpia_ome_kalli);
        mp_amo_tzopelik_atl = MediaPlayer.create(this, R.raw.amo_tzopelik_atl);
        mp_como_te_llamas = MediaPlayer.create(this,R.raw.como_te_llamas);
        mp_mi_mama_tiene_un_gato= MediaPlayer.create(this,R.raw.mi_mama_tiene_un_gato);
        mp_no = MediaPlayer.create(this, R.raw.no);
        mp_naranja = MediaPlayer.create(this, R.raw.naranaja);
        mp_frijol = MediaPlayer.create(this, R.raw.frijol);
        mp_correcto = MediaPlayer.create(this,R.raw.audiocorrecto);
        mp_incorrecto = MediaPlayer.create(this,R.raw.erroraprecor);
    }
    @SuppressLint("SetTextI18n")
    private void Comprobar(){
        if(score >=90 && score <100){
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
                        Score();
                        BaseDeDatos();
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
                        Intent intent = new Intent(Sec3Nivel4Juego.this,LevelsSection3.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        }else {
            //Metodo que nos llevara al siguiente nivel
            Intent intent=new Intent(this,LevelsSection3.class);
            Toast.makeText(this, "Haz terminado todos los niveles", Toast.LENGTH_SHORT).show();
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
                        mo_itzkuinli.start();
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
                        mp_nawi_taztzas.start();
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
                        mp_nikpia_ome_kalli.start();
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
                        mp_amo_tzopelik_atl.start();
                    }
                });
                break;
            case 5:
                TIlayout.setVisibility(View.INVISIBLE);
                iv_aleatorio.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.VISIBLE);
                rb1.setText(vectorPalabras[5]);
                rb2.setText(vectorPalabras[2]);
                tvPalabra.setText("Escucha la palabra y selecciona el boton con su traduccion a nahuatl de manera correcta");
                iv_aleatorio.setImageResource(R.drawable.ic_question);
                btntAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_como_te_llamas.start();
                    }
                });
                break;
            case 6:
                TIlayout.setVisibility(View.INVISIBLE);
                iv_aleatorio.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.VISIBLE);
                rb1.setText(vectorPalabras[4]);
                rb2.setText(vectorPalabras[6]);
                tvPalabra.setText("Escucha la palabra y selecciona el boton con su traduccion a nahuatl de manera correcta");
                iv_aleatorio.setImageResource(R.drawable.ic_mother);
                btntAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_mi_mama_tiene_un_gato.start();
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
                rb1.setText(vectorPalabras[8]);
                rb2.setText(vectorPalabras[5]);
                tvPalabra.setText("Escucha la palabra y selecciona el boton con su traduccion a nahuatl de manera correcta");
                iv_aleatorio.setImageResource(R.drawable.ic_orangesvg);
                btntAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_naranja.start();
                    }
                });
                break;
            case 9:
                TIlayout.setVisibility(View.INVISIBLE);
                iv_aleatorio.setVisibility(View.VISIBLE);
                radioGroup.setVisibility(View.VISIBLE);
                rb1.setText(vectorPalabras[9]);
                rb2.setText(vectorPalabras[3]);
                tvPalabra.setText("Escucha la palabra y selecciona el boton con su traduccion a nahuatl de manera correcta");
                iv_aleatorio.setImageResource(R.drawable.ic_beanssvg);
                btntAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_frijol.start();
                    }
                });
                break;
        }
    }
    private void Score(){
        switch (score){
            case 91:
                iv_score.setImageResource(R.drawable.score2_uno);
                break;
            case 92:
                iv_score.setImageResource(R.drawable.score2_dos);
                break;
            case 93:
                iv_score.setImageResource(R.drawable.score2_tres);
                break;
            case 94:
                iv_score.setImageResource(R.drawable.score2_cuatro);
                break;
            case 95:
                iv_score.setImageResource(R.drawable.score2_cinco);
                break;
            case 96:
                iv_score.setImageResource(R.drawable.score2_seis);
                break;
            case 97:
                iv_score.setImageResource(R.drawable.score2_siete);
                break;
            case 98:
                iv_score.setImageResource(R.drawable.score2_ocho);
                break;
            case 99:
                iv_score.setImageResource(R.drawable.score2_nueve);
                break;
            case 100:
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
                        startActivity(new Intent(Sec3Nivel4Juego.this,LevelsSection3.class));
                        finish();
                    }
                });
                btnSigNivel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Sec3Nivel4Juego.this,MainActivity3HomeLevels.class));
                        Toast.makeText(Sec3Nivel4Juego.this, "Felicidades acabaste todos los niveles", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(Sec3Nivel4Juego.this, LevelsSection3.class);
                startActivity(intent);
                finish();
            }
        });
    }
}