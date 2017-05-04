package fvi.at.ua.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

        rFirstName = (EditText)findViewById(R.id.first_name_editText);
        rLastName = (EditText)findViewById(R.id.last_name_editText);
        rPassword = (EditText)findViewById(R.id.password_editText);
        rEmail = (EditText)findViewById(R.id.email_editText);
        rLogin = (EditText)findViewById(R.id.login_editText);

        rRegister = (Button)findViewById(R.id.send_button);

        rRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Register button click");
                saveInfoUser(rRegister);
               // Intent registerIntent = new Intent(RegisterActivity.this,QuizActivity.class);
               // RegisterActivity.this.startActivity(registerIntent);
            }
        });
    }
    //Save user login info
    public void saveInfoUser(View v){
        SharedPreferences sharedPreferences = getSharedPreferences("infoUser", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        //editor.putString("firstname",rFirstName.getText().toString());
        //editor.putString("lastname",rLastName.getText().toString());
       // editor.putString("password",rPassword.getText().toString());
        if(rFirstName.getText().toString().equals("") || rLastName.getText().toString().equals("") ||
                rEmail.getText().toString().equals("") || rLogin.getText().toString().equals("") ||
                rPassword.getText().toString().equals("")){
            Toast.makeText(this, "Please enter data", Toast.LENGTH_LONG).show();
        } else {
            editor.putString("login", rLogin.getText().toString());
            editor.putString("password", rPassword.getText().toString());

            Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();
        }
    }

}
