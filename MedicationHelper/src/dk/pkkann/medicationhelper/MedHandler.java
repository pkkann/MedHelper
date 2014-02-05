package dk.pkkann.medicationhelper;

import java.util.List;

public class MedHandler {
	
	private MedRegister medR;
	
	public MedHandler(MedRegister medR) {
		this.medR = medR;
	}
	
	public void createMedication(String med_name, int minute, int hour) {
		Medication med = new Medication(minute, hour, med_name);
		medR.registerMed(med);
	}
	
	public void saveMedication(int position, String med_name, int minute, int hour) {
		Medication med = medR.getMed(position);
		med.setMed_name(med_name);
		med.setMinute(minute);
		med.setHour(hour);
	}
	
	public void deleteMedication(int position) {
		Medication med = medR.getMed(position);
		medR.removeMed(med);
	}
	
	public Medication getMedication(String med_name) {
		return medR.getMed(med_name);
	}
	
	public Medication getMedication(int index) {
		return medR.getMed(index);
	}
	
	public List<Medication> getAllMedications() {
		return medR.getMedications();
	}

}
