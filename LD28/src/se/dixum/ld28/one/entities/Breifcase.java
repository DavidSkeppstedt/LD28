package se.dixum.ld28.one.entities;

import se.dixum.simple.entities.base.SimpleBaseEntity;
import se.dixum.simple.gfx.SimpleSprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Breifcase implements SimpleBaseEntity {

	
	private SimpleSprite sprite;
	private Rectangle rect;
	
	
	
	
	public Breifcase() {
		// TODO Auto-generated constructor stub
		init();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
		sprite = new SimpleSprite(new TextureRegion(
				new Texture(Gdx.files.internal("gfx/suitcase.png"))
					
				
				
				), new Vector2(300,300));
		
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(SpriteBatch batch) {
		// TODO Auto-generated method stub
		sprite.drawSprite(batch);
	}
	

}
