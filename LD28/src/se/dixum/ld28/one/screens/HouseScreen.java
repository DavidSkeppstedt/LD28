package se.dixum.ld28.one.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import se.dixum.ld28.one.entities.Dialog;
import se.dixum.ld28.one.entities.Mobster;
import se.dixum.ld28.one.entities.Player;
import se.dixum.ld28.one.util.ScreenSettings;
import se.dixum.simple.gfx.SimpleGL;
import se.dixum.simple.gfx.SimpleTileMap;
import se.dixum.simple.screen.base.SimpleScreen;
import se.dixum.simple.utils.SimpleInput;

public class HouseScreen extends SimpleScreen {
	
	private SpriteBatch batch;
	private BitmapFont font;
	private SimpleTileMap map;
	private Mobster mobster;
	private Player player;
	private Dialog dialog;
	public HouseScreen(Game game) {
		super(game);
		
	}

	@Override
	public void init() {
		ScreenSettings.level = 0;
		player = GameScreen.PLAYER;
		batch = GameScreen.BATCH;
		camera = new OrthographicCamera(1280, 768);
		camera.setToOrtho(false);
		map = new SimpleTileMap("gfx/world/map/home/home2.tmx",1);
		SimpleTileMap.parseTileMap(map, "collision", GameScreen.WORLD, 1/32f);
		dialog = new Dialog("gfx/world/dialogBeginning.txt", null);
		mobster = new Mobster(player, dialog);
	}

	@Override
	public void update(float delta) {
		player.update(delta);
		GameScreen.WORLD.step(delta, 6, 3);
		mobster.update(delta);
		dialog.update(delta);
		
		if (Gdx.input.isKeyPressed(Keys.NUM_0)) {
			getGame().setScreen(new TownScreen(getGame()));
		}
	}

	@Override
	public void draw() {
		
		SimpleGL.OpenGLClear(0,0,0,1);
		camera.update();
		map.draw(camera);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			player.draw(batch);
			mobster.draw(batch);
			dialog.draw(batch);
			
		batch.end();
		
		
	}

}
