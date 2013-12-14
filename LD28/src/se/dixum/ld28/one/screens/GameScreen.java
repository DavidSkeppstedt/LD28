package se.dixum.ld28.one.screens;


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
	private Conversation conversation; 
	private Array<Array<String>> conversations; 
	private String currentSpeecher, currentDialog;
	private int speekIndex = 0;
	private	SimpleSprite dialogRectangle; 
	private boolean talk;  
	
	private Timer keyTimer; 
	
	
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
	conversation = new Conversation("test.txt");
		
	conversations = conversation.getConversationArray(); 
	talk = true;
	currentDialog = "";
	currentSpeecher = "";
		
	dialogRectangle = new SimpleSprite(new TextureRegion(new Texture(Gdx.files.internal("gfx/dialogRectangle.png"))),new Vector2(0, 0));
		
	keyTimer = new Timer();
		
		

	}

	@Override
	public void update(float delta) {
	
		player.update(delta);
		world.step(delta, 6, 3);

		
		gameTimer.checkTimer();

		keyTimer.testTimer();
		
		if(talk&&Gdx.input.isKeyPressed(Keys.ENTER)&&!keyTimer.getStatus()){
			
			keyTimer.start(1000);
			
			speekIndex++;
			if(speekIndex >= conversations.get(1).size){
				talk = false; 
				speekIndex = 0;

	
			}else{
				currentDialog = conversations.get(1).get(speekIndex);
				currentSpeecher = conversations.get(0).get(speekIndex);
			}
			
		}
		if(talk&&speekIndex==0){
			currentDialog = conversations.get(1).get(speekIndex);
			currentSpeecher = conversations.get(0).get(speekIndex);
		}
			  
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

	
			font.draw(batch, gameTimer.getTimeLeft(),200, SimpleSettings.GHEIGHT-100);
			if(talk){
				dialogRectangle.drawSprite(batch);
				font.draw(batch, currentSpeecher, 30, 70);
				font.draw(batch, currentDialog, 60, 40);
			}
			

		batch.end();
		
		physRenderer.render(world, physCamera.combined);
		
		
	}

}
