package se.dixum.ld28.one.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import se.dixum.ld28.one.util.Conversation;
import se.dixum.ld28.one.util.Timer;
import se.dixum.simple.entities.base.SimpleBaseEntity;
import se.dixum.simple.gfx.SimpleSprite;

public class Dialog implements SimpleBaseEntity{

	private Conversation conversation; 
	private Array<Array<String>> conversations; 
	private String currentSpeecher, currentDialog;
	private int speekIndex = 0;
	private	SimpleSprite dialogRectangle; 
	private boolean talk;  
	
	private Timer keyTimer; 
	
	private BitmapFont font;
	
	public Dialog(String path){
		conversation = new Conversation(path);
		init();
	}
	@Override
	public void init() {
		
		
		conversations = conversation.getConversationArray(); 
		talk = true;
		currentDialog = "";
		currentSpeecher = "";
			
		dialogRectangle = new SimpleSprite(new TextureRegion(new Texture(Gdx.files.internal("gfx/dialogRectangle.png"))),new Vector2(0, 0));
			
		keyTimer = new Timer();
		
		font = new BitmapFont();
		
	}

	@Override
	public void update(float delta) {
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
	public void draw(SpriteBatch batch) {
		if(talk){
			dialogRectangle.drawSprite(batch);
			font.draw(batch, currentSpeecher, 30, 70);
			font.draw(batch, currentDialog, 60, 40);
		}
	}

}
