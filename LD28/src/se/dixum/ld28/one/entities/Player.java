package se.dixum.ld28.one.entities;

import se.dixum.ld28.one.screens.GameScreen;
import se.dixum.simple.entities.base.SimpleBaseEntity;
import se.dixum.simple.entities.base.SimpleEntity;
import se.dixum.simple.gfx.SimpleAnimated;
import se.dixum.simple.utils.SimpleInput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Shape;

public class Player extends SimpleEntity implements SimpleBaseEntity {

	SimpleAnimated sprite;
	Animation right,left,up,down;
	ShapeRenderer s;
	public Player() {
	
		init();
		
	}
	
	
	@Override
	public void init() {
		s = new ShapeRenderer();
		sprite = new SimpleAnimated(new Texture(Gdx.files.internal("gfx/player/player.png")),
				32, 32, 0.24f);
		right = sprite.createAnimation(0, 3, 0);
		left = sprite.createAnimation(0, 3, 1);
		up = sprite.createAnimation(0, 3, 2);
		down = sprite.createAnimation(0, 3, 3);
		sprite.setPosition(new Vector2(320,100));
		sprite.setCurrentAnimation(right);
		sprite.setScale(new Vector2(2,2));
	
		sprite.setVelocity(new Vector2(0,0));
	
	}

	@Override
	public void update(float delta) {
		changeAnimation(delta);
		movement();
		changePosition(delta);
		
		if (Gdx.input.isKeyPressed(Keys.R)) {
			sprite.setPosition(new Vector2(320,100));
		}
	
		
	}
	
	private void changePosition(float delta) {
		sprite.setPosition(new Vector2(sprite.getPosition().x + sprite.getVelX(),
				sprite.getPosition().y+sprite.getVelY()));
		
	}


	private void movement() {
		
		
		if (checkCollision(SimpleInput.RIGHT,0) && !(SimpleInput.UP ||SimpleInput.DOWN)) {
			sprite.setVelX(3);
		}else {
			sprite.setVelX(0);
		}
		if (checkCollision(SimpleInput.LEFT,1) && !(SimpleInput.UP||SimpleInput.DOWN)) {
			sprite.setVelX(-3);
		}
		if (checkCollision(SimpleInput.UP,2) && !(SimpleInput.RIGHT||SimpleInput.LEFT)) {
			sprite.setVelY(3);
		}else {
			sprite.setVelY(0);
		}
		if (checkCollision(SimpleInput.DOWN,3)&& !(SimpleInput.RIGHT||SimpleInput.LEFT)) {
			sprite.setVelY(-3);
		}else if (!SimpleInput.UP){
			sprite.setVelY(0);
		}
		
		
		
		
	}


	private boolean checkCollision(boolean key,int i) {
		if (key){
			
			// 2 == collision
			float  x= (sprite.getPosition().x + 32)/32;
			float  y = (sprite.getPosition().y + 32)/32;
			
			switch(i) {
			case 0:
					
				if (GameScreen.home.getTile(x+1,y) != 2 && GameScreen.home.getTile(x+1,y-1) != 2) {
					return true;
				}else {
					return false;
				}
					
					
			case 1:
				if (GameScreen.home.getTile(x-1,y) != 2 && GameScreen.home.getTile(x-1,y-1) != 2) {
					return true;
				}else {
					return false;
				}
					
	
			case 2:
				if (GameScreen.home.getTile(x, y+1) !=2) {
					return true;
				}else {
					return false;
				}	
					
			case 3:
				if (GameScreen.home.getTile(x, y-1) !=2) {
					
					return true;
				}else {
					sprite.setPosition(sprite.getX(), sprite.getY()+4);
					return false;
				}	
					
					
			}
			
			
			
			
			
			
		}
		return false;
	}


	private void changeAnimation(float delta ) {
		sprite.updateAnimation(delta);
		
		if (SimpleInput.RIGHT && !(SimpleInput.LEFT || SimpleInput.UP || SimpleInput.DOWN)) {
			sprite.setCurrentAnimation(right);
		}
		if (SimpleInput.LEFT && !(SimpleInput.RIGHT || SimpleInput.UP || SimpleInput.DOWN)) {
			sprite.setCurrentAnimation(left);
		}
		if (SimpleInput.UP && !(SimpleInput.LEFT || SimpleInput.RIGHT || SimpleInput.DOWN)) {
			sprite.setCurrentAnimation(up);
		}
		if (SimpleInput.DOWN && !(SimpleInput.LEFT || SimpleInput.UP || SimpleInput.RIGHT)) {
			sprite.setCurrentAnimation(down);
		}
		
	}
	
	
	

	@Override
	public void draw(SpriteBatch batch) {
		sprite.drawAnimation(batch);
		batch.end();
		
		s.begin(ShapeType.Line);
			s.rect(sprite.getX(), sprite.getY(), 64, 64);
			/*s.circle(sprite.getX()+32, sprite.getY()+32, 2);
			
			s.circle(sprite.getX()+64, sprite.getY()+32, 2);
			s.circle(sprite.getX(), sprite.getY()+32, 2);
			s.circle(sprite.getX()+64, sprite.getY(), 2);
			s.circle(sprite.getX(), sprite.getY(), 2);
			
			
			/*s.circle(sprite.getX(), sprite.getY(), 2);
			/*s.circle(sprite.getX()+64, sprite.getY()+64, 2);
			
			
			
			/*s.circle(sprite.getX(), sprite.getY()+64, 2);
			s.circle(sprite.getX()+64, sprite.getY(), 2);*/
		s.end();
		
		batch.begin();

	}

}
