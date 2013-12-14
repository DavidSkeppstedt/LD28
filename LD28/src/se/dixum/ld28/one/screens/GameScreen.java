package se.dixum.ld28.one.screens;

import se.dixum.simple.gfx.SimpleGL;
import se.dixum.simple.screen.base.SimpleScreen;
import se.dixum.simple.utils.SimpleSettings;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class GameScreen extends SimpleScreen {

	
	private SpriteBatch batch;
	
	
	public GameScreen(Game game) {
		super(game);

	}

	@Override
	public void init() {
		
	camera = new OrthographicCamera(SimpleSettings.GWIDTH,SimpleSettings.GHEIGHT);
	camera.setToOrtho(false);
	batch = new SpriteBatch();
	
		
	}

	@Override
	public void update(float delta) {
	
		
		
		
	
	}

	@Override
	public void draw() {
		SimpleGL.OpenGLClear(0,0,0,0);
		camera.update();
		
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			//Render stuff
		batch.end();
		
		
		
		
	}

}
