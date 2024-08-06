package com.epam.rd.autocode.queue;

/**
 * @author D. Kolesnikov
 */
public final class Buyer {
	
	private static char nextName; 
	
	static {
		resetNames();
	}
	
	public static void resetNames() {
		nextName = 'A'; 
	}

	public static Buyer nextBuyer() {
		return new Buyer(nextName++);
	}
	
	private final char name;
	
	private Buyer(char name) {
		this.name = name;
	}
	
	@Override
	public final String toString() {
		return String.valueOf(name);
	}
	
}
