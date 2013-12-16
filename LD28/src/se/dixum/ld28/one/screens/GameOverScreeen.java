package se.dixum.ld28.one.screens;

import se.dixum.simple.gfx.SimpleGL;
import se.dixum.simple.screen.base.SimpleScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverScreeen extends SimpleScreen {
	
	private SpriteBatch batch;
	private BitmapFont font;
	
	public GameOverScreeen(Game game) {
		super(game);
		init();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		batch = GameScreen.BATCH;
		camera = new OrthographicCamera(1280,768);
		camera.setToOrtho(false);
		font = new BitmapFont(Gdx.files.internal("font/gameover.fnt"));
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void draw() {
		SimpleGL.OpenGLClear(0,0,0,1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			//Render stuff
			//Maybe a menu here or something
			font.draw(batch, "GAME OVER", 1280/2 -100, 768/2);
			
		
		batch.end();

	}

}
