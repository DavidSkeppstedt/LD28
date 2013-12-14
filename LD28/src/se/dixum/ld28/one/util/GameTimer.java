package se.dixum.ld28.one.util;

import com.badlogic.gdx.utils.TimeUtils;

public class GameTimer {

	private boolean timerOn = false; 
	private long timeEnd;
	private long time; 
	private long timeLeft; 
	private long timeConstant;
	
	private int hours, minutes, seconds;
	
	/**
	 * 
	 * @param gameTime in seconds 
	 * @param realTime in seconds
	 */
	public GameTimer(long gameTime, long realTime){
		gameTime *= 1000;
		realTime *= 1000;
		
		if(!timerOn){
			time = realTime;
			timeEnd = TimeUtils.millis() + time;
			timerOn = true;
		}
	
	}
	public void checkTimer(){
		if(timerOn){
			timeLeft = (timeEnd - TimeUtils.millis());
			if(timeLeft < 0){
				timerOn = false;
			}
		}
		
	}
	public String getTimeLeft(){
		if(timerOn){
			long temp = timeLeft*timeConstant;
			hours = (int)temp/3600/1000;
			minutes = (int)(temp/1000%3600)/60;
			seconds = (int) (temp/1000%60);
			
			return ("Hours: "+hours+"   Minutes: "+minutes+"   Seconds: "+seconds);
		}else{
			return ("Stop");
		}
	}
}
