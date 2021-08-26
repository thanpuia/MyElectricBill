package com.ltp.myelectricbill;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    RelativeLayout settingsLayout;
    ImageView myImage;
    TextView settingsTextView;
    TextView billAmount;
    EditText billUnit;
    EditText firstRoundEditText;
    EditText secondRoundEditText;
    EditText thirdRoundEditText;

    Double FIRST_ROUND;
    Double SECOND_ROUND;
    Double THIRD_ROUND;

    Double userBillUnit;
    Double userBillAmount;

    int COUNTER;
    Random rand = new Random();
    /*
    BILL TARIFF
    Example for 1000 unit used:
    1-100, Rs 4.80	    100	480
    101-200, Rs 5.50	100	550
    201-above, Rs 5.90	800	4,720*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        COUNTER=2;

        myImage = findViewById(R.id.myImage);
        firstRoundEditText = findViewById(R.id.first_round);
        secondRoundEditText = findViewById(R.id.second_round);
        thirdRoundEditText = findViewById(R.id.third_round);

        settingsLayout = findViewById(R.id.settings);
        settingsTextView = findViewById(R.id.settings_label);

        billAmount = findViewById(R.id.bill_amount);
        billUnit = findViewById(R.id.bill_unit);

        settingsTextView.setVisibility(View.GONE);
        settingsLayout.setVisibility(View.GONE);


    }

    public void calculateClick(View view) {
        FIRST_ROUND = Double.valueOf(firstRoundEditText.getText().toString());//4.80;
        SECOND_ROUND = Double.valueOf(secondRoundEditText.getText().toString());//5.50;
        THIRD_ROUND = Double.valueOf(thirdRoundEditText.getText().toString());//5.90;

        try {
            userBillUnit = Double.valueOf(billUnit.getText().toString());
        }catch (Exception e){
            userBillUnit = 0.0;
        }
        if(userBillUnit<=100){
            firstRound();
        }else if(userBillUnit >= 101 && userBillUnit <= 200){
            secondRound();
        }else if (userBillUnit>=201){
            thirdRound();
        }else{
            myDefault();
        }

        billAmount.setText("Total Bill= Rs."+String.valueOf(userBillAmount));

    }

    public void imageClick(View view) {
        int[] images = {
                R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d,R.drawable.e,R.drawable.f,
                R.drawable.g,R.drawable.h
        };

        int rand_int1 = rand.nextInt(7);
        myImage.setImageResource(images[rand_int1]);

    }
    private void firstRound() {
        userBillAmount = userBillUnit * FIRST_ROUND;
    }
    private void secondRound() {
        userBillAmount = 100 * FIRST_ROUND;
        userBillAmount = (100-userBillUnit)*SECOND_ROUND + userBillAmount;
    }
    private void thirdRound() {
        userBillAmount = 100 * FIRST_ROUND;
        userBillAmount = 100 * SECOND_ROUND + userBillAmount;
        userBillAmount = Math.abs(200-userBillUnit) * THIRD_ROUND + userBillAmount;

    }

    private void myDefault() {
        Log.d("TAG","Default round");

        userBillAmount = 00.0;
    }

    public void settingsClick(View view) {


        if(COUNTER % 2 ==0){
            settingsTextView.setVisibility(View.VISIBLE);
            settingsLayout.setVisibility(View.VISIBLE);
            myImage.setVisibility(View.GONE);
        }else{
            settingsTextView.setVisibility(View.GONE);
            settingsLayout.setVisibility(View.GONE);
            myImage.setVisibility(View.VISIBLE);
        }
        COUNTER++;
    }


}