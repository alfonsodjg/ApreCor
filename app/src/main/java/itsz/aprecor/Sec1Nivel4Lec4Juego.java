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

public class Sec1Nivel4Lec4Juego extends AppCompatActivity {

    private TextView tv_vidas,tv_score,tvGuia;
    private ImageView iv_score;
    private RadioButton rbNo,rbAmo,rbDonde,rbKanin,rbCuanto,rbKech,rbComo,rbTlen,rbUn,rbSe,rbMi,rbNot;
    private MediaPlayer mp_correcto, mp_incorrecto;
    //Variable para recibir parametro score, recordemos que este no pertenece a la bd
    private String string_score;
    int score,vidas=3;
    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Metodo que quita action bar sin descomponer el color de status bar and navigationbar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAGS_CHANGED,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sec1_nivel4_lec4_juego);

        tv_vidas=findViewById(R.id.tv_vidas);
        tv_score=findViewById(R.id.tv_score);
        iv_score=findViewById(R.id.iv_score);
        tvGuia = findViewById(R.id.tvGuia);
        rbNo=findViewById(R.id.rbNo);
        rbAmo=findViewById(R.id.rbAmo);
        rbDonde=findViewById(R.id.rbDonde);
        rbKanin=findViewById(R.id.rbKanin);
        rbCuanto=findViewById(R.id.rbCuanto);
        rbKech=findViewById(R.id.rbKech);
        rbComo=findViewById(R.id.rbComo);
        rbTlen=findViewById(R.id.rbTlen);
        rbUn=findViewById(R.id.rbUn);
        rbSe=findViewById(R.id.rbSe);
        rbMi=findViewById(R.id.rbMi);
        rbNot=findViewById(R.id.rbNot);

        //Recuperamos el parametro score que viene de la clase anterio
        string_score = getIntent().getStringExtra("score");
        score = Integer.parseInt(string_score);
        tv_score.setText(score + "/24");

        findViewById(R.id.btnComprobarSec1Nivel4).setOnClickListener(new View.OnClickListener() {
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
            //tvGemas.setText("8");
            BD.close();
        }else{
            BD.close();
        }//Fin de conexion de sqlite

        mp_correcto = MediaPlayer.create(this,R.raw.audiocorrecto);
        mp_incorrecto = MediaPlayer.create(this,R.raw.erroraprecor);
    }
    public void Comprobar() {
        if(score >=18 && score <=24) {
            boolean bandera = false;
            if (rbNo.isChecked()) {
                if (rbAmo.isChecked()) {
                    score++;
                    tv_score.setText(score + "/6");
                    rbNo.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbAmo.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbNo.setEnabled(false);
                    rbAmo.setEnabled(false);
                    rbNo.setChecked(false);
                    rbAmo.setChecked(false);
                    mp_correcto.start();
                    bandera = true;
                    BaseDeDatos();
                } else {
                    vidas--;
                    bandera = false;
                    mp_incorrecto.start();
                }
            } else if (rbDonde.isChecked()) {
                if (rbKanin.isChecked()) {
                    score++;
                    tv_score.setText(score + "/6");
                    rbDonde.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbKanin.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbDonde.setEnabled(false);
                    rbKanin.setEnabled(false);
                    rbDonde.setChecked(false);
                    rbKanin.setChecked(false);
                    mp_correcto.start();
                    bandera = true;
                    BaseDeDatos();
                } else {
                    vidas--;
                    bandera = false;
                    mp_incorrecto.start();
                }
            } else if (rbCuanto.isChecked()) {
                if (rbKech.isChecked()) {
                    score++;
                    tv_score.setText(score + "/6");
                    rbCuanto.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbKech.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbCuanto.setEnabled(false);
                    rbKech.setEnabled(false);
                    rbCuanto.setChecked(false);
                    rbKech.setChecked(false);
                    mp_correcto.start();
                    bandera = true;
                    BaseDeDatos();
                } else {
                    vidas--;
                    bandera = false;
                    mp_incorrecto.start();
                }
            } else if (rbComo.isChecked()) {
                if (rbTlen.isChecked()) {
                    score++;
                    tv_score.setText(score + "/6");
                    rbComo.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbTlen.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbComo.setEnabled(false);
                    rbTlen.setEnabled(false);
                    rbComo.setChecked(false);
                    rbTlen.setChecked(false);
                    mp_correcto.start();
                    bandera = true;
                    BaseDeDatos();
                } else {
                    vidas--;
                    bandera = false;
                    mp_incorrecto.start();
                }
            } else if (rbUn.isChecked()) {
                if (rbSe.isChecked()) {
                    score++;
                    tv_score.setText(score + "/6");
                    rbUn.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbSe.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbUn.setEnabled(false);
                    rbSe.setEnabled(false);
                    rbUn.setChecked(false);
                    rbSe.setChecked(false);
                    mp_correcto.start();
                    bandera = true;
                    BaseDeDatos();
                } else {
                    vidas--;
                    bandera = false;
                    mp_incorrecto.start();
                }
            } else if (rbMi.isChecked()) {
                if (rbNot.isChecked()) {
                    score++;
                    tv_score.setText(score + "/6");
                    rbMi.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbNot.setBackgroundResource(R.drawable.radios_correctos_levels_seccion1);
                    rbMi.setEnabled(false);
                    rbNot.setEnabled(false);
                    rbMi.setChecked(false);
                    rbNot.setChecked(false);
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

            //Swich que recupera cada imagen de la carpeta drawable para el score segun sea el caso
            switch (score) {
                case 19:
                    iv_score.setImageResource(R.drawable.scoreuno);
                    break;
                case 20:
                    iv_score.setImageResource(R.drawable.scoredos);
                    break;
                case 21:
                    iv_score.setImageResource(R.drawable.scoretres);
                    break;
                case 22:
                    iv_score.setImageResource(R.drawable.scorecuatro);
                    break;
                case 23:
                    //iv_score.setImageResource(R.drawable.scorecinco);
                    break;
                case 24:
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
                            startActivity(new Intent(Sec1Nivel4Lec4Juego.this,LevelsSection1.class));
                            finish();
                        }
                    });
                    btnSigNivel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(Sec1Nivel4Lec4Juego.this,MainActivity3HomeLevels.class));
                            Toast.makeText(Sec1Nivel4Lec4Juego.this, "Haz desbloqueado el nivel intermedio", Toast.LENGTH_SHORT).show();
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
                        Intent intent = new Intent(Sec1Nivel4Lec4Juego.this, LevelsSection1.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }

        }else {
            //Metodo que nos llevara al siguiente nivel
            Intent intent = new Intent(this,LevelsSection1.class);
            Toast.makeText(this, "Haz terminado los niveles del basico", Toast.LENGTH_SHORT).show();
            /*string_score = String.valueOf(score);
            intent.putExtra("score",string_score);*/
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
                Intent intent = new Intent(Sec1Nivel4Lec4Juego.this, LevelsSection1.class);
                startActivity(intent);
                BaseDeDatos();
                finish();
            }
        });
    }
}