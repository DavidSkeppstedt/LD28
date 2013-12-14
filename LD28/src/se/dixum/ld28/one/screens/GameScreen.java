package se.dixum.ld28.one.screens;

import se.dixum.ld28.one.entities.Player;
import se.dixum.ld28.one.map.World;
import se.dixum.simple.gfx.SimpleGL;
import se.dixum.simple.screen.base.SimpleScreen;
import se.dixum.simple.utils.SimpleInput;
import se.dixum.simple.utils.SimpleSettings;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
public class GameScreen extends SimpleScreen {

	
	private SpriteBatch batch;
	private Player player;
	public static World home;
	public GameScreen(Game game) {
		super(game);

	}

	@Override
	public void init() {
		
	camera = new OrthographicCamera(SimpleSettings.GWIDTH,SimpleSettings.GHEIGHT);
	camera.setToOrtho(false);
	Gdx.input.setInputProcessor(new SimpleInput());
	batch = new SpriteBatch();
	player = new Player();
	
	home = new World("gfx/world/map/home.tmx");
	
	
	
		
	}

	@Override
	public void update(float delta) {
	
		player.update(delta);
		
		
	
	}

	@Override
	public void draw() {
		SimpleGL.OpenGLClear(1,1,1,1);
		camera.update();
		
		home.draw(camera);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			//Render stuff
			player.draw(batch);
		batch.end();
		
		
		
		
	}

}
