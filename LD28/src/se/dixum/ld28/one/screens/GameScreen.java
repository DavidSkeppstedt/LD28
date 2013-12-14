package se.dixum.ld28.one.screens;
import se.dixum.ld28.one.entities.Player;
import se.dixum.ld28.one.factories.MoneyFactory;
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
import com.badlogic.gdx.physics.box2d.World;


public class GameScreen extends SimpleScreen {
	
	public static SimpleBodyFactory BODYFACTORY;
	public static MoneyFactory MONEYFACTORY;
	public static World WORLD;
	public static Player PLAYER;
	public static SpriteBatch BATCH;
	public static BitmapFont FONT;
	public static GameTimer GAME_TIMER;
	
	private SimpleSprite logo;
	
	//private OrthographicCamera physCamera;
	//private Box2DDebugRenderer physRenderer;



	
	public GameScreen(Game game) {
		super(game);

	}

	@Override
	public void init() {
		
		
		camera = new OrthographicCamera(SimpleSettings.GWIDTH,SimpleSettings.GHEIGHT);
		camera.setToOrtho(false);
		Gdx.input.setInputProcessor(new SimpleInput());
		BATCH = new SpriteBatch();
		FONT = new BitmapFont();
		BODYFACTORY = new SimpleBodyFactory();
		WORLD = new World(new Vector2(0,0),true);
		GAME_TIMER = new GameTimer(86400, 600);
		
		PLAYER = new Player(WORLD);
		MONEYFACTORY = new MoneyFactory();
		
		logo = new SimpleSprite(new TextureRegion(new Texture(Gdx.files.internal("gfx/title/title.png"))), new Vector2(320,320));
		

	}

	@Override
	public void update(float delta) {
		//Nothing to do here


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
