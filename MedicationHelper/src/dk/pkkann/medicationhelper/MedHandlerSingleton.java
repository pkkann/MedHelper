package dk.pkkann.medicationhelper;

public class MedHandlerSingleton {
	
	private static MedHandler instance;
	
	private MedHandlerSingleton() {}
	
	public static MedHandler getInstance() {
		if(instance == null) {
			makeInstance();
			return instance;
		} else {
			return instance;
		}
	}
	
	private static void makeInstance() {
		instance = new MedHandler(MedRegisterSingleton.getInstance());
	}

}
