package fvi.at.ua.geoquiz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        Log.d(TAG, "registerSend");
        initialize();
        if (!validate()) {
            Toast.makeText(this, "SingUp has failed", Toast.LENGTH_SHORT).show();
        } else {
            saveInfoUser(rRegister);
            onSingUpSuccess();
        }
    }

     public void onSingUpSuccess() {
         Log.d(TAG, "onSingUpSuccess");
         showDialog(rRegister);
    }

    public boolean validate() {
        Log.d(TAG, "validate");
        //todo what will after the valid input
        boolean valid = true;
        Log.d(TAG, "validate " + valid);
        if(firstName.isEmpty() || firstName.length() > 32) {
            rFirstName.setError("enter First name");
            valid = false;
           // Toast.makeText(this, "Please enter valid NAME", Toast.LENGTH_LONG).show();
        }

        if(lastName.isEmpty()){
            rLastName.setError("enter Last name");
            valid = false;
        }

        if(email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(rEmail.getText().toString()).matches()){
            rEmail.setError("enter email");
            valid = false;
        }

        if(login.isEmpty()) {
            rLogin.setError("enter login");
            valid = false;
        }

        if(password.isEmpty()){
            rPassword.setError("enter password");
            valid = false;
            Log.d(TAG, "validate "+ valid);
        }

        return valid;
    }

    public void initialize() {
        Log.d(TAG, "initialize ");
        firstName = rFirstName.getText().toString().trim();
        lastName = rLastName.getText().toString().trim();
        email = rEmail.getText().toString().trim();
        login = rLogin.getText().toString().trim();
        password = rPassword.getText().toString().trim();
    }

    private void initFind(){
        Log.d(TAG, "initFind ");
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

        String loginPref = sharedPref.getString("login", "");
        String passwordPref = sharedPref.getString("password", "");
        Log.d(TAG, "loginPref " + loginPref + " " + "passwordPref " + passwordPref);

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
