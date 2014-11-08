package toroshu.stepcounter;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SecondActivity extends ListActivity {
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second1);

		String[] options = { "X", "Y", "Z" };
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, options));
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		
		Intent intent = new Intent(SecondActivity.this, ButtonActivity.class);
		intent.putExtra(MainActivity.NO, position + 1);
		startActivity(intent);
	}
}
