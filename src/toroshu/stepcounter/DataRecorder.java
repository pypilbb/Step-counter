package toroshu.stepcounter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class DataRecorder extends Service implements SensorEventListener {

	SensorManager sm;
	Sensor sensor;

	String fileName;

	String x[], y[], z[];
	String t[];
	int i = 0;

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Deprecated
	public void onStart(Intent intent, int startId) {
		Log.e("hi", "bro");

		// Sensors wali thing
		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

		sm.registerListener((SensorEventListener) this, sensor,
				100000);

		// file name
		fileName = intent.getStringExtra(ButtonActivity.FN);

		x = new String[10000];
		y = new String[10000];
		z = new String[10000];
		t = new String[10000];

		Toast.makeText(getApplicationContext(), "[  Data Recording Started  ]",
				Toast.LENGTH_SHORT).show();

		super.onStart(intent, startId);
	}

	@Override
	public void onDestroy() {
		Log.e("hi", "bye");
 
		Toast.makeText(getApplicationContext(), "]  Data Recording Stopped  [",
				Toast.LENGTH_SHORT).show();

		try {
			File sdcard = Environment.getExternalStorageDirectory();
			File directory = new File(sdcard.getAbsolutePath()
					+ "/Step Counter");
			directory.mkdir();
			File file = new File(directory, fileName + ".txt");
			FileOutputStream fout = new FileOutputStream(file);
			OutputStreamWriter osw = new OutputStreamWriter(fout);

			String str = "x = [ " + arrayToString(x)
					+ "]\n0000000000000000000000000000000000000000\ny = [  " + arrayToString(y)
					+ "]\n0000000000000000000000000000000000000000\nz = [  :  " + arrayToString(z)
					+ "]\n0000000000000000000000000000000000000000\nt = [  :  " + arrayToString(t) + " ]";

			osw.write(str);
			osw.flush();
			osw.close();

			Toast.makeText(getApplicationContext(), "Data Saved",
					Toast.LENGTH_SHORT).show();

			sm.unregisterListener(this);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		super.onDestroy();
	}

	String arrayToString(String[] str) {

		String result = "";
		for (int i = 0; (i < str.length && str[i + 1] != null); i++) {
			result += str[i] + " ";
		}
		return result;

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		try {

			t[i] = String.valueOf(System.currentTimeMillis()) + " , ";
			x[i] = String.valueOf(event.values[0]) + " , ";
			y[i] = String.valueOf(event.values[1]) + " , ";
			z[i] = String.valueOf(event.values[2]) + " , ";
			i++;

		}

		catch (Exception e) {
			t[i++] = e.toString();
			onDestroy();
		}
	}

}
