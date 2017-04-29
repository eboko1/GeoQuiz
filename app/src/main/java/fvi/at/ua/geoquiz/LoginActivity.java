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
    private EditText rName;
    private EditText rPassword;
    private Button rSend;
    private TextView rRegister;
    private static final String TAG = "LoginActivity ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
        setContentView(R.layout.activity_login);

        rName = (EditText)findViewById(R.id.name_editText);
        rPassword = (EditText)findViewById(R.id.password_editText);
        rSend = (Button) findViewById(R.id.send_button);
        rRegister =(TextView)findViewById(R.id.register_textView);


        rRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"onClick register");
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
    }
}
