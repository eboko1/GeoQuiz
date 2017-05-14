package fvi.at.ua.geoquiz;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Vika on 08.05.2017.
 */

public class CheatActivity extends AppCompatActivity  {

    private static final String EXTRA_ANSWER_IS_TRUE ="fvi.at.ua.geoquiz.answer_is_true";
    private static final String EXTRA_ANSWER_SHOWN ="fvi.at.ua.geoquiz.answer_shown";

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


        //add animation
        chAnswerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
                if(chAnswerIsTrue == true) {
                    chAnswerTView.setText(getText(R.string.true_button));
                } else {
                    chAnswerTView.setText(getText(R.string.false_button));
                }
                   setAnswerShownResult(true);
                //add animation code
                int cx = chAnswerBtn.getWidth() / 2;
                int cy = chAnswerBtn.getHeight() / 2;
                float radius = chAnswerBtn.getWidth();

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Animator anim = ViewAnimationUtils.createCircularReveal(chAnswerBtn, cx, cy, radius, 0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            chAnswerTView.setVisibility(View.VISIBLE);
                            chAnswerBtn.setVisibility(View.INVISIBLE);
                        }
                    });
                    anim.start();
                }
            }
        });

    }

    private void setAnswerShownResult(boolean isAnswerShown) {
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);
        setResult(RESULT_OK, data);
    }

    // get data from QuizActivity
    public static Intent cheatIntent(Context packageContext, boolean answerTrue){
        Intent i = new Intent(packageContext, CheatActivity.class);

        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerTrue);
        return i;
    }

    public static boolean wasAnswerShown(Intent result){
        return result.getBooleanExtra(EXTRA_ANSWER_SHOWN, false);
    }
}
