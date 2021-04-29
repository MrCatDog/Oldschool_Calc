package com.example.Calculator_Old.calc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Calculator_Old.R;

public class Calculator extends AppCompatActivity {
    private final static String RESULT_TAG = "result";
    private final static String BUT_RESP_TAG = "button respond";

    private Button zeroBtn;
    private Button oneBtn;
    private Button twoBtn;
    private Button threeBtn;
    private Button fourBtn;
    private Button fiveBtn;
    private Button sixBtn;
    private Button sevenBtn;
    private Button eightBtn;
    private Button nineBtn;

    private Button plusBtn;
    private Button minusBtn;
    private Button multBtn;
    private Button divBtn;

    private Button equalsBtn;
    private Button clearBtn;
    private Button okBtn;

    private TextView result;

    ButtonResponderCalc buttonResponderCalc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        zeroBtn = findViewById(R.id.zero_btn);
        oneBtn = findViewById(R.id.one_btn);
        twoBtn = findViewById(R.id.two_btn);
        threeBtn = findViewById(R.id.three_btn);
        fourBtn = findViewById(R.id.four_btn);
        fiveBtn = findViewById(R.id.five_btn);
        sixBtn = findViewById(R.id.six_btn);
        sevenBtn = findViewById(R.id.seven_btn);
        eightBtn = findViewById(R.id.eight_btn);
        nineBtn = findViewById(R.id.nine_btn);

        plusBtn = findViewById(R.id.plus_btn);
        minusBtn = findViewById(R.id.minus_btn);
        multBtn = findViewById(R.id.mult_btn);
        divBtn = findViewById(R.id.divide_btn);

        equalsBtn = findViewById(R.id.equals_btn);
        clearBtn = findViewById(R.id.clear_btn);
        okBtn = findViewById(R.id.ok_btn);

        result = findViewById(R.id.result);

        buttonResponderCalc = new ButtonResponderCalc(this);

        zeroBtn.setOnClickListener(buttonResponderCalc);
        oneBtn.setOnClickListener(buttonResponderCalc);
        twoBtn.setOnClickListener(buttonResponderCalc);
        threeBtn.setOnClickListener(buttonResponderCalc);
        fourBtn.setOnClickListener(buttonResponderCalc);
        fiveBtn.setOnClickListener(buttonResponderCalc);
        sixBtn.setOnClickListener(buttonResponderCalc);
        sevenBtn.setOnClickListener(buttonResponderCalc);
        eightBtn.setOnClickListener(buttonResponderCalc);
        nineBtn.setOnClickListener(buttonResponderCalc);

        plusBtn.setOnClickListener(buttonResponderCalc);
        minusBtn.setOnClickListener(buttonResponderCalc);
        multBtn.setOnClickListener(buttonResponderCalc);
        divBtn.setOnClickListener(buttonResponderCalc);

        clearBtn.setOnClickListener(buttonResponderCalc);
        equalsBtn.setOnClickListener(buttonResponderCalc);
        okBtn.setOnClickListener(buttonResponderCalc);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(RESULT_TAG, result.getText().toString());
        outState.putBundle(BUT_RESP_TAG, buttonResponderCalc.save());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        result.setText(savedInstanceState.getString(RESULT_TAG));
        buttonResponderCalc.load(savedInstanceState.getBundle(BUT_RESP_TAG));
    }

    public void addResultSymbol(String ch) {
        result.append(ch);
    }

    public void clear() {
        result.setText("");
    }

    public void setAnswer(String answer) {
        result.setText(answer);
    }

    public String getAnswer() {
        return result.getText().toString();
    }
}

