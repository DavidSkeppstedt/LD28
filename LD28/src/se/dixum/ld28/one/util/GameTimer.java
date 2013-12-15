package se.dixum.ld28.one.util;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.TimeUtils;

public class GameTimer {

	private boolean timerOn = false; 
	private long timeEnd;
	private long time; 
	private long timeLeft; 
	private long timeConstant;
	private int hours, minutes, seconds;
	private boolean pause;
	private BitmapFont font;
	
	/**
	 * 
	 * @param gameTime in seconds 
	 * @param realTime in seconds
	 */
	public GameTimer(long gameTime, long realTime){
		
		
		gameTime *= 1000;
		realTime *= 1000;
		
		timeConstant = gameTime/realTime;
	
		time = realTime;
		pause = false;
		
		font = new BitmapFont();
	}
	public void startTimer(){
		if(!timerOn){
			
			timeEnd = TimeUtils.millis() + time;
			timerOn = true;
		}
	}
	public void checkTimer(){
		if(timerOn&&!pause){
			timeLeft = (timeEnd - TimeUtils.millis());
			if(timeLeft < 0){
				timerOn = false;
			}
		}
		
	}
	/**
	 * @param in milliseconds
	 */
	public void subtractTime(long time){
		timeEnd -= time/timeConstant;
	}
	public void pause(){
		pause = true;
		
	}
	public void resume(){
		pause = false; 
		timeEnd = TimeUtils.millis()+timeLeft;
	}
	public void draw(SpriteBatch batch){
		if(timerOn){
			long temp = timeLeft*timeConstant;
			hours = (int)temp/3600/1000;
			minutes = (int)(temp/1000%3600)/60;
			seconds = (int) (temp/1000%60);
			font.draw(batch, "Time Left: Hours: "+hours+"   Minutes: "+minutes+"   Seconds: "+seconds,225,740);
		}
	}
}
