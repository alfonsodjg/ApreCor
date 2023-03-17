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

public class Sec1Nivel2Lec2Juego extends AppCompatActivity {

    private TextView tv_vidas,tv_score,tvGuia,tvCompletaPares;
    private ImageView iv_score;
    private Button btnComprobar;
    private String temp_score;
    private RadioButton rbGato,rbMiston,rbPerro,rbItzkuintli,rbGallina,rbPiolama,rbVibora,rbKowatl,rbRaton,rbQuimichi,rbHormiga,rbAskatl;
    private MediaPlayer mp_correcto, mp_incorrecto;
    private String string_score;

    int score,best_score,vidas=3;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Metodo que quita action bar sin descomponer el color de status bar and navigationbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sec1_nivel2_lec2_juego);

        tv_score = findViewById(R.id.tv_score);
        tv_vidas = findViewById(R.id.tv_vidas);
        iv_score = findViewById(R.id.iv_score);
        tvCompletaPares = findViewById(R.id.tvCompletaPares);
        tvGuia = findViewById(R.id.tvGuia);
        rbGato = findViewById(R.id.rbGato);
        rbPerro = findViewById(R.id.rbSi);
        rbRaton = findViewById(R.id.rbAqui);
        rbVibora = findViewById(R.id.rbBien);
        rbGallina = findViewById(R.id.rbCasa);
        rbHormiga = findViewById(R.id.rbGracias);
        rbMiston = findViewById(R.id.rbKalli);
        rbItzkuintli = findViewById(R.id.rbNikan);
        rbAskatl = findViewById(R.id.rbAskatl);
        rbQuimichi = findViewById(R.id.rbTlamo);
        rbKowatl = findViewById(R.id.rbTlasohkamati);
        rbPiolama = findViewById(R.id.rbAtl);
        btnComprobar = findViewById(R.id.btnComprobarSec1Nivel2);

        btnComprobar.setOnClickListener(new View.OnClickListener() {
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
            temp_score = consulta.getString(1);
            best_score = Integer.parseInt(temp_score);
            //tvGemas.setText("8");
            BD.close();
        }else{
            BD.close();
        }//Fin de conexion de sqlite

        string_score =getIntent().getStringExtra("score");
        score = Integer.parseInt(string_score);
        tv_score.setText(score + "/12");

        mp_correcto = MediaPlayer.create(this,R.raw.audiocorrecto);
        mp_incorrecto = MediaPlayer.create(this, R.raw.erroraprecor);

    }//Fin del oncreate

public void Comprobar() {
        if(score >=6 && score<12) {
            boolean bandera = false;
            if (rbGato.isChecked()) {
                if (rbMiston.isChecked()) {
                    score++;
                    tv_score.setText(score + "/12");
                    bandera = true;
                    BaseDeDatos();
                    mp_correcto.start();
                    rbGato.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbMiston.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbGato.setEnabled(false);
                    rbMiston.setEnabled(false);
                    rbGato.setChecked(false);
                    rbMiston.setChecked(false);
                } else {
                    vidas--;
                    mp_incorrecto.start();
                }
            } else if (rbGallina.isChecked()) {
                if (rbPiolama.isChecked()) {
                    score++;
                    tv_score.setText(score + "/12");
                    bandera = true;
                    BaseDeDatos();
                    mp_correcto.start();
                    rbGallina.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbPiolama.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbGallina.setEnabled(false);
                    rbPiolama.setEnabled(false);
                    rbGallina.setChecked(false);
                    rbPiolama.setChecked(false);
                } else {
                    vidas--;
                    mp_incorrecto.start();
                }
            } else if (rbPerro.isChecked()) {
                if (rbItzkuintli.isChecked()) {
                    score++;
                    tv_score.setText(score + "/12");
                    bandera = true;
                    BaseDeDatos();
                    mp_correcto.start();
                    rbPerro.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbItzkuintli.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbPerro.setEnabled(false);
                    rbItzkuintli.setEnabled(false);
                    rbPerro.setChecked(false);
                    rbItzkuintli.setChecked(false);

                } else {
                    vidas--;
                    mp_incorrecto.start();
                }
            } else if (rbVibora.isChecked()) {
                if (rbKowatl.isChecked()) {
                    score++;
                    tv_score.setText(score + "/12");
                    bandera = true;
                    BaseDeDatos();
                    mp_correcto.start();
                    rbVibora.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbKowatl.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbVibora.setEnabled(false);
                    rbKowatl.setEnabled(false);
                    rbVibora.setChecked(false);
                    rbKowatl.setChecked(false);

                } else {
                    vidas--;
                    mp_incorrecto.start();
                }
            } else if (rbRaton.isChecked()) {
                if (rbQuimichi.isChecked()) {
                    score++;
                    tv_score.setText(score + "/12");
                    bandera = true;
                    BaseDeDatos();
                    mp_correcto.start();
                    rbRaton.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbQuimichi.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbRaton.setEnabled(false);
                    rbQuimichi.setEnabled(false);
                    rbRaton.setChecked(false);
                    rbQuimichi.setChecked(false);
                } else {
                    vidas--;
                    mp_incorrecto.start();
                }
            } else if (rbHormiga.isChecked()) {
                if (rbAskatl.isChecked()) {
                    score++;
                    tv_score.setText(score + "/12");
                    bandera = true;
                    BaseDeDatos();
                    mp_correcto.start();
                    rbHormiga.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbAskatl.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbHormiga.setEnabled(false);
                    rbAskatl.setEnabled(false);
                    rbHormiga.setChecked(false);
                    rbAskatl.setChecked(false);

                } else {
                    vidas--;
                    mp_incorrecto.start();
                }
            } else {
                Toast.makeText(this, "Selecciona una pareja", Toast.LENGTH_SHORT).show();
            }
            //El numero de case comienza en 7 por que va de la mano con el score
            switch (score) {
                case 7:
                    iv_score.setImageResource(R.drawable.scoreuno);
                    break;
                case 8:
                    iv_score.setImageResource(R.drawable.scoredos);
                    break;
                case 9:
                    iv_score.setImageResource(R.drawable.scoretres);
                    break;
                case 10:
                    iv_score.setImageResource(R.drawable.scorecuatro);
                    break;
                case 11:
                    //iv_score.setImageResource(R.drawable.scorecinco);
                    break;
                case 12:
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
                            startActivity(new Intent(Sec1Nivel2Lec2Juego.this,LevelsSection1.class));
                            finish();
                        }
                    });
                    btnSigNivel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(Sec1Nivel2Lec2Juego.this,Sec1Nivel3Lec3.class));
                            finish();
                        }
                    });
                    break;
            }
            if (!bandera) {
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
                        Intent intent = new Intent(Sec1Nivel2Lec2Juego.this, LevelsSection1.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }

        }else {
            //Metodo que nos llevara al siguiente nivel
            Intent intent= new Intent(this,Sec1Nivel3Lec3.class);
            Toast.makeText(this, "Felicidades avanzaste al nivel-palabras", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(Sec1Nivel2Lec2Juego.this, LevelsSection1.class);
                startActivity(intent);
                BaseDeDatos();
                finish();
            }
        });
    }
}