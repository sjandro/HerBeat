package com.asalazar.alex.herbeat;

import android.app.Activity;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

/**
 * Created by sjand on 4/4/2016.
 */
public class LineChartCreator {
    private static final String TAG = "com.asalazar.alex.activitylogbeta";
    private LineChart lineChart;
    ArrayList<Entry> entries;
    ArrayList<String> XVals;
    String color;

    public LineChartCreator(LineChart lineChart, String color){
        this.lineChart = lineChart;
        entries = new ArrayList<>();
        XVals = new ArrayList<>();
        this.color = color;
    }

//    public void setVisibility(View view){
//
//    }

    public void setLineChart(){
        ArrayList<String> xvals = getXAxisValues();
        LineDataSet dataSet = getDataSetLineChart();
        LineData data = new LineData(xvals, dataSet);
        lineChart.setData(data);
        lineChart.setDescription("");
        //lineChart.animateXY(2000, 2000);
        //lineChart.setViewPortOffsets(0, 20, 0, 0);


//        lineChart.getAxisRight().setEnabled(false);
//
//        // enable touch gestures
//        lineChart.setTouchEnabled(false);
//
//        // enable scaling and dragging
//        lineChart.setDragEnabled(false);
//        lineChart.setScaleEnabled(false);
//        lineChart.setPinchZoom(false);
//        lineChart.setDrawGridBackground(false);
//        lineChart.setGridBackgroundColor(Color.parseColor("#ffffff"));
//        XAxis xAxis = lineChart.getXAxis();
//        xAxis.setDrawGridLines(false);
//        xAxis.setTextColor(Color.parseColor("#727272"));
//        xAxis.setAxisLineWidth(3);
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
//        xAxis.setAxisLineColor(Color.parseColor("#ffffff"));
//        xAxis.setAvoidFirstLastClipping(true);
//        YAxis yAxis = lineChart.getAxisLeft();
//        yAxis.setTextColor(Color.parseColor("#727272"));
//        yAxis.setAxisLineColor(Color.parseColor("#ffffff"));
//        yAxis.setAxisLineWidth(3);
//
//        Legend l = lineChart.getLegend();
//        l.setTextColor(Color.parseColor("#727272"));

        //lineChart.setVisibleXRangeMaximum(15);
        lineChart.invalidate();
    }

    private void updateGraph(){

    }


    private LineDataSet getDataSetLineChart() {

//        entries.add(new Entry(4f, 0));
//        entries.add(new Entry(8f, 1));
//        entries.add(new Entry(6f, 2));
//        entries.add(new Entry(2f, 3));
//        entries.add(new Entry(18f, 4));
//        entries.add(new Entry(9f, 5));
//        entries.add(new Entry(4f, 6));
//        entries.add(new Entry(8f, 7));
//        entries.add(new Entry(6f, 8));
//        entries.add(new Entry(2f, 9));
//        entries.add(new Entry(18f, 10));
//        entries.add(new Entry(9f, 11));


        LineDataSet lineDataSet = new LineDataSet(entries, "Data");
        int[] colors = {Color.parseColor(color)};
        lineDataSet.setColors(colors);
        lineDataSet.setDrawCubic(true);
        lineDataSet.setDrawFilled(true);
        lineDataSet.setFillColor(Color.parseColor(color));
        lineDataSet.setDrawCircles(false);
        lineDataSet.setDrawValues(false);
        //lineDataSet.setValueTextColor(Color.parseColor("#ffffff"));


        return lineDataSet;
    }

    public void add_to_y_vals(Entry entry){
        entries.add(entry);
    }

    private ArrayList<String> getXAxisValues() {
//        XVals.add("Val1");
//        XVals.add("Val2");
//        XVals.add("Val3");
//        XVals.add("Val4");
//        XVals.add("Val5");
//        XVals.add("Val6");
//        XVals.add("Val7");
//        XVals.add("Val8");
//        XVals.add("Val9");
//        XVals.add("Val10");
//        XVals.add("Val11");
//        XVals.add("Val12");

        return XVals;
    }

    public void add_x_vals(String val){
        XVals.add(val);
    }

}
