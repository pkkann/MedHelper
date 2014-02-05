package dk.pkkann.medicationhelper.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import dk.pkkann.medicationhelper.MedHandler;
import dk.pkkann.medicationhelper.MedHandlerSingleton;
import dk.pkkann.medicationhelper.R;

public class EditMedActivity extends Activity {
	
	private Intent intent;
	private TimePicker time;
	private MedHandler medH;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_med);
		
		medH = MedHandlerSingleton.getInstance();
		
		intent = getIntent();
		
		time = (TimePicker) findViewById(R.id.edit_time);
		time.setIs24HourView(true);
		
		populateView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_med, menu);
		return true;
	}
	
	private void populateView() {
		EditText med_name = (EditText) findViewById(R.id.edit_med_name);
		med_name.setText(intent.getStringExtra("med_name"));
		
		time.setCurrentHour(intent.getIntExtra("hour", 12));
		time.setCurrentMinute(intent.getIntExtra("minute", 12));
	}
	
	public void action_save_med(MenuItem item) {
		EditText med_nameView = (EditText) findViewById(R.id.edit_med_name);
		String med_name = med_nameView.getText().toString();
		
		int hour = time.getCurrentHour();
		int minute = time.getCurrentMinute();
		int position = intent.getIntExtra("position", -1);
		
		medH.saveMedication(position, med_name, minute, hour);
		MedicationsActivity.adapter.notifyDataSetChanged();
		Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
		finish();
	}
	
	public void action_remove_med(MenuItem item) {
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		        switch (which){
		        case DialogInterface.BUTTON_POSITIVE:
		        	int position = intent.getIntExtra("position", -1);
		    		medH.deleteMedication(position);
		    		MedicationsActivity.adapter.notifyDataSetChanged();
		    		Toast.makeText(EditMedActivity.this, "Deleted", Toast.LENGTH_SHORT).show();
		    		finish();
		            break;

		        case DialogInterface.BUTTON_NEGATIVE:
		            //No button clicked
		            break;
		        }
		    }
		};

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Are you sure you want to delete?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No", dialogClickListener).show();
		
	}

}
