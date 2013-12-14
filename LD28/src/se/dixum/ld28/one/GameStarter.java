package se.dixum.ld28.one;

import se.dixum.ld28.one.screens.GameScreen;

import com.badlogic.gdx.Game;

public class GameStarter extends Game {
	
	
	@Override
	public void create() {
	
		setScreen(new GameScreen(this));
	
	
	}
	

	
}
