package se.dixum.ld28.one.map;

import se.dixum.ld28.one.GameStarter;
import se.dixum.ld28.one.screens.GameScreen;
import se.dixum.ld28.one.screens.TownScreen;
import se.dixum.ld28.one.util.ScreenSettings;
import se.dixum.simple.entities.base.SimpleBaseEntity;
import se.dixum.simple.gfx.SimpleAnimated;
import se.dixum.simple.gfx.SimpleSprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Hud implements SimpleBaseEntity{

	private BitmapFont font;
	private Color color; 
	private String money; 
	private SimpleSprite hud;
	private Animation heart, brokenHeart, noHeart;
	private Array<SimpleAnimated> sprite;
	Game game;
	
	public Hud(Game game){
		this.game = game;
		init();
	}
	
	@Override
	public void init() {
		font = new BitmapFont();
		
		hud = new SimpleSprite(new TextureRegion(new Texture(Gdx.files.internal("gfx/world/hud.png"))),new Vector2(10, 710));
		sprite = new Array<SimpleAnimated>();
		for(int i = 0; i < 3;i++){
			sprite.insert(i, new SimpleAnimated(new Texture(Gdx.files.internal("gfx/player/life.png")),
					16, 16, 0));
		}
		
		
		
		
		heart  = sprite.get(0).createAnimation(0, 1, 0);
		brokenHeart = sprite.get(0).createAnimation(1, 2, 0);
		noHeart = sprite.get(0).createAnimation(2, 3, 0);
	
		
		sprite.get(0).setPosition(150, 725);
		sprite.get(1).setPosition(170, 725);
		sprite.get(2).setPosition(190, 725);
		
	}
	@Override
	public void update(float delta) {
		
		
		if (ScreenSettings.helth <=0) {
			game.setScreen(new TownScreen(game));
			ScreenSettings.helth = 100;
			GameStarter.GAME_TIMER.subtractTime(4*3600*1000);
		}
		
		
		if(ScreenSettings.moneyAccount < 0){
			color = Color.RED;
		}else{
			color = Color.BLACK;
		}
		money = ""+ScreenSettings.moneyAccount+" $";
			if(ScreenSettings.helth <= 0){
				sprite.get(0).setCurrentAnimation(noHeart);
				sprite.get(1).setCurrentAnimation(noHeart);
				sprite.get(2).setCurrentAnimation(noHeart);
			}else if(ScreenSettings.helth <= 100-500/6){
				sprite.get(0).setCurrentAnimation(brokenHeart);
				sprite.get(1).setCurrentAnimation(noHeart);
				sprite.get(2).setCurrentAnimation(noHeart);
			}else if(ScreenSettings.helth <= 100-400/6){
				sprite.get(0).setCurrentAnimation(heart);
				sprite.get(1).setCurrentAnimation(noHeart);
				sprite.get(2).setCurrentAnimation(noHeart);
			}else if(ScreenSettings.helth <= 100-300/6){
				sprite.get(0).setCurrentAnimation(heart);
				sprite.get(1).setCurrentAnimation(brokenHeart);
				sprite.get(2).setCurrentAnimation(noHeart);
			}else if(ScreenSettings.helth <= 100-200/6){
				sprite.get(0).setCurrentAnimation(heart);
				sprite.get(1).setCurrentAnimation(heart);
				sprite.get(2).setCurrentAnimation(noHeart);
			}else if(ScreenSettings.helth < 100){
				sprite.get(0).setCurrentAnimation(heart);
				sprite.get(1).setCurrentAnimation(heart);
				sprite.get(2).setCurrentAnimation(brokenHeart);
			}else if(ScreenSettings.helth == 100){
				sprite.get(0).setCurrentAnimation(heart);
				sprite.get(1).setCurrentAnimation(heart);
				sprite.get(2).setCurrentAnimation(heart);
			}
			
			
		for(SimpleAnimated s:sprite){
			s.updateAnimation(delta);
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		hud.drawSprite(batch);
		
		for(SimpleAnimated s:sprite){
			s.drawAnimation(batch);
		}
		
		font.setColor(color);
		font.setScale(2);
		font.draw(batch,money, 20, 750);
		
	}

}
