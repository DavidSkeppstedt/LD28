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
	
	private float count = 0,timer = 0.01f;
	public PoliceFactory(SimpleTileMap map) {
		this.map = map;
		init();
	}
	
	
	@Override
	public void init() {
		policelist = new Array<Police>();
		
	}
	
	
	public void addPolice() {
		
		
		
		if (policelist.size < maxPolice) {
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
	
	
	@Override
	public void update(float delta) {
		addPolice();
		
		
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
