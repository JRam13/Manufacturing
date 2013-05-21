package phmm;

import java.io.IOException;
import java.util.Scanner;

/* 
 * 
 * The PhFMM must support a simple UserInterface layer, which should:
1. Allow the user to manually set control values in the hardware system.
2. Allow the user to read the control values. 
3. Start the system using the manually controlled values, let it run for T seconds, and then automatically Stop.
4. Allow the user to select a recipe that is used to manufacture a particular part and execute that recipe.
*
*
 */

public class UserInterface {
	private static int option;
	private static MachineControlFacade mch;

	
	public static void startUI() throws Exception {
		
		mch = new MachineControlFacade();
		
	    while (option != 12) {
	    	System.out.println("Machine Control");
	        System.out.println("--------------------------");
	    	System.out.println("1) Set Control Values");
	        System.out.println("2) Read Control Values");
	        System.out.println("3) Manual Start");
	        System.out.println("4) Select Recipe");
	        System.out.println("12) Back To Main Menu \n");
	    
		    Scanner scn = new Scanner(System.in); 
			System.out.println("Choose a number: ");
			try {
				option = scn.nextInt();
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
		
			if(option == 1){
				System.out.println();
				setControls();
			}
			else if(option == 2){
				System.out.println();
				viewControls();
			}
			else if(option == 3){
				System.out.println();
				ManualStart();
			}
			else if(option == 4){
				System.out.println();
				selectRecipe();
			}
			else if(option == 12){
				option = 0;
				break;
			}
			else {
				System.out.println("Choose a proper option");
			}
	    }
	
	}
	
	private static void setControls(){
		Scanner scn = new Scanner(System.in); 
		System.out.println("Select Air Pressure (PSI 0-200): ");
		int psi = scn.nextInt();
		System.out.println("Select Electric Current (Amps 0-200): ");
		int amps = scn.nextInt();
		System.out.println("Select Time (Seconds 0-100): ");
		int time = scn.nextInt();
		
		mch.setControlValues(psi, amps, time);
		
		System.out.println();
		
	}
	
	private static void viewControls(){
		
		mch.getControlValues();
		
		System.out.println();
		
	}
	
	private static void ManualStart() throws IOException{
		mch.startMachine();
	}
	
	private static void selectRecipe() throws Exception{
		System.out.println("Select Recipe");
        System.out.println("--------------------------");
    	System.out.println("1) Constant Pressure");
        System.out.println("2) Constant Current");
        System.out.println("3) Ramp");
        
        Scanner scn = new Scanner(System.in); 
		System.out.println("Choose a number: ");
		try {
			option = scn.nextInt();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		if(option == 1){
			System.out.println();
			mch.startConstantPressure();
		}
		else if(option == 2){
			System.out.println();
			mch.startConstantCurrent();
		}
		else if(option == 3){
			System.out.println();
			mch.startRamp();
		}
		mch.isPartGood();
        System.out.println();
	}
	
	
	
}
