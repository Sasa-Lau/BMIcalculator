package com.example.windows8.bmi;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    String gender;
    Button male, female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        male = (Button)findViewById(R.id.maleIcon);
        female = (Button)findViewById(R.id.femaleIcon);
    }

    public void fnMale(View vw){
        gender = "m";
        Intent intent = new Intent(this, CalculateActivity.class);
        intent.putExtra("gender",gender);
        startActivity(intent);
    }

    public void fnFemale(View vw){
        gender="f";
        Intent intentFemale = new Intent(this, CalculateActivity.class);
        intentFemale.putExtra("gender",gender);
        startActivity(intentFemale);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intentActivity = new Intent(this,WeightCalcActivity.class);
            startActivity(intentActivity);
            return true;
        }else if(id == R.id.action_graph)
        {
            Intent intentActivity = new Intent(this,graph.class);
            startActivity(intentActivity);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
