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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText lName;
    private EditText lPassword;
    private Button lSend;
    private TextView lRegister;
    private static final String TAG = "LoginActivity ";

    private static final String KEY_LOGIN ="login";
    private static final String KEY_PASSWORD ="password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d(TAG,"onCreate LoginActivity");

        lName = (EditText)findViewById(R.id.name_editText);
        lPassword = (EditText)findViewById(R.id.password_editText);
        lSend = (Button) findViewById(R.id.send_in_button);
        lRegister =(TextView)findViewById(R.id.register_textView);

        lRegister.setOnClickListener(this);
        lSend.setOnClickListener(this);

        //saved data in loginEditText
        loadUserLogin();
        loadUserPassword();
    }

    //loading data with file infoUser .INI
    private String loadUserLogin(){
        SharedPreferences shpref = getSharedPreferences("infoUser", Context.MODE_PRIVATE);
        String login = shpref.getString(KEY_LOGIN, "");
        lName.setText(login);

        return login;
    }
    //loading data with file infoUser .INI
    private String loadUserPassword(){
        SharedPreferences shpref = getSharedPreferences("infoUser", Context.MODE_PRIVATE);
        String password = shpref.getString(KEY_PASSWORD, "");
        lPassword.setText(password);
        return password;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.register_textView:
                Log.d(TAG, "onClick register Here! text view");
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
                break;
            case R.id.send_in_button:
                if(lName.getText().toString().equals(loadUserLogin()) && lPassword.getText().toString().equals(loadUserPassword())){
                    Intent quizIntent = new Intent(LoginActivity.this, QuizActivity.class);
                    LoginActivity.this.startActivity(quizIntent);
                } else {
                    Toast.makeText(LoginActivity.this,"Please go to Register form",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    protected void onDestroy(){

        super.onDestroy();


    }
}
