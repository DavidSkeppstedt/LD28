package se.dixum.ld28.one.entities;

import se.dixum.ld28.one.screens.GameScreen;
import se.dixum.ld28.one.util.ScreenSettings;
import se.dixum.simple.entities.base.Angle;
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
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends SimpleEntity implements SimpleBaseEntity {

	private SimpleAnimated sprite;
	private Animation right,left,up,down,stand_r,stand_l,stand_u,stand_d;
	private float speed = 6;
	
	
	private Body body;
	private World world;
	private Rectangle pRect;

	
	private boolean metMobbster;
	private boolean freezPlayer; 
	
	public Player(World world) {
	
		this.world = world;
		init();
		
	}

	
	public SimpleAnimated getSprite() {
		return sprite;
	}
	
	public Rectangle getRect() {
		pRect.set(body.getPosition().x*32-32, body.getPosition().y*32-32, 64, 64);
		
		return pRect;
		
		
	}
	

	public Body getBody() {
		return body;
	}
	
	@Override
	public void init() {
		pRect = new Rectangle();
		metMobbster = false; 
		freezPlayer = false;
		if (ScreenSettings.level != 0) {
			metMobbster = true;
		}
		System.out.println(ScreenSettings.level);
		sprite = new SimpleAnimated(new Texture(Gdx.files.internal("gfx/player/player.png")),
				32, 32, 0.24f);
		right = sprite.createAnimation(0, 3, 0);
		left = sprite.createAnimation(0, 3, 1);
		up = sprite.createAnimation(0, 3, 2);
		down = sprite.createAnimation(0, 3, 3);
		stand_r = sprite.createAnimation(0, 1, 0);
		stand_l = sprite.createAnimation(2, 3, 1);
		stand_u = sprite.createAnimation(0, 1, 2);
		stand_d= sprite.createAnimation(0, 1, 3);
	
		sprite.setPosition(new Vector2(100,100));
		sprite.setCurrentAnimation(right);
		sprite.setScale(new Vector2(2,2));
		
	
		body = GameScreen.BODYFACTORY.createBody(new Vector2(5,15), BodyType.DynamicBody).
				createFixture(GameScreen.BODYFACTORY.createPolyShape((30/32f)/2f, (58/32f)/2f),0.5f,.5f,0)
				.build(world);
		body.setFixedRotation(true);
		sprite.setPosition(body.getPosition());
		sprite.setOrigin(new  Vector2(32,32));
		
		
		 
	}

	@Override
	public void update(float delta) {
		changeAnimation(delta);
	
		movement();
		if(freezPlayer){
			body.setLinearVelocity(0, 0);
			
		}
		sprite.setPosition(body.getPosition());
		sprite.setRotation(body.getAngle()*MathUtils.radiansToDegrees);
		
		if (Gdx.input.isKeyPressed(Keys.R)) {
			sprite.setPosition(new Vector2(120,100));
		}
		if(sprite.getPosition().x>=10&&!metMobbster){
			freezPlayer = true;
			metMobbster = true;
			

		}
		
	}
	
	private void movement() {
		
		if (SimpleInput.UP) {
			body.setLinearVelocity(0, speed);
		}else {
			body.setLinearVelocity(0, 0);
			
		
			
			
		}
		if (SimpleInput.DOWN){
			body.setLinearVelocity(0, -speed);
		}
		if (SimpleInput.RIGHT) {
			body.setLinearVelocity(speed, 0);
		}
		if (SimpleInput.LEFT){
			body.setLinearVelocity(-speed, 0);
		}
		
		
		
		
	}

	private void changeAnimation(float delta ) {
		sprite.updateAnimation(delta);
		
		if (SimpleInput.RIGHT && !(SimpleInput.LEFT || SimpleInput.UP || SimpleInput.DOWN)) {
			sprite.setCurrentAnimation(right);
			setAngle(Angle.RIGHT);
		}
		if (SimpleInput.LEFT && !(SimpleInput.RIGHT || SimpleInput.UP || SimpleInput.DOWN)) {
			sprite.setCurrentAnimation(left);
			setAngle(Angle.LEFT);
		}
		if (SimpleInput.UP && !(SimpleInput.LEFT || SimpleInput.RIGHT || SimpleInput.DOWN)) {
			sprite.setCurrentAnimation(up);
			setAngle(Angle.UP);
		}
		if (SimpleInput.DOWN && !(SimpleInput.LEFT || SimpleInput.UP || SimpleInput.RIGHT)) {
			sprite.setCurrentAnimation(down);
			setAngle(Angle.DOWN);
		}
		
		
		if (body.getLinearVelocity().x == 0 && body.getLinearVelocity().y == 0) {
			switch (getAngle()) {
			case DOWN:
				sprite.setCurrentAnimation(stand_d);
				break;
			case LEFT:
				sprite.setCurrentAnimation(stand_l);
				break;
			case RIGHT:
				sprite.setCurrentAnimation(stand_r);
				break;
			case UP:
				sprite.setCurrentAnimation(stand_u);
				break;
		
			
			
			}
		}
		
		
		
	}
	
	
	

	@Override
	public void draw(SpriteBatch batch) {
		sprite.drawAnimation(batch,32);


	}
	public void setFreezPlayer(boolean freez){
		this.freezPlayer = freez; 
	}
	public boolean getFreezPlayer(){
		return freezPlayer;  
	}

}
