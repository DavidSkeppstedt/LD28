package se.dixum.ld28.one.screens;

import se.dixum.ld28.one.util.Conversation;
import se.dixum.ld28.one.util.GameTimer;
import se.dixum.simple.gfx.SimpleGL;
import se.dixum.simple.screen.base.SimpleScreen;
import se.dixum.simple.utils.SimpleSettings;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;

public class GameScreen extends SimpleScreen {

	
	private SpriteBatch batch;
	private GameTimer gameTimer;
	
	private BitmapFont font;
	
	private Conversation conversation; 
	
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
		
		
		System.out.print(conversation.printConversation());
		System.out.println("--------------------");
		System.out.print(conversation.printConversation(0));
		System.out.println("--------------------");
		Array<Array<String>> arr = conversation.getConversationArray();

		for(int i = 0;i < arr.get(0).size;i++) {
			System.out.print(
					arr.get(0).get(i)+":\n\t"+
					arr.get(1).get(i)+"\n"
					);
		}
	}

	@Override
	public void update(float delta) {
	
		gameTimer.checkTimer();

		
		
	
	}

	@Override
	public void draw() {
		SimpleGL.OpenGLClear(0,0,0,0);
		camera.update();
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
			//Render stuff
		
		font.draw(batch, gameTimer.getTimeLeft(), 100, 100);
		
		batch.end();
		
		
		
		
	}

}
