package se.dixum.ld28.one.screens;
import se.dixum.ld28.one.entities.Player;
import se.dixum.ld28.one.factories.MoneyFactory;
import se.dixum.ld28.one.map.Hud;
import se.dixum.ld28.one.util.GameTimer;
import se.dixum.simple.gfx.SimpleGL;
import se.dixum.simple.gfx.SimpleSprite;
import se.dixum.simple.physics.SimpleBodyFactory;
import se.dixum.simple.screen.base.SimpleScreen;
import se.dixum.simple.utils.SimpleInput;
import se.dixum.simple.utils.SimpleSettings;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;


public class GameScreen extends SimpleScreen {
	
	public static SimpleBodyFactory BODYFACTORY;
	public static MoneyFactory MONEYFACTORY;
	public static World WORLD;
	public static Player PLAYER;
	public static SpriteBatch BATCH;
	public static BitmapFont FONT;
	public static Hud HUD; 
	
	
	private SimpleSprite logo;
	
	//private OrthographicCamera physCamera;
	//private Box2DDebugRenderer physRenderer;



	
	public GameScreen(Game game) {
		super(game);

	}

	@Override
	public void init() {
		
		
		camera = new OrthographicCamera(1280,768);
		camera.setToOrtho(false);
		Gdx.input.setInputProcessor(new SimpleInput());
		BATCH = new SpriteBatch();
		FONT = new BitmapFont();
		BODYFACTORY = new SimpleBodyFactory();
		WORLD = new World(new Vector2(0,0),true);
		HUD = new Hud();
		
		PLAYER = new Player(WORLD);
		MONEYFACTORY = new MoneyFactory();
		
		
		logo = new SimpleSprite(new TextureRegion(new Texture(Gdx.files.internal("gfx/title/title.png"))), new Vector2(320,320));
		

	}
	public static void reInit() {
		WORLD = new World(new Vector2(0,0),true);
		PLAYER = new Player(WORLD);
	}

	@Override
	public void update(float delta) {
		//Nothing to do here
		if (SimpleInput.ACTION) {
			getGame().setScreen(new HouseScreen(getGame()));
		}
		
		
	}

	@Override
	public void draw() {
		SimpleGL.OpenGLClear(0,0,0,1);
		camera.update();
		BATCH.setProjectionMatrix(camera.combined);
		BATCH.begin();
			//Render stuff
			//Maybe a menu here or something
			logo.drawSprite(BATCH);
			
		
		BATCH.end();
		
		
		
	}


}
