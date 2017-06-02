package fvi.at.ua.geoquiz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaCodec;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private EditText rFirstName,rLastName,rEmail, rLogin, rPassword;
    private String firstName, lastName, email, login, password;
    private Button rRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate register class");
        setContentView(R.layout.activity_register);

       initFind();

        rRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Register button click");

                registerSend();
                //saveInfoUser(rRegister);
            }
        });

    }

    public void registerSend() {
        initialize();
        if (!validate()) {
            Toast.makeText(this, "SingUp has failed", Toast.LENGTH_SHORT).show();
        } else {
            saveInfoUser(rRegister);
            onSingUpSuccess();
        }
    }

     public void onSingUpSuccess() {
         showDialog(rRegister);
    }

    public boolean validate() {
        //todo what will after the valid input
        boolean valid = true;

        if(firstName.isEmpty() || firstName.length() > 32) {
            rFirstName.setError("Please enter valid First name");
            valid = false;
           // Toast.makeText(this, "Please enter valid NAME", Toast.LENGTH_LONG).show();
        }

        if(lastName.isEmpty()){
            rLastName.setError("Please enter valid Last name");
            valid = false;
            Toast.makeText(this, "Please enter Last NAME", Toast.LENGTH_LONG).show();
        }

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(rEmail.getText().toString()).matches()){
            rEmail.setError("Please enter valid email");
            valid = false;
            // Toast.makeText(this, "Please enter valid EMAIL", Toast.LENGTH_LONG).show();
        }

        if(login.isEmpty()) {
            rLogin.setError("Please enter valid login");
            valid = false;
            //Toast.makeText(this, "Please enter Login", Toast.LENGTH_LONG).show();
        }

        if(password.isEmpty()){
            rPassword.setError("Please enter valid password");
            valid = false;
           // Toast.makeText(this, "Please enter PASSWORD", Toast.LENGTH_LONG).show();
        }

        return valid;
    }

    public void initialize() {
        firstName = rFirstName.getText().toString().trim();
        lastName = rLastName.getText().toString().trim();
        email = rEmail.getText().toString().trim();
        login = rLogin.getText().toString().trim();
        password = rPassword.getText().toString().trim();
    }

    private void initFind(){
        rFirstName = (EditText)findViewById(R.id.first_name_editText);
        rLastName = (EditText)findViewById(R.id.last_name_editText);
        rPassword = (EditText)findViewById(R.id.password_editText);
        rEmail = (EditText)findViewById(R.id.email_editText);
        rLogin = (EditText)findViewById(R.id.login_editText);

        rRegister = (Button)findViewById(R.id.send_button);

    }



    //Save user login info
    public void saveInfoUser(View v){
        SharedPreferences sharedPref = getSharedPreferences("infoUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

            editor.putString("login", rLogin.getText().toString());
            editor.putString("password", rPassword.getText().toString());
            editor.commit();
            Toast.makeText(RegisterActivity.this, "Data saved!", Toast.LENGTH_SHORT).show();
    }

    public void showDialog(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Welcome!!!")
                .setMessage("Do you want to go to the login form?")
                .setCancelable(true)
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(RegisterActivity.this, "Your data is saved, go to the login form", Toast.LENGTH_LONG).show();
                    }
                })
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        RegisterActivity.this.startActivity(registerIntent);
                    }
                })
                //for .setCancelable(true)
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        Toast.makeText(RegisterActivity.this, "Go to the login form", Toast.LENGTH_LONG).show();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
