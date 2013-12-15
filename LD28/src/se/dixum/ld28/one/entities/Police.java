package se.dixum.ld28.one.entities;

import se.dixum.ld28.one.screens.GameScreen;
import se.dixum.simple.entities.base.SimpleBaseEntity;
import se.dixum.simple.gfx.SimpleAnimated;
import se.dixum.simple.gfx.SimpleTileMap;
import se.dixum.simple.utils.SimpleInput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Police implements SimpleBaseEntity{
	
	private Vector2 pos;
	private SimpleAnimated sprite;
	private Animation left,right,up,down;
	private State state;
	private int speed_pat = 3;
	private int speed_tau = 5;
	
	
	private float counter = 0;
	private float timer = 1f;
	private SimpleTileMap map;
	
	
	private boolean xFound = false;
	private boolean yFound = false;

	
	public Police(Vector2 pos,SimpleTileMap map) {
		this.pos = pos;
		this.map = map;
		init();
	}
	
	@Override
	public void init() {
		sprite = new SimpleAnimated(
				new Texture(Gdx.files.internal("gfx/police/police.png")), 32, 32, 0.25f);
		sprite.setPosition(pos);
		sprite.setScale(new Vector2(2,2));
		right = sprite.createAnimation(0, 3, 0);
		left = sprite.createAnimation(3, 6, 0);
		up = sprite.createAnimation(6, 9, 0);
		down = sprite.createAnimation(9, 12, 0);
		sprite.setCurrentAnimation(right);
		state = State.PATROLING;
		
		sprite.setVelY(speed_pat);
		
		
	}

	@Override
	public void update(float delta) {
		sprite.updateAnimation(delta);
		checkBehavioure();
		updateAnimation();
		
	}
	
	private void checkBehavioure() {
		switch(state){
		case PATROLING:
			patroloing();
			break;
		case TAUNTED:
			taunted();
			break;
		}
		
		
		
		if (getDisToPlayer() < 500) {
			if (Gdx.input.isKeyPressed(Keys.ALT_LEFT)){
				state = State.TAUNTED;
			}
		}
		
		
		
		
		
		
	}
	
	private float getDisToPlayer()  {
		float x,y,xx,yy,dx,dy,d;
		x = GameScreen.PLAYER.getBody().getPosition().x *32;
		y = GameScreen.PLAYER.getBody().getPosition().y *32;
		
		xx = sprite.getX();
		yy = sprite.getY();
		
		dx = Math.abs(x-xx);
		dy = Math.abs(yy-y);
		
		
		d = (float) Math.hypot(dx, dy);
		
		
		return d;
		
	}
	
	
	
	
	private void patroloing(){
		
		sprite.setPosition(sprite.getX()+sprite.getVelX(),sprite.getY()+sprite.getVelY());
		
	
		if (sprite.getX() >1280-64) {
			counter = 0;
			sprite.setVelX(-speed_pat);
			
		}
		
		if (sprite.getX() < 0) {
			counter = 0;
			sprite.setVelX(speed_pat);
			
		}
		
		if (sprite.getY() > 768-64) {
			counter = 0;
			sprite.setVelY(-speed_pat);
		}
		
		if (sprite.getY() < 64) {
			counter = 0;
			sprite.setVelY(speed_pat);
		}
		
		
		
		
		if (counter > timer) {
			
			int r= MathUtils.random(0, 10);
			
			
			if (r > 5) {
				 
				if (Math.abs(sprite.getVelY()) > 0) {
					sprite.setVelX(speed_pat);
					sprite.setVelY(0);
				}else if (Math.abs(sprite.getVelX()) > 0){
					sprite.setVelX(speed_pat*-1);
					
				}
				
				
				
				
			}else {
				
				
				if (Math.abs(sprite.getVelX()) > 0) {
					sprite.setVelY(speed_pat);
					sprite.setVelX(0);
				}else if (Math.abs(sprite.getVelY()) > 0){
					sprite.setVelY(speed_pat*-1);
					
				}
				
				
				
			}
			
			
		
			
			
			counter = 0;
		
		
		}else {
			counter +=Gdx.graphics.getDeltaTime();
		}
		
		
		
		
		if (map.getTileID(sprite.getPosition().x/32, sprite.getPosition().y/32, 0) == 1) {
			
			if (Math.abs(sprite.getVelX()) > 0 ) {
				sprite.setVelX(sprite.getVelX()*-1);
			}
			if (Math.abs(sprite.getVelY()) > 0 ) {
				sprite.setVelY(sprite.getVelY()*-1);
			}
			
			
			
			
			
		}
		
		
	}
	
	private void taunted() {
		sprite.setPosition(sprite.getX()+sprite.getVelX(),sprite.getY()+sprite.getVelY());
		
		//Find player
		float px = GameScreen.PLAYER.getBody().getPosition().x *32;
		float py = GameScreen.PLAYER.getBody().getPosition().y *32;
		float dif = 16;
		float dify = 16;
		
		if (sprite.getX() > (px-dif) && sprite.getX() <(px+dif) ) {
			xFound = true;
			sprite.setVelX(0);
		}else {
			xFound = false;
		}
		
		if (sprite.getY() > (py-dify) && sprite.getY() < (py+dify)) {
			yFound = true;
			sprite.setVelY(0);
		}else {
			yFound = false;
		}
		
		
		
		
		
		
		System.out.println(xFound);
		// Left
		if (px < sprite.getX()) {
			if (!xFound){
			sprite.setVelX(-speed_tau);
			sprite.setVelY(0);
			}
		}
		
		if (px > sprite.getX()) {
			if (!xFound) {
				sprite.setVelX(speed_tau);
				sprite.setVelY(0);
			}
		}
		
		
		
		if (py > sprite.getY()) {
			
			if (!yFound && xFound) {
				sprite.setVelY(speed_tau);
				sprite.setVelX(0);
			}
		}
		
		if (py < sprite.getY()) {
			
			if (!yFound && xFound) {
				sprite.setVelY(-speed_tau);
				sprite.setVelX(0);
			}
			
			
		}
		
		
		
		
		
		//Check where player is
		//left
			//can move left?
			//can move up down?
			//move right
		//right
		
		//down
		
		//up
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	private void updateAnimation() {

		if (sprite.getVelX() > 0) {
			sprite.setCurrentAnimation(right);
		}else {
			sprite.setCurrentAnimation(left);
		}
		
		if (sprite.getVelY() >0) {
			sprite.setCurrentAnimation(up);
		}else if (sprite.getVelY() < 0){
			sprite.setCurrentAnimation(down);
		}
		
		
	
	
	}
	
	
	

	@Override
	public void draw(SpriteBatch batch) {
		sprite.drawAnimation(batch);
		
	}

}
