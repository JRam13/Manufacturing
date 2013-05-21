package phmm;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * The PhFMM must also contain a MachineControl layer that accepts user input, and manages and coordinates the hardware. It should:
1. Get control values from the hardware system to the UI.
2. Send control values to the hardware system.
3. Support 3 different machine modes (i.e. types of recipe strategies), in which the hardware is controlled, and varies, 
		in one of 3 particular ways over a given amount of time. The names and algorithms for the three modes are:
	3.1. ConstantPressure: T (time, in seconds) = { 0, 1,...10 }, PSI (should remain constant) = part size + 100, AMPS = T * 2
	3.2. ConstantCurrent: T = { 0...20 }, PSI = 50 - (T * 2) where PSI can never get below 10, AMPS (constant) = part size + 50
	3.3. Ramp: T = { 0...30 }, PSI = ramp up from 0 to 100, in 10 PSI increments each second and then hold at 100,  
	AMPS = ramp up, starting from part size, in 20 AMP increments each second. Only part sizes of 50 or above can be used. 
	If a recipe has a smaller part size, the MachineControl must generate an error. This part size restriction only applies to the Ramp recipe.
4. Support executing a recipe.
5. Validate that a recipe executed successfully by comparing generated data with reference data. If the data matches, 
		the MachineControl should return a 'good part' result, otherwise return a 'bad part' result.  
		When the UserInterface manually controls the hardware, no validation is necessary.
 */

public class MachineControl {
	private int airPressure;
	private int currentAmps;
	private int time;
	private static HardwareFacade hw;
	private ArrayList<Integer> airPs; 
	private ArrayList<Integer> currAmps; 
	private ArrayList<Integer> times; 
	private boolean isPartGood;
	

	public MachineControl(){
		hw = new HardwareFacade();
		airPs = new ArrayList<Integer>();
		currAmps = new ArrayList<Integer>();
		times = new ArrayList<Integer>();
	}
	
	public int getAirPressure() {
		return airPressure;
	}
	public void setAirPressure(int airP) {
		airPressure = hw.setAirPressure(airP);
	}
	public int getCurrentAmps() {
		return currentAmps;
	}
	public void setCurrentAmps(int currentAmps) {
		this.currentAmps = hw.setCurrentAmps(currentAmps);
	}
	public int getTime() {
		return time;
	}
	public void setTime(int ctime) {
		this.time = hw.setTime(ctime);
	}
	
	public void startMachineManual() throws IOException{
		hw.startMachineManual();
	}

	public void setValues(String recipeName, int airP, int time2, int partSize, int currAmp) throws IOException {
		Scanner readName = new Scanner (recipeName); 
		readName.useDelimiter(",");
		readName.next();
		String machineType = readName.next();
		
		if(machineType.equals("ConstantPressure")){
			makeConstantPressureValues(recipeName, airP, time2, partSize, machineType);
		}
		else if(machineType.equals("ConstantCurrent")){
			makeConstantCurrentValues(recipeName, currAmp, time2, partSize, machineType);
		}
		else if(machineType.equals("Ramp")){
			makeRampValues(recipeName, airP, currAmp, time2, partSize, machineType);
		}
		else {
			System.err.println("Could not find that Machine Type");
		}
		
		isPartGood = isPartGood(machineType);
		appendResults(machineType);
		
	}
	
	private boolean isPartGood(String machineType) throws FileNotFoundException {
		Scanner referenceScanner = new Scanner (new File("HW3_" + machineType + ".csv.reference.csv"));
		referenceScanner.nextLine();
		Scanner dasFile = new Scanner (new File ("HW3_" + machineType + ".DAS.csv"));
		dasFile.nextLine();
		//Scanner readPartSize = new Scanner (recName); 
		//readPartSize.useDelimiter(",");
		while(dasFile.hasNext()){
			if(!referenceScanner.nextLine().equals(dasFile.nextLine())){
				return false;
			}
		}
		return true;
		
	}

	private void makeRampValues(String recipeName, int airP, int currAmp,
			int time2, int partSize, String machineType) {
		
		if(partSize > 49){
			int timeInterval = 0;
			int psi = 0;
			int amp = currAmp;
			for (int i = 0; i < time2+1; i++) {
				times.add(timeInterval);
				airPs.add(psi);
				currAmps.add(amp);
				timeInterval++;
				psi += 10;
				if(psi > 100){
					psi = 100;
				}
				amp += 20;
				if(amp > 200){
					amp = 200;
				}
				
			}
			hw.makePart(recipeName, times , airPs, currAmps, machineType);
			airPs.clear();
			currAmps.clear();
			times.clear();
		}
		else{
			System.err.println("Cannot process Machine Type Ramp: Part size too small.");
		}
		
	}

	private void makeConstantCurrentValues(String recipeName, int currAmp,
			int time2, int partSize, String machineType) {
		
		int timeInterval = 0;
		for (int i = 0; i < time2+1; i++) {
			times.add(timeInterval);
			int psi = 50 - (timeInterval * 2);
			if(psi < 10){
				psi = 10;
			}
			airPs.add(psi);
			currAmps.add(currAmp);
			timeInterval++;
		}
		hw.makePart(recipeName, times , airPs, currAmps, machineType);
		airPs.clear();
		currAmps.clear();
		times.clear();
		
	}

	private void makeConstantPressureValues(String recipeName, int airP, int time2, int partSize, String machineType){
		
		int timeInterval = 0;
		for (int i = 0; i < time2+1; i++) {
			times.add(timeInterval);
			airPs.add(airP);
			currAmps.add(timeInterval * 2);
			timeInterval++;
		}
		hw.makePart(recipeName, times , airPs, currAmps, machineType);
		airPs.clear();
		currAmps.clear();
		times.clear();
	}
	
	public boolean isPartGood() {
		return isPartGood;
	}
	
	private void appendResults(String machineType) throws IOException{
		
		try
		{
		    String filename= "HW3_" + machineType+".DAS.csv";
		    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
		    if(isPartGood){
		    	fw.write(",,,GOOD PART \n");//appends the string to the file
		    }
		    else{
		    	fw.write(",,,BAD PART \n");//appends the string to the file
		    }
		    fw.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
		
		if (Desktop.isDesktopSupported()) {
		    try {
		        File myFile = new File("HW3_" + machineType+".DAS.csv");
		        Desktop.getDesktop().open(myFile);
		    } catch (Exception ex) {
		        // no application registered for PDFs
		    }
		}
	}
	

}
