package com.giraldezjorge.a_escote;

import java.math.BigDecimal;

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
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/Weston Free.otf");
        resultLabel.setTypeface(font);

        // Create the text view
        TextView textView = (TextView) findViewById(R.id.result);
        BigDecimal resultRounded = new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP);

        textView.setText(resultRounded.toString());
        textView.setTypeface(font);

    }
}
