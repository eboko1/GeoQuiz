package fvi.at.ua.geoquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
    private static final String EXTRA_ANSWER_IS_TRUE ="fvi.at.ua.geoquiz.answer_is_true";

    private TextView chAnswerTView;
    private Button chAnswerBtn;

    private boolean chAnswerIsTrue;

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
                chAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

                if(chAnswerIsTrue == true) {
                    chAnswerTView.setText(getText(R.string.true_button));
                } else {
                    chAnswerTView.setText(getText(R.string.false_button));

                }
                break;
        }
    }

    // get data from QuizActivity
    public static Intent cheatIntent(Context packageContext, boolean answerTrue){
        Intent i = new Intent(packageContext, CheatActivity.class);

        i.putExtra(EXTRA_ANSWER_IS_TRUE,answerTrue);
        return i;
    }
}
