package se.dixum.ld28.one.factories;

import se.dixum.ld28.one.entities.Money;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class MoneyFactory {
	
	private Array<Money> moneyArray;
	
	public MoneyFactory() {
		moneyArray = new Array<Money>();
	}
	
	
	public void addMoney(Money m) {
		moneyArray.add(m);
	}
	
	public void update(float delta) {
		for (Money m:moneyArray) {
			m.update(delta);
		}
	}
	
	
	
	public void draw(SpriteBatch batch){
		
		for (Money m:moneyArray) {
			m.draw(batch);
		}
	}
	
	
	
	
	
	
	
	
}
