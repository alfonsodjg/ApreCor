package itsz.aprecor;

import static android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;


//Librerias para iniciar sesion en gooogle
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Objects;


public class MainActivity extends AppCompatActivity{

    //Declaraciones de componentes para iniciar sesion con correo
    private TextView tvCrearCuenta,tvResetPass;
    private TextInputEditText edtEmail,edtPass;
    private Button btnLogin;
    FirebaseAuth mAuth;
    AwesomeValidation awesomeValidation;
    //Fin de declaraciones para iniciar sesion con correo

    //Variables para iniciarSesionGoogle
    private static final String TAG = "GoogleActivity";
    private static final int RC_SIGN_IN = 9001;
    private GoogleSignInClient mGoogleSignInClient;
    //Fin de variables iniciarSesionGoogle

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().setNavigationBarColor(getResources().getColor(R.color.blueceil));//Cambia el color del navigation bar solo en esta clase
        //Cambia el color degradado en el status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        @SuppressLint("UseCompatLoadingForDrawables") android.graphics.drawable.Drawable background = MainActivity.this.getResources().getDrawable(R.drawable.background_display2);
        getWindow().setBackgroundDrawable(background);//final de codigo que cambia color del status bar
        //Colorea los iconos del status bar a negros
        //this.getWindow().getDecorView().getWindowInsetsController().setSystemBarsAppearance(APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS);

        //Condicion que valida el nivel del api para que los iconos del status bar se pongan en modo oscuro
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        tvResetPass = findViewById(R.id.tvResetPass);
        mAuth =FirebaseAuth.getInstance();

        //Si ya existe un usuario, solo que nos lleve a los niveles
        FirebaseAuth mAuth =FirebaseAuth.getInstance();
        FirebaseUser user =mAuth.getCurrentUser();
        if(user !=null){
            HomeLevels();
        }

        //Validaciones de EditText al iniciar sesion con Correo
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        awesomeValidation.addValidation(this,R.id.edtEmail, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(this,R.id.edtPass,".{6,}",R.string.invalid_pass);
        //Fin de validaciones al iniciar sesion con Correo

        edtEmail=(TextInputEditText) findViewById(R.id.edtEmail);
        edtPass =(TextInputEditText) findViewById(R.id.edtPass);
        tvCrearCuenta = findViewById(R.id.tvCrearCuenta);
        btnLogin =(Button)findViewById(R.id.btnLogin);
        tvResetPass=(TextView)findViewById(R.id.tvResetPass);


        //Metodo del TextView que lleva a la actividad para restablecer contraseña con correo
        tvResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ResetPassActivity.class);
                startActivity(intent);
                finish();
            }
        });//Fin de Metodo TextView que lleva a la actividad para restablecer contraseña con correo

        //Metodo TextView para crear cuenta con correo
        tvCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2RegistrarCuenta.class);
                startActivity(intent);
                finish();
                /*FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.framelayout,new Fragment(R.layout.fragment_create_acouutn));
                transaction.commit();*/
            }
        });//Fin de metodo TextView para crear cuenta con correo


        FirebaseAuth finalMAuth = mAuth;
        //Metodo para boton Iniciar sesion con correo
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Condicion de validacion
                if (awesomeValidation.validate()) {
                    String mail = edtEmail.getText().toString().trim();
                    String pass = edtPass.getText().toString().trim();

                    finalMAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //Logica por si la validacion de email y pass se cumplen y si se inicia sesion usando el objeto task
                            if(task.isSuccessful()){
                                HomeLevels();
                            }else{
                                String error=((FirebaseAuthException) task.getException()).getErrorCode();
                                TodosErrores(error);
                            }
                        }
                    });;

                }
            }
        });//Fin del metodo del boton para iniciar sesion con CORREO

        //Inicializacion de Iniciar sesion con google
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        //fin de inicializacion de configuracion con google

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        //Fin de inicializacion de iniciarSesionGoogle


        //Metodo boton para iniciar con google
        findViewById(R.id.btnLoginGoogle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });//Fin del metodo del boton para iniciar sesion con google

    }//Fin del oncreate

    //Metodo para ir a la pantalla principal de los niveles SESION CON CORREO
    public void HomeLevels(){
        Intent intent = new Intent(MainActivity.this,MainActivity3HomeLevels.class);
        startActivity(intent);
        finish();
    }//Fin del metodo para pantallaPrincipal de login con correo

    //Metodo para verificar los errores al ingresar datos en los editText con correo
    private void TodosErrores(String error) {

        switch (error) {

            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(MainActivity.this, "El formato del token personalizado es incorrecto. Por favor revise la documentación", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                Toast.makeText(MainActivity.this, "El token personalizado corresponde a una audiencia diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_CREDENTIAL":
                Toast.makeText(MainActivity.this, "La credencial de autenticación proporcionada tiene un formato incorrecto o ha caducado.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_EMAIL":
                Toast.makeText(MainActivity.this, "La dirección de correo electrónico está mal formateada.", Toast.LENGTH_LONG).show();
                edtEmail.setError("La dirección de correo electrónico está mal formateada.");
                edtPass.requestFocus();
                break;

            case "ERROR_WRONG_PASSWORD":
                Toast.makeText(MainActivity.this, "La contraseña no es válida o el usuario no tiene contraseña.", Toast.LENGTH_LONG).show();
                edtPass.setError("la contraseña es incorrecta ");
                edtPass.requestFocus();
                edtPass.setText("");
                break;

            case "ERROR_USER_MISMATCH":
                Toast.makeText(MainActivity.this, "Las credenciales proporcionadas no corresponden al usuario que inició sesión anteriormente..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_REQUIRES_RECENT_LOGIN":
                Toast.makeText(MainActivity.this,"Esta operación es sensible y requiere autenticación reciente. Inicie sesión nuevamente antes de volver a intentar esta solicitud.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                Toast.makeText(MainActivity.this, "Ya existe una cuenta con la misma dirección de correo electrónico pero diferentes credenciales de inicio de sesión. Inicie sesión con un proveedor asociado a esta dirección de correo electrónico.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_EMAIL_ALREADY_IN_USE":
                Toast.makeText(MainActivity.this, "La dirección de correo electrónico ya está siendo utilizada por otra cuenta..   ", Toast.LENGTH_LONG).show();
                edtPass.setError("La dirección de correo electrónico ya está siendo utilizada por otra cuenta.");
                edtPass.requestFocus();
                break;

            case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                Toast.makeText(MainActivity.this, "Esta credencial ya está asociada con una cuenta de usuario diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_DISABLED":
                Toast.makeText(MainActivity.this, "La cuenta de usuario ha sido inhabilitada por un administrador..", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_TOKEN_EXPIRED":
                Toast.makeText(MainActivity.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_NOT_FOUND":
                Toast.makeText(MainActivity.this, "No hay ningún registro de usuario que corresponda a este identificador. Es posible que se haya eliminado al usuario.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_USER_TOKEN":
                Toast.makeText(MainActivity.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_OPERATION_NOT_ALLOWED":
                Toast.makeText(MainActivity.this, "Esta operación no está permitida. Debes habilitar este servicio en la consola.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_WEAK_PASSWORD":
                Toast.makeText(MainActivity.this, "La contraseña proporcionada no es válida..", Toast.LENGTH_LONG).show();
                edtPass.setError("La contraseña no es válida, debe tener al menos 6 caracteres");
                edtPass.requestFocus();
                break;

        }

    }//Fin del metodo para vericar errores al iniciar sesion con correo

    // Metodo onStart de login con google
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }//Fin del metodo onStart con google

    //Metodo onActivityResult de login con google
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
            }
        }
    }// final onActivityResult

    //Metodo para autenticar con google
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }//Fin de autenticacion con google

    // Metodo para iniciar sesion con google
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }//Fin metodo de inicio sesion con google

    //Metodo para verificar si el usuario accedio
    private void updateUI(FirebaseUser user) {
        FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
        if(user1 != null){
            Intent intent = new Intent(MainActivity.this, MainActivity3HomeLevels.class);
            startActivity(intent);
            finish();
        }
    }//Fin del metodo de verificacion con google si el usuario accedio

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,MainActivity3HomeLevels.class));
        finish();
        super.onBackPressed();
    }
}

