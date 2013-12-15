package se.dixum.ld28.one.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import se.dixum.ld28.one.screens.GameScreen;
import se.dixum.ld28.one.util.ScreenSettings;
import se.dixum.simple.entities.base.SimpleBaseEntity;
import se.dixum.simple.entities.base.SimpleEntity;
import se.dixum.simple.gfx.SimpleAnimated;

public class Mobster extends SimpleEntity implements SimpleBaseEntity{
	
	private SimpleAnimated sprite;
	private Animation right,left,up,down;
	private float speed = 5;
	private boolean firstWalk;
	
	private Player player;

	private Dialog dialog;
	
	public Mobster(Player player, Dialog dialog){
		this.player = player;
		this.dialog = dialog;
		init();
	}
	public Mobster(Player player){
		this.player = player;
		init();
	}
	@Override
	public void init() {
		sprite = new SimpleAnimated(new Texture(Gdx.files.internal("gfx/mobster/mobster.png")),
				32, 32, 0.24f);
		right = sprite.createAnimation(0, 3, 0);
		left = sprite.createAnimation(4, 6, 0);
		up = sprite.createAnimation(7, 9, 0);
		down = sprite.createAnimation(10, 12, 0);	
		
		if(ScreenSettings.level == 2){
			sprite.setPosition(new Vector2(320,515));
		}else{
			sprite.setPosition(new Vector2(-50,120));
		}
		sprite.setCurrentAnimation(right);
		sprite.setScale(new Vector2(2,2));
		
		firstWalk = false;
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		sprite.updateAnimation(delta);
		if(firstWalk){
			walkFirstMap();
		}
		
		if(player.getFreezPlayer()){
			firstWalk = true;
		}
		
		
		if(ScreenSettings.level == 2){
			
			if(100>Math.hypot(sprite.getX()-player.getBody().getPosition().x*32, sprite.getY()-player.getBody().getPosition().y*32)){
				System.out.println("jaaaaaaaaaaaaaaaaaaaa");
			}
			if(false){
				walkBack();
			}
		}
		
		sprite.setX(sprite.getX()+sprite.getVelX());
		sprite.setY(sprite.getY()+sprite.getVelY());
		
		if(sprite.getVelX() > 0){
			sprite.setCurrentAnimation(right);
		}else if(sprite.getVelX() < 0){
			sprite.setCurrentAnimation(left);
		}else if(sprite.getVelY() > 0){
			sprite.setCurrentAnimation(up);
		}else if(sprite.getVelY() < 0){
			sprite.setCurrentAnimation(down);
		}
		
		
	}
	private void walkFirstMap(){

		if(sprite.getY()>300&& sprite.getX()==10*32){
			sprite.setVelocity(0, 0);
			dialog.startDialog();
			
		}else if(sprite.getY() > 510){
			sprite.setVelocity(-1*speed,0);
		}else if(sprite.getY() >= 385&&sprite.getX() <= 400){
			sprite.setVelocity(0, speed);
		}else if(sprite.getY() >= 385){
			sprite.setVelocity(speed*-1, 0);
		}else if(sprite.getX() >= 1100){
			sprite.setVelocity(0, speed);
		}else{
			sprite.setVelocity(speed, 0);
		}
		
	}
	private void walkBack(){
		
		if(sprite.getY() > 510&&sprite.getX()<=385){
			sprite.setVelocity(speed,0);
		}else if(sprite.getY() >= 387&&sprite.getX()<=400){
			sprite.setVelocity(0, -1*speed);
		}else if(sprite.getY()>=380&&sprite.getX()<=1100){
			sprite.setVelocity(speed, 0);
		}else if(sprite.getX() >= 1100&&sprite.getY()>=135){
			sprite.setVelocity(0, -1*speed);
		}else if(sprite.getY()<= 160&&sprite.getX()>=-50){
			sprite.setVelocity(-1*speed, 0);
		}else{
			sprite.setVelocity(0,0);
		}
		
	}
	@Override
	public void draw(SpriteBatch batch) {
		sprite.drawAnimation(batch);

		
	}

}
