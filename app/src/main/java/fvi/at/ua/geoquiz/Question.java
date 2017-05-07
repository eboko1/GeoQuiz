package fvi.at.ua.geoquiz;

import android.util.Log;

/**
 * Created by Vika on 28.04.2017.
 */

public class Question {
    private boolean mAnswerTrue;
    private int mTextId;
    private static final String TAG = "Question ";

    public Question(int textResId, boolean answerTrue) {
            Log.d(TAG,"Constructor Question");
            mAnswerTrue = answerTrue;
            mTextId = textResId;
    }

    public boolean getIsAnswerTrue() {
        return mAnswerTrue;
    }

    public int getTextResId() {
        return mTextId;
    }

    public void setAnswerTrue(boolean mAnswerTrue) {
        this.mAnswerTrue = mAnswerTrue;
    }

    public void setTextId(int mTextId) {
        this.mTextId = mTextId;
    }
}
