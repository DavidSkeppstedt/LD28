package se.dixum.ld28.one.screens;

import se.dixum.ld28.one.entities.Player;
import se.dixum.ld28.one.map.WorldMap;
import se.dixum.simple.gfx.SimpleGL;
import se.dixum.simple.gfx.SimpleTileMap;
import se.dixum.simple.physics.SimpleBodyFactory;
import se.dixum.simple.screen.base.SimpleScreen;
import se.dixum.simple.utils.SimpleInput;
import se.dixum.simple.utils.SimpleSettings;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
public class GameScreen extends SimpleScreen {

	
	private SpriteBatch batch;
	private Player player;
	public static WorldMap home;
	private OrthographicCamera physCamera;
	public static SimpleBodyFactory BODYFACTORY;
	private World world;
	private Box2DDebugRenderer physRenderer;
	
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
	}

	@Override
	public void update(float delta) {
	
		player.update(delta);
		
		world.step(delta, 6, 3);
	
	}

	@Override
	public void draw() {
		SimpleGL.OpenGLClear(1,1,1,1);
		camera.update();
		physCamera.update();
		home.draw(camera);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			//Render stuff
			player.draw(batch);
		batch.end();
		
		physRenderer.render(world, physCamera.combined);
		
		
	}

}
