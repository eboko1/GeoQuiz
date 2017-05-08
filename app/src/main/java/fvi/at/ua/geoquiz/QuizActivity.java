package fvi.at.ua.geoquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;

    private int mCurrentIndex = 0;

    private Question[] mQuestionBank = new Question[]{
            new Question(R.string.question_hoverla, true),
            new Question(R.string.question_dnipro, false),
            new Question(R.string.question_kyiv, true),
            new Question(R.string.question_lviv, false)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        //question one with array question Hoverla
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);


        mTrueButton = (Button) findViewById(R.id.true_button);
        mFalseButton = (Button) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mPrevButton = (ImageButton) findViewById(R.id.prev_button);

        mPrevButton.setOnClickListener(this);
        mNextButton.setOnClickListener(this);
        mFalseButton.setOnClickListener(this);
        mTrueButton.setOnClickListener(this);
        mQuestionTextView.setOnClickListener(this);
    }

    @Override
    public void onSaveInstanceState (Bundle savedInstanceState){

        super.onSaveInstanceState(savedInstanceState);
       // Log.d(TAG,"onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
    }

    private void updateQuestion(){
        mQuestionTextView.setText( mQuestionBank[mCurrentIndex].getTextResId());

    }

    private  void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].getIsAnswerTrue();
        int messageResId = 0;
        if (userPressedTrue == answerIsTrue){
            messageResId = R.string.correct_text;
        } else {
            messageResId = R.string.incorrect_text;
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.prev_button:
                if (mCurrentIndex > 0) {
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                    updateQuestion();
                    //Toast.makeText(this, "mCurrentIndexPrev " + mCurrentIndex, Toast.LENGTH_LONG).show();
                } else {
                    mCurrentIndex = 1;
                    mCurrentIndex = (mCurrentIndex - 1) % mQuestionBank.length;
                    updateQuestion();
                }
                    break;
            case R.id.next_button:
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
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
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
                break;
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
