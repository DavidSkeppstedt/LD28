package se.dixum.ld28.one;

import se.dixum.ld28.one.screens.GameScreen;
import se.dixum.ld28.one.util.GameTimer;

import com.badlogic.gdx.Game;

public class GameStarter extends Game {
	
	public static GameTimer GAME_TIMER;

	
	@Override
	public void create() {
		
		GAME_TIMER = new GameTimer(86400, 600);
		
		setScreen(new GameScreen(this));
	
	
	}
	@Override
	public void render() {
		super.render();
		GAME_TIMER.checkTimer();
	}
	

	
}
