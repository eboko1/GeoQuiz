package fvi.at.ua.geoquiz;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Vika on 08.05.2017.
 */

public class CheatActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView chAnswerTView;
    private Button chAnswerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_cheat);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorActionBar)));

        chAnswerTView = (TextView)findViewById(R.id.answer_tView);
        chAnswerBtn =(Button)findViewById(R.id.answer_btn);

        chAnswerBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.answer_btn:

                chAnswerTView.setText("Waiting...");
                break;
        }
    }
    
}
