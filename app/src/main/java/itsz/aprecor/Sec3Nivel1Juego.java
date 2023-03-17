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
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Sec3Nivel1Juego extends AppCompatActivity {
    private Button btnAleatorio;
    private TextView tv_score, tv_vidas;
    private TextInputLayout TILayout;
    private TextInputEditText TIEditText;
    private ImageView iv_score;
    private MediaPlayer mp_correcto,mp_incorrecto,mp_chicknawi, mp_chikome, mp_chikuase, mp_chikueyi,mp_elote,mp_eyi, mp_frijol, mp_uno,mp_dos, mp_manzana;
    private int numAleatorio,score=60, vidas = 3;

    private String []palabrasEspañol ={"Nueve", "Siete","Seis", "Ocho", "Elotl","Tres","Etl","Se","Ome","Tzatza"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Metodo que quita action bar sin descomponer el color de status bar and navigationbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_sec3_nivel1_juego);

        btnAleatorio = findViewById(R.id.btnAleatorio);
        tv_score = findViewById(R.id.tv_score);
        tv_vidas = findViewById(R.id.tv_vidas);
        iv_score = findViewById(R.id.iv_score);
        TILayout = findViewById(R.id.TILayout);
        TIEditText = findViewById(R.id.TIEditText);
        AudioAleatorio();

        findViewById(R.id.btnComprobarSec3Nivel3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BtnComprobarSec3Nivel1();
            }
        });

        mp_chicknawi = MediaPlayer.create(this, R.raw.chiknawi);
        mp_chikome = MediaPlayer.create(this, R.raw.chikome);
        mp_chikuase = MediaPlayer.create(this,R.raw.chikuase);
        mp_chikueyi = MediaPlayer.create(this,R.raw.chikueyi);
        mp_elote = MediaPlayer.create(this,R.raw.elote);
        mp_eyi = MediaPlayer.create(this,R.raw.eyi);
        mp_frijol = MediaPlayer.create(this, R.raw.frijol);
        mp_uno = MediaPlayer.create(this,R.raw.uno);
        mp_dos = MediaPlayer.create(this, R.raw.dos);
        mp_manzana = MediaPlayer.create(this, R.raw.manzana);
        mp_correcto = MediaPlayer.create(this, R.raw.audiocorrecto);
        mp_incorrecto = MediaPlayer.create(this,R.raw.erroraprecor);

    }
    @SuppressLint("SetTextI18n")
    public void BtnComprobarSec3Nivel1(){
        if(score>=60 && score<70) {
            boolean banera = false;
            String respuesta = TIEditText.getText().toString().trim();
            if (!respuesta.isEmpty()) {
                if (palabrasEspañol[numAleatorio].equalsIgnoreCase(respuesta)) {
                    score++;
                    banera = true;
                    tv_score.setText(score + "/10");
                    mp_correcto.start();
                    TIEditText.setText("");
                    Toast.makeText(this, "La palabra se actualizo, vuelve a escuchar nuevamente", Toast.LENGTH_SHORT).show();
                    IvScore();
                    AudioAleatorio();
                    BaseDeDatos();
                } else {
                    vidas--;
                    Toast.makeText(this, "La palabra se actualizo, vuelve a escuchar nuevamente", Toast.LENGTH_SHORT).show();
                    mp_incorrecto.start();
                    TIEditText.setText("");
                    AudioAleatorio();
                }
            } else {
                Toast.makeText(this, "Ingresa una respuesta", Toast.LENGTH_SHORT).show();
            }

            if (!banera) {
                switch (vidas) {
                    case 2:
                        Toast.makeText(this, "Te quedan dos vidas", Toast.LENGTH_SHORT).show();
                        tv_vidas.setText("2");
                        break;
                    case 1:
                        Toast.makeText(this, "Te queda una vidas", Toast.LENGTH_SHORT).show();
                        tv_vidas.setText("1");
                        break;
                    case 0:
                        Toast.makeText(this, "Haz perdido todas tus vidas", Toast.LENGTH_SHORT).show();
                        tv_vidas.setText("0");
                        Intent intent = new Intent(Sec3Nivel1Juego.this, LevelsSection3.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        }else{
            //Metodo que nos llevara al siguiente nivel
            Intent intent=new Intent(this,Sec3Nivel2Juego.class);
            Toast.makeText(this, "Felicidades haz pasado al nivel-practica 5", Toast.LENGTH_SHORT).show();
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
    public void AudioAleatorio(){
        numAleatorio = (int) (Math.random() * 10);
        switch (numAleatorio){
            //{"Nueve", "Siete","Seis", "Ocho", "Elotl","Tres","Etl","Se","Ome","Tzatza"};
            case 0:
                btnAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_chicknawi.start();
                        TILayout.setHint("Escribe en letra la traduccion en español");
                    }
                });
                break;
            case 1:
                btnAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_chikome.start();
                        TILayout.setHint("Escribe en letra la traduccion en español");
                    }
                });
                break;
            case 2:
                btnAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_chikuase.start();
                        TILayout.setHint("Escribe en letra la traduccion en español");
                    }
                });
                break;
            case 3:
                btnAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_chikueyi.start();
                        TILayout.setHint("Escribe en letra la traduccion en español");
                    }
                });
                break;
            case 4:
                btnAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_elote.start();
                        TILayout.setHint("Escribe en letra la traduccion en nahuatl");
                    }
                });
                break;
            case 5:
                btnAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_eyi.start();
                        TILayout.setHint("Escribe en letra la traduccion en español");
                    }
                });
                break;
            case 6:
                btnAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_frijol.start();
                        TILayout.setHint("Escribe en letra la traduccion en nahuatl");
                    }
                });
                break;
            case 7:
                btnAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_uno.start();
                        TILayout.setHint("Escribe en letra la traduccion en nahuatl");
                    }
                });
                break;
            case 8:
                btnAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_dos.start();
                        TILayout.setHint("Escribe en letra la traduccion en nahuatl");
                    }
                });
                break;
            case 9:
                btnAleatorio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mp_manzana.start();
                        TILayout.setHint("Escribe en letra la traduccion en nahuatl");
                    }
                });
                break;
        }
    }
    public void IvScore(){
        switch (score){
            //Iniciamos el case en 61 para que la accion se ejecute pues el score se inicializo con 60
            case 61:
                iv_score.setImageResource(R.drawable.score2_uno);
                break;
            case 62:
                iv_score.setImageResource(R.drawable.score2_dos);
                break;
            case 63:
                iv_score.setImageResource(R.drawable.score2_tres);
                break;
            case 64:
                iv_score.setImageResource(R.drawable.score2_cuatro);
                break;
            case 65:
                iv_score.setImageResource(R.drawable.score2_cinco);
                break;
            case 66:
                iv_score.setImageResource(R.drawable.score2_seis);
                break;
            case 67:
                iv_score.setImageResource(R.drawable.score2_siete);
                break;
            case 68:
                iv_score.setImageResource(R.drawable.score2_ocho);
                break;
            case 69:
                iv_score.setImageResource(R.drawable.score2_nueve);
                break;
            case 70:
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
                        startActivity(new Intent(Sec3Nivel1Juego.this,LevelsSection3.class));
                        finish();
                    }
                });
                btnSigNivel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(Sec3Nivel1Juego.this,Sec3Nivel2Juego.class));
                        finish();
                    }
                });
                break;
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
                Intent intent = new Intent(Sec3Nivel1Juego.this, LevelsSection3.class);
                startActivity(intent);
                finish();
            }
        });
    }
}