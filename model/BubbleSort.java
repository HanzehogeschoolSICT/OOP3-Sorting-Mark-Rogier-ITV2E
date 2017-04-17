package model;
import java.util.ArrayList;

public class BubbleSort extends Sort {

	private int i = 0;
	
	public void step(ArrayList<Integer> array) {
		if(array.get(j-1) > array.get(j)) {
            int temp = array.get(j);
            array.set(j,array.get(j-1));
            array.set(j-1,temp);
		}
		if(j == array.size()-i-1) {
			i++;
			j=1;
		} else {
			j++;
		}
	}
	
	public String toString() {
		return "BubbleSort";
	}

	public void reset(ArrayList<Integer> array) {
		i=0;
		j=1;
	}
}