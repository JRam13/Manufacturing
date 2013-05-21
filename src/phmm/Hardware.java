package phmm;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Hardware {
	private int airPressure;
	private int currentAmps;
	private int time;
	private static BufferedWriter br;
	private static FileWriter fr;

	public int getAirPressure() {
		return airPressure;
	}

	public int setAirPressure(int airP) {
		if(airP < 0){
			airP = 0;
		}
		else if(airP > 200){
			airP = 200;
		}
		this.airPressure = airP;
		return airPressure;
	}
	
	public int getCurrentAmps() {
		return currentAmps;
	}

	public int setCurrentAmps(int currAmp) {
		if(currAmp < 0){
			currAmp = 0;
		}
		else if(currAmp > 200){
			currAmp = 200;
		}
		this.currentAmps = currAmp;
		return currentAmps;
	}	
	public int getTime() {
		return time;
	}

	public int setTime(int ctime) {
		if(ctime < 0){
			ctime = 0;
		}
		else if(ctime > 100){
			ctime = 100;
		}
		this.time = ctime;
		return time;
	}
	
	public void startMachineManual(){
		File file = new File("Manual.DAS.csv");
    	try {
			fr = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Can't make new file");
		}
    	br = new BufferedWriter(fr);
    	
    	try {
			br.write("Manual");
	    	br.newLine();
	    	int timeIntervals = 0;
	    	for (int i = 0; i < time+1; i++) {
	    		br.write(timeIntervals + "," + airPressure + "," + currentAmps);
	        	br.newLine();
	        	timeIntervals++;
			}
    	br.close();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Can't write to file");
		}
    	
    	if (Desktop.isDesktopSupported()) {
		    try {
		        File myFile = new File("Manual.DAS.csv");
		        Desktop.getDesktop().open(myFile);
		    } catch (Exception ex) {
		        // no application registered for PDFs
		    }
		}
	}

	public void makePart(String recipeName, ArrayList<Integer> times, ArrayList<Integer> airPs, ArrayList<Integer> currAmps, String machineType) {
		
		File file = new File("HW3_" + machineType+".DAS.csv");
    	try {
			fr = new FileWriter(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Can't make new file");
		}
    	br = new BufferedWriter(fr);
    	
    	try {
			br.write(recipeName);
	    	br.newLine();
	    	int timeIntervals = 0;
	    	for (int i = 0; i < times.size(); i++) {
	    		br.write(timeIntervals + "," + airPs.get(i) + "," + currAmps.get(i));
	        	br.newLine();
	        	timeIntervals++;
			}
    	br.close();
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println("Can't write to file");
		}
	}

}
