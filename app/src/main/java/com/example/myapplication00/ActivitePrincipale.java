package com.example.myapplication00;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ActivitePrincipale extends AppCompatActivity {

    private TextView screen;
    private int op1 = 0;
    private int op2 = 0;
    private Ops operator = null;
    private boolean isOp1 = true;

    public enum Ops {
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screen = (TextView) findViewById(R.id.screen);

        Button calculator_button_equals = (Button)findViewById(R.id.calculator_button_equals);
        calculator_button_equals.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                compute();
            }
        });

    }

    public void compute() {
        if(!isOp1){
            switch(operator) {
                case PLUS : op1 = op1 + op2; break;
                case MINUS : op1 = op1 - op2; break;
                case MULTIPLY : op1 = op1 * op2; break;
                case DIVIDE : op1 = op1 / op2; break;
                default : return; // do nothing if no operator
            }
            op2 = 0;
            isOp1 = true;
            updateDisplay();
        }
    }

    public void setOperator(View v) {
        if (v.getId() == R.id.calculator_button_plus) {
            operator = Ops.PLUS;
        }
        else if (v.getId() == R.id.calculator_button_minus) {
            operator = Ops.MINUS;
        }
        else if (v.getId() == R.id.calculator_button_multiply) {
            operator = Ops.MULTIPLY;
        }
        else if (v.getId() == R.id.calculator_button_divide) {
            operator = Ops.DIVIDE;
        }
        else {
            Toast.makeText(this, "Opérateur non reconnu", Toast.LENGTH_LONG);
            return;
        }
        isOp1=false;
        updateDisplay();
    }

    public void addNumber(View v){
        try {
            int val = Integer.parseInt(((Button)v).getText().toString());
            if (isOp1) {
                op1 = op1 * 10 + val;
                updateDisplay();
            } else {
                op2 = op2 * 10 + val;
                updateDisplay();
            }
        }catch (NumberFormatException | ClassCastException e) {
            Toast.makeText(this, "Valeur erronée",Toast.LENGTH_LONG);
        }
    }

    private void updateDisplay() {
        int v=op1;
        if(!isOp1) {
            v=op2;
        }
        screen.setText(String.format("%9d",v));
    }

}