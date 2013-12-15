package se.dixum.ld28.one.entities;

import se.dixum.ld28.one.screens.GameScreen;
import se.dixum.simple.audio.SimpleSound;
import se.dixum.simple.entities.base.SimpleBaseEntity;
import se.dixum.simple.entities.base.SimpleEntity;
import se.dixum.simple.gfx.SimpleAnimated;
import se.dixum.simple.utils.SimpleInput;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;


public class Granny extends SimpleEntity implements SimpleBaseEntity {
	

	
	private SimpleAnimated sprite;
	private Animation left,right,up,down
	,stand_l,stand_r,stand_u,stand_d;
	private Vector2 position;
	
	private float timer = 2,timer2 = 0.6f,time3 = 1.5f;
	private float count = 0,count2 = 0,count3 = 0;
	
	private boolean afraid = false;
	private boolean shouldDisable = false;
	private SimpleSound scream,boo;
	private boolean drop = false;
	public Granny(Vector2 position) {
		this.position = position;
		
		init();
		
	}
	
	
	@Override
	public void init() {
		sprite = new SimpleAnimated(
				new Texture(Gdx.files.internal("gfx/granny/tant.png")), 
				32, 32, 0.25f);
		sprite.setScale(new Vector2(2,2));
		right = sprite.createAnimation(0, 3, 0);
		left = sprite.createAnimation(3, 6, 0);
		up = sprite.createAnimation(6, 9, 0);
		down = sprite.createAnimation(9, 12, 0);
		sprite.setPosition(position);
		sprite.setCurrentAnimation(right);
		
		//sprite.setPosition(new Vector2(320,320));
		sprite.setVelX(1.5f);
		
		scream = new SimpleSound(Gdx.audio.newSound(Gdx.files.internal("sound/granny/granny.ogg")));
		scream.setVolume(0.3f);
		boo = new SimpleSound(Gdx.audio.newSound(Gdx.files.internal("sound/granny/boo.ogg")));
		
	}

	@Override
	public void update(float delta) {
		sprite.updateAnimation(delta);
		sprite.setPosition(sprite.getX() + sprite.getVelX(),sprite.getY()+sprite.getVelY());
		
		if (Math.hypot(Math.abs(GameScreen.PLAYER.getSprite().getX()*32 - sprite.getX()),
				Math.abs(GameScreen.PLAYER.getSprite().getY()*32 - sprite.getY())) < 150) {
			if (SimpleInput.ACTION) {
				SimpleInput.ACTION = false;
				drop = true;
				boo.play();
				runAway();
			}
			
		}
		
		if (shouldDisable) {
			
			if (count3 > time3) {
				setAfraid(true);
				count3 = 0;
			}else {
				count3 +=Gdx.graphics.getDeltaTime();
			}
			
			
			
		}
		
		
		
		move();
		changeAnim();
		dropMoney();
		
	}
	private void dropMoney() {
		//Fun code here!
		if (drop) {
				
				if (count2 > timer2) {
					
					GameScreen.MONEYFACTORY.addMoney(new Money(new Vector2(sprite.getX(),sprite.getY())));
					count2 = 0;
					drop=false;
					
				}else {
					count2 +=Gdx.graphics.getDeltaTime();
				}
				//
			
		
		
		}
	
	}
	
	private void runAway() {
		
		scream.play();
		
		count = -100;
		shouldDisable = true;
		
		//Play some  sound here maybe?
		
		float n = MathUtils.random(0,100);
		System.out.println(n);
		if (n < 25) {
			//First quadrant
			//++
			
			sprite.setVelocity(4, 4);

		}
		if (n >25 && n < 50) {
			//Sec quadrant
			//-+
			sprite.setVelocity(-4, 4);
		}
		
		if (n >50 && n<75) {
			//third quadrant
			//--
			sprite.setVelocity(-4, -4);
		}

		if (n >75 && n<100) {
			//fourt 
			//+-
			sprite.setVelocity(4, -4);
		}
		
		
	}
	
	private void move() {
		
		if (sprite.getX() >1280-64) {
			count = 0;
			sprite.setVelX(-3);
		}
		
		if (sprite.getX() < 0) {
			count = 0;
			sprite.setVelX(3);
			
		}
		
		
		
		
		
	
		
		
		
		
		if (count > timer && !shouldDisable) {
			
			
			int r = MathUtils.random(0,100);
			if (r > 50) {
				sprite.setVelX(1.5f);
			}else {
				sprite.setVelX(-1.5f);
			}
			
			r = -1;
			count = 0;
		}else {
			count += Gdx.graphics.getDeltaTime();
		}
		
	}
	
	
	public void changeAnim() {
		
		if (sprite.getVelX() > 0) {
			sprite.setCurrentAnimation(right);
		}else {
			sprite.setCurrentAnimation(left);
		}
		
		
		
	}
	
	
	
	
	
	

	@Override
	public void draw(SpriteBatch batch) {
		 
		sprite.drawAnimation(batch);

	}


	public boolean isAfraid() {
		return afraid;
	}


	public void setAfraid(boolean afraid) {
		this.afraid = afraid;
	}

}
