package se.dixum.ld28.one.entities;

import se.dixum.ld28.one.screens.GameScreen;
import se.dixum.ld28.one.util.ScreenSettings;
import se.dixum.simple.audio.SimpleSound;
import se.dixum.simple.entities.base.SimpleBaseEntity;
import se.dixum.simple.gfx.SimpleSprite;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Money implements SimpleBaseEntity {
	
	private Rectangle rect,pRect;
	private SimpleSprite sprite;
	private Vector2 spawnPos;
	ShapeRenderer s;
	private boolean pickUp = false;
	private SimpleSound money;
	public Money(Vector2 spawnPos) {
		this.spawnPos = spawnPos;
		init();
		
	}
	@Override
	public void init() {
		s = new ShapeRenderer();
		sprite = new SimpleSprite(
					new TextureRegion(
						new Texture(
								Gdx.files.internal("gfx/money/dollarsedel.png")
								))
									,spawnPos);
		rect = new Rectangle(sprite.getPosition().x,sprite.getPosition().y,sprite.getSprite().getWidth(),sprite.getSprite().getHeight());
		money = new SimpleSound(Gdx.audio.newSound(Gdx.files.internal("sound/money/money.wav")));

	}

	@Override
	public void update(float delta) {
		
		if (rect.overlaps(GameScreen.PLAYER.getRect())) {
			if (!pickUp) {
				money.play();
				int r = MathUtils.random(25,250);
				ScreenSettings.moneyAccount +=r;
			}
			pickUp = true;
		
		}
		
		

		

	}

	@Override
	public void draw(SpriteBatch batch) {
		if (!pickUp) {
			sprite.drawSprite(batch);
		}

	}

}
