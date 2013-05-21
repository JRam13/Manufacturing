package phmm;

import java.io.File;
import java.util.Scanner;

public interface Recipes {
	String recipeName();
	int airPressure();
	int currentAmps();
	int time();
	int partSize();
	
}

class ConstantPressure implements Recipes {
	private String recName;
	private int airP;
	private int currAmps;
	private int partS;
	
	public ConstantPressure() throws Exception{
		Scanner readFile = new Scanner (new File("hw3_ConstantPressure.csv"));
		this.recName = readFile.nextLine();
		Scanner readPartSize = new Scanner (recName); 
		readPartSize.useDelimiter(",");
		readPartSize.next();
		readPartSize.next();
		this.partS = readPartSize.nextInt();
		
		this.airP = this.partS + 100;
	}
	
	@Override
	public int airPressure() {
		// TODO Auto-generated method stub
		return this.airP;
	}

	@Override
	public int currentAmps() {
		// TODO Auto-generated method stub
		return this.currAmps;
	}

	@Override
	public int time() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public String recipeName() {
		// TODO Auto-generated method stub
		return recName;
	}

	@Override
	public int partSize() {
		// TODO Auto-generated method stub
		return partS;
	}
	
}

class ConstantCurrent implements Recipes {
	private String recName;
	private int currAmps;
	private int partS;
	
	public ConstantCurrent() throws Exception{
		Scanner readFile = new Scanner (new File("hw3_ConstantCurrent.csv"));
		this.recName = readFile.nextLine();
		Scanner readPartSize = new Scanner (recName); 
		readPartSize.useDelimiter(",");
		readPartSize.next();
		readPartSize.next();
		this.partS = readPartSize.nextInt();
		
		currAmps = partS + 50;
	}
	
	@Override
	public int airPressure() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int currentAmps() {
		// TODO Auto-generated method stub
		return this.currAmps;
	}

	@Override
	public int time() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public String recipeName() {
		// TODO Auto-generated method stub
		return recName;
	}

	@Override
	public int partSize() {
		// TODO Auto-generated method stub
		return partS;
	}
	
}

class Ramp implements Recipes {
	private String recName;
	private int currAmps;
	private int partS;
	
	public Ramp() throws Exception{
		Scanner readFile = new Scanner (new File("hw3_Ramp.csv"));
		this.recName = readFile.nextLine();
		Scanner readPartSize = new Scanner (recName); 
		readPartSize.useDelimiter(",");
		readPartSize.next();
		readPartSize.next();
		this.partS = readPartSize.nextInt();
		
		currAmps = partS;
	}
	
	@Override
	public int airPressure() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int currentAmps() {
		// TODO Auto-generated method stub
		return this.currAmps;
	}

	@Override
	public int time() {
		// TODO Auto-generated method stub
		return 30;
	}

	@Override
	public String recipeName() {
		// TODO Auto-generated method stub
		return recName;
	}

	@Override
	public int partSize() {
		// TODO Auto-generated method stub
		return partS;
	}
	
}
