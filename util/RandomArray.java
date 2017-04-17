package util;
import java.util.ArrayList;
import java.util.Collections;

public class RandomArray {
	
	private ArrayList<Integer> array;
	
	public RandomArray() {
		array = new ArrayList<Integer>();
	}
	
	public RandomArray(ArrayList<Integer> array) {
		this.array = array;
	}
	
	public RandomArray fillArray(int n) {
		for(int i=1; i<=n; i++) {
			array.add(i);
		}
		
		return this;
	}
	
	public String toString() {
		return array.toString();
	}
	
	public ArrayList<Integer> getArray() {
		return array;
	}
	
	public RandomArray randomShuffle() {
		Collections.shuffle(array);
		
		return this;
	}
}
