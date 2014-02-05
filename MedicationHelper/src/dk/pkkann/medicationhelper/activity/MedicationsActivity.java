package dk.pkkann.medicationhelper.activity;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import dk.pkkann.medicationhelper.MedHandler;
import dk.pkkann.medicationhelper.MedHandlerSingleton;
import dk.pkkann.medicationhelper.Medication;
import dk.pkkann.medicationhelper.R;

public class MedicationsActivity extends Activity {
	
	private MedHandler medH;
	public static ArrayAdapter<Medication> adapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_medications);
		
		medH = MedHandlerSingleton.getInstance();
		
		populateListView();
		registerOnClick();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.medications, menu);
		return true;
	}
	
	private void registerOnClick() {
		ListView list = (ListView) findViewById(R.id.med_list);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
				Medication med = medH.getMedication(position);
				
				Intent intent = new Intent(MedicationsActivity.this, EditMedActivity.class);
				
				intent.putExtra("med_name", med.getMed_name());
				intent.putExtra("minute", med.getMinute());
				intent.putExtra("hour", med.getHour());
				intent.putExtra("position", position);
				
				startActivity(intent);
			}
		});
		
	}
	
	public void action_add_med(MenuItem item) {
		Intent intent = new Intent(this, AddMedActivity.class);
		startActivity(intent);
	}

	private void populateListView() {
		adapter = new MyListAdapter(medH.getAllMedications());
		ListView list = (ListView) findViewById(R.id.med_list);
		list.setAdapter(adapter);
	}

	private class MyListAdapter extends ArrayAdapter<Medication> {
		public MyListAdapter(List<Medication> objects) {
			super(MedicationsActivity.this, R.layout.item_view, objects);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// Make sure we have a view to work with (May have been given null)
			View itemView = convertView;
			if(itemView == null) {
				itemView = getLayoutInflater().inflate(R.layout.item_view, parent, false);
			}
			
			// Find the car to work with
			Medication currentMed = medH.getMedication(position);
			
			// Fill the view
			TextView titleView = (TextView) itemView.findViewById(R.id.item_title);
			titleView.setText(currentMed.getMed_name());
			
			TextView subtitleView = (TextView) itemView.findViewById(R.id.item_subtitle);
			String hourS = String.valueOf(currentMed.getHour());
			String minuteS = String.valueOf(currentMed.getMinute());
			
			if(hourS.length() < 2) {
				hourS = "0" + hourS;
			}
			if(minuteS.length() < 2) {
				minuteS = "0" + minuteS;
			}
			
			subtitleView.setText("" + hourS + ":" + minuteS);
			
			return itemView;
		}
		
		
	}

}
