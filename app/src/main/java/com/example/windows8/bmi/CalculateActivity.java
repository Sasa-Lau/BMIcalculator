package com.example.windows8.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CalculateActivity extends AppCompatActivity {
    EditText edtweight,edtheight;
    BMIdb dbBMI;
    double BMI;
    String gender;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        edtweight = (EditText)findViewById(R.id.weight);
        edtheight = (EditText)findViewById(R.id.height);
        dbBMI = new BMIdb(getApplicationContext());
        Intent intent= getIntent();
        gender = intent.getStringExtra("gender");


    }

    public void calculateBMI(View vw)
    {
        double weightD , heightD, parcel;

        weightD = Double.parseDouble(edtweight.getText().toString());
        heightD = Math.pow((Double.parseDouble(edtheight.getText().toString())/100),2);

        BMI = weightD/heightD;
        final DecimalFormat df = new DecimalFormat("#.#");


        if(edtheight.getText().toString().trim().equals("")){
            edtheight.setError("This field is required!");

        }else if(edtweight.getText().toString().trim().equals("")) {
            edtweight.setError("This field is required!");
        }else {

            Runnable run = new Runnable() {
                @Override
                public void run() {

                    try {
                        int intNewId = dbBMI.fnTotalRow() + 1;
                        String strQry = "Insert into " + BMIdb.tblName + " values('" + intNewId + "','" + edtweight.getText().toString() + "','" + edtheight.getText().toString() + "','" + String.valueOf(BMI) + "');";
                        dbBMI.fnExecuteSql(strQry, getApplicationContext());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast showSuccess = Toast.makeText(getApplicationContext(), "Information Successfully Saved into database!", Toast.LENGTH_SHORT);
                                showSuccess.show();
                            }
                        });
                    }catch (Exception e)
                    {
                        Log.i("Apperror","erorrrr here>>>>>>>>>>>> "+e.getMessage());
                    }


                }
            };

            Thread thrsave = new Thread(run);
            thrsave.start();


            Intent intent = new Intent(this, ResultActivity.class);
            Bundle b = new Bundle();
            b.putDouble("key", BMI);
            b.putString("gender", gender);
            intent.putExtras(b);
            startActivity(intent);

        }

    }

}
