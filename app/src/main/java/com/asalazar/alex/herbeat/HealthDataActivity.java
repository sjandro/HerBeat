package com.asalazar.alex.herbeat;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import com.microsoft.band.BandClient;
import com.microsoft.band.BandClientManager;
import com.microsoft.band.BandException;
import com.microsoft.band.BandInfo;
import com.microsoft.band.BandIOException;
import com.microsoft.band.ConnectionState;
import com.microsoft.band.UserConsent;
import com.microsoft.band.sensors.BandHeartRateEvent;
import com.microsoft.band.sensors.BandHeartRateEventListener;
import com.microsoft.band.sensors.HeartRateConsentListener;
import android.os.AsyncTask;


/**
 * Created by sjand on 7/4/2016.
 */
public class HealthDataActivity extends Activity {

    protected PieChartCreator PIECHART;
    protected BarChartCreator BARCHART;
    protected LineChartCreator LINECHART_HB;
    protected LineChartCreator LINECHART_CAL;
    protected LineChartCreator LINECHART_STRS;
    protected int COUNTER;
    protected int COUNTER_CAL;
    protected float TIMEELAPSE;
    protected float pastTime;
    //protected RadarChartCreator RADARCHART;
    protected ImageView heartImage;
    protected TextView heartText;
    protected TextView caloriesTV;

    private BandClient client = null;

    private TextView textView;

    private BandHeartRateEventListener mHeartRateEventListener = new BandHeartRateEventListener() {
        @Override
        public void onBandHeartRateChanged(final BandHeartRateEvent event) {
            if (event != null) {
                do_work(event);
            }
        }
    };

    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            // message from API client! message from wear! The contents is the heartbeat.
            if(textView!=null) {
                Date c = Calendar.getInstance().getTime();
                Long mil = Calendar.getInstance().getTimeInMillis();
                int yVal = msg.what;
                System.out.println(msg.what);
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                SimpleDateFormat sdf = new SimpleDateFormat("ss");
                String second = sdf.format(System.currentTimeMillis());
                System.out.println("handleMessage(second): " + second);
                textView.setText("Heart Beat: " + Integer.toString(yVal));
                if(yVal != 0) {
                    LINECHART_HB.add_x_vals("");
                    LINECHART_HB.add_to_y_vals(new Entry(yVal, COUNTER));
                    LINECHART_HB.setLineChart();

                    float newTime = Float.valueOf(second);

                    if(pastTime > newTime){
                     //TIMEELAPSE = 60 - pastTime + newTime;
                        TIMEELAPSE = 1;
                    }
                    else
                        TIMEELAPSE = newTime - pastTime;

                    if(TIMEELAPSE < 0)
                        TIMEELAPSE = TIMEELAPSE * -1;

                    System.out.println("handleMessage(TIMEELAPSE):" + TIMEELAPSE);

                    pastTime = newTime;
                    float cal = (float) ((-55.0969 + (0.6309 * (float)yVal) + (0.1988 * 185) + (0.2017 * 23))/4.184) * 60 * (TIMEELAPSE /3600);
                    System.out.println("handleMessage(Calories):" + cal);
                    caloriesTV.setText("Calories Burn: " + cal);
                    writeBeat(String.valueOf(yVal) + "," + c + "," + cal +"\n");

                    LINECHART_CAL.add_x_vals("");
                    LINECHART_CAL.add_to_y_vals(new Entry(cal, COUNTER));
                    LINECHART_CAL.setLineChart();

                    Random rand = new Random();
                    int value = rand.nextInt(50);
                    LINECHART_STRS.add_x_vals("");
                    LINECHART_STRS.add_to_y_vals(new Entry(value,COUNTER));
                    LINECHART_STRS.setLineChart();

                    COUNTER++;

                    checkDangerZone(yVal);
                }

            }
        }
    };

    private void do_work(final BandHeartRateEvent event) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                printStatus(String.format("Heart Rate = %d beats per minute\n"
                        + "Quality = %s\n", event.getHeartRate(), event.getQuality()));

                Date c = Calendar.getInstance().getTime();
                Long mil = Calendar.getInstance().getTimeInMillis();
                int yVal = event.getHeartRate();
                System.out.println(yVal);
                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                SimpleDateFormat sdf = new SimpleDateFormat("ss");
                String second = sdf.format(System.currentTimeMillis());
                System.out.println("handleMessage(second): " + second);
                textView.setText("Heart Beat: " + Integer.toString(yVal));
                if (yVal != 0) {
                    LINECHART_HB.add_x_vals("");
                    LINECHART_HB.add_to_y_vals(new Entry(yVal, COUNTER));
                    LINECHART_HB.setLineChart();

                    float newTime = Float.valueOf(second);

                    if (pastTime > newTime) {
                        //TIMEELAPSE = 60 - pastTime + newTime;
                        TIMEELAPSE = 1;
                    } else
                        TIMEELAPSE = newTime - pastTime;

                    if (TIMEELAPSE < 0)
                        TIMEELAPSE = TIMEELAPSE * -1;

                    System.out.println("handleMessage(TIMEELAPSE):" + TIMEELAPSE);

                    pastTime = newTime;
                    float cal = (float) ((-55.0969 + (0.6309 * (float) yVal) + (0.1988 * 185) + (0.2017 * 23)) / 4.184) * 60 * (TIMEELAPSE / 3600);
                    System.out.println("handleMessage(Calories):" + cal);
                    caloriesTV.setText("Calories Burn: " + cal);
                    writeBeat(String.valueOf(yVal) + "," + c + "," + cal + "\n");

                    LINECHART_CAL.add_x_vals("");
                    LINECHART_CAL.add_to_y_vals(new Entry(cal, COUNTER));
                    LINECHART_CAL.setLineChart();

                    Random rand = new Random();
                    int value = rand.nextInt(50);
                    LINECHART_STRS.add_x_vals("");
                    LINECHART_STRS.add_to_y_vals(new Entry(value, COUNTER));
                    LINECHART_STRS.setLineChart();

                    COUNTER++;

                    checkDangerZone(yVal);
                }
            }
        });
    }

    private void checkDangerZone(int rate) {
        if(rate == 0){
            heartImage.setImageResource(R.drawable.heart_black);
            heartText.setText("No data at the moment");
        }
        else if(rate > 0 && rate < 80){
            heartImage.setImageResource(R.drawable.heart_green);
            heartText.setText("Heart rate in great condition!");
        }
        else if(rate > 80 && rate < 90){
            heartImage.setImageResource(R.drawable.heart_orange);
            heartText.setText("Heart rate in caution condition!");
        }
        else if(rate > 90){
            heartImage.setImageResource(R.drawable.heart_red);
            heartText.setText("Heart rate in dangerous condition!");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heath_data_tab);

        View view = findViewById(R.id.hdView);
        textView = (TextView) findViewById(R.id.heartbeat);
        caloriesTV = (TextView) findViewById(R.id.calories);
        LineChart lineChartHB = (LineChart) findViewById(R.id.lineChartHB);
        LineChart lineChartCalories = (LineChart) findViewById(R.id.lineChartCalories);
        LineChart lineChartStress = (LineChart) findViewById(R.id.lineChartStress);
        PieChart pieChart = (PieChart) findViewById(R.id.pieChart);
        BarChart barChart = (BarChart) findViewById(R.id.barChart);
        heartImage =(ImageView) findViewById(R.id.monitorHeart);
        heartText = (TextView) findViewById(R.id.monitorTV);
        COUNTER = 0; TIMEELAPSE = 0; COUNTER_CAL = 0;

        checkDangerZone(0);

        SimpleDateFormat sdf = new SimpleDateFormat("ss");
        String second = sdf.format(System.currentTimeMillis());
        pastTime = Float.valueOf(second);

        LINECHART_HB = new LineChartCreator(lineChartHB, "#E91E63");
        LINECHART_HB.setLineChart();
        LINECHART_CAL = new LineChartCreator(lineChartCalories, "#2196F3");
        LINECHART_CAL.setLineChart();
        LINECHART_STRS = new LineChartCreator(lineChartStress, "#607D8B");
        LINECHART_STRS.setLineChart();

        PIECHART = new PieChartCreator(pieChart, this);
        Date c = Calendar.getInstance().getTime();
        String date = c.toString().split(" ")[1] + c.toString().split(" ")[2] + c.toString().split(" ")[5];
        ArrayList<String> test = getTimes(date);
        for(int i = 0; i < test.size(); ++ i){
            System.out.println(test.get(i));
            PIECHART.add_beats(Integer.valueOf(test.get(i).split(",")[0]));
        }
        PIECHART.setPieChart();

        BARCHART = new BarChartCreator(barChart);
        try {
            String cur = test.get(0).split(",")[1].split(" ")[3].split(":")[0];
            ArrayList<String> l = new ArrayList<>();
            float sum = 0;
            for (int i = 0; i < test.size(); ++i) {
                //System.out.println(test.get(i));
                String newCur = test.get(i).split(",")[1].split(" ")[3].split(":")[0];
                if (!newCur.equals(cur)) {
                    l.add(cur + "," + sum);
                    sum = 0;
                } else {
                    sum = sum + Float.valueOf(test.get(i).split(",")[2]);
                }
                cur = newCur;

            }
            l.add(cur + "," + sum);

            for (int i = 0; i < l.size(); ++i) {
                String parts[] = l.get(i).split(",");
                BARCHART.add_labels(parts[0]);
                BARCHART.addEntries(new BarEntry(Float.valueOf(parts[1]), i));
            }

            BARCHART.setBarChart();
        }
        catch (Exception ex){
            System.out.println("Error");
        }



//
//        PIECHART = new PieChartCreator( view , this);
//        PIECHART.setPieChart();
//
//        BARCHART = new BarChartCreator(view);
//        BARCHART.setBarChart();
//
//        LINECHART = new LineChartCreator(view);
//        LINECHART.setLineChart();
//
//        RADARCHART = new RadarChartCreator(view);
//        RADARCHART.setRadarChart();

        final WeakReference<Activity> reference = new WeakReference<Activity>(this);
        new HeartRateConsentTask().execute(reference);

        new HeartRateSubscriptionTask().execute();

    }

    @Override
    protected void onResume() {
        super.onResume();
        // register our handler with the DataLayerService. This ensures we get messages whenever the service receives something.
        //DataLayerListenerService.setHandler(handler);
    }

    @Override
    protected void onPause() {
        // unregister our handler so the service does not need to send its messages anywhere.
        //DataLayerListenerService.setHandler(null);
        super.onPause();
        if (client != null) {
            try {
                client.getSensorManager().unregisterHeartRateEventListener(mHeartRateEventListener);
            } catch (BandIOException e) {
                printStatus(e.getMessage());
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (client != null) {
            try {
                client.disconnect().await();
            } catch (InterruptedException e) {
                // Do nothing as this is happening during destroy
            } catch (BandException e) {
                // Do nothing as this is happening during destroy
            }
        }
        super.onDestroy();
    }


    public void writeBeat(String time){
        try{
            File directory = new File(Environment.getExternalStorageDirectory()+ File.separator+"HerBeat");
            directory.mkdirs();
            String date = time.split(",")[1].split(" ")[1] + time.split(",")[1].split(" ")[2] + time.split(",")[1].split(" ")[5].replace("\\s+","");

            date = date+"log.txt";
            FileWriter fw = new FileWriter("sdcard/HerBeat/" + date.replace("\\s+","").replace("\n", "").replace("\r", ""), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(time);
            bw.close();
        }
        catch (IOException ex){
            System.out.println("Can't open file... " + ex.toString());
        }
    }

    public ArrayList<String> getTimes(String date){
        ArrayList<String> times = new ArrayList<>();
        try{
            String line;
            date = date+"log.txt";
            FileReader fr = new FileReader("sdcard/HerBeat/"+date.replace("\\s+",""));
            BufferedReader br = new BufferedReader(fr);

            while ((line = br.readLine()) != null)   {
                times.add(line);
            }
        }
        catch(IOException ex){
            System.out.println("Can't open file... " + ex.toString());

        }
        return times;
    }

    private class HeartRateSubscriptionTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                if (getConnectedBandClient()) {
                    if (client.getSensorManager().getCurrentHeartRateConsent() == UserConsent.GRANTED) {
                        client.getSensorManager().registerHeartRateEventListener(mHeartRateEventListener);
                    } else {
                        printStatus("You have not given this application consent to access heart rate data yet."
                                + " Please press the Heart Rate Consent button.\n");
                    }
                } else {
                    printStatus("Band isn't connected. Please make sure bluetooth is on and the band is in range.\n");
                }
            } catch (BandException e) {
                String exceptionMessage="";
                switch (e.getErrorType()) {
                    case UNSUPPORTED_SDK_VERSION_ERROR:
                        exceptionMessage = "Microsoft Health BandService doesn't support your SDK Version. Please update to latest SDK.\n";
                        break;
                    case SERVICE_ERROR:
                        exceptionMessage = "Microsoft Health BandService is not available. Please make sure Microsoft Health is installed and that you have the correct permissions.\n";
                        break;
                    default:
                        exceptionMessage = "Unknown error occured: " + e.getMessage() + "\n";
                        break;
                }
                printStatus(exceptionMessage);

            } catch (Exception e) {
                printStatus(e.getMessage());
            }
            return null;
        }
    }

    private class HeartRateConsentTask extends AsyncTask<WeakReference<Activity>, Void, Void> {
        @Override
        protected Void doInBackground(WeakReference<Activity>... params) {
            try {
                if (getConnectedBandClient()) {

                    if (params[0].get() != null) {
                        client.getSensorManager().requestHeartRateConsent(params[0].get(), new HeartRateConsentListener() {
                            @Override
                            public void userAccepted(boolean consentGiven) {
                            }
                        });
                    }
                } else {
                    printStatus("Band isn't connected. Please make sure bluetooth is on and the band is in range.\n");
                }
            } catch (BandException e) {
                String exceptionMessage="";
                switch (e.getErrorType()) {
                    case UNSUPPORTED_SDK_VERSION_ERROR:
                        exceptionMessage = "Microsoft Health BandService doesn't support your SDK Version. Please update to latest SDK.\n";
                        break;
                    case SERVICE_ERROR:
                        exceptionMessage = "Microsoft Health BandService is not available. Please make sure Microsoft Health is installed and that you have the correct permissions.\n";
                        break;
                    default:
                        exceptionMessage = "Unknown error occured: " + e.getMessage() + "\n";
                        break;
                }
                printStatus(exceptionMessage);

            } catch (Exception e) {
                printStatus(e.getMessage());
            }
            return null;
        }
    }

    private void printStatus(final String string) {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                System.out.println(string);
            }
        });
    }

    private boolean getConnectedBandClient() throws InterruptedException, BandException {
        if (client == null) {
            BandInfo[] devices = BandClientManager.getInstance().getPairedBands();
            if (devices.length == 0) {
                printStatus("Band isn't paired with your phone.\n");
                return false;
            }
            client = BandClientManager.getInstance().create(getBaseContext(), devices[0]);
        } else if (ConnectionState.CONNECTED == client.getConnectionState()) {
            return true;
        }

        printStatus("Band is connecting...\n");
        return ConnectionState.CONNECTED == client.connect().await();
    }
}
