package pobj.tme6.notation;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Utils {

	static public <T> boolean hasOnlyPrivateAttributes(Class<T> c) {
		for (Field f : c.getDeclaredFields()) {
			if (!Modifier.isPrivate(f.getModifiers())) {
				return false;
			}
		}
		return true;
	}

	static public <T>  boolean hasNoAttribute(Class<T> c) {
		return c.getDeclaredFields().length == 0;
	}
	
	static public <T,U> boolean hasFieldOfType(Class<T> c, Class<U> t) {
		for (Field f : c.getDeclaredFields()) {
			if (f.getType() == t) {
				return true;
			}
		}
		return false;		
	}
	
	static int[] makeArray(int... data) {
		int[] r = new int[100];
		for (int i =0; i < data.length; i++) {
			r[i] = data[i];
		}
		return r;
	}
}
