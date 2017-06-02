package com.example.windows8.bmi;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ResultActivity extends AppCompatActivity {

    TextView txtResult,txtstatus;
    JSONObject jsnObj = new JSONObject();
    WebServiceCall wsc = new WebServiceCall();
    String strStatus;
    double BMI;
    final DecimalFormat df = new DecimalFormat("#.#");
    String gender;
    ImageView imv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
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

        Bundle b = getIntent().getExtras();
         BMI = b.getDouble("key");
        gender=b.getString("gender");



        txtResult = (TextView)findViewById(R.id.resultValue);
        txtstatus= (TextView)findViewById(R.id.status);

        txtResult.setText(df.format(BMI));

//        imv.setImageResource(R.drawable.malee);
//        imv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.malee));
        Log.i("MyBMIApp",">>>>>>>"+gender+"<<<<<<< @@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        if(gender.equals("m"))
        {
            Log.i("MyBMIApp",">>>>>>>"+gender+"<<<<<<< @@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//            int id = getResources().getIdentifier(lowerCountryCode, "drawable", getPackageName());
            imv= (ImageView)findViewById(R.id.imageViewRange);
            imv.setImageResource(R.drawable.malee);
            imv.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.malee));
        }



        Runnable run = new Runnable() {
            @Override
            public void run() {

                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("selectFn","fnGetValue"));
                params.add(new BasicNameValuePair("varBMIValue",df.format(BMI)));

                try{
                    jsnObj = wsc.makeHttpRequest(wsc.fnGetURL(), "POST", params);

                    strStatus = jsnObj.getString("status");


                }catch (Exception e)
                {
                    Log.i("MyBMIApp",e.getMessage());


                }
                 runOnUiThread(new Runnable()
                {
                    public void run(){
//                    Toast.makeText(getApplicationContext(),"You are in "+strStatus+" condition!!", Toast.LENGTH_SHORT).show();
                        txtstatus.setText("You are in "+strStatus+" condition!!");

                        if(gender=="m")
                        {
                            imv.setImageResource(R.drawable.malee);
                        }
                }
                });
            }
        };
        Thread thrDateTime = new Thread(run);
        thrDateTime.start();

    }

}
