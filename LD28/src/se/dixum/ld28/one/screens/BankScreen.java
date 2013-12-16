package se.dixum.ld28.one.screens;

import se.dixum.ld28.one.GameStarter;
import se.dixum.ld28.one.entities.Player;
import se.dixum.ld28.one.factories.PoliceFactory;
import se.dixum.ld28.one.map.Hud;
import se.dixum.ld28.one.util.ScreenSettings;
import se.dixum.simple.gfx.SimpleGL;
import se.dixum.simple.gfx.SimpleTileMap;
import se.dixum.simple.screen.base.SimpleScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BankScreen extends SimpleScreen {
	
	
	private SpriteBatch batch;
	private Player player;
	private SimpleTileMap map;
	private Hud hud;
	private PoliceFactory pf;
	private int needed_kills = 5;

	public BankScreen(Game game) {
		super(game);
		
		
	}

	@Override
	public void init() {
		ScreenSettings.level = 3;
		camera = new OrthographicCamera(1280, 768);
		camera.setToOrtho(false);
		batch = GameScreen.BATCH;
		hud = GameScreen.HUD;
		
		map = new SimpleTileMap("gfx/world/map/bank/bank.tmx", 1);
		
		GameScreen.reInit();
		player = GameScreen.PLAYER;
		player.setFreezPlayer(false);
		player.getBody().setTransform(38, 11, 0);
		SimpleTileMap.parseTileMap(map, "collision",GameScreen.WORLD, 1/32f);
		pf = new PoliceFactory(map);
	}

	@Override
	public void update(float delta) {
		player.update(delta);
		GameScreen.WORLD.step(delta, 6, 3);
		hud.update(delta);
		pf.update(delta);
		
		System.out.println(pf.removed_police);
		if (needed_kills == pf.removed_police) {
			getGame().setScreen(new BankWinScreen(getGame()));
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
		
			pf.draw(batch);
			GameStarter.GAME_TIMER.draw(batch);
			hud.draw(batch);
			
			
		batch.end();
		
	}

}
