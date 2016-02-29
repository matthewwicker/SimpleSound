package simplesound;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import javax.media.*;
import java.net.*;
import java.io.*;
import java.util.*;

/**
 * The Class SimplePlayback.
 */
public class SimplePlayback {
	
	/** The path to file. */
	protected String pathToFile;
	
	/** The file to play. */
	protected File fileToPlay;
	
	/** The wav clip. */
	protected Clip wavClip;
	
	/** The mp3 player. */
	protected Player mp3Player;
	
	/** The player thread. */
	protected Thread playerThread;
	
	/** The player thread mp3. */
	protected Thread playerThreadMp3;
	
	/**
	 * Instantiates a new simple playback. 
	 * Sets pathToFile to null. In order to play back a file
	 * the user needs to specify a path to a file
	 */
	SimplePlayback(){
		pathToFile = null;
	}
	
	/**
	 * Instantiates a new simple playback. 
	 * Sets pathToFile. If it is a valid path, you can play back
	 * the file. 
	 *
	 * @param path the path to the file
	 */
	SimplePlayback(String path){
		pathToFile = path;
	}
	
	/**
	 * Play a '.wav' formatted file.
	 */
	public void playWav(){
		try {
			fileToPlay = new File(pathToFile);
			wavClip = AudioSystem.getClip();
			wavClip.open(AudioSystem.getAudioInputStream(fileToPlay));
			wavClip.start();
			Thread.sleep(wavClip.getMicrosecondLength()/1000);
		} 
		catch (LineUnavailableException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();} 
		catch (UnsupportedAudioFileException e) {e.printStackTrace();} 
		catch (InterruptedException e) {e.printStackTrace();}
		
	}
	
	/**
	 * Play a '.wav' formatted file. With a different path than the
	 * currently set path. If you use the default SimplePlayback()
	 * then you can use this to at once set the pathToFile and play. 
	 *
	 * @param pathToFile the path to file
	 */
	public void playWav(String pathToFile){
		this.fileToPlay = new File(pathToFile);
		PlayerThread pt = new PlayerThread(this);
		playerThread = new Thread(pt);
		playerThread.start();
		
	}
	
	/**
	 * Pause '.wav' formatted play back.
	 */
	public void pauseWav(){
		wavClip.stop();
	}
	
	/**
	 * Resume '.wav' formatted play back.
	 */
	public void resumeWav(){
		wavClip.start();
	}
	
	/**
	 * Play '.mp3' formatted file.
	 */
	public void playMp3(){
		PlayerThreadMp3 pt3 = new PlayerThreadMp3(this);
		playerThreadMp3 = new Thread(pt3);
		playerThreadMp3.start();
	}
	
	/**
	 * Play a '.mp3' formatted file. With a different path than the
	 * currently set path. If you use the default SimplePlayback()
	 * then you can use this to at once set the pathToFile and play.
	 * @param pathToFile the path to file
	 */
	public void playMp3(String pathToFile){
		this.pathToFile = pathToFile;
		PlayerThreadMp3 pt3 = new PlayerThreadMp3(this);
		playerThreadMp3 = new Thread(pt3);
		playerThreadMp3.start();
	}
	
	/**
	 * Gets the file that you are playing.
	 *
	 * @return the file that you are playing
	 */
	public File getFileToPlay() {
		return fileToPlay;
	}
	
	/**
	 * Stop mp3 playback.
	 */
	public void stopMp3(){
		mp3Player.close();
		playerThreadMp3.stop();
	}
	
	/**
	 * Stop wav playback.
	 */
	public void stopWav(){
		wavClip.stop();
		playerThread.stop();
	}
	
	/**
	 * Play. A method that takes the pathToFile and assesses its format 
	 * (if it ends in .wav or .mp3) then plays that file. 
	 */
	public void play(){
		fileToPlay = new File(pathToFile);
		if(fileToPlay!=null){
			if(fileToPlay.getAbsolutePath().substring(fileToPlay.getAbsolutePath().length() - 4) == ".mp3"){
					this.playMp3();
			}
			else if(fileToPlay.getAbsolutePath().substring(fileToPlay.getAbsolutePath().length() - 3) == ".wav"){
				System.out.println("Playing file");
				this.playWav();
			}
		}
		else{
			System.err.println("Error: Filename can not be null");
		}
	}
	
	/**
	 * Play. A method that takes the pathToFile and assesses its format 
	 * (if it ends in .wav or .mp3) then plays that file. Takes in
	 * the path to the desired content.
	 *
	 * @param pathToFile the path to file
	 */
	public void play(String pathToFile){
		this.pathToFile = pathToFile;
		this.play();
		
	}
}
class PlayerThread implements Runnable{
	SimplePlayback play;
	PlayerThread(SimplePlayback a){
		play = a;
	}
	@Override
	public void run() {
		try {
			play.wavClip = AudioSystem.getClip();
			play.wavClip.open(AudioSystem.getAudioInputStream(play.fileToPlay));
			play.wavClip.start();
			Thread.sleep(play.wavClip.getMicrosecondLength()/1000);
		} 
		catch (LineUnavailableException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();} 
		catch (UnsupportedAudioFileException e) {e.printStackTrace();} 
		catch (InterruptedException e) {e.printStackTrace();}
		
	}
	
}
class PlayerThreadMp3 implements Runnable{
	SimplePlayback play;
	PlayerThreadMp3(SimplePlayback a){
		play = a;
	}
	@Override
	public void run() {
		try {
			File file = new File(play.pathToFile);
			FileInputStream fis = new FileInputStream(file);
			play.mp3Player = new Player(fis);
			play.mp3Player.play();
		} 
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (JavaLayerException e) {e.printStackTrace();}
		
	}
	
}