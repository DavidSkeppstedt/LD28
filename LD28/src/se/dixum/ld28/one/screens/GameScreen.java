package se.dixum.ld28.one.screens;

import se.dixum.ld28.one.util.Conversation;
import se.dixum.ld28.one.util.GameTimer;
import se.dixum.ld28.one.util.Timer;
import se.dixum.simple.gfx.SimpleGL;
import se.dixum.simple.gfx.SimpleSprite;
import se.dixum.simple.screen.base.SimpleScreen;
import se.dixum.simple.utils.SimpleSettings;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GameScreen extends SimpleScreen {

	
	private SpriteBatch batch;
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
		batch = new SpriteBatch();
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
		SimpleGL.OpenGLClear(0,0,0,0);
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			//Render stuff
		
		
		
		
		font.draw(batch, gameTimer.getTimeLeft(),200, SimpleSettings.GHEIGHT-100);
		if(talk){
			dialogRectangle.drawSprite(batch);
			font.draw(batch, currentSpeecher, 30, 70);
			font.draw(batch, currentDialog, 60, 40);
		}
		
		batch.end();
		
		
		
		
	}

}
