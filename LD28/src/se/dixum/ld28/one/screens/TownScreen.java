package se.dixum.ld28.one.screens;

import se.dixum.ld28.one.GameStarter;
import se.dixum.ld28.one.entities.Player;
import se.dixum.ld28.one.entities.Police;
import se.dixum.ld28.one.factories.GrannyFactory;
import se.dixum.ld28.one.map.Hud;
import se.dixum.ld28.one.util.ScreenSettings;
import se.dixum.simple.gfx.SimpleGL;
import se.dixum.simple.gfx.SimpleTileMap;
import se.dixum.simple.screen.base.SimpleScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class TownScreen extends SimpleScreen {
	
	
	private SpriteBatch batch;
	private Player player;
	private SimpleTileMap map;
	private GrannyFactory gf;
	private Hud hud;
	private Police police;
	public TownScreen(Game game) {
		super(game);
		
		
	}

	@Override
	public void init() {
		ScreenSettings.level = 1;
		camera = new OrthographicCamera(1280, 768);
		camera.setToOrtho(false);
		batch = GameScreen.BATCH;
		hud = GameScreen.HUD;
		
		map = new SimpleTileMap("gfx/world/map/town/tiletown.tmx", 1);
		
		GameScreen.reInit();
		player = GameScreen.PLAYER;
		player.setFreezPlayer(false);
		player.getBody().setTransform(2, 11, 0);
		SimpleTileMap.parseTileMap(map, "collision",GameScreen.WORLD, 1/30f);
		gf = new GrannyFactory();
		police = new Police(new Vector2(364,320),map);
		
		
	}

	@Override
	public void update(float delta) {
		player.update(delta);
		GameScreen.WORLD.step(delta, 6, 3);
		gf.update(delta);
		GameScreen.MONEYFACTORY.update(delta);
		hud.update(delta);
		
		if (gf.scaredGrannies >=3) {
			police.setTaunted();
		}
		
		if (ScreenSettings.moneyAccount >= 0) {
			getGame().setScreen(new HouseScreen2(getGame()));
		}
		

		

		police.update(delta);
	}

	@Override
	public void draw() {
		SimpleGL.OpenGLClear(0,0,0,1);
		camera.update();
		map.draw(camera);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			player.draw(batch);
			gf.draw(batch);
			GameScreen.MONEYFACTORY.draw(batch);
			GameStarter.GAME_TIMER.draw(batch);
			hud.draw(batch);
			police.draw(batch);
			
		batch.end();
		
	}

}
