package dk.pkkann.medicationhelper.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import dk.pkkann.medicationhelper.MedHandler;
import dk.pkkann.medicationhelper.MedHandlerSingleton;
import dk.pkkann.medicationhelper.R;

public class AddMedActivity extends Activity {
	
	private MedHandler medH;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_med);
		
		medH = MedHandlerSingleton.getInstance();
		TimePicker time = (TimePicker) findViewById(R.id.add_time);
		time.setIs24HourView(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_med, menu);
		return true;
	}
	
	public void action_finish(MenuItem item) {
		EditText med_name = (EditText) findViewById(R.id.add_med_name);
		TimePicker time = (TimePicker) findViewById(R.id.add_time);
		
		medH.createMedication(med_name.getText().toString(), time.getCurrentMinute(), time.getCurrentHour());
		Toast.makeText(this, getString(R.string.toast_added) + " - " + time.getCurrentHour() + ":" + time.getCurrentMinute(), Toast.LENGTH_SHORT).show();
		MedicationsActivity.adapter.notifyDataSetChanged();
		finish();
	}

}
