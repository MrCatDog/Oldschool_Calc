package com.example.ui_calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final static String EXPRESSION_TAG = "expression";
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

    private TextView expression;
    private TextView result;

    ButtonResponder buttonResponder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

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

        expression = findViewById(R.id.expression);
        result = findViewById(R.id.result);

        buttonResponder = new ButtonResponder(this);

        zeroBtn.setOnClickListener(buttonResponder);
        oneBtn.setOnClickListener(buttonResponder);
        twoBtn.setOnClickListener(buttonResponder);
        threeBtn.setOnClickListener(buttonResponder);
        fourBtn.setOnClickListener(buttonResponder);
        fiveBtn.setOnClickListener(buttonResponder);
        sixBtn.setOnClickListener(buttonResponder);
        sevenBtn.setOnClickListener(buttonResponder);
        eightBtn.setOnClickListener(buttonResponder);
        nineBtn.setOnClickListener(buttonResponder);

        plusBtn.setOnClickListener(buttonResponder);
        minusBtn.setOnClickListener(buttonResponder);
        multBtn.setOnClickListener(buttonResponder);
        divBtn.setOnClickListener(buttonResponder);

        clearBtn.setOnClickListener(buttonResponder);
        equalsBtn.setOnClickListener(buttonResponder);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXPRESSION_TAG, expression.getText().toString());
        outState.putString(RESULT_TAG, result.getText().toString());
        outState.putBundle(BUT_RESP_TAG, buttonResponder.save());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        expression.setText(savedInstanceState.getString(EXPRESSION_TAG));
        result.setText(savedInstanceState.getString(RESULT_TAG));
        buttonResponder.load(savedInstanceState.getBundle(BUT_RESP_TAG));
    }

    //хотелось бы сохранить ссылку на объект класса, но гугл говорит, что я устарел и депрекейтнулся
//    @Nullable
//    @Override
//    public Object onRetainCustomNonConfigurationInstance() {
//        return super.onRetainCustomNonConfigurationInstance();
//    }

    public void addResultSymbol(String ch) {
        result.append(ch);
    }

    public void appendExpression(String text) {
        expression.append(text);
    }

    public void clearResult() {
        result.setText("");
    }

    public void clearExpression() {
        expression.setText("");
    }

    public void clear() {
        clearResult();
        clearExpression();
    }

    public void setAnswer(String answer) {
        result.setText(answer);
    }
}
