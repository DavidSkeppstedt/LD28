package se.dixum.ld28.one.screens;


import se.dixum.ld28.one.entities.Dialog;
import se.dixum.ld28.one.entities.Mobster;
import se.dixum.ld28.one.entities.Player;
import se.dixum.ld28.one.map.WorldMap;
import se.dixum.simple.gfx.SimpleGL;
import se.dixum.simple.gfx.SimpleTileMap;
import se.dixum.simple.physics.SimpleBodyFactory;
import se.dixum.ld28.one.util.Conversation;
import se.dixum.ld28.one.util.GameTimer;
import se.dixum.ld28.one.util.Timer;
import se.dixum.simple.gfx.SimpleSprite;
import se.dixum.simple.screen.base.SimpleScreen;
import se.dixum.simple.utils.SimpleInput;
import se.dixum.simple.utils.SimpleSettings;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;


public class GameScreen extends SimpleScreen {

	
	private SpriteBatch batch;
	private Player player;
	public static WorldMap home;
	private OrthographicCamera physCamera;
	public static SimpleBodyFactory BODYFACTORY;
	private World world;
	private Box2DDebugRenderer physRenderer;
	private GameTimer gameTimer;
	private BitmapFont font;
	private Mobster mobster;
	
	private Dialog dialogBeginning;
	
	public GameScreen(Game game) {
		super(game);

	}

	@Override
	public void init() {
		

		camera = new OrthographicCamera(SimpleSettings.GWIDTH,SimpleSettings.GHEIGHT);
		camera.setToOrtho(false);
		Gdx.input.setInputProcessor(new SimpleInput());
		batch = new SpriteBatch();		
		home = new WorldMap("gfx/world/map/home2.tmx");
		physCamera = new OrthographicCamera(40,24);
		physCamera.position.set(20,12,0);
		BODYFACTORY = new SimpleBodyFactory();
		
		world = new World(new Vector2(0,0),true);
		
		SimpleTileMap.parseTileMap(home.getMap(), "collision", world, 1/32f);
		player = new Player(world);
		physRenderer = new Box2DDebugRenderer();
	
		font = new BitmapFont();
		gameTimer = new GameTimer(86400,600);
	
		
		dialogBeginning = new Dialog("gfx/world/dialogBeginning.txt");
		
		mobster = new Mobster(player,dialogBeginning);
	}

	@Override
	public void update(float delta) {
		
		
		player.update(delta);
		world.step(delta, 6, 3);
		mobster.update(delta);
		
		gameTimer.checkTimer();
		
		dialogBeginning.update(delta);
	}

	@Override
	public void draw() {
		SimpleGL.OpenGLClear(0,0,0,1);
		camera.update();

		physCamera.update();
		home.draw(camera);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			//Render stuff
			player.draw(batch);
			mobster.draw(batch);
			
			dialogBeginning.draw(batch);
	
			font.draw(batch, gameTimer.getTimeLeft(),200, SimpleSettings.GHEIGHT-100);
			
		
		batch.end();
		
		//physRenderer.render(world, physCamera.combined);
		
		
	}

}
