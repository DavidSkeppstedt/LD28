package se.dixum.ld28.one.util;

import com.badlogic.gdx.utils.TimeUtils;

public class GameTimer {

	private boolean timerOn = false; 
	private long timeEnd;
	private long time; 
	private long timeLeft; 
	
	private int hours, minutes, seconds;
	
	public GameTimer(){
		
		
		if(!timerOn){
			time = 600*1000;
			timeEnd = TimeUtils.millis() + time;
			timerOn = true;
		}
	
	}
	public void checkTimer(){
		if(timerOn){
			timeLeft = (timeEnd - TimeUtils.millis())/1000;
			if(timeLeft < 0){
				timerOn = false;
			}
		}
		
	}
	public String getTimeLeft(){
		if(timerOn){
			long temp = timeLeft*144;
			hours = (int)temp/3600;
			minutes = (int)(temp%3600)/60;
			seconds = (int) (temp%60);
			
			return (hours+" "+minutes+" "+seconds);
		}else{
			return ("Stop");
		}
	}
}
