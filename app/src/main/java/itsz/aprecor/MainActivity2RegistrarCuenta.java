package itsz.aprecor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;

import java.util.Objects;

public class MainActivity2RegistrarCuenta extends AppCompatActivity {


    private TextInputEditText edtRegistrarEmail,edtRegistrarPass;
    private Button btnRegistrar;

    FirebaseAuth firebaseAuth;
    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity2_registrar_cuenta);
        Objects.requireNonNull(getSupportActionBar()).setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.statusBar)));


        firebaseAuth = FirebaseAuth.getInstance();
        awesomeValidation =new AwesomeValidation(ValidationStyle.BASIC);
        //Valida los campoos de los editText
        awesomeValidation.addValidation(this,R.id.edtRegistrarEmail,Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(this,R.id.edtRegistrarPass,".{6,}",R.string.invalid_pass);

        edtRegistrarEmail =(TextInputEditText) findViewById(R.id.edtRegistrarEmail);
        edtRegistrarPass =(TextInputEditText) findViewById(R.id.edtRegistrarPass);
        btnRegistrar = (Button) findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String mail= edtRegistrarEmail.getText().toString().trim();
                String contras= edtRegistrarPass.getText().toString().trim();
                //Condicional para validar los campos
                if(awesomeValidation.validate()){
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(mail,contras).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()){
                                Toast.makeText(MainActivity2RegistrarCuenta.this, "Usuario creado con exito", Toast.LENGTH_SHORT).show();
                                edtRegistrarEmail.setText("");
                                edtRegistrarEmail.setText("");
                                startActivity(new Intent(MainActivity2RegistrarCuenta.this,MainActivity3HomeLevels.class));
                                finish();
                            }else{

                                String error =((FirebaseAuthException) task.getException()).getErrorCode();
                                TodosErrores(error);

                            }
                        }
                    });
                }else{
                    Toast.makeText(MainActivity2RegistrarCuenta.this, "Complete todos los datos", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }

    private void TodosErrores(String error) {

        switch (error) {

            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(MainActivity2RegistrarCuenta.this, "El formato del token personalizado es incorrecto. Por favor revise la documentación", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                Toast.makeText(MainActivity2RegistrarCuenta.this, "El token personalizado corresponde a una audiencia diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_CREDENTIAL":
                Toast.makeText(MainActivity2RegistrarCuenta.this, "La credencial de autenticación proporcionada tiene un formato incorrecto o ha caducado.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_EMAIL":
                Toast.makeText(MainActivity2RegistrarCuenta.this, "La dirección de correo electrónico está mal formateada.", Toast.LENGTH_LONG).show();
                edtRegistrarEmail.setError("La dirección de correo electrónico está mal formateada.");
                edtRegistrarPass.requestFocus();
                break;

            case "ERROR_WRONG_PASSWORD":
                Toast.makeText(MainActivity2RegistrarCuenta.this, "La contraseña no es válida o el usuario no tiene contraseña.", Toast.LENGTH_LONG).show();
                edtRegistrarPass.setError("la contraseña es incorrecta ");
                edtRegistrarPass.requestFocus();
                edtRegistrarPass.setText("");
                break;

            case "ERROR_USER_MISMATCH":
                Toast.makeText(MainActivity2RegistrarCuenta.this, "Las credenciales proporcionadas no corresponden al usuario que inició sesión anteriormente..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_REQUIRES_RECENT_LOGIN":
                Toast.makeText(MainActivity2RegistrarCuenta.this,"Esta operación es sensible y requiere autenticación reciente. Inicie sesión nuevamente antes de volver a intentar esta solicitud.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                Toast.makeText(MainActivity2RegistrarCuenta.this, "Ya existe una cuenta con la misma dirección de correo electrónico pero diferentes credenciales de inicio de sesión. Inicie sesión con un proveedor asociado a esta dirección de correo electrónico.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_EMAIL_ALREADY_IN_USE":
                Toast.makeText(MainActivity2RegistrarCuenta.this, "La dirección de correo electrónico ya está siendo utilizada por otra cuenta..   ", Toast.LENGTH_LONG).show();
                edtRegistrarPass.setError("La dirección de correo electrónico ya está siendo utilizada por otra cuenta.");
                edtRegistrarPass.requestFocus();
                break;

            case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                Toast.makeText(MainActivity2RegistrarCuenta.this, "Esta credencial ya está asociada con una cuenta de usuario diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_DISABLED":
                Toast.makeText(MainActivity2RegistrarCuenta.this, "La cuenta de usuario ha sido inhabilitada por un administrador..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_TOKEN_EXPIRED":
                Toast.makeText(MainActivity2RegistrarCuenta.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_NOT_FOUND":
                Toast.makeText(MainActivity2RegistrarCuenta.this, "No hay ningún registro de usuario que corresponda a este identificador. Es posible que se haya eliminado al usuario.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_USER_TOKEN":
                Toast.makeText(MainActivity2RegistrarCuenta.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_OPERATION_NOT_ALLOWED":
                Toast.makeText(MainActivity2RegistrarCuenta.this, "Esta operación no está permitida. Debes habilitar este servicio en la consola.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_WEAK_PASSWORD":
                Toast.makeText(MainActivity2RegistrarCuenta.this, "La contraseña proporcionada no es válida..", Toast.LENGTH_LONG).show();
                edtRegistrarPass.setError("La contraseña no es válida, debe tener al menos 6 caracteres");
                edtRegistrarPass.requestFocus();
                break;

        }

    }
    //Metodo para mostrar overflow
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.overregresar,menu);
        return true;
    }
    //Metodo para darle accion al item seleccionado del menuoverflow
    public boolean onOptionsItemSelected(MenuItem item){
        int id= item.getItemId();
        if(id == R.id.itemRegresar){
            Intent intent = new Intent(MainActivity2RegistrarCuenta.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MainActivity2RegistrarCuenta.this,MainActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}