package itsz.aprecor;

import static itsz.aprecor.R.drawable.radio_normal_buttons_juego;
import static itsz.aprecor.R.drawable.radios_correctos_levels_seccion1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.DialogInterface;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Sec1Nivel1Lec1Juego extends AppCompatActivity {

    //private Button btnAlertNo, btnAlertSi;
    private TextView tv_vidas,tv_score,tvGuia,tvCompletaPares;
    private ImageView iv_score;
    private Button btnComprobarNivel1;
    private RadioButton rbTomatl,rbTomate,rbPlatano,rbTzapotl,rbManzana,rbTzatza,rbElote,rbElotl,rbFrijol,rbEtl,rbNaranja,rbXocotl;
    private MediaPlayer mp_incorrecto, mp_incorrecto2,mp_correcto;
    private String string_score;
    int score,vidas=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Metodo que quita action bar sin descomponer el color de status bar and navigationbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sec1_nivel1_lec1_juego);

        rbTomate=findViewById(R.id.rbSi);
        rbTomatl=findViewById(R.id.rbAtl);
        rbPlatano=findViewById(R.id.rbAqui);
        rbTzapotl=findViewById(R.id.rbKalli);
        rbManzana=findViewById(R.id.rbGracias);
        rbTzatza=findViewById(R.id.rbKualli);
        rbElote=findViewById(R.id.rbAgua);
        rbElotl=findViewById(R.id.rbNikan);
        rbFrijol=findViewById(R.id.rbBien);
        rbEtl=findViewById(R.id.rbTlasohkamati);
        rbNaranja=findViewById(R.id.rbCasa);
        rbXocotl=findViewById(R.id.rbTlamo);
        tv_score=findViewById(R.id.tv_score);
        tv_vidas=findViewById(R.id.tv_vidas);
        tvCompletaPares = findViewById(R.id.tvCompletaPares);
        tvGuia = findViewById(R.id.tvGuia);
        iv_score=findViewById(R.id.iv_score);

        findViewById(R.id.btnComprobarNivel4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ComprobarNivel1();
            }
        });

        //Metodo que muestra la guia en Tv "Ver ejemplo"
        tvGuia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MostrarGuia();
            }
        });

        mp_correcto = MediaPlayer.create(this,R.raw.audiocorrecto);
        mp_incorrecto = MediaPlayer.create(this, R.raw.erroraprecor);
    }//Fin del oncreate

    @SuppressLint("UseCompatLoadingForDrawables")
    public void ComprobarNivel1() {
        if(score <=5) {
            boolean bandera = false;
            if (rbPlatano.isChecked()){
                if (rbTzapotl.isChecked()) {
                    score++;
                    tv_score.setText(score + "/6");
                    bandera = true;
                    BaseDeDatos();
                    mp_correcto.start();
                    rbPlatano.setBackgroundResource(radios_correctos_levels_seccion1);
                    rbTzapotl.setBackgroundResource(radios_correctos_levels_seccion1);
                    rbPlatano.setEnabled(false);
                    rbTzapotl.setEnabled(false);
                    rbPlatano.setChecked(false);
                    rbPlatano.setChecked(false);
                    rbTzapotl.setChecked(false);

                } else {
                    vidas--;
                    bandera = false;
                    mp_incorrecto.start();
                }
            } else if (rbTomate.isChecked()) {
                if (rbTomatl.isChecked()) {
                    score++;
                    tv_score.setText(score + "/6");
                    bandera = true;
                    BaseDeDatos();
                    mp_correcto.start();
                    rbTomate.setBackgroundResource(radios_correctos_levels_seccion1);
                    rbTomatl.setBackgroundResource(radios_correctos_levels_seccion1);
                    rbTomate.setEnabled(false);
                    rbTomatl.setEnabled(false);
                    rbTomate.setChecked(false);
                    rbTomatl.setChecked(false);
                } else {
                    vidas--;
                    bandera = false;
                    mp_incorrecto.start();

                }
            } else if (rbNaranja.isChecked()) {
                if (rbXocotl.isChecked()) {
                    score++;
                    tv_score.setText(score + "/6");
                    bandera = true;
                    BaseDeDatos();
                    mp_correcto.start();
                    rbNaranja.setBackgroundResource(radios_correctos_levels_seccion1);
                    rbXocotl.setBackgroundResource(radios_correctos_levels_seccion1);
                    rbNaranja.setEnabled(false);
                    rbXocotl.setEnabled(false);
                    rbNaranja.setChecked(false);
                    rbXocotl.setChecked(false);
                } else {
                    vidas--;
                    bandera = false;
                    mp_incorrecto.start();
                }
            } else if (rbElote.isChecked()) {
                if (rbElotl.isChecked()) {
                    score++;
                    tv_score.setText(score + "/6");
                    bandera = true;
                    BaseDeDatos();
                    mp_correcto.start();
                    rbElote.setBackgroundResource(radios_correctos_levels_seccion1);
                    rbElotl.setBackgroundResource(radios_correctos_levels_seccion1);
                    rbElote.setEnabled(false);
                    rbElotl.setEnabled(false);
                    rbElote.setChecked(false);
                    rbElotl.setChecked(false);
                } else {
                    vidas--;
                    bandera = false;
                    mp_incorrecto.start();
                }
            } else if (rbFrijol.isChecked()) {
                if (rbEtl.isChecked()) {
                    score++;
                    tv_score.setText(score + "/6");
                    bandera = true;
                    BaseDeDatos();
                    mp_correcto.start();
                    rbFrijol.setBackgroundResource(radios_correctos_levels_seccion1);
                    rbEtl.setBackgroundResource(radios_correctos_levels_seccion1);
                    rbFrijol.setEnabled(false);
                    rbEtl.setEnabled(false);
                    rbFrijol.setChecked(false);
                    rbEtl.setChecked(false);
                } else {
                    vidas--;
                    bandera = false;
                    mp_incorrecto.start();
                }
            } else if (rbManzana.isChecked()) {
                if (rbTzatza.isChecked()) {
                    score++;
                    tv_score.setText(score + "/6");
                    bandera = true;
                    BaseDeDatos();
                    mp_correcto.start();
                    rbManzana.setBackgroundResource(radios_correctos_levels_seccion1);
                    rbTzatza.setBackgroundResource(radios_correctos_levels_seccion1);
                    rbManzana.setEnabled(false);
                    rbTzatza.setEnabled(false);
                    rbManzana.setChecked(false);
                    rbTzatza.setChecked(false);
                } else {
                    vidas--;
                    bandera = false;
                    mp_incorrecto.start();
                }
            } else {
                Toast.makeText(this, "Selecciona una pareja", Toast.LENGTH_SHORT).show();
            }

            switch (score) {
                case 1:
                    iv_score.setImageResource(R.drawable.scoreuno);
                    break;
                case 2:
                    iv_score.setImageResource(R.drawable.scoredos);
                    break;
                case 3:
                    iv_score.setImageResource(R.drawable.scoretres);
                    break;
                case 4:
                    iv_score.setImageResource(R.drawable.scorecuatro);
                    break;
                case 5:
                    //iv_score.setImageResource(R.drawable.scorecinco);
                    break;
                case 6:
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
                            startActivity(new Intent(Sec1Nivel1Lec1Juego.this,LevelsSection1.class));
                            finish();
                        }
                    });
                    btnSigNivel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(Sec1Nivel1Lec1Juego.this,Sec1Nivel2Lec2.class));
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
                        Intent intent = new Intent(Sec1Nivel1Lec1Juego.this, LevelsSection1.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }

        }else {
            //Metodo que nos llevara al siguiente nivel
            Intent intent = new Intent(this,Sec1Nivel2Lec2.class);
            Toast.makeText(this, "Felicidades, avanzaste al nivel animales", Toast.LENGTH_SHORT).show();
            string_score = String.valueOf(score);
            intent.putExtra("score",string_score);
            startActivity(intent);
            finish();
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
                Intent intent = new Intent(Sec1Nivel1Lec1Juego.this, LevelsSection1.class);
                startActivity(intent);
                BaseDeDatos();
                finish();
            }
        });
    }
}
