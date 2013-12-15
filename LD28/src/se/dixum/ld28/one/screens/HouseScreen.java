package se.dixum.ld28.one.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import se.dixum.ld28.one.GameStarter;
import se.dixum.ld28.one.entities.Dialog;
import se.dixum.ld28.one.entities.Mobster;
import se.dixum.ld28.one.entities.Player;
import se.dixum.ld28.one.map.Hud;
import se.dixum.ld28.one.util.ScreenSettings;
import se.dixum.ld28.one.util.GameTimer;
import se.dixum.simple.gfx.SimpleGL;
import se.dixum.simple.gfx.SimpleTileMap;
import se.dixum.simple.screen.base.SimpleScreen;

public class HouseScreen extends SimpleScreen {
	
	private SpriteBatch batch;;
	private SimpleTileMap map;
	private Mobster mobster;
	private Player player;
	private Dialog dialog;
	private GameTimer gameTimer; 
	private Hud hud;
	private Rectangle door;
	
	
	
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
		dialog = new Dialog("dialogs/dialogBeginning.txt");
		mobster = new Mobster(player, dialog);
		hud = GameScreen.HUD;
		
		door = new Rectangle(0, 96, 64, 64);
		
		
	}

	@Override
	public void update(float delta) {
		player.update(delta);
		GameScreen.WORLD.step(delta, 6, 3);
		mobster.update(delta);
		dialog.update(delta);
		hud.update(delta);
		
		//For dev only
		if (Gdx.input.isKeyPressed(Keys.NUM_0)) {
			getGame().setScreen(new TownScreen(getGame()));
		}
		
		
		if (player.getRect().overlaps(door)) {
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
			GameStarter.GAME_TIMER.draw(batch);
			hud.draw(batch);
		batch.end();
		
		
	}

}
