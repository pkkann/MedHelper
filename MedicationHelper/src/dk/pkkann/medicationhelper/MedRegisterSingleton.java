package dk.pkkann.medicationhelper;

public class MedRegisterSingleton {
	
	private static MedRegister instance;
	
	private MedRegisterSingleton() {}
	
	public static MedRegister getInstance() {
		if(instance == null) {
			makeInstance();
			return instance;
		} else {
			return instance;
		}
	}
	
	private static void makeInstance() {
		instance = new MedRegister();
	}

}
