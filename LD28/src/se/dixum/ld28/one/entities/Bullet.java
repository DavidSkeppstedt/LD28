package se.dixum.ld28.one.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntIntMap;

import se.dixum.simple.entities.base.SimpleBaseEntity;
import se.dixum.simple.gfx.SimpleAnimated;

public class Bullet implements SimpleBaseEntity{

	private Animation bulletUp, bulletDown, bulletLeft, bulletRight;
	private SimpleAnimated sprite;
	private Player player;
	private float speed; 
	
	/**
	 * 
	 * @param player
	 * @param dir direction 1 = up 2 = left 3 = down 4 = right
	 */
	public Bullet(Player player, int dir){
		sprite = new SimpleAnimated(new Texture(Gdx.files.internal("gfx/player/bullet.png")), 16, 16, 0);
		
		speed = 10;
		
		
		switch (dir) {
		case 1:
			sprite.setVelY(speed);
			sprite.setVelX(0);
			break;
		case 2:
			sprite.setVelY(0);
			sprite.setVelX(speed *-1);
			break;
		case 3:
			sprite.setVelY(speed *-1);
			sprite.setVelX(0);
			break;
		case 4:
			sprite.setVelY(0);
			sprite.setVelX(speed);
			break;
		default:
			sprite.setVelY(speed);
			sprite.setVelX(0);
			break;
		}
		
		
		this.player = player;
		
		init();
	}
	
	@Override
	public void init() {
		
		
		bulletRight = sprite.createAnimation(0, 1, 0);
		bulletUp = sprite.createAnimation(1, 2, 0);
		bulletLeft = sprite.createAnimation(2, 3, 0);
		bulletDown = sprite.createAnimation(3, 4, 0);
		
	
		
		sprite.setPosition(player.getBody().getPosition().x*32,player.getBody().getPosition().y*32);
		
		
		if(sprite.getVelX() == 0&&sprite.getVelY() != 0){
			if(sprite.getVelY()<0){
				sprite.setCurrentAnimation(bulletDown);
			}else{
				sprite.setCurrentAnimation(bulletUp);
			}
		}else if(sprite.getVelY() == 0&&sprite.getVelX() != 0){
			if(sprite.getVelX()<0){
				sprite.setCurrentAnimation(bulletLeft);
			}else{
				sprite.setCurrentAnimation(bulletRight);
			}
		}else {
			sprite.setCurrentAnimation(bulletRight);
		}
	}

	@Override
	public void update(float delta){
		sprite.updateAnimation(delta);
		sprite.setPosition(sprite.getPosition().x+sprite.getVelX(), sprite.getPosition().y+sprite.getVelY());
	}

	@Override
	public void draw(SpriteBatch batch) {
		sprite.drawAnimation(batch);
		
	}
	public boolean isOutOfMap(){
		if(sprite.getPosition().x > 1300||sprite.getPosition().x < -10){
			return true;
		}else if(sprite.getPosition().y > 780||sprite.getPosition().y < -10){
			return true;
		}else{
			return false;
		}
	}

}
