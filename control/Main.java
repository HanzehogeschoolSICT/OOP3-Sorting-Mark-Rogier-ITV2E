package control;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import model.Sort;
import util.RandomArray;
import view.GUIBuilder;

public class Main extends Application {
	
	private ArrayList<Integer> array;
	private Sort currentSorter;
		
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//Fill array
		array = new RandomArray().fillArray(100).randomShuffle().getArray();
		
		new GUIBuilder(this, primaryStage, array);
    }
	
	public Sort getCurrentSorter() {
		return currentSorter;
	}
	
	public void setCurrentSorter(Sort sorter) {
		this.currentSorter = sorter;
	}
}
