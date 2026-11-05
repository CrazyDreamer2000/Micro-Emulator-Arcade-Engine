package pobj.tme6.test;

import pobj.tme6.device.Screen;

public class ScreenMain {
	
	static public void main(String[] args) {
		Screen s = new Screen();
        s.write(0,  66);
        s.write(1, 79);
        s.write(2, 78);
        s.write(3, 74);
        s.write(4,  79);
        s.write(5, 85);
        s.write(6, 82);
        s.write(8, 33);
        s.tick(150);
        // affiche BONJOUR !
        s.tick(75);
        // affiche BONJOUR !
	}

}
