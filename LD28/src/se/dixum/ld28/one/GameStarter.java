package se.dixum.ld28.one;

import se.dixum.ld28.one.screens.GameOverScreeen;
import se.dixum.ld28.one.screens.GameScreen;
import se.dixum.ld28.one.util.GameTimer;
import se.dixum.ld28.one.util.ScreenSettings;

import com.badlogic.gdx.Game;

public class GameStarter extends Game {
	
	public static GameTimer GAME_TIMER;

	
	@Override
	public void create() {
		
		GAME_TIMER = new GameTimer(86400, 150);
		
		setScreen(new GameScreen(this));
	
	
	}
	@Override
	public void render() {
		super.render();
		GAME_TIMER.checkTimer();
	
		if (ScreenSettings.TIMER_START&&!GAME_TIMER.isTimerOn()) {
			setScreen(new GameOverScreeen(this));
		}
	}
	

	
}
