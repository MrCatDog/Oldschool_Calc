package com.example.Calculator_Old;

import android.content.Intent;
import android.view.View;

import java.util.Stack;

public class ButtonResponderMain implements View.OnClickListener {

    public static final int REQUEST_CODE_DO_CALCULATIONS = 1;
    public static final int STACK_SIZE = 5;

    public static class SizedStack<T> extends Stack<T> {
        private final int maxSize;

        public SizedStack(int size) {
            super();
            this.maxSize = size;
        }

        @Override
        public T push(T object) {
            while (this.size() >= maxSize) {
                this.remove(0);
            }
            return super.push(object);
        }
    }

    private final MainActivity mainActivity;
    private final SizedStack<String> savedNums = new SizedStack<>(STACK_SIZE);
    private String currentAnswer = "";
    private final String delimiter;

    public ButtonResponderMain(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        delimiter = mainActivity.getResources().getString(R.string.saved_nums_delimiter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.calc_btn:
                mainActivity.startActivityForResult(new Intent(mainActivity, Calculator.class), REQUEST_CODE_DO_CALCULATIONS);
                break;
            case R.id.save_btn:
                if (currentAnswer.equals("")) {
                    return;
                }
                savedNums.push(currentAnswer);

                //API>=26
                //mainActivity.setSavedNumsText(String.join("\n", savedNums));
                //а ещё и не в том порядке пойдёт. проще уж так:

                StringBuilder stringBuilder = new StringBuilder();
                for (int i = savedNums.size() - 1; i > 0; i--) {
                    stringBuilder.append(savedNums.get(i)).append(delimiter);
                }
                stringBuilder.append(savedNums.get(0));
                mainActivity.setSavedNumsText(stringBuilder.toString());
                break;
        }
    }

    public void setCurrentAnswer(String currentAnswer) {
        this.currentAnswer = currentAnswer;
    }
}
