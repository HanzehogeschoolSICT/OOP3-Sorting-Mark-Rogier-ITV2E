package util;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class Tone {

	private MidiChannel[] channels = null;
    
	private int channel = 0;
	private int volume = 30;
    
	private int low = 50;
	private int high = 100;
	private int range = high - low;
	
    public void playSound(int percentage, int length) {
    	if(channels == null) {
    		openChannel();
    	}
    	
    	int tone = (int) ((float) low + ((float) range * ((float) percentage / (float) 100)));
    	
    	channels[channel].allNotesOff();
    	
    	Thread t = new Thread(new Runnable() {
    		public void run() {
    			channels[channel].noteOn( tone, volume );
    	        try {
    				Thread.sleep( length );
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    	        
    	        channels[channel].allNotesOff();
    		}
    	});
    	t.start();
    }
    
    private void openChannel() {
    	try {
			Synthesizer synth = MidiSystem.getSynthesizer();
			synth.open();
			channels = synth.getChannels();
			
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
    }
}