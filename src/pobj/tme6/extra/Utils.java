package pobj.tme6.extra;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

// Utilities
public class Utils {

	// Load ROM file into int[] array
	public static int[] loadROMbin(String filename, int length) {
		int[] res = new int[length];
		try {
			InputStream f = new BufferedInputStream(new FileInputStream(filename));
			for (int i=0; i<length; i++) {
				int x = f.read();
				if (x < 0) break;
				res[i] = x;
			}
			f.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
}
