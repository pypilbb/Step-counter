package toroshu.stepcounter;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ButtonActivity extends Activity {

	SharedPreferences preferences;
	Editor editor;

	public static final String PREFS = "Myprefs";

	public static final String FN = "File Name";

	public static final String SR = "Service Running";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_button);

		Intent intent = getIntent();
		final int i = intent.getIntExtra(MainActivity.NO, 0);

		// generating file Name
		Calendar c = Calendar.getInstance();
		final String fileName = c.get(Calendar.YEAR) + "_" + c.get(Calendar.MONTH)
				+ "_" + c.get(Calendar.DAY_OF_MONTH) + "_"
				+ c.get(Calendar.HOUR_OF_DAY) + "_" + c.get(Calendar.MINUTE)
				+ "_" + c.get(Calendar.SECOND);

		Toast.makeText(ButtonActivity.this, fileName, Toast.LENGTH_SHORT)
				.show();

		preferences = getSharedPreferences(PREFS, MODE_PRIVATE);
		editor = preferences.edit();

		final Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener() {

			Intent intent = new Intent(ButtonActivity.this, DataRecorder.class);

			@Override
			public void onClick(View v) {
				if (preferences.getBoolean(SR, false)) {
					
					
					editor.putBoolean(SR, false);
					editor.commit();

					button.setText("Start Recording Data ");
					
					stopService(intent);

				} else {

					editor.putBoolean(SR, true);
					editor.commit();

					button.setText("Stop Recording Data ");

					intent.putExtra("MetaData", i);
					intent.putExtra(FN, fileName);
					startService(intent);

				}

			}
		});

	}

}
