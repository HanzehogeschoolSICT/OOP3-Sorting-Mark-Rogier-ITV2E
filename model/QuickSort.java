package model;
import java.util.ArrayList;
import java.util.HashMap;

public class QuickSort extends Sort {
	
	private int pivot;
	private int wall;
	private int startWall;
	
	private boolean looping = false;
	
	private HashMap<Integer, Integer> ranges = new HashMap<Integer, Integer>();
	
	public void step(ArrayList<Integer> array) {
		if(!looping) {
			beforeLoop(array);
			looping = true;
		}
		
		if(looping) {
			if(j < pivot) {
				loop(array);
			} else {
				looping = false;
			}
		}
		
		if(!looping) {
			afterLoop(array);
		}
	}
	
	private void beforeLoop(ArrayList<Integer> array) {
		if(ranges.size() != 0) {
			wall = (int) ranges.keySet().toArray()[0];
			pivot = (int) ranges.values().toArray()[0];
			ranges.remove(wall);
		}
		
		startWall = wall;
		
		j = wall;
	}
	
	private void loop(ArrayList<Integer> array) {
		if(array.get(j) < array.get(pivot)) {
			int temp = array.get(wall);
			
			array.set(wall, array.get(j));
			array.set(j, temp);
			wall++;
		}
		j++;
	}
	
	private void afterLoop(ArrayList<Integer> array) {
		int temp = array.get(wall);
		array.set(wall, array.get(pivot));
		array.set(pivot, temp);
		
		if((wall-1) - startWall > 1) {
			ranges.put(startWall, wall-1);
		}
		if(pivot - wall > 1) {
			ranges.put(wall, pivot);
		}
		
		int zero = array.get(0);
		int one = array.get(1);
		if(zero > one) {
			array.set(0, one);
			array.set(1, zero);
		}
	}
	
	public String toString() {
		return "QuickSort";
	}

	public void reset(ArrayList<Integer> array) {
		j = 0;
		pivot = array.size()-1;
		wall = 0;
		if(ranges != null) {
			ranges.clear();
		}
		looping = false;
	}
}