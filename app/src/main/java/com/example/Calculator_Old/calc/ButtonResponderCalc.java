package com.example.Calculator_Old.calc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.Calculator_Old.R;

import java.util.Locale;

public class ButtonResponderCalc implements View.OnClickListener {

    public final static String ZERO = "0";
    public final static String ONE = "1";
    public final static String TWO = "2";
    public final static String THREE = "3";
    public final static String FOUR = "4";
    public final static String FIVE = "5";
    public final static String SIX = "6";
    public final static String SEVEN = "7";
    public final static String EIGHT = "8";
    public final static String NINE = "9";

    public final static String PLUS_OPERATION = "+";
    public final static String MINUS_OPERATION = "-";
    public final static String MULT_OPERATION = "*";
    public final static String DIV_OPERATION = "/";

    public final static String NUMBER_TAG = "number";
    public final static String ANSWER_TAG = "answer";
    public final static String LAST_OP_TAG = "lastOp";
    public final static String WAS_ANSWERED_TAG = "answered";
    public final static String WAS_ERROR_TAG = "error";

    public final String errorMsg;

    private final Calculator calculator;
    private String number = "";
    private double answer = 0;
    private String lastOperator = "";
    private boolean wasAnswered = false;
    private boolean wasError = false;

    public ButtonResponderCalc(Calculator mainActivity) {
        this.calculator = mainActivity;
        errorMsg = mainActivity.getResources().getString(R.string.error_msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.clear_btn: //что делать с этим варнингом
                this.clearAll();
                break;
            case R.id.zero_btn:
                addSymbol(ZERO);
                break;
            case R.id.one_btn:
                addSymbol(ONE);
                break;
            case R.id.two_btn:
                addSymbol(TWO);
                break;
            case R.id.three_btn:
                addSymbol(THREE);
                break;
            case R.id.four_btn:
                addSymbol(FOUR);
                break;
            case R.id.five_btn:
                addSymbol(FIVE);
                break;
            case R.id.six_btn:
                addSymbol(SIX);
                break;
            case R.id.seven_btn:
                addSymbol(SEVEN);
                break;
            case R.id.eight_btn:
                addSymbol(EIGHT);
                break;
            case R.id.nine_btn:
                addSymbol(NINE);
                break;

            case R.id.plus_btn:
                addOperator(PLUS_OPERATION);
                break;

            case R.id.minus_btn:
                addOperator(MINUS_OPERATION);
                break;

            case R.id.mult_btn:
                addOperator(MULT_OPERATION);
                break;

            case R.id.divide_btn:
                addOperator(DIV_OPERATION);
                break;

            case R.id.equals_btn:
                if (number.isEmpty()) {
                    return;
                }
                if (!lastOperator.isEmpty()) {
                    calculate();
                } else {
                    answer = Double.parseDouble(number);
                }

                if (answer == Double.POSITIVE_INFINITY || answer == Double.NEGATIVE_INFINITY || ((Double) answer).equals(Double.NaN)) {
                    calculator.setAnswer(errorMsg);
                    this.clear();
                    wasError = true;
                    return;
                }

                int answerInt = ((Double) answer).intValue();
                //double тащит за собой не значащие нули
                if (answerInt == answer) {
                    calculator.setAnswer(String.format(Locale.getDefault(), "%d", answerInt));
                } else {
                    calculator.setAnswer(String.format(Locale.getDefault(), "%f", answer));//тут тащит
                }
                wasAnswered = true;
                //Кроме флага ничего не меняется, мы просто ставим ответ в поле вывода
                break;
            case R.id.ok_btn:
                String finalAnswer = calculator.getAnswer();
                if (finalAnswer.isEmpty() || finalAnswer.equals(errorMsg)) {
                    calculator.setResult(Activity.RESULT_CANCELED);
                } else {
                    Intent intent = new Intent().putExtra(ANSWER_TAG, finalAnswer);
                    calculator.setResult(Activity.RESULT_OK, intent);
                }
                calculator.finish();
                break;
        }
    }

    private void addSymbol(String symbol) {
        if (wasError || wasAnswered) {
            this.clearAll();
        }
        number = number.concat(symbol);
        calculator.addResultSymbol(symbol);
    }

    private void addOperator(String operator) {
        if (number.isEmpty()) {
            return;
        }
        if (!wasAnswered) {
            if (!lastOperator.isEmpty()) {
                calculate();
            } else {
                answer = Double.parseDouble(number);
            }
        } else {
            wasAnswered = false;
        }
        lastOperator = operator;
        calculator.clear();
        number = "";
    }

    private void calculate() {
        double num = Double.parseDouble(number);
        switch (lastOperator) {
            case PLUS_OPERATION:
                answer += num;
                break;
            case MINUS_OPERATION:
                answer -= num;
                break;
            case MULT_OPERATION:
                answer *= num;
                break;
            case DIV_OPERATION:
                answer /= num;
                break;
        }
    }

    private void clear() {
        this.wasAnswered = false;
        this.wasError = false;
        this.lastOperator = "";
        this.answer = 0;
        this.number = "";
    }

    private void clearAll() {
        this.clear();
        calculator.clear();
    }

    public Bundle save() {
        Bundle savedState = new Bundle();
        savedState.putString(NUMBER_TAG, number);
        savedState.putDouble(ANSWER_TAG, answer);
        savedState.putString(LAST_OP_TAG, lastOperator);
        savedState.putBoolean(WAS_ANSWERED_TAG, wasAnswered);
        savedState.putBoolean(WAS_ERROR_TAG, wasError);
        return savedState;
    }

    public void load(Bundle loaded) {
        number = loaded.getString(NUMBER_TAG);
        answer = loaded.getDouble(ANSWER_TAG);
        lastOperator = loaded.getString(LAST_OP_TAG);
        wasAnswered = loaded.getBoolean(WAS_ANSWERED_TAG);
        wasError = loaded.getBoolean(WAS_ERROR_TAG);
    }
}
