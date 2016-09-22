package com.asalazar.alex.herbeat;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

/**
 * Created by sjand on 7/4/2016.
 */
public class BarChartCreator {
    private BarChart barChart;
    ArrayList<String> labels = new ArrayList<>();
    ArrayList<BarEntry> entries = new ArrayList<>();


    public BarChartCreator(BarChart barChart){
        this.barChart = barChart;
    }

    public void setVisibility(View view){

    }

    public void setBarChart(){
        BarData data = new BarData(getLabels(), getBarEntries());
        barChart.setData(data);
        barChart.setDescription("");
        barChart.animateXY(2000, 2000);

        barChart.invalidate();
    }

    private ArrayList<String> getLabels(){
        return labels;
    }

    public void add_labels(String s){
        labels.add(s);
    }

    public void addEntries(BarEntry e){
        entries.add(e);
    }

    private BarDataSet getBarEntries() {

//        entries.add(new BarEntry(4f, 0));
//        entries.add(new BarEntry(8f, 1));
//        entries.add(new BarEntry(6f, 2));
//        entries.add(new BarEntry(12f, 3));
//        entries.add(new BarEntry(18f, 4));
//        entries.add(new BarEntry(9f, 5));

        BarDataSet dataset = new BarDataSet(entries, "Calories");

        return dataset;
    }
}
