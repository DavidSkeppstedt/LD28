package se.dixum.ld28.one.entities;

import se.dixum.ld28.one.screens.GameScreen;
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
import com.badlogic.gdx.physics.box2d.World;

public class Granny extends SimpleEntity implements SimpleBaseEntity {
	

	
	private SimpleAnimated sprite;
	private Animation left,right,up,down
	,stand_l,stand_r,stand_u,stand_d;
	private World world;
	
	private float timer = 2;
	private float count = 0;
	private boolean afraid = false;
	private boolean shouldDisable = false;
	public Granny(World world) {
		this.world  = world;
		
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
		
		sprite.setCurrentAnimation(right);
		
		sprite.setPosition(new Vector2(320,320));
		sprite.setVelX(1.5f);

	}

	@Override
	public void update(float delta) {
		sprite.updateAnimation(delta);
		sprite.setPosition(sprite.getX() + sprite.getVelX(),sprite.getY()+sprite.getVelY());
		move();
		changeAnim();
		
	}
	private void dropMoney() {
		//Fun code here!
	}
	
	private void runAway() {
		System.out.println("ARGH!");
		count = -100;
		shouldDisable = true;
		//Play some  sound here maybe?
		float n = MathUtils.random(0,100);
		if (n > 0&&n > 25) {
			//First quadrant
			//++
			
			sprite.setVelocity(4, 4);
			
			
			
		}
		if (n >25 && n > 50) {
			//Sec quadrant
			//-+
			sprite.setVelocity(-4, 4);
		}
		
		if (n >50 && n>75) {
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
		
		
		
		
		
		if (Math.hypot(Math.abs(GameScreen.player.getSprite().getX()*32 - sprite.getX()),
				Math.abs(GameScreen.player.getSprite().getY()*32 - sprite.getY())) < 150) {
			if (SimpleInput.ACTION) {
				SimpleInput.ACTION = false;
				//DROP MONEY!
				//RUN AWAY!
				dropMoney();
				runAway();
			}
			
		}
		
		
		
		
		if (count > timer) {
			
			
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

}
