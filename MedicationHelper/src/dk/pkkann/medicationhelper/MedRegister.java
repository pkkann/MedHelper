package dk.pkkann.medicationhelper;

import java.util.ArrayList;
import java.util.List;

public class MedRegister {
	
	private List<Medication> meds = new ArrayList<Medication>();
	
	public MedRegister() {
		
	}
	
	public void setMedications(List<Medication> meds) {
		this.meds = meds;
	}
	
	public List<Medication> getMedications() {
		return meds;
	}
	
	public void registerMed(Medication med) {
		meds.add(med);
	}
	
	public Medication getMed(int index) {
		return meds.get(index);
	}
	
	public Medication getMed(String med_name) {
		for(Medication med : meds) {
			if(med.getMed_name().equals(med_name)) {
				return med;
			}
		}
		return null;
	}
	
	public void removeMed(int index) {
		meds.remove(index);
	}
	
	public void removeMed(Medication med) {
		meds.remove(med);
	}
	
	public void removeMed(String med_name) {
		for(Medication med : meds) {
			if(med.getMed_name().equals(med_name)) {
				meds.remove(meds.indexOf(med));
			}
		}
	}

}
