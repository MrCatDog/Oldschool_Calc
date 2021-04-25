package com.example.Calculator_Old;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button saveBtn;
    private Button calcBtn;

    private TextView currentAnswer;
    private TextView savedNums;

    private ButtonResponderMain buttonResponderMain;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        saveBtn = findViewById(R.id.save_btn);
        calcBtn = findViewById(R.id.calc_btn);

        currentAnswer = findViewById(R.id.current_answer);
        savedNums = findViewById(R.id.saved_numbers);

        buttonResponderMain = new ButtonResponderMain(this);

        saveBtn.setOnClickListener(buttonResponderMain);
        calcBtn.setOnClickListener(buttonResponderMain);
    }

    //да ёклмн, это вроде бы логика, а не UI, но как и куда его девать отсюда?
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ButtonResponderMain.REQUEST_CODE_DO_CALCULATIONS) {
            if (resultCode == RESULT_OK) {
                String currAnswer = data.getStringExtra(ButtonResponderCalc.ANSWER_TAG);
                buttonResponderMain.setCurrentAnswer(currAnswer);
                setCurrentAnswerText(currAnswer);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void setCurrentAnswerText(String currentAnswer) {
        this.currentAnswer.setText(currentAnswer);
    }

    public void setSavedNumsText(String savedNumsText) {
        this.savedNums.setText(savedNumsText);
    }
}
