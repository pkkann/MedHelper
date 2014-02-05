package dk.pkkann.medicationhelper;

import java.util.Date;

public class Medication {
	
	private int minute, hour;
	private Date created;
	private String med_name;
	
	public Medication(int minute, int hour, String med_name) {
		this.minute = minute;
		this.hour = hour;
		this.med_name = med_name;
		this.created = new Date();
	}
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getMed_name() {
		return med_name;
	}

	public void setMed_name(String med_name) {
		this.med_name = med_name;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	
	

}
