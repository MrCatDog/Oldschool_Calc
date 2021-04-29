package com.example.Calculator_Old;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public final static String SAVED_TAG = "saved1";

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

        // изначально я ошибочно пологал, что хранить ответы можно и в Set, но,
        // вспомнив, что значения в Set уникальны, убрал эту реализацию, начав хранить данные в String.
        // Когда всё было готово, я долго ловил ошибку каста Set в String,
        // хотя сам Set в коде более негде не фигурировал.
        // полагаю, что записанные данные в формате Set остались на тестируемом устройстве и,
        // вызываясь по идентичному ключу, создавали ошибку.
        // вопрос № 1: как программно почистить за собой?
        // вопрос № 2: как вручную вычистить это с тестируемого устройства?
        // отчистка данных приложения и его удаления не помогли.
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        buttonResponderMain.loadAnswers(sharedPreferences.getString(SAVED_TAG, null));
    }

    //Сначала накодил это в onDestroy, но выходило не очень.
    // Вспомнил, что там нельзя сохранять данные, но так и не нагуглил инфу об этом. Нужно повторить.
    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        String saved = buttonResponderMain.saveAnswers();
        editor.putString(SAVED_TAG, saved);
        editor.apply();
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
