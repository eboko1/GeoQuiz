package fvi.at.ua.geoquiz;

/**
 * Created by Vika on 28.04.2017.
 */

public class Question {
    private boolean mAnswerTrue;
    private int mTextId;

    public Question(int textResId, boolean answerTrue) {
            mAnswerTrue = answerTrue;
            mTextId = textResId;
    }

    public boolean isAnswerTrue() {
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
