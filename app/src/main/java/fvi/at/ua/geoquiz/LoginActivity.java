package fvi.at.ua.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText lName;
    private EditText lPassword;
    private Button lSend;
    private TextView lRegister;

     LoginButton loginButton;
     CallbackManager callbackManager;

    private static final String TAG = "LoginActivity ";

    private static final String KEY_LOGIN ="login";
    private static final String KEY_PASSWORD ="password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d(TAG,"onCreate LoginActivity");

        init();

        lRegister.setOnClickListener(this);
        lSend.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        //saved data in loginEditText
        loadUserLogin();
        loadUserPassword();

    }

    private  void clean(){
        lPassword.setText("");
    }
    public void setLogin(){
        lName.setText(loadUserLogin());
    }
    private void init(){
        lName = (EditText)findViewById(R.id.name_editText);
        lPassword = (EditText)findViewById(R.id.password_editText);
        lSend = (Button) findViewById(R.id.send_in_button);
        lRegister =(TextView)findViewById(R.id.register_textView);

        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton)findViewById(R.id.facebookButton);
    }

    //loading data with file infoUser .INI
    private String loadUserLogin(){
        SharedPreferences shpref = getSharedPreferences("infoUser", Context.MODE_PRIVATE);
        String login = shpref.getString(KEY_LOGIN, "");
        return login;
    }
    //loading data with file infoUser .INI
    private String loadUserPassword(){
        SharedPreferences shpref = getSharedPreferences("infoUser", Context.MODE_PRIVATE);
        String password = shpref.getString(KEY_PASSWORD, "");
       // lPassword.setText(password);
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
                } else if ((lName.getText().toString().isEmpty()) ) {
                        lName.setError("enter name");
                } else if (lPassword.getText().toString().isEmpty()){
                        lPassword.setError("enter password");
                } else {
                         Toast.makeText(LoginActivity.this,"Please!!! go to Register form",Toast.LENGTH_LONG).show();
                }
            break;

            case R.id.facebookButton:
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Intent i = new Intent(LoginActivity.this, QuizActivity.class);
                        startActivity(i);
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(LoginActivity.this, "Login cancelled", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(LoginActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }
                });
            break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
        clean();
        Log.d(TAG,"onResume() called");
    }
    @Override
    protected void onPause() {
        super.onPause();

    }

    // pass result login callbackManager
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }


}
