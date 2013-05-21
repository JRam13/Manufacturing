package phmm;

import java.io.IOException;

public class MachineControlFacade {
	private static MachineControl mch;
	public Recipes recipeType;
	
	public MachineControlFacade() {
		
		mch = new MachineControl();
	}
	
	//DOES NOT MODIFY VALUES (HARDWARE DOES)
	public void setControlValues(int psi, int curr, int time){
		mch.setAirPressure(psi);
		mch.setCurrentAmps(curr);
		mch.setTime(time);
		
		System.out.println("Control Values Set");
	}
	
	//GET MODIFIED VALUES
	public void getControlValues(){
		System.out.println("Air Pressure Value is: " + mch.getAirPressure() + "psi");
		System.out.println("Electric current is set to: " + mch.getCurrentAmps() + "AMPs");
		System.out.println("Time for manufacturing is: " + mch.getTime() + "secs");
	}
	
	public void startMachine() throws IOException{
		mch.startMachineManual();
	}
	
	//SET THE RECIPE TYPE (VIA STRATEGY PATTERN)
	public void startConstantPressure() throws Exception{
		recipeType = new ConstantPressure();
		mch.setValues(recipeType.recipeName(), recipeType.airPressure(), recipeType.time(), recipeType.partSize(), recipeType.currentAmps());
	}

	public void startConstantCurrent() throws Exception {
		recipeType = new ConstantCurrent();
		mch.setValues(recipeType.recipeName(), recipeType.airPressure(), recipeType.time(), recipeType.partSize(), recipeType.currentAmps());
	}

	public void startRamp() throws Exception {
		recipeType = new Ramp();
		mch.setValues(recipeType.recipeName(), recipeType.airPressure(), recipeType.time(), recipeType.partSize(), recipeType.currentAmps());

	}

	public void isPartGood() {
		if(mch.isPartGood()){
			System.out.println("Recipe successfully created.");
		}
		else{
			System.err.println("Recipe Created, but part is defective.");
		}
		
	}
}
