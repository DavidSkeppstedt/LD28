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
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Player extends SimpleEntity implements SimpleBaseEntity {

	private SimpleAnimated sprite;
	private Animation right,left,up,down,stand_r,stand_l,stand_u,stand_d;
	private float speed = 6;
	
	
	private Body body;
	private World world;
	private Rectangle pRect;

	
	private Array<Bullet> bullets;
	
	private boolean metMobbster;
	private boolean freezPlayer; 
	
	private Dialog dialog2;
	private Dialog dialog3;
	
	
	private long endTime = 0;
	private int sleepTime = 1000; 
	private boolean timerOn = false; 
	
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
		bullets = new Array<Bullet>();
		pRect = new Rectangle();
		metMobbster = false; 
		freezPlayer = false;
		if (ScreenSettings.level != 0) {
			metMobbster = true;
		}
	
		sprite = new SimpleAnimated(new Texture(Gdx.files.internal("gfx/player/player_final.png")),
				32, 32, 0.24f);
		right = sprite.createAnimation(0, 3, 0);
		left = sprite.createAnimation(3, 6, 0);
		up = sprite.createAnimation(6, 9, 0);
		down = sprite.createAnimation(9, 12, 0);
		stand_r = sprite.createAnimation(0, 1, 0);
		stand_l = sprite.createAnimation(3, 4, 0);
		stand_u = sprite.createAnimation(6, 7, 0);
		stand_d= sprite.createAnimation(9, 10, 0);		
		 
		sprite.setPosition(new Vector2(100,100));
	
		
		sprite.setCurrentAnimation(right);
		sprite.setScale(new Vector2(2,2)); 
		
		
	
		body = GameScreen.BODYFACTORY.createBody(new Vector2(5,15), BodyType.DynamicBody).
				createFixture(GameScreen.BODYFACTORY.createPolyShape((30/32f)/2f, (58/32f)/2f),0.5f,.5f,0)
				.build(world);
		body.setFixedRotation(true);
		sprite.setPosition(body.getPosition());
		sprite.setOrigin(new  Vector2(32,32));

		dialog2 = new Dialog("dialogs/dialog2.txt");
		dialog3 = new Dialog("dialogs/dialog3.txt");
	}

	@Override
	public void update(float delta) {
		
		dialog2.update(delta);
		dialog3.update(delta);
		

		
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
		
		if(checkTimer()&&Gdx.input.isKeyPressed(Keys.ALT_LEFT)){
			starTimer();
			shoot();
		}
		
		
		for(Bullet b:bullets){
			if(b.isOutOfMap()){
				bullets.removeValue(b, false);
			}
			b.update(delta);
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
	
	public void checkIfHit(Array<Bullet> blist) {
		
		for (Bullet b: blist) {
			if (getRect().overlaps(b.getRectangle())) {
				//Player hit!
				if (!b.isDead()){
				ScreenSettings.helth -=17;
					b.setDead(true);
				}
			}
			
			
			
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
		for(Bullet b:bullets){
			b.draw(batch);
		}
		
		sprite.drawAnimation(batch,32);
		dialog2.draw(batch);
		dialog3.draw(batch);

		
	}
	public void setFreezPlayer(boolean freez){
		this.freezPlayer = freez; 
	}
	public boolean getFreezPlayer(){
		return freezPlayer;  
	}
	public Dialog getDialog2() {
		return dialog2;
	}
	public Dialog getDialog3() {
		return dialog3;
	}
	public void shoot() {
		int r = 0;
		switch (getAngle()){
		case DOWN:
			r = 3;
			break;
		case LEFT:
			r = 2;
			break;
		case RIGHT:
			r=4;
			break;
		case UP:
			r = 1;
			break;
		default:
			break;
		
		}
		
		bullets.add(new Bullet(new Vector2(getBody().getPosition().x *32,getBody().getPosition().y*32), r));
	}

	private boolean checkTimer(){
		if(endTime <= TimeUtils.millis()){
			endTime = 0;
			timerOn = false;
			return true;
		}else{
			return false;
		}
	}
	private void starTimer(){
		if(!timerOn){
			endTime = TimeUtils.millis()+sleepTime;
			timerOn = true; 
		}
	}


	public Array<Bullet> getBullets() {
		return bullets;
	}


	public void setBullets(Array<Bullet> bullets) {
		this.bullets = bullets;
	}
}
