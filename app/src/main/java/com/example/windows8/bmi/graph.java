package com.example.windows8.bmi;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class graph extends AppCompatActivity {

    BMIdb dbBMI;
    Cursor resultset;
    final DecimalFormat df = new DecimalFormat("#.#");
    int i=0 , t = 0 ;
    Float axis ;
    ArrayList<Entry> entries = new ArrayList<>();
    LineChart lineChart ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);



        dbBMI = new BMIdb(getApplicationContext());


        if(dbBMI.fnTotalRow() == 0 )
        {
            for(int z=1 ; z<5;z++){
                String strQry = "Insert into "+dbBMI.tblName+" values('"+z+ "', '0' ,'0','0');";
                dbBMI.fnExecuteSql(strQry,getApplicationContext());
            }
            drawGraph();


        } else if(dbBMI.fnTotalRow() == 1){

            for(int z=2 ; z<5;z++){
                String strQry = "Insert into "+dbBMI.tblName+" values('"+z+ "', '0' ,'0','0');";
                dbBMI.fnExecuteSql(strQry,getApplicationContext());
            }
            drawGraph();
        }else if(dbBMI.fnTotalRow() == 2) {
            for(int z=3 ; z<5;z++){
                String strQry = "Insert into "+dbBMI.tblName+" values('"+z+ "', '0' ,'0','0');";

                dbBMI.fnExecuteSql(strQry,getApplicationContext());
            }
            drawGraph();

        }else if(dbBMI.fnTotalRow() == 3){
            for(int z=4 ; z<5;z++){
                String strQry = "Insert into "+dbBMI.tblName+" values('"+z+ "', '0' ,'0','0');";
                dbBMI.fnExecuteSql(strQry,getApplicationContext());
            }
            drawGraph();

        }else{
            drawGraph();
        }


    }

    public void drawGraph(){

       lineChart = (LineChart) findViewById(R.id.chart);
        try{

            resultset=dbBMI.fnGetAllData();
            if (resultset.moveToLast()){

                while(!resultset.isAfterLast()&& i<4){


                    String data = resultset.getString(resultset.getColumnIndex("bmi_value"));
                    // do what ever you want here
                    axis=Float.parseFloat(data);

                    entries.add(new Entry(axis, i));
                    Log.i("Myapp",">>>>>>>"+data);
                    i++;

                    resultset.moveToPrevious();
                }
            }
            resultset.close();
            ArrayList<Entry> output = new ArrayList<>();
            int itemCount = entries.size();

//


            LineDataSet dataset = new LineDataSet(entries, "# of Calls");
            ArrayList<String> labels = new ArrayList<String>();
            labels.add("");
            labels.add("");
            labels.add("");
            labels.add("");


            LineData data = new LineData(labels, dataset);
            dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
            dataset.setDrawCubic(true);
            dataset.setDrawFilled(true);

            lineChart.setData(data);
            lineChart.animateY(5000);


        }catch (Exception e)
        {

            e.printStackTrace();
            Log.i("BMICALC","Exceptionnnnnnnnnnnnnnnnnnnnnnnnnnnnnn"+e.getMessage());
        }
    }

}
