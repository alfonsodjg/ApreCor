package itsz.aprecor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPassActivity extends AppCompatActivity {

    private TextInputEditText edtResetPass;
    private Button btnResetPass;
    //private ProgressDialog proDialiog;
    private String email;
    AwesomeValidation awesomeValidation;

    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.statusBar)));

        mAuth = FirebaseAuth.getInstance();



        edtResetPass=(TextInputEditText) findViewById(R.id.edtResetPass);
        btnResetPass=(Button) findViewById(R.id.btnResetPass);

        awesomeValidation = new AwesomeValidation(ValidationStyle.COLORATION);
        awesomeValidation.addValidation(this,R.id.edtResetPass, Patterns.EMAIL_ADDRESS,R.string.invalid_name);
        btnResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = edtResetPass.getText().toString().trim();
                if (!email.isEmpty()){
                    resetPassword();
                }else{
                    Toast.makeText(ResetPassActivity.this, "Debe ingresar un correo", Toast.LENGTH_SHORT).show();
                }
                //proDialiog.dismiss();
            }
        });

    }//Fin del oncreate

    //Metodo para restablecer contraseña
    private void resetPassword() {
        //Idioma en el que llegara el correo
        mAuth.setLanguageCode("es");
        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(ResetPassActivity.this, "Se a enviado un correro para reestablecer contraseña", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ResetPassActivity.this,MainActivity.class));
                    finish();
                    edtResetPass.setText("");
                }else{
                    Toast.makeText(ResetPassActivity.this, "No se pudo enviar el correo de reestablecimiento", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Metodos para inflar menu overflow
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overregresar,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if (id == R.id.itemRegresar){
            Intent intent = new Intent(ResetPassActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ResetPassActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}