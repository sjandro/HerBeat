package com.asalazar.alex.herbeat;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;


import java.util.ArrayList;

/**
 * Created by sjand on 7/4/2016.
 */
public class RadarChartCreator {


    private RadarChart mChart;


    public RadarChartCreator(RadarChart radarChart){
        mChart = radarChart;

    }

    public void setVisibility(View view){

    }

    public void setRadarChart(){

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(4f, 0));
        entries.add(new Entry(5f, 1));
        entries.add(new Entry(2f, 2));
        entries.add(new Entry(7f, 3));
        entries.add(new Entry(6f, 4));
        entries.add(new Entry(5f, 5));

        ArrayList<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(1f, 0));
        entries2.add(new Entry(5f, 1));
        entries2.add(new Entry(6f, 2));
        entries2.add(new Entry(3f, 3));
        entries2.add(new Entry(4f, 4));
        entries2.add(new Entry(8f, 5));

        RadarDataSet dataset_comp1 = new RadarDataSet(entries, "DataSet1");
        RadarDataSet dataset_comp2 = new RadarDataSet(entries2, "DataSet2");

        dataset_comp1.setColor(Color.CYAN);
        dataset_comp2.setColor(Color.RED);

        dataset_comp1.setDrawFilled(true);
        dataset_comp2.setDrawFilled(true);

        ArrayList<RadarDataSet> dataSets = new ArrayList<>();

        dataSets.add(dataset_comp1);
        dataSets.add(dataset_comp2);

        ArrayList<String> labels = new ArrayList<String>();

        labels.add("Val1");
        labels.add("Val2");
        labels.add("Val3");
        labels.add("Val4");
        labels.add("Val5");
        labels.add("Val6");

        RadarData data = new RadarData(labels, dataSets);

        mChart.setData(data);
        mChart.getYAxis().setDrawLabels(false);

        String description = "Data";
        mChart.setDescription(description);
        mChart.setWebLineWidthInner(0.5f);

        mChart.invalidate();
        mChart.animate();
    }



}
