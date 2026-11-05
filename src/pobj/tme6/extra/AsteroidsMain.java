package pobj.tme6.extra;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class AsteroidsMain extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		AsteroidsKeyListener keys = new AsteroidsKeyListener();
		AsteroidsScreen screen = new AsteroidsScreen(stage, keys);
		AsteroidsEmulator emu = new AsteroidsEmulator(screen, keys);
		
		AnimationTimer timer = new AnimationTimer() {
			private long last;
			@Override public void handle(long now) {
				if (last != 0) emu.run((now - last) / 1000000000.);
				last = now;
		    }
		};
		timer.start();
	}
		
	public static void main(String[] args) { 
		launch(args); 
	} 
}
