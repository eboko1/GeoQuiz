package fvi.at.ua.geoquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    private EditText lName;
    private EditText lPassword;
    private Button lSend;
    private TextView lRegister;
    private static final String TAG = "LoginActivity ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate LoginActivity");
        setContentView(R.layout.activity_login);

        lName = (EditText)findViewById(R.id.name_editText);
        lPassword = (EditText)findViewById(R.id.password_editText);
        lSend = (Button) findViewById(R.id.send_button);
        lRegister =(TextView)findViewById(R.id.register_textView);


        lRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick register text view");
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
    }
}
