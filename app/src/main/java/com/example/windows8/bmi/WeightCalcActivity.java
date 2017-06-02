package com.example.windows8.bmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class WeightCalcActivity extends AppCompatActivity {
    EditText edtOriWeight, edtCurrWeight;
    TextView textView3;
    int Curr;
    String CurrWeight;
    int Ori;
    String OriWeight;
    String totalPercentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_calc);
        edtOriWeight=(EditText)findViewById(R.id.edtOriWeight);
        edtCurrWeight=(EditText)findViewById(R.id.edtCurrWeight);
        textView3 =(TextView)findViewById(R.id.textView3);
    }

    public void fnGreet(View vw)
    {

        if(edtOriWeight.getText().toString().trim().equals("")){
            edtOriWeight.setError("This field is required!");

        }else if(edtCurrWeight.getText().toString().trim().equals("")){
            edtCurrWeight.setError("This field is required!");

        }else
        {
            CurrWeight = edtCurrWeight.getText().toString();
            Curr = Integer.parseInt(CurrWeight);
            OriWeight = edtOriWeight.getText().toString();
            Ori = Integer.parseInt(OriWeight);
            //int year = Calendar.getInstance().get(Calendar.YEAR);

            int percentage = (int)((((double)Curr-(double)Ori)/(double)Ori)*100);
            totalPercentage = String.valueOf(percentage);
            textView3.setText(" Your Weight Loss Percentage:  "+totalPercentage+ " %");
        }

    }
}
