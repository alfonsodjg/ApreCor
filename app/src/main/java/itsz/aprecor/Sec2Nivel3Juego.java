package itsz.aprecor;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Sec2Nivel3Juego extends AppCompatActivity {

    private ImageView iv_score,ivCvSec2Nivel3;
    private TextView tvAleatorioSec2Nivel3,tvAleatorioCardSec2Nivel3,tv_score,tv_vidas,tvIndicacion;
    private CardView cvSec2Nivel3;
    private TextInputLayout TiLayout;
    private TextInputEditText edtRespuestaSec2Nivel3;
    private RadioGroup radioGroup;
    private RadioButton rb1,rb2,rb3;
    private String string_score;
    private int numAleatorio,vidas=3,score;
    private MediaPlayer mp_correcto, mp_incorrecto;

    private String [] vectorPalabras = {"Gallina","Hormiga","Raton","Agua","Perro","Uno","Si","Bien","Mio","Aqui"};
    private String [] vectorPalabrasNahuatl = {"Piolama","Askatl","Kimichi","Atl","Itszkuintli","Se","Tlamo","Kualli","No","Nikan"};

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Metodo que quita action bar sin descomponer el color de status bar and navigationbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sec2_nivel3_juego);

        iv_score = findViewById(R.id.iv_score);
        ivCvSec2Nivel3 = findViewById(R.id.ivCvSec2Nivel3);
        tvAleatorioSec2Nivel3 = findViewById(R.id.tvAleatorioSec2Nivel3);
        tvAleatorioCardSec2Nivel3 = findViewById(R.id.tvAleatorioCardSec2Nivel3);
        tvIndicacion = findViewById(R.id.tvIndicacion);
        tv_score = findViewById(R.id.tv_score);
        tv_vidas = findViewById(R.id.tv_vidas);
        cvSec2Nivel3 = findViewById(R.id.cvSec2Nivel3);
        TiLayout = findViewById(R.id.TitLayout);
        edtRespuestaSec2Nivel3 = findViewById(R.id.edtRespuestaSec2Nivel3);
        radioGroup = findViewById(R.id.RadioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);

        string_score=getIntent().getStringExtra("score");
        score=40;
        tv_score.setText(score + "/50");

        findViewById(R.id.btnComprobarSec3Nivel3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnComprobarSec2Nivel3();
            }
        });

        Paleatorio();
        mp_correcto = MediaPlayer.create(this,R.raw.audiocorrecto);
        mp_incorrecto = MediaPlayer.create(this,R.raw.erroraprecor);
    }//Fin del oncreate
    @SuppressLint("SetTextI18n")
    public void btnComprobarSec2Nivel3(){
        if(score>=40 && score < 50) {

            boolean bandera = true;


            if(numAleatorio==0) {
                if (rb1.isChecked()) {
                    score++;
                    tv_score.setText(score + "/10");
                    Score();
                    mp_correcto.start();
                    rb1.setChecked(false);
                    radioGroup.clearCheck();
                    //rb1.setSelected(false);
                    Paleatorio();
                    BaseDeDatos();
                } else {
                    vidas--;
                    rb2.setChecked(false);
                    rb3.setChecked(false);
                    radioGroup.clearCheck();
                    mp_incorrecto.start();
                    bandera = false;
                    Paleatorio();
                }
            }else if(numAleatorio==1){
                String edtRespuesta = edtRespuestaSec2Nivel3.getText().toString().trim();
                String traduccion = vectorPalabrasNahuatl[numAleatorio];
                    if (!edtRespuesta.isEmpty() && edtRespuesta.equalsIgnoreCase(traduccion)) {
                        score++;
                        tv_score.setText(score + "/10");
                        mp_correcto.start();
                        edtRespuestaSec2Nivel3.setText("");
                        Score();
                        Paleatorio();
                        BaseDeDatos();
                    } else {
                        vidas--;
                        bandera = false;
                        mp_incorrecto.start();
                        edtRespuestaSec2Nivel3.setText("");
                        Paleatorio();
                    }
            }else if(numAleatorio==2){
                if (rb3.isChecked()) {
                    score++;
                    tv_score.setText(score + "/10"); //solo funciona internamente, no se muestra en la pantalla
                    rb3.setChecked(false);
                    radioGroup.clearCheck();
                    mp_correcto.start();
                    Score();
                    Paleatorio();
                    BaseDeDatos();
                } else {
                    vidas--;
                    rb1.setChecked(false);
                    rb2.setChecked(false);
                    radioGroup.clearCheck();
                    mp_incorrecto.start();
                    bandera = false;
                    Paleatorio();
                }
            }else if(numAleatorio==3){
                String edtRespuesta = edtRespuestaSec2Nivel3.getText().toString().trim();
                String traduccion = vectorPalabrasNahuatl[numAleatorio];
                if(!edtRespuesta.isEmpty() && edtRespuesta.equalsIgnoreCase(traduccion)){
                    score++;
                    tv_score.setText(score + "/10");
                    edtRespuestaSec2Nivel3.setText("");
                    mp_correcto.start();
                    Score();
                    Paleatorio();
                    BaseDeDatos();
                }else{
                    vidas--;
                    mp_incorrecto.start();
                    edtRespuestaSec2Nivel3.setText("");
                    bandera=false;
                    Paleatorio();
                }
            }else if(numAleatorio==4){
                if (rb1.isChecked()) {
                    score++;
                    tv_score.setText(score + "/10");
                    rb1.setChecked(false);
                    radioGroup.clearCheck();
                    mp_correcto.start();
                    Score();
                    Paleatorio();
                    BaseDeDatos();
                } else {
                    vidas--;
                    rb2.setChecked(false);
                    rb3.setChecked(false);
                    radioGroup.clearCheck();
                    mp_incorrecto.start();
                    bandera = false;
                    Paleatorio();
                }
            }else if(numAleatorio==5){
                String edtRespuesta = edtRespuestaSec2Nivel3.getText().toString().trim();
                String traduccion = vectorPalabrasNahuatl[numAleatorio];
                if(!edtRespuesta.isEmpty() && edtRespuesta.equalsIgnoreCase(traduccion)){
                    score++;
                    tv_score.setText(score + "/10");
                    edtRespuestaSec2Nivel3.setText("");
                    mp_correcto.start();
                    Score();
                    Paleatorio();
                    BaseDeDatos();
                }else{
                    vidas--;
                    mp_incorrecto.start();
                    edtRespuestaSec2Nivel3.setText("");
                    bandera=false;
                    Paleatorio();
                }
            }else if(numAleatorio==6){
                if (rb2.isChecked()) {
                    score++;
                    tv_score.setText(score + "/10");
                    rb2.setChecked(false);
                    radioGroup.clearCheck();
                    mp_correcto.start();
                    Score();
                    Paleatorio();
                    BaseDeDatos();
                } else {
                    vidas--;
                    rb1.setChecked(false);
                    rb3.setChecked(false);
                    radioGroup.clearCheck();
                    mp_incorrecto.start();
                    bandera = false;
                    Paleatorio();
                }
            }else if(numAleatorio==7){
                String edtRespuesta = edtRespuestaSec2Nivel3.getText().toString().trim();
                String traduccion = vectorPalabrasNahuatl[numAleatorio];
                if(!edtRespuesta.isEmpty() && edtRespuesta.equalsIgnoreCase(traduccion)){
                    score++;
                    tv_score.setText(score + "/10");
                    edtRespuestaSec2Nivel3.setText("");
                    mp_correcto.start();
                    Score();
                    Paleatorio();
                    BaseDeDatos();
                }else{
                    vidas--;
                    mp_incorrecto.start();
                    edtRespuestaSec2Nivel3.setText("");
                    bandera=false;
                    Paleatorio();
                }
            }else if(numAleatorio==8){
                if (rb2.isChecked()) {
                    score++;
                    tv_score.setText(score + "/10");
                    rb2.setChecked(false);
                    radioGroup.clearCheck();
                    mp_correcto.start();
                    Score();
                    Paleatorio();
                    BaseDeDatos();
                } else {
                    vidas--;
                    rb1.setChecked(false);
                    rb3.setChecked(false);
                    radioGroup.clearCheck();
                    mp_incorrecto.start();
                    bandera = false;
                    Paleatorio();
                }
            }else if(numAleatorio==9){
                String edtRespuesta = edtRespuestaSec2Nivel3.getText().toString().trim();
                String traduccion = vectorPalabrasNahuatl[numAleatorio];
                if(!edtRespuesta.isEmpty() && edtRespuesta.equalsIgnoreCase(traduccion)){
                    score++;
                    tv_score.setText(score + "/10");
                    mp_correcto.start();
                    edtRespuestaSec2Nivel3.setText("");
                    Score();
                    Paleatorio();
                    BaseDeDatos();
                }else{
                    vidas--;
                    mp_incorrecto.start();
                    edtRespuestaSec2Nivel3.setText("");
                    bandera=false;
                    Paleatorio();
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
                        Intent intent = new Intent(Sec2Nivel3Juego.this, LevelsSection2.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        }else{
            //Metodo que nos llevara al siguiente nivel
            Intent intent = new Intent(this,Sec2Nivel4Juego.class);
            Toast.makeText(this, "Felicidades desbloqueaste el nivel practica-4", Toast.LENGTH_SHORT).show();
            string_score = String.valueOf(score);
            intent.putExtra("score",string_score);
            startActivity(intent);
            finish();
        }
    }
    public void Paleatorio(){

        numAleatorio = (int)(Math.random() * 10);

        for (int i=0; i < vectorPalabras.length;i++){
            tvAleatorioSec2Nivel3.setText("¿Como se dice '" +vectorPalabras[numAleatorio] + "'?");
            break;
        }
        if(numAleatorio==0){
            cvSec2Nivel3.setVisibility(View.GONE);
            TiLayout.setVisibility(View.GONE);
            radioGroup.setVisibility(View.VISIBLE);
            tvIndicacion.setVisibility(View.VISIBLE);
            rb1.setText(vectorPalabrasNahuatl[numAleatorio]);
            rb2.setText(vectorPalabrasNahuatl[1]);
            rb3.setText(vectorPalabrasNahuatl[2]);
        }else if(numAleatorio==1){
            radioGroup.setVisibility(View.GONE);
            tvIndicacion.setVisibility(View.GONE);
            cvSec2Nivel3.setVisibility(View.VISIBLE);
            TiLayout.setVisibility(View.VISIBLE);
            cvSec2Nivel3.setCardBackgroundColor(Color.parseColor("#25FFF6"));
            tvAleatorioCardSec2Nivel3.setText(vectorPalabras[numAleatorio]);
            tvAleatorioSec2Nivel3.setText(R.string.TraduceLaPalabra );
            ivCvSec2Nivel3.setImageResource(R.drawable.hormiga);
        }else if(numAleatorio==2){
            cvSec2Nivel3.setVisibility(View.GONE);
            TiLayout.setVisibility(View.GONE);
            tvIndicacion.setVisibility(View.VISIBLE);
            radioGroup.setVisibility(View.VISIBLE);
            rb1.setText(vectorPalabrasNahuatl[0]);
            rb2.setText(vectorPalabrasNahuatl[1]);
            rb3.setText(vectorPalabrasNahuatl[numAleatorio]);
        }else if(numAleatorio==3){
            cvSec2Nivel3.setVisibility(View.VISIBLE);
            TiLayout.setVisibility(View.VISIBLE);
            cvSec2Nivel3.setCardBackgroundColor(Color.parseColor("#FFAEC9"));
            tvAleatorioCardSec2Nivel3.setText(vectorPalabras[numAleatorio]);
            tvAleatorioSec2Nivel3.setText(R.string.TraduceLaPalabra);
            ivCvSec2Nivel3.setImageResource(R.drawable.ic_watersvg);
            radioGroup.setVisibility(View.GONE);
            tvIndicacion.setVisibility(View.GONE);
        }else if(numAleatorio==4){
            cvSec2Nivel3.setVisibility(View.GONE);
            TiLayout.setVisibility(View.GONE);
            tvIndicacion.setVisibility(View.VISIBLE);
            radioGroup.setVisibility(View.VISIBLE);
            rb1.setText(vectorPalabrasNahuatl[numAleatorio]);
            rb2.setText(vectorPalabrasNahuatl[3]);
            rb3.setText(vectorPalabrasNahuatl[1]);
        }else if(numAleatorio==5){
            cvSec2Nivel3.setVisibility(View.VISIBLE);
            TiLayout.setVisibility(View.VISIBLE);
            cvSec2Nivel3.setCardBackgroundColor(Color.parseColor("#99D9EA"));
            tvAleatorioCardSec2Nivel3.setText(vectorPalabras[numAleatorio]);
            tvAleatorioSec2Nivel3.setText(R.string.TraduceLaPalabra);
            ivCvSec2Nivel3.setImageResource(R.drawable.ic_onesvg);
            radioGroup.setVisibility(View.GONE);
            tvIndicacion.setVisibility(View.GONE);
        }else if(numAleatorio==6){
            cvSec2Nivel3.setVisibility(View.GONE);
            TiLayout.setVisibility(View.GONE);
            tvIndicacion.setVisibility(View.VISIBLE);
            radioGroup.setVisibility(View.VISIBLE);
            rb1.setText(vectorPalabrasNahuatl[7]);
            rb2.setText(vectorPalabrasNahuatl[numAleatorio]);
            rb3.setText(vectorPalabrasNahuatl[8]);
        }else if(numAleatorio==7){
            cvSec2Nivel3.setVisibility(View.VISIBLE);
            TiLayout.setVisibility(View.VISIBLE);
            cvSec2Nivel3.setCardBackgroundColor(Color.parseColor("#09EA22"));
            tvAleatorioCardSec2Nivel3.setText(vectorPalabras[numAleatorio]);
            tvAleatorioSec2Nivel3.setText(R.string.TraduceLaPalabra);
            ivCvSec2Nivel3.setImageResource(R.drawable.ic_goodsvg);
            radioGroup.setVisibility(View.GONE);
            tvIndicacion.setVisibility(View.GONE);
        }else if(numAleatorio==8){
            cvSec2Nivel3.setVisibility(View.GONE);
            TiLayout.setVisibility(View.GONE);
            tvIndicacion.setVisibility(View.VISIBLE);
            radioGroup.setVisibility(View.VISIBLE);
            radioGroup.setVisibility(View.VISIBLE);
            rb1.setText(vectorPalabrasNahuatl[2]);
            rb2.setText(vectorPalabrasNahuatl[numAleatorio]);
            rb3.setText(vectorPalabrasNahuatl[1]);
        }else if(numAleatorio==9){
            cvSec2Nivel3.setVisibility(View.VISIBLE);
            TiLayout.setVisibility(View.VISIBLE);
            cvSec2Nivel3.setCardBackgroundColor(Color.parseColor("#ADB1FF"));
            tvAleatorioCardSec2Nivel3.setText(vectorPalabras[numAleatorio]);
            tvAleatorioSec2Nivel3.setText(R.string.TraduceLaPalabra);
            ivCvSec2Nivel3.setImageResource(R.drawable.ic_aquisvg);
            radioGroup.setVisibility(View.GONE);
            tvIndicacion.setVisibility(View.GONE);
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
    public void Score(){
        switch (score){
            //El case empieza en 41 por que el score del nivel anterior es de 40, sin ello la bd no actualiza nuevo puntaje
            case 41:
                iv_score.setImageResource(R.drawable.score2_uno);
                break;
            case 42:
                iv_score.setImageResource(R.drawable.score2_dos);
                break;
            case 43:
                iv_score.setImageResource(R.drawable.score2_tres);
                break;
            case 44:
                iv_score.setImageResource(R.drawable.score2_cuatro);
                break;
            case 45:
                iv_score.setImageResource(R.drawable.score2_cinco);
                break;
            case 46:
                iv_score.setImageResource(R.drawable.score2_seis);
                break;
            case 47:
                iv_score.setImageResource(R.drawable.score2_siete);
                break;
            case 48:
                iv_score.setImageResource(R.drawable.score2_ocho);
                break;
            case 49:
                iv_score.setImageResource(R.drawable.score2_nueve);
                break;
            case 50:
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
                        startActivity(new Intent(Sec2Nivel3Juego.this,LevelsSection2.class));
                        finish();
                    }
                });
                btnSigNivel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Sec2Nivel3Juego.this,Sec2Nivel4Juego.class));
                        finish();
                    }
                });
                break;
        }
    }
    public void onBackPressed(){
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
                Intent intent = new Intent(Sec2Nivel3Juego.this, LevelsSection2.class);
                startActivity(intent);
                finish();
            }
        });
    }
}