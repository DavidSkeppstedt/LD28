package se.dixum.ld28.one.factories;

import se.dixum.ld28.one.entities.Police;
import se.dixum.simple.entities.base.SimpleBaseEntity;
import se.dixum.simple.gfx.SimpleTileMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class PoliceFactory implements SimpleBaseEntity{

	private Array<Police> policelist;
	private int maxPolice = 5;
	private SimpleTileMap map;
	public static int removed_police = 0;
	
	private float count = 0,timer = 0.01f;
	
	private float count2 = 0;
	private float timer2 = 1.5f;
	private boolean done = false;
	
	
	public PoliceFactory(SimpleTileMap map) {
		this.map = map;
		init();
	}
	
	
	@Override
	public void init() {
		policelist = new Array<Police>();
		
	}
	
	
	public void addPolice() {
		
		if (policelist.size == maxPolice) {
			done = true;
		}
		
		if (policelist.size < maxPolice && !done) {
			if (count > timer)	{
				Vector2 v= new Vector2(MathUtils.random(200, 1280-64), MathUtils.random(64, 350));
				
				Police p = new Police(v, map);
				
				policelist.add(p);
				count = 0;
			}else {
				count +=Gdx.graphics.getDeltaTime();
			}
			
			
		}
		
		
		
		
		
	}
	
	private void removePolice() {
		if (policelist.size >= 0) {
		for (int i = policelist.size-1; i > 0; i--) {
			
			if (policelist.get(i).isDead()) {
				
				policelist.removeIndex(i);
				
			}
			
			
		}
		}
		
		
		
	}
	
	
	
	@Override
	public void update(float delta) {
		addPolice();
		
		if (count2 > timer2){
			removePolice();
		}else {
			count2 +=Gdx.graphics.getDeltaTime(); 
		}
		
		
		for (Police p: policelist){
			p.update(delta);
		}
		
		
	}

	@Override
	public void draw(SpriteBatch batch) {
		for (Police p: policelist){
			p.draw(batch);
		}
		
	}

}
