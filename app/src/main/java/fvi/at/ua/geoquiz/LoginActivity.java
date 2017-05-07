package fvi.at.ua.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText lName;
    private EditText lPassword;
    private Button lSend;
    private TextView lRegister;
    private static final String TAG = "LoginActivity ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Log.d(TAG,"onCreate LoginActivity");

        lName = (EditText)findViewById(R.id.name_editText);
        lPassword = (EditText)findViewById(R.id.password_editText);
        lSend = (Button) findViewById(R.id.send_in_button);
        lRegister =(TextView)findViewById(R.id.register_textView);


        lRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Log.d(TAG, "onClick register Here! text view");
                        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                        LoginActivity.this.startActivity(registerIntent);
            }

        });
        lSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lName.getText().toString().equals("admin") && lPassword.getText().toString().equals("admin")){
                    Intent quizIntent = new Intent(LoginActivity.this, QuizActivity.class);
                    LoginActivity.this.startActivity(quizIntent);
                } else {
                    Toast.makeText(LoginActivity.this,"Please go to Register form",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
