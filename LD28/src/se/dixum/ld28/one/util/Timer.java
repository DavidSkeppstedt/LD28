package se.dixum.ld28.one.util;

import com.badlogic.gdx.utils.TimeUtils;

public class Timer {
	
	private long stopTime; 
	private boolean on =false;
	
	/**
	 * 
	 * @param time milliseconds
	 */
	public Timer(){
		
	}
	public boolean testTimer(){
		if(stopTime <= TimeUtils.millis()){
			on = false; 
		}
		return on;
	}
	public void start(int time){
		
		stopTime = TimeUtils.millis()+time;
		on = true;
	}
	public boolean getStatus(){
		return on;
	}
}
