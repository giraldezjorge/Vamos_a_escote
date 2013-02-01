package com.giraldezjorge.a_escote;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Escote extends Activity {

    public final static String EXTRA_MESSAGE = "com.giraldezjorge.a_escote.RESULT";
    public final static String MONEY_AMOUNT = "com.giraldezjorge.a_escote.MONEY";
    public final static String PERSONS_AMOUNT = "com.giraldezjorge.a_escote.PERSONS";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.escote);
        TextView txt = (TextView) findViewById(R.id.header);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Weston Free.otf");
        txt.setTypeface(font);
    }

    public void calculate(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, Result.class);
        EditText moneyEditText = (EditText) findViewById(R.id.money_amount);
        String moneyValue = moneyEditText.getText().toString();

        EditText personsEditText = (EditText) findViewById(R.id.persons_amount);
        String personsValue = personsEditText.getText().toString();

        if (moneyValue.length() == 0 || personsValue.length() == 0) {
            Toast emptyParamsToast = Toast.makeText(getApplicationContext(), R.string.empty_params, Toast.LENGTH_LONG);
            emptyParamsToast.show();
        } else {
            Double money = null;
            Toast validationToast = null;
            try {
                money = Double.parseDouble(moneyValue);
            } catch (NumberFormatException e) {
                validationToast = Toast.makeText(getApplicationContext(), R.string.money_is_not_a_number,
                        Toast.LENGTH_LONG);
            }
            Integer persons = null;
            try {
                persons = Integer.parseInt(personsValue);
            } catch (NumberFormatException e) {
                validationToast = Toast.makeText(getApplicationContext(), R.string.persons_is_not_a_number,
                        Toast.LENGTH_LONG);
            }

            if (validationToast == null && money < persons) {
                validationToast = Toast.makeText(getApplicationContext(), R.string.money_less_than_persons,
                        Toast.LENGTH_LONG);
            }

            if (validationToast != null) {
                validationToast.show();
            } else {
                Double amount = money / persons;
                intent.putExtra(EXTRA_MESSAGE, amount);
                intent.putExtra(MONEY_AMOUNT, money);
                intent.putExtra(PERSONS_AMOUNT, persons);
                startActivity(intent);
            }
        }
    }
}
