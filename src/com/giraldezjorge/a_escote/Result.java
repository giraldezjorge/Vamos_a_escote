package com.giraldezjorge.a_escote;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class Result extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the text view as the activity layout
        setContentView(R.layout.result);

        Intent intent = getIntent();
        Double value = intent.getDoubleExtra(Escote.EXTRA_MESSAGE, 0.0);

        TextView resultLabel = (TextView) findViewById(R.id.resultLabel);
        TextView tipLabel = (TextView) findViewById(R.id.tipLabel);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Weston Free.otf");
        resultLabel.setTypeface(font);
        tipLabel.setTypeface(font);

        // Create the text view
        DecimalFormat df = new DecimalFormat("#.##");
        TextView textView = (TextView) findViewById(R.id.result);
        String stringValue = df.format(value);
        BigDecimal resultRounded = new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);

        textView.setText(resultRounded.toString());
        textView.setTypeface(font);

        TextView tipView = (TextView) findViewById(R.id.tip);
        tipView.setTypeface(font);
        tipView.setText(String.valueOf(calculateTip(resultRounded.doubleValue(),
                intent.getDoubleExtra(Escote.MONEY_AMOUNT, 0.0), intent.getIntExtra(Escote.PERSONS_AMOUNT, 0))));
    }

    private Double calculateTip(Double paidAmount, Double moneyAmount, Integer persons) {
        return (paidAmount * persons) - moneyAmount;
    }

}
