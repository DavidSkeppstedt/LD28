package se.dixum.ld28.one.map;

import se.dixum.simple.gfx.SimpleTileMap;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class WorldMap {
	private SimpleTileMap map;
	public SimpleTileMap getMap() {
		return map;
	}
	public WorldMap(String filepath) {
		map = new SimpleTileMap(filepath, 1);
	}
	
	
	
	public void draw(OrthographicCamera camera) {
		map.draw(camera);
		
	}
	public void draw(OrthographicCamera camera,int[] layer) {
		map.draw(camera,layer);
	
	
	}
	
	
	
	
	public int getTile(float x, float y) {
		return map.getTileID(x, y, 0);
	}
	
	

	
	
}
