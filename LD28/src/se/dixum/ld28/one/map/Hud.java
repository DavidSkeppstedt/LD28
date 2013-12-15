package se.dixum.ld28.one.map;

import java.util.Vector;

import javax.xml.bind.ParseConversionEvent;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import se.dixum.ld28.one.util.ScreenSettings;
import se.dixum.simple.entities.base.SimpleBaseEntity;
import se.dixum.simple.gfx.SimpleSprite;
import sun.org.mozilla.javascript.internal.ast.DoLoop;

public class Hud implements SimpleBaseEntity{

	private BitmapFont font;
	private Color color; 
	private String money; 
	private SimpleSprite hud;
	
	public Hud(){
		init();
	}
	
	@Override
	public void init() {
		font = new BitmapFont();

		
		hud = new SimpleSprite(new TextureRegion(new Texture(Gdx.files.internal("gfx/world/hud.png"))),new Vector2(10, 710));
	}

	@Override
	public void update(float delta) {
		if(ScreenSettings.moneyAccount < 0){
			color = Color.RED;
		}else{
			color = Color.BLACK;
		}
		money = ""+ScreenSettings.moneyAccount+" $";
		
	}

	@Override
	public void draw(SpriteBatch batch) {
		hud.drawSprite(batch);
		
		font.setColor(color);
		font.setScale(2);
		font.draw(batch,money, 20, 750);
		
	}

}
