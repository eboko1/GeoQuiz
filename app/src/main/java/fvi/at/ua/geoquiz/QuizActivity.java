package fvi.at.ua.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final  int REQUEST_CODE_CHEAT = 0;



    private Button qTrueButton;
    private Button qFalseButton;
    private ImageButton qNextButton;
    private ImageButton qPrevButton;
    private TextView qQuestionTextView;

    private TextView qCheatTextV;
    private TextView rightTextView;
    private TextView wrongTextView;

    // count score
    private int qRight = 0;
    private int qWrong = 0;
    private int qCurrentIndex = 0;
    private boolean qIsCheater;

    private Question[] qQuestionBank = new Question[]{
            new Question(R.string.question_hoverla, true),
            new Question(R.string.question_dnipro, false),
            new Question(R.string.question_kyiv, true),
            new Question(R.string.question_lviv, false)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorActionBar)));

        if (savedInstanceState != null) {
            qCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
            qRight = savedInstanceState.getInt("right");
            qWrong = savedInstanceState.getInt("wrong");
        }
        qQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        //question one with array question Hoverla
        int question = qQuestionBank[qCurrentIndex].getTextResId();
        qQuestionTextView.setText(question);


        qTrueButton = (Button)findViewById(R.id.true_button);
        qFalseButton = (Button)findViewById(R.id.false_button);
        qNextButton = (ImageButton)findViewById(R.id.next_button);
        qPrevButton = (ImageButton)findViewById(R.id.prev_button);

        qCheatTextV = (TextView)findViewById(R.id.cheat_textView);

        rightTextView = (TextView)findViewById(R.id.right_textView);
        rightTextView.setText(String.valueOf(qRight));

        wrongTextView = (TextView)findViewById(R.id.wrong_textView);
        wrongTextView.setText(String.valueOf(qWrong));

        qPrevButton.setOnClickListener(this);
        qNextButton.setOnClickListener(this);
        qFalseButton.setOnClickListener(this);
        qTrueButton.setOnClickListener(this);
        qQuestionTextView.setOnClickListener(this);

        qCheatTextV.setOnClickListener(this);
    }
    private  void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = qQuestionBank[qCurrentIndex].getIsAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_toast;
            qRight++;
            rightTextView.setText(String.valueOf(qRight));
        } else {
            messageResId = R.string.incorrect_toast;
            qWrong++;
            wrongTextView.setText(String.valueOf(qWrong));
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onSaveInstanceState (Bundle savedInstanceState){

        super.onSaveInstanceState(savedInstanceState);
       // Log.d(TAG,"onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, qCurrentIndex);
        savedInstanceState.putInt("right", qRight);
        savedInstanceState.putInt("wrong", qWrong);
    }

    private void updateQuestion(){
        qQuestionTextView.setText( qQuestionBank[qCurrentIndex].getTextResId());

    }



    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.prev_button:
                if (qCurrentIndex > 0) {
                    qCurrentIndex = (qCurrentIndex - 1) % qQuestionBank.length;
                    updateQuestion();
                    //Toast.makeText(this, "mCurrentIndexPrev " + mCurrentIndex, Toast.LENGTH_LONG).show();
                } else {
                    qCurrentIndex = 1;
                    qCurrentIndex = (qCurrentIndex - 1) % qQuestionBank.length;
                    updateQuestion();
                }
                    break;
            case R.id.next_button:
                qCurrentIndex = (qCurrentIndex + 1) % qQuestionBank.length;
                updateQuestion();
                //Toast.makeText(this, "mCurrentIndexNext "+mCurrentIndex,Toast.LENGTH_LONG).show();
                break;
            case R.id.false_button:
                checkAnswer(false);
                // Toast.makeText(this, R.string.correct_text,Toast.LENGTH_LONG).show();
                break;
            case R.id.true_button:
                checkAnswer(true);
                // Toast.makeText(QuizActivity.this, R.string.incorrect_text,Toast.LENGTH_LONG).show();
                break;
            case R.id.question_text_view:
                qCurrentIndex = (qCurrentIndex + 1) % qQuestionBank.length;
                updateQuestion();
                break;
            case R.id.cheat_textView:
                //start cheat activity
               // Intent intent = new Intent(this, CheatActivity.class);
                boolean answerIsTrue = qQuestionBank[qCurrentIndex].getIsAnswerTrue();
                Intent intent = CheatActivity.cheatIntent(this,answerIsTrue);
                //startActivity(intent);
                startActivityForResult(intent, REQUEST_CODE_CHEAT );
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT){
            if(data == null){
                return;
            }
            qIsCheater =CheatActivity.wasAnswerShown(data);
        }
    }
    @Override
    public void onStart(){
        super.onStart();
        Log.d(TAG,"onStart() called");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG,"onPause() called");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.d(TAG,"onResume() called");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.d(TAG,"onStop() called");
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy() called");
    }


}
