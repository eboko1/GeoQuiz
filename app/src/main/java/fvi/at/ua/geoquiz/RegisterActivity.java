package fvi.at.ua.geoquiz;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private EditText rFirstName;
    private EditText rLastName;
    private EditText rPassword;
    private EditText rEmail;
    private EditText rLogin;
    private Button rRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate register class");
        setContentView(R.layout.activity_register);

       init();
        rRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Register button click");
                saveInfoUser(rRegister);
            }
        });

    }
    private void init(){
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


        if(rFirstName.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter NAME", Toast.LENGTH_LONG).show();

        } else if(rLastName.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter Last NAME", Toast.LENGTH_LONG).show();

        } else if(rEmail.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter EMAIL", Toast.LENGTH_LONG).show();

        } else if(rLogin.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter Login", Toast.LENGTH_LONG).show();

        } else if(rPassword.getText().toString().isEmpty()){
            Toast.makeText(this, "Please enter PASSWORD", Toast.LENGTH_LONG).show();

        } else if (rEmail.getText().toString().contains("@") == false) {
            Toast.makeText(this, "Please enter valid EMAIL", Toast.LENGTH_LONG).show();
        } else {
            editor.putString("login", rLogin.getText().toString());
            editor.putString("password", rPassword.getText().toString());
            editor.commit();
            Toast.makeText(RegisterActivity.this, "Data saved!", Toast.LENGTH_SHORT).show();

            showDialog(rRegister);

        }


    }

    public void showDialog(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("AlertDialog")
                .setMessage("You back to login form?")
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       Toast.makeText(RegisterActivity.this, "Go to login form", Toast.LENGTH_SHORT).show();
                    }
                })
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        RegisterActivity.this.startActivity(registerIntent);
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}
