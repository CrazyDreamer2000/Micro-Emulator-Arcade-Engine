package pobj.tme6.extra;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

// Swing keyboard input
public class AsteroidsKeyListener {

	private AsteroidsInput in0, in1;
	
	// key configuration
	private KeyCode[] keys_left = { KeyCode.LEFT, KeyCode.KP_LEFT, KeyCode.A  };
	private KeyCode[] keys_right = { KeyCode.RIGHT, KeyCode.KP_RIGHT, KeyCode.D };
	private KeyCode[] keys_thrust = { KeyCode.UP, KeyCode.KP_UP, KeyCode.W };
	private KeyCode[] keys_fire = { KeyCode.SPACE, KeyCode.SHIFT  };
	private KeyCode[] keys_hyperspace = { KeyCode.ENTER, KeyCode.S, KeyCode.DOWN, KeyCode.KP_DOWN  };
	private KeyCode[] keys_coin = { KeyCode.DIGIT5 };
	private KeyCode[] keys_1p = { KeyCode.DIGIT1 };
	private KeyCode[] keys_2p = { KeyCode.DIGIT2 };
	
	public void setInputs(AsteroidsInput in0, AsteroidsInput in1) {
		this.in0 = in0;
		this.in1 = in1;
	}
	
	private void do_key(KeyEvent e, KeyCode[] vk, AsteroidsInput in, int line, boolean pressed) {
		for (KeyCode x : vk) {
			if (e.getCode() == x) {
				in.setInput(line, pressed);
			}
		}
	}
	
	private void do_all_keys(KeyEvent event, boolean pressed) {
		do_key(event, keys_hyperspace, in0, 3, pressed);
		do_key(event, keys_fire, in0, 4, pressed);
		do_key(event, keys_coin, in1, 0, pressed);
		do_key(event, keys_1p, in1, 3, pressed);
		do_key(event, keys_2p, in1, 4, pressed);
		do_key(event, keys_thrust, in1, 5, pressed);
		do_key(event, keys_right, in1, 6, pressed);
		do_key(event, keys_left, in1, 7, pressed);
	}
	
	public void keyPressed(KeyEvent event) {
		if (event.getCode() == KeyCode.ESCAPE) {
			System.exit(0);
		}
		do_all_keys(event, true);
	}

	public void keyReleased(KeyEvent event) {	 
		do_all_keys(event, false);
	 }
}
