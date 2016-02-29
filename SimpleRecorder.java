package simplesound;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
/**
 * The Class SimpleRecorder.
 */
public class SimpleRecorder {
	
	/** The audio format for recording. */
	protected AudioFormat format;
	
	/** The info from the present working machine.*/
	protected DataLine.Info info;
	
	/** The target data line for the recording. */
	protected TargetDataLine targetLine;
	
	/** The duration for the recording. */
	protected int duration;
	
	/** The audio input stream for the recording. */
	protected AudioInputStream audioStream;
	
	/** The audio file name. */
	protected String audioFileName;
	
	/** The audio file format type. */
	protected AudioFileFormat.Type type;
	
	/** The audio file. */
	protected File audioFile;
	
	/** The rec thread.  */
	protected Thread recThread;
	
	/**
	 * Constructor
	 * 
	 * Instantiates a new simple recorder. Defaults are set to recording a wave file,
	 * named 'audio.wav' for 10 seconds.
	 */
	SimpleRecorder(){
		format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
		info =  new DataLine.Info(TargetDataLine.class, format);
		if(!AudioSystem.isLineSupported(info)){System.out.println("Recording not supported");}
		try {
			targetLine = (TargetDataLine)AudioSystem.getLine(info);
			targetLine.open();
		} 
		catch (LineUnavailableException e) {e.printStackTrace();}
		audioFileName = "audio.wav";
		type = AudioFileFormat.Type.WAVE;
		duration = 10;
	}
	
	/**
	 * Constructor
	 *
	 * Instantiates a new simple recorder. Specifying a duration for the recording. 
	 * The default name is still 'audio.wav' and it is still a wave file
	 *
	 * @param duration the duration
	 */
	SimpleRecorder(int duration){
		format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
		info =  new DataLine.Info(TargetDataLine.class, format);
		if(!AudioSystem.isLineSupported(info)){System.out.println("Recording not supported");}
		try {
			targetLine = (TargetDataLine)AudioSystem.getLine(info);
			targetLine.open();
		} 
		catch (LineUnavailableException e) {e.printStackTrace();}
		audioFileName = "audio.wav";
		type = AudioFileFormat.Type.WAVE;
		this.duration = duration;
	}
	
	/**
	 * Constructor
	 *
	 * Instantiates a new simple recorder. Setting the duration and file name. 
	 * Default format is wave.
	 *
	 * @param duration the duration
	 * @param filename the filename
	 */
	SimpleRecorder(int duration, String filename){
		format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 44100, 16, 2, 4, 44100, false);
		info =  new DataLine.Info(TargetDataLine.class, format);
		if(!AudioSystem.isLineSupported(info)){System.out.println("Recording not supported");}
		try {
			targetLine = (TargetDataLine)AudioSystem.getLine(info);
			targetLine.open();
		} 
		catch (LineUnavailableException e) {e.printStackTrace();}
		audioFileName = filename;
		type = AudioFileFormat.Type.WAVE;
		this.duration = duration;
	}
	
	/**
	 * Constructor
	 *
	 * Instantiates a new simple recorder. Where user specifies the format, type, duration
	 * and filename for the recording.
	 *
	 * @param duration the duration
	 * @param filename the filename
	 * @param format the format
	 * @param type the type
	 */
	SimpleRecorder(int duration, String filename, AudioFormat format, AudioFileFormat.Type type){
		this.format = format;
		info =  new DataLine.Info(TargetDataLine.class, format);
		if(!AudioSystem.isLineSupported(info)){System.out.println("Recording not supported");}
		try {
			targetLine = (TargetDataLine)AudioSystem.getLine(info);
			targetLine.open();
		} 
		catch (LineUnavailableException e) {e.printStackTrace();}
		audioFileName = filename;
		this.type = type;
		this.duration = duration;
	}
	
	/**
	 * Record.
	 */
	public void record(){
		RecordThread rt = new RecordThread(this);
		recThread = new Thread(rt);
		recThread.start();
		try {Thread.sleep(duration*1000);}
		catch (InterruptedException e) {e.printStackTrace();}
	}
	/**
	 * Record.
	 *
	 * @param duration: the duration of the recording
	 */
	public void record(int duration){
		this.duration = duration;
		RecordThread rt = new RecordThread(this);
		recThread = new Thread(rt);
		recThread.start();
		try {Thread.sleep(this.duration*1000);}
		catch (InterruptedException e) {e.printStackTrace();}
	}
	/**
	 * Record indefinitely. Halt with stopRecording() method
	 */
	public void recordIndefinitely(){
		RecordThread rt = new RecordThread(this);
		recThread = new Thread(rt);
		recThread.start();
	}
	/**
	 * Stop the recording.
	 */
	public void stopRecord(){
		try {audioStream.close();} 
		catch (IOException e) {e.printStackTrace();}	
	}
	/**
	 * Gets the format.
	 *
	 * @return the format
	 */
	public AudioFormat getFormat() {
		return format;
	}
	
	/**
	 * Sets the format.
	 *
	 * @param format the new format
	 */
	public void setFormat(AudioFormat format) {
		this.format = format;
	}
	
	/**
	 * Gets the duration.
	 *
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}
	
	/**
	 * Sets the duration.
	 *
	 * @param duration the new duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	/**
	 * Gets the audio file name.
	 *
	 * @return the audio file name
	 */
	public String getAudioFileName() {
		return audioFileName;
	}
	
	/**
	 * Sets the audio file name.
	 *
	 * @param audioFileName the new audio file name
	 */
	public void setAudioFileName(String audioFileName) {
		this.audioFileName = audioFileName;
	}
	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public AudioFileFormat.Type getType() {
		return type;
	}
	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(AudioFileFormat.Type type) {
		this.type = type;
	}
	/**
	 * Gets the audio file.
	 *
	 * @return the audio file
	 */
	public File getAudioFile() {
		return audioFile;
	}
	/**
	 * Gets the target line.
	 *
	 * @return the target line
	 */
	public TargetDataLine getTargetLine() {
		return targetLine;
	}	
	/**
	 * Gets the audio stream.
	 *
	 * @return the audio stream
	 */
	public AudioInputStream getAudioStream() {
		return audioStream;
	}
}
class RecordThread implements Runnable{
	SimpleRecorder rec;
	RecordThread(SimpleRecorder a){
		rec = a;
	}
	@Override
	public void run() {
		rec.targetLine.start();
		File file = new File(rec.getAudioFileName());
		rec.audioFile = file;
		AudioInputStream ais = new AudioInputStream(rec.getTargetLine());
		rec.audioStream = ais;
		long t = System.currentTimeMillis();
		long recordDuration = rec.getDuration() * 1000;
		long end = t+recordDuration;
		while(System.currentTimeMillis()<end){
			try {AudioSystem.write(rec.audioStream, rec.type, rec.audioFile);}
			catch (IOException e) { e.printStackTrace(); }	
		}	
		
	}
	
}
