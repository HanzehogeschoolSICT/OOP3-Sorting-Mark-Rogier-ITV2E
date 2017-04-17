package view;

import java.util.ArrayList;

import control.Main;
import control.StepThread;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.BubbleSort;
import model.InsertionSort;
import model.MergeSort;
import model.QuickSort;
import model.Sort;
import util.RandomArray;
import util.SortUtils;
import util.Tone;

public class GUIBuilder {

	private GUIBuilder guiHandler;
	private Tone tone;
	private StepThread stepThread;
	private Main main;

	private ArrayList<Integer> array;
	private XYChart.Series<String, Number> barChartValues;
	
	
	public GUIBuilder(Main main, Stage primaryStage, ArrayList<Integer> array) {
		guiHandler = this;
		tone = new Tone();
		stepThread = new StepThread(main);
		
		this.main = main;
		this.array = array;
		
		primaryStage.setTitle("Sorting fun!");

		//Close program on GUI close
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent event) {
		        System.exit(0);
		    }
		});
		
		//Add GUI items
		ArrayList<Node> guiItems = new ArrayList<Node>();
        guiItems.add(makeHistogram());
        guiItems.add(makeSortDropdown(new BubbleSort()));
        guiItems.addAll(makeButtons());
		
        VBox vBox = new VBox();
        vBox.getChildren().addAll(guiItems);
         
        StackPane root = new StackPane();
        root.getChildren().add(vBox);
        
        //Load window
        Scene scene = new Scene(root, 800, 450);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //Load histogram
        updateHistogram();
	}
	
	@SuppressWarnings("unchecked")
	private Node makeHistogram() {
		final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        final BarChart<String, Number> barChart = new BarChart<String, Number>(xAxis, yAxis);
        
        barChart.setCategoryGap(0);
        barChart.setBarGap(0);
        barChart.setAnimated(false);
        barChart.setLegendVisible(false);
        
        xAxis.setLabel("Index");       
        yAxis.setLabel("Value");
         
        barChartValues = new XYChart.Series<String, Number>();
        barChart.getData().addAll(barChartValues);
        
        return barChart;
	}
	
	private Node makeSortDropdown(Sort defaultSort) {
		ObservableList<Sort> options = 
    	    FXCollections.observableArrayList(
    	        new BubbleSort(),
    	        new InsertionSort(),
    	        new QuickSort(),
    	        new MergeSort()
    	    );
		
    	final ComboBox<Sort> comboBox = new ComboBox<Sort>(options);
    	main.setCurrentSorter(defaultSort);
    	comboBox.setValue(defaultSort);
    	
    	comboBox.setOnAction( 
    		new EventHandler<ActionEvent>() {
    			public void handle(ActionEvent e) {
    				main.setCurrentSorter((Sort) comboBox.getValue());
    				
    				main.getCurrentSorter().reset(array);
    				updateHistogram();
    			}
    		}
    	);
    	
    	return comboBox;
	}
	
	private ArrayList<Node> makeButtons() {
		ArrayList<Node> buttons = new ArrayList<Node>();
		
        buttons.add(makeButton("Randomise list", new Runnable() {
        	public void run() {
				array = new RandomArray(array).randomShuffle().getArray();
	        	
				main.getCurrentSorter().reset(array);
	            updateHistogram();
	            
	            System.out.println();
			}
		}));
        
        buttons.addAll(makeStepButtons());
		
        buttons.add(makeButton("Stop stepping", new Runnable() {
        	public void run() {
				stepThread.stopThread();
			}
		}));
		
		return buttons;
	}
	
	private Button makeButton(String name, Runnable response) {
		Button button = new Button();
		button.setText(name);
		button.addEventHandler(MouseEvent.MOUSE_CLICKED, 
		    new EventHandler<MouseEvent>() {
				public void handle(MouseEvent e) {
		           response.run();
		        }
			}
		);
		return button;
	}
	
	private ArrayList<Button> makeStepButtons() {
		ArrayList<Button> buttons = new ArrayList<Button>();
		
		buttons.add(makeStepButton());
        buttons.add(make10StepButton());
        buttons.add(make5msStepButton());
        buttons.add(make50msStepButton());
        
        return buttons;
	}
	
	private Button makeStepButton() {
		return makeButton("1 Step", new Runnable() {
			public void run() {
				main.getCurrentSorter().step(array);
	            updateHistogram();
			}
		});
	}
	
	private Button make10StepButton() {
		return makeButton("10 Steps", new Runnable() {
			public void run() {
				for(int i=0;i<10;i++) {
					main.getCurrentSorter().step(array);
	            }
	            updateHistogram();
			}
		});
	}
	
	private Button make5msStepButton() {
		return makeButton("Steps every 5ms", new Runnable() {
			public void run() {
				stepThread.startThread(guiHandler, array, 5);
			}
		});
	}
	
	private Button make50msStepButton() {
		return makeButton("Steps every 50ms", new Runnable() {
			public void run() {
				stepThread.startThread(guiHandler, array, 50);
			}
		});
	}
	
	public void updateHistogram() {
		boolean sorted = SortUtils.isSorted(array);
				
		for(int i=0; i < array.size(); i++) {
			try { 
				XYChart.Data<String, Number> entry;
				
				if(i > barChartValues.getData().size()) {
					entry = new XYChart.Data<String, Number>(i + "", array.get(i));
					barChartValues.getData().add(entry);
				} else {
					entry = barChartValues.getData().get(i);
					entry.setYValue(array.get(i));
				}
				
				if(sorted) {
					entry.getNode().setStyle("-fx-bar-fill: green;");
					
				} else if(i == main.getCurrentSorter().getPointer()) {
					entry.getNode().setStyle("-fx-bar-fill: blue;");
					
					tone.playSound(array.get(i), 5);
					
				} else {
					entry.getNode().setStyle("");
				}
				
			} catch (Exception e) {
				continue;
			}
	    }
	}
}
