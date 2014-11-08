package toroshu.stepcounter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

	public static final String NO = "Navigation Option";
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		String[] options = { "Mono-axial analysis", "Full analysis" };

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, options);

		setListAdapter(adapter);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Toast.makeText(MainActivity.this, position + " selected!!",
				Toast.LENGTH_SHORT).show();

		if (position == 0) {

			intent = new Intent(this, SecondActivity.class);

			intent.putExtra(NO, position);
		} else {

			intent = new Intent(this, ButtonActivity.class);

		}
		startActivity(intent);
	}
}
