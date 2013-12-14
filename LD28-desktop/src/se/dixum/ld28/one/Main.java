package se.dixum.ld28.one;

import se.dixum.simple.utils.SimpleSettings;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "LD28";
		cfg.useGL20 = false;
		cfg.width = SimpleSettings.GWIDTH;
		cfg.height = SimpleSettings.GHEIGHT;
		
		new LwjglApplication(new GameStarter(), cfg);
	}
}
