package ca.yorku.eecs.mcalcpro;

import android.annotation.SuppressLint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import ca.roumani.i2c.MPro;

import static android.content.Context.SENSOR_SERVICE;

public class MCalcPro_Activity extends AppCompatActivity implements TextToSpeech.OnInitListener, SensorEventListener {
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mcalcpro_layout);
        this.tts = new TextToSpeech(this, this);

        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void onInit(int status) {
        this.tts.setLanguage(Locale.US);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        double ax = event.values[0];
        double ay = event.values[1];
        double az = event.values[2];
        double a  = Math.sqrt(ax*ax + ay*ay + az*az);
        if (a>20){
            ((EditText) findViewById(R.id.pBox)).setText("");
            ((EditText) findViewById(R.id.aBox)).setText("");
            ((EditText) findViewById(R.id.iBox)).setText("");
            ((TextView) findViewById(R.id.output)).setText("");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {

    }

    public void buttonClicked(View v){

        EditText pBox = (EditText)findViewById(R.id.pBox);
        String p = pBox.getText().toString();

        EditText aBox = (EditText)findViewById(R.id.aBox);
        String a = aBox.getText().toString();

        EditText iBox = (EditText)findViewById(R.id.iBox);
        String i = iBox.getText().toString();


        try{

            MPro mp = new MPro();
            mp.setPrinciple(p);
            mp.setAmortization(a);
            mp.setInterest(i);
            System.out.println(mp.computePayment("%.2f"));
            System.out.println(mp.outstandingAfter(2,"%,16.0f"));

            String s = "Monthly payment = " + mp.computePayment("%,.2f");


            s += "\n\n";
            s += "By making this payments monthly for ";
            s += "\n\n";
            s += String.format("%8d", 0) + mp.outstandingAfter(0, "%,16.0f");
            s += "\n\n";
            s += String.format("%8d", 1) + mp.outstandingAfter(1, "%,16.0f");
            s += "\n\n";
            s += String.format("%8d", 2) + mp.outstandingAfter(2, "%,16.0f");
            s += "\n\n";
            s += String.format("%8d", 3) + mp.outstandingAfter(3, "%,16.0f");
            s += "\n\n";
            s += String.format("%8d", 4) + mp.outstandingAfter(4, "%,16.0f");
            s += "\n\n";
            s += String.format("%8d", 5) + mp.outstandingAfter(5, "%,16.0f");
            s += "\n\n";
            s += String.format("%8d", 10) + mp.outstandingAfter(10, "%,16.0f");
            s += "\n\n";
            s += String.format("%8d", 15) + mp.outstandingAfter(15, "%,16.0f");
            s += "\n\n";
            s += String.format("%8d", 20) + mp.outstandingAfter(20, "%,16.0f");


            ((TextView) findViewById(R.id.output)).setText(s);
            tts.speak(s, TextToSpeech.QUEUE_FLUSH, null);
        }catch(Exception e)
        {
            e.getMessage();
            Toast label = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            label.show();
        }
    }
}