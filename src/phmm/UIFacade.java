package phmm;

import java.util.Scanner;


public class UIFacade {
	private static int option;
	private static MachineControlFacade mch;
	
	public static void main(String[] args) throws Exception {
		
		mch = new MachineControlFacade();
		new UserInterface();
		
		System.out.println("==========================================================");
		System.out.println("Welcome To The Phoenix Fictitious Manufacturing Machine");
	    System.out.println("==========================================================\n");
		
	    while (option != 12) {
	    	System.out.println("Main Menu");
	        System.out.println("--------------------------");
	    	System.out.println("1) Ceate Automated Test");
	        System.out.println("(Runs a Manual and Recipe Machines Without User Interface) \n");
	        System.out.println("2) Go To Machine Control");
	        System.out.println("(Create Custom Control Values or Choose Recipes) \n");
	        System.out.println("12) Exit \n");
	    
		    Scanner scn = new Scanner(System.in); 
			System.out.println("Choose a number: ");
			try {
				option = scn.nextInt();
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
		
			if(option == 1){
				System.out.println();
				makeTest();
			}
			else if(option == 2){
				System.out.println();
				UserInterface.startUI();
			}
			else if(option == 12){
				System.out.println("Goodbye.");
				System.exit(1);
			}
	    }
	}

	private static void makeTest() throws Exception {
		
		mch.setControlValues(100, 200, 30);
		System.out.println("Making manual file with control values of: ");
		mch.getControlValues();
		mch.startMachine();
		
		System.out.println();
		
		System.out.println("Making Recipe: Constant Pressure");
		mch.startConstantPressure();
		System.out.println();
		
		System.out.println("Making Recipe: Constant Current");
		mch.startConstantCurrent();
		System.out.println();
		
		System.out.println("Making Recipe: Ramp");
		mch.startConstantCurrent();
		System.out.println();
		
	}
	

}
