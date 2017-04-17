package control;

import java.util.ArrayList;

import javafx.application.Platform;
import util.SortUtils;
import view.GUIBuilder;

public class StepThread {

	private Thread autoStepThread;
	private Main main;
	
	public StepThread(Main main) {
		this.main = main;
	}
	
	@SuppressWarnings("deprecation")
	public void stopThread() {
		if(autoStepThread != null) {
			autoStepThread.stop();
			autoStepThread = null;
		}
	}
	
	public void startThread(GUIBuilder gui, ArrayList<Integer> array, int stepDelay) {
		stopThread();
		
		autoStepThread = new Thread(new Runnable() {
			public void run() {
				while(!SortUtils.isSorted(array)) {
					main.getCurrentSorter().step(array);
					
					updateHistogram(gui);
					
					try {
						Thread.sleep(stepDelay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		autoStepThread.start();
	}
	
	private void updateHistogram(GUIBuilder gui) {
		Platform.runLater(new Runnable() {
    	    @Override
    	    public void run() {
    	    	gui.updateHistogram();
    	    }
		});
	}
}
