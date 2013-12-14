package se.dixum.ld28.one.entities;

import se.dixum.ld28.one.screens.GameScreen;
import se.dixum.simple.entities.base.SimpleBaseEntity;
import se.dixum.simple.entities.base.SimpleEntity;
import se.dixum.simple.gfx.SimpleAnimated;
import se.dixum.simple.utils.SimpleInput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player extends SimpleEntity implements SimpleBaseEntity {

	SimpleAnimated sprite;
	Animation right,left,up,down;
	
	public Player() {
	
		init();
		
	}
	
	
	@Override
	public void init() {
		
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
		
		
		
		
		
	}
	
	private void changePosition(float delta) {
		sprite.setPosition(new Vector2(sprite.getPosition().x + sprite.getVelX(),
				sprite.getPosition().y+sprite.getVelY()));
		
	}


	private void movement() {
		
		
		if (checkCollision(SimpleInput.RIGHT) && !(SimpleInput.UP ||SimpleInput.DOWN)) {
			sprite.setVelX(3);
		}else {
			sprite.setVelX(0);
		}
		if (checkCollision(SimpleInput.LEFT) && !(SimpleInput.UP||SimpleInput.DOWN)) {
			sprite.setVelX(-3);
		}
		if (checkCollision(SimpleInput.UP) && !(SimpleInput.RIGHT||SimpleInput.LEFT)) {
			sprite.setVelY(3);
		}else {
			sprite.setVelY(0);
		}
		if (checkCollision(SimpleInput.DOWN)&& !(SimpleInput.RIGHT||SimpleInput.LEFT)) {
			sprite.setVelY(-3);
		}
		
		
		
		
	}


	private boolean checkCollision(boolean key) {
		if (key){
			
			// 2 == collision
			float  x= (sprite.getPosition().x + sprite.getFrameWidth()*2/2)/32;
			float  y = (sprite.getPosition().y + sprite.getFrameHeight()*2/2)/32;
			
			
			if (GameScreen.home.getTile(x, y) != 2) {
				return true;
			}else {
				return false;
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

	}

}
