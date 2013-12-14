package se.dixum.ld28.one.factories;

import se.dixum.ld28.one.entities.Granny;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class GrannyFactory {
	private Array<Granny> grannylist;
	private float spawnTime = 3;
	private float counter = 0;
	private int maxNum = 10;
	
	
	
	public GrannyFactory() {
		init();
		
	}
	
	public void spawnGranny() {
		if (counter > spawnTime) {
			counter = 0;
			addGranny();
		}else {
			counter +=Gdx.graphics.getDeltaTime();
		}
	}
	
	public void addGranny() {
		if (grannylist.size < maxNum) {
				Vector2 v = new Vector2(MathUtils.random(0, 1280-64),MathUtils.random(0, 720));
				Granny g = new Granny(v);
				grannylist.add(g);
				v = null;
				g = null;
		}
		
		
	}
	
	public void checkRemove(){
		
	}
	
	
	
	
	public void init(){
		grannylist = new Array<Granny>();
				
	}
	
	public void update(float delta) {
		spawnGranny();
		checkRemove();
		
		for (Granny g:grannylist){
			g.update(delta);
		}
		
		
	}
	
	
	
	public void draw(SpriteBatch batch) {
		for (Granny g:grannylist){
			g.draw(batch);
		}
	}
	
	
	
}
