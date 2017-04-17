package model;
import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort extends Sort {

	private boolean looping = false;
	
	private ArrayList<ArrayList<Integer>> sortedLists = new ArrayList<ArrayList<Integer>>();
	
	private ArrayList<Integer> a = null;
	private ArrayList<Integer> b = null;
	private ArrayList<Integer> mergedArray = null;

	public void step(ArrayList<Integer> array) {
		if(!looping) {
			beforeLoop(array);
			looping = true;
		}
		
		if(a.size() > 0 || b.size() > 0) {
			loop(array);
		} else {
			looping = false;
		}
		
		if(!looping) {
			afterLoop(array);
		}
	}
	
	private void beforeLoop(ArrayList<Integer> array) {
		if(sortedLists.size() == 0) {
			for(int x = 0; x < array.size(); x++) {
				sortedLists.add(new ArrayList<Integer>(Arrays.asList(array.get(x))));
			}
		}
		
		a = sortedLists.remove(0);
		b = sortedLists.remove(0);
			
		mergedArray = new ArrayList<Integer>();
		j = 0;
	}
	
	private void loop(ArrayList<Integer> array) {
		int lowest = Integer.MAX_VALUE;
		
		if(a.size() > 0) {
			lowest = a.get(0);
		}
		if(b.size() > 0) {
			if(b.get(0) < lowest) {
				lowest = b.get(0);
				b.remove(0);
			}
		}
		if(a.size() > 0 && lowest == a.get(0)) {
			a.remove(0);
		}
		
		mergedArray.add(lowest);
		j = lowest;
	}
	
	private void afterLoop(ArrayList<Integer> array) {
		sortedLists.add(mergedArray);
		
		array.clear();
		for(int x = 0; x < sortedLists.size(); x++) {
			array.addAll(sortedLists.get(x));
		}
	}
	
	public String toString() {
		return "MergeSort";
	}

	public void reset(ArrayList<Integer> array) {
		j = 0;
		looping = false;
		if(sortedLists != null) {
			sortedLists.clear();
		}
		a = null;
		b = null;
		mergedArray = null;
	}
}