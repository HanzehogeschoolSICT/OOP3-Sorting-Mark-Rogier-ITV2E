package test;

import java.util.ArrayList;

import model.BubbleSort;
import model.InsertionSort;
import model.QuickSort;
import model.Sort;
import util.RandomArray;
import util.SortUtils;

public class Test {

	public Test(int size) {
		ArrayList<Integer> array = new RandomArray().fillArray(size).randomShuffle().getArray();
		Sort sort;
		
		sort = new BubbleSort();
		sort.reset(array);
		while(!SortUtils.isSorted(array)) {
			sort.step(array);
		}
		assert SortUtils.isSorted(array);
		
		array = new RandomArray(array).randomShuffle().getArray();
		
		sort = new InsertionSort();
		sort.reset(array);
		while(!SortUtils.isSorted(array)) {
			sort.step(array);
		}
		assert SortUtils.isSorted(array);
		
		array = new RandomArray(array).randomShuffle().getArray();
		
		sort = new QuickSort();
		sort.reset(array);
		while(!SortUtils.isSorted(array)) {
			sort.step(array);
		}
		assert SortUtils.isSorted(array);
		
		array = new RandomArray(array).randomShuffle().getArray();

		sort = new InsertionSort();
		sort.reset(array);
		while(!SortUtils.isSorted(array)) {
			sort.step(array);
		}
		assert SortUtils.isSorted(array);
	}
}
