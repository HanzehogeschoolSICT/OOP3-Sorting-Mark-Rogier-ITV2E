package model;
import java.util.ArrayList;

public abstract class Sort {
		
	protected int j = 0;
	
	public Sort() {
		return;
	}
	
	public Sort(ArrayList<Integer> array) {
		reset(array);
	}
	
	public abstract void step(ArrayList<Integer> array);
	
	public abstract String toString();
	
	public abstract void reset(ArrayList<Integer> array);
	
	public int getPointer() {
		return j;
	}
}



