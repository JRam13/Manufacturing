package phmm;

import java.util.ArrayList;

public class HardwareFacade {
	private static Hardware hw;
	
	public HardwareFacade() {
		
		hw = new Hardware();
	}

	public int setAirPressure(int airP) {
		return hw.setAirPressure(airP);
	}

	public int setCurrentAmps(int currentAmps) {
		return hw.setCurrentAmps(currentAmps);
	}

	public int setTime(int ctime) {
		return hw.setTime(ctime);
	}

	public void startMachineManual() {
		System.out.println("Manually starting machine...");
		hw.startMachineManual();
		System.out.println("Manually stopping maching. \n");
		
	}

	public void makePart(String recipeName, ArrayList<Integer> times, ArrayList<Integer> airPs,
			ArrayList<Integer> currAmps, String machineType) {
		
		System.out.println("Starting machine...");
		System.out.println("Automatically starting recipe for: " + machineType);
		hw.makePart(recipeName, times, airPs, currAmps, machineType);
		System.out.println("Stopping machine. \n");
		
	}
	
}
