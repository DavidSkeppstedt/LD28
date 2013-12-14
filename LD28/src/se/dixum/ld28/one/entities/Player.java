package se.dixum.ld28.one.entities;

import se.dixum.ld28.one.screens.GameScreen;
import se.dixum.simple.entities.base.SimpleBaseEntity;
import se.dixum.simple.entities.base.SimpleEntity;
import se.dixum.simple.gfx.SimpleAnimated;
import se.dixum.simple.utils.SimpleInput;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends SimpleEntity implements SimpleBaseEntity {

	SimpleAnimated sprite;
	Animation right,left,up,down;
	ShapeRenderer s;
	
	private Body body;
	private World world;
	public Player(World world) {
	
		this.world = world;
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
		sprite.setPosition(new Vector2(100,100));
		sprite.setCurrentAnimation(right);
		sprite.setScale(new Vector2(2,2));
	
		//sprite.setVelocity(new Vector2(0,0));
	
		
		body = GameScreen.BODYFACTORY.createBody(new Vector2(5,5), BodyType.DynamicBody).
				createFixture(GameScreen.BODYFACTORY.createPolyShape((30/32f)/2f, (62/32f)/2f),0.5f,.5f,0)
				.build(world);
		body.setFixedRotation(true);
		sprite.setPosition(body.getPosition());
		sprite.setOrigin(new  Vector2(32,32));
		
		System.out.println(body.getPosition());
		
	}

	@Override
	public void update(float delta) {
		changeAnimation(delta);
		movement();
	//	changePosition(delta);
	
		sprite.setPosition(body.getPosition());
		sprite.setRotation(body.getAngle()*MathUtils.radiansToDegrees);
		
		if (Gdx.input.isKeyPressed(Keys.R)) {
			sprite.setPosition(new Vector2(120,100));
		}
	
		
	}
	
	private void changePosition(float delta) {
		sprite.setPosition(new Vector2(sprite.getPosition().x + sprite.getVelX(),
				sprite.getPosition().y+sprite.getVelY()));
		
	}


	private void movement() {
		
		if (SimpleInput.UP) {
			body.setLinearVelocity(0, 3);
		}else {
			body.setLinearVelocity(0, 0);
		}
		if (SimpleInput.DOWN){
			body.setLinearVelocity(0, -3);
		}
		if (SimpleInput.RIGHT) {
			body.setLinearVelocity(3, 0);
		}
		if (SimpleInput.LEFT){
			body.setLinearVelocity(-3, 0);
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
		sprite.drawAnimation(batch,32);


	}

}
