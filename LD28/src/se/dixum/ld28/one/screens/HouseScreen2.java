package se.dixum.ld28.one.screens;

import se.dixum.ld28.one.GameStarter;
import se.dixum.ld28.one.entities.Mobster;
import se.dixum.ld28.one.entities.Player;
import se.dixum.ld28.one.map.Hud;
import se.dixum.ld28.one.util.GameTimer;
import se.dixum.ld28.one.util.ScreenSettings;
import se.dixum.simple.gfx.SimpleGL;
import se.dixum.simple.gfx.SimpleTileMap;
import se.dixum.simple.screen.base.SimpleScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class HouseScreen2 extends SimpleScreen{

	private SpriteBatch batch;;
	private SimpleTileMap map;
	private Mobster mobster;
	private Player player;
	private GameTimer gameTimer; 
	private Hud hud;
	private Rectangle door;
	private boolean  shouldRobBank = false;
	
	
	public HouseScreen2(Game game) {
		super(game);
		
	}
	public void init() {
		ScreenSettings.level = 2;
		
		batch = GameScreen.BATCH;
		camera = new OrthographicCamera(1280, 768);
		camera.setToOrtho(false);
		map = new SimpleTileMap("gfx/world/map/home/home2.tmx",1);
		GameScreen.reInit();
		player = GameScreen.PLAYER;
		SimpleTileMap.parseTileMap(map, "collision", GameScreen.WORLD, 1/32f);
		mobster = new Mobster(player);
		hud = GameScreen.HUD;
		
		player.getBody().setTransform(5, 5, 0);
		door = new Rectangle(0, 96, 64, 64);
	}
	@Override
	public void update(float delta) {
		player.update(delta);
		GameScreen.WORLD.step(delta, 6, 3);
		mobster.update(delta);
		hud.update(delta);

		if (shouldRobBank) {
			if (player.getRect().overlaps(door)){
				getGame().setScreen(new BankScreen(getGame()));
				
			}
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
			GameStarter.GAME_TIMER.draw(batch);
			hud.draw(batch);
			
		batch.end();
	}


}
