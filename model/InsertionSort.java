package model;
import java.util.ArrayList;

public class InsertionSort extends Sort {
	
	private int i;
	private int temp;
	private int value;

	public void step(ArrayList<Integer> array) {
		if(i < array.size()) {
			if(temp==-1) {
				temp = array.get(i); 
			}
			
			if(j>=0 && temp < array.get(j)) {
				value = array.get(j);
				array.set(j+1, value);
				j--;
			} else {
				array.set(j+1, temp);
				i++;
				if(i < array.size()) { 
					temp = array.get(i);
				}
				j=i-1;
			}
		}
	}
	
	public String toString() {
		return "InsertionSort";
	}

	public void reset(ArrayList<Integer> array) {
		i=1;
		j=0;
		temp=-1;
	}
}