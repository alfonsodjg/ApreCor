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
import android.widget.TextView;
import android.widget.Toast;

public class Sec1Nivel3Lec3Juego extends AppCompatActivity {

    private TextView tv_vidas,tv_score,tvGuia;
    private ImageView iv_score;
    private RadioButton rbAgua,rbAtl,rbSi,rbTlamo,rbCasa,rbKalli,rbBien,rbKualli,rbGracias,rbTlasohkamati,rbAqui,rbNikan;
    private MediaPlayer mp_correcto,mp_incorrecto;
    private String string_score;

    int score,numeroAleatorio,vidas=3;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Metodo que quita action bar sin descomponer el color de status bar and navigationbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sec1_nivel3_lec3_juego);

        rbAgua=findViewById(R.id.rbAgua);
        rbAtl=findViewById(R.id.rbAtl);
        rbSi=findViewById(R.id.rbSi);
        rbTlamo=findViewById(R.id.rbTlamo);
        rbCasa=findViewById(R.id.rbCasa);
        rbKalli=findViewById(R.id.rbKalli);
        rbBien=findViewById(R.id.rbBien);
        rbKualli=findViewById(R.id.rbKualli);
        rbGracias=findViewById(R.id.rbGracias);
        rbTlasohkamati=findViewById(R.id.rbTlasohkamati);
        rbAqui=findViewById(R.id.rbAqui);
        rbNikan=findViewById(R.id.rbNikan);
        tv_vidas=findViewById(R.id.tv_vidas);
        tv_score=findViewById(R.id.tv_score);
        iv_score=findViewById(R.id.iv_score);
        tvGuia = findViewById(R.id.tvGuia);

        //Recuperamos el score que viene de la clase anterior, este score no pertenece a la bd
        string_score=getIntent().getStringExtra("score");
        score = Integer.parseInt(string_score);
        tv_score.setText(score + "/18");
        //Metodo para boton comprobar
        findViewById(R.id.btnComprobarSec1Nivel3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comprobar();
            }
        });

        //Metodo que muestra la guia en Tv "Ver ejemplo"
        tvGuia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarGuia();
            }
        });

        //Conexion base de datos sqlite
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"BD", null,1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        Cursor consulta = BD.rawQuery(
                "select * from puntaje where score=(select max(score) from puntaje)", null);

        if (consulta.moveToFirst()){
            String temp_score = consulta.getString(1);
            BD.close();
        }else{
            BD.close();
        }//Fin de conexion de sqlite

        mp_correcto = MediaPlayer.create(this,R.raw.audiocorrecto);
        mp_incorrecto = MediaPlayer.create(this,R.raw.erroraprecor);
    }

    @SuppressLint("SetTextI18n")
    public void Comprobar() {
        if(score >=12 && score<18) {
            boolean bandera = false;
            if (rbCasa.isChecked()) {
                if (rbKalli.isChecked()) {
                    score++;
                    tv_score.setText(score + "/18");
                    rbCasa.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbKalli.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbCasa.setEnabled(false);
                    rbKalli.setEnabled(false);
                    rbCasa.setChecked(false);
                    rbKalli.setChecked(false);
                    mp_correcto.start();
                    bandera = true;
                    BaseDeDatos();
                } else {
                    vidas--;
                    bandera = false;
                    mp_incorrecto.start();
                }
            } else if (rbSi.isChecked()) {
                if (rbTlamo.isChecked()) {
                    score++;
                    tv_score.setText(score + "/18");
                    rbSi.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbTlamo.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbSi.setEnabled(false);
                    rbTlamo.setEnabled(false);
                    rbSi.setChecked(false);
                    rbTlamo.setChecked(false);
                    mp_correcto.start();
                    bandera = true;
                    BaseDeDatos();
                } else {
                    vidas--;
                    bandera = false;
                    mp_incorrecto.start();
                }
            } else if (rbAgua.isChecked()) {
                if (rbAtl.isChecked()) {
                    score++;
                    tv_score.setText(score + "/18");
                    rbAgua.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbAtl.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbAgua.setEnabled(false);
                    rbAtl.setEnabled(false);
                    rbAgua.setChecked(false);
                    rbAtl.setChecked(false);
                    mp_correcto.start();
                    bandera = true;
                    BaseDeDatos();
                } else {
                    vidas--;
                    bandera = false;
                    mp_incorrecto.start();
                }
            } else if (rbBien.isChecked()) {
                if (rbKualli.isChecked()) {
                    score++;
                    tv_score.setText(score + "/18");
                    rbBien.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbKualli.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbBien.setEnabled(false);
                    rbKualli.setEnabled(false);
                    rbBien.setChecked(false);
                    rbKualli.setChecked(false);
                    mp_correcto.start();
                    bandera = true;
                    BaseDeDatos();
                } else {
                    vidas--;
                    bandera = false;
                    mp_incorrecto.start();
                }
            } else if (rbGracias.isChecked()) {
                if (rbTlasohkamati.isChecked()) {
                    score++;
                    tv_score.setText(score + "/18");
                    rbGracias.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbTlasohkamati.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbGracias.setEnabled(false);
                    rbTlasohkamati.setEnabled(false);
                    rbGracias.setChecked(false);
                    rbTlasohkamati.setChecked(false);
                    mp_correcto.start();
                    bandera = true;
                    BaseDeDatos();
                } else {
                    vidas--;
                    bandera = false;
                    mp_incorrecto.start();
                }
            } else if (rbAqui.isChecked()) {
                if (rbNikan.isChecked()) {
                    score++;
                    tv_score.setText(score + "/18");
                    rbAqui.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbNikan.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbAqui.setEnabled(false);
                    rbNikan.setEnabled(false);
                    rbAqui.setChecked(false);
                    rbNikan.setChecked(false);
                    mp_correcto.start();
                    bandera = true;
                    BaseDeDatos();
                } else {
                    vidas--;
                    bandera = false;
                    mp_incorrecto.start();
                }
            } else {
                Toast.makeText(this, "Selecciona una pareja", Toast.LENGTH_SHORT).show();
            }

            switch (score) {
                case 13:
                    iv_score.setImageResource(R.drawable.scoreuno);
                    break;
                case 14:
                    iv_score.setImageResource(R.drawable.scoredos);
                    break;
                case 15:
                    iv_score.setImageResource(R.drawable.scoretres);
                    break;
                case 16:
                    iv_score.setImageResource(R.drawable.scorecuatro);
                    break;
                case 17:
                    //iv_score.setImageResource(R.drawable.scorecinco);
                    break;
                case 18:
                    iv_score.setImageResource(R.drawable.scoreseis);
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
                            startActivity(new Intent(Sec1Nivel3Lec3Juego.this,LevelsSection1.class));
                            finish();
                        }
                    });
                    btnSigNivel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(Sec1Nivel3Lec3Juego.this,Sec1Nivel4Lec4.class));
                            finish();
                        }
                    });
                    break;
            }
            if (bandera == false) {
                switch (vidas) {
                    case 2:
                        Toast.makeText(this, "Te quedan 2 vidas", Toast.LENGTH_SHORT).show();
                        tv_vidas.setText("2");
                        break;
                    case 1:
                        Toast.makeText(this, "Te queda una vida", Toast.LENGTH_SHORT).show();
                        tv_vidas.setText("1");
                        break;
                    case 0:
                        Toast.makeText(this, "Haz perdido todas tus vidas", Toast.LENGTH_SHORT).show();
                        tv_vidas.setText("0");
                        Intent intent = new Intent(Sec1Nivel3Lec3Juego.this, LevelsSection1.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }

        }else {
            //Metodo que nos llevara al siguiente nivel
            Intent intent = new Intent(this,Sec1Nivel4Lec4.class);
            Toast.makeText(this, "Felicidades alcanzaste el nivel-palabras2", Toast.LENGTH_SHORT).show();
            string_score=String.valueOf(score);
            intent.putExtra("score",string_score);
            startActivity(intent);
            finish();
        }

    }

    //Muestra la alerta guia en Tv Ver ejemplo
    private void MostrarGuia(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.AlertDialogTheme);
        LayoutInflater inflater = getLayoutInflater();

        //Ruta donde se encuentra la alerta personalizada
        View view = inflater.inflate(R.layout.alertguia_cpmpleta_pares,null);
        builder.setView(view);

        //Variable constante que crea la alerta
        final AlertDialog dialogo = builder.create();
        dialogo.setCancelable(false);
        //Metodo que cambia color de la alerta
        dialogo.getWindow().setBackgroundDrawable(getDrawable(R.drawable.background_alert_guia));

        dialogo.show();

        //Metodo para boton no, al mostrar la alerta
        Button btnOk = view.findViewById(R.id.btnOk);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogo.cancel();
            }
        });
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
                Intent intent = new Intent(Sec1Nivel3Lec3Juego.this, LevelsSection1.class);
                startActivity(intent);
                BaseDeDatos();
                finish();
            }
        });
    }
}