package com.asalazar.alex.herbeat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.PercentFormatter;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by sjand on 4/4/2016.
 */
public class PieChartCreator {
    private static final String TAG = "com.asalazar.alex.activitylogbeta";
    private PieChart mChart;
    private Activity main;
    private Context context;

    ArrayList<String> XVals = new ArrayList<>();
    ArrayList<Entry> entries = new ArrayList<>();

    ArrayList<Integer> beats = new ArrayList<>();
    ArrayList<Integer> colors = new ArrayList<>();

    public PieChartCreator(PieChart pieChart, Context context){
        this.context = context;
        mChart = pieChart;

    }

    public void setVisibility(View view){

    }

    public void add_beats(int i){beats.add(i);}

    public void setPieChart(){
        // configure pie chart
        mChart.setUsePercentValues(true);
        //getDataSetPieChart();
        mChart.setDescription("");

        // enable hole and configure
        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColorTransparent(true);

        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);


        // enable rotation of the chart by touch
        mChart.setRotationAngle(90);
        mChart.setRotationEnabled(false);
        mChart.setDrawSliceText(false);


        // set a chart value selected listener
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                // display msg when value selected
                if (e == null)
                    return;

            }

            @Override
            public void onNothingSelected() {

            }
        });

        // add data

        ArrayList<Entry> yVals = getDataSetPieChart();
//        ArrayList<String> XVals = getXAxisValues();
        addData(XVals, yVals);

        // customize legends
        Legend l = mChart.getLegend();
        l.setTextColor(Color.parseColor("#727272"));
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
    }

    private void addData(ArrayList<String> x, ArrayList<Entry> y) {

        ArrayList<Entry> yVals1 = y;
        ArrayList<String> xVal1 = x;

        // create pie data set
        PieDataSet dataSet1 = new PieDataSet(yVals1, "Heart Beat");
        dataSet1.setSelectionShift(5);
        dataSet1.setSliceSpace(3);

        dataSet1.setColors(colors);

        ArrayList<PieDataSet> pieDataSets = new ArrayList<>();
        pieDataSets.add(dataSet1);




        // instantiate pie data object now
        PieData data = new PieData(xVal1, dataSet1);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.parseColor("#727272"));

        mChart.setData(data);

        // undo all highlights
        mChart.highlightValues(null);

        // update pie chart
        mChart.invalidate();
    }

//    private ArrayList<String> getXAxisValues() {
//
//        return XVals;
//    }
//
//    public void add_to_y_vals(Entry entry){
//        entries.add(entry);
//    }
//
//    public void add_x_vals(String val){
//        XVals.add(val);
//    }
//
//    private ArrayList<Entry> getDataSetPieChart() {
//
//        return entries;
//    }

    private ArrayList<Entry> getDataSetPieChart() {
        ArrayList<Entry> entries = new ArrayList<>();

        int one = 0; int two = 0; int three = 0; int count = 0;

        for(int i = 0; i < beats.size(); ++i){
            if(i == 0){

            }
            else if(i > 0 && i < 80){
                one++;
            }
            else if(i > 80 && i < 90){
                two++;
            }
            else if(i > 90){
                three++;
            }
        }

        XVals.clear();

        if(one != 0){
            entries.add(new BarEntry(one, count));
            XVals.add("Good");
            count++;
            colors.add(Color.parseColor("#8BC34A"));
        }
        if(two != 0){
            entries.add(new BarEntry(two, count));
            XVals.add("Cautious");
            count++;
            colors.add(Color.parseColor("#FFC107"));
        }
        if(three != 0){
            entries.add(new BarEntry(three, count));
            XVals.add("Dangerous");
            count++;
            colors.add(Color.parseColor("#F44336"));
        }


        return entries;
    }



}
