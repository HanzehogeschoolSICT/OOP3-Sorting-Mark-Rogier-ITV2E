package util;

import java.util.ArrayList;

public class SortUtils {

	public static boolean isSorted(ArrayList<Integer> array) {
		for(int x = 1; x < array.size(); x++) {
			int lowValue = array.get(x-1);
			int highValue = array.get(x);
			
			if(lowValue > highValue) {
				return false;
			}
		}
		
		return true;
	}
}
