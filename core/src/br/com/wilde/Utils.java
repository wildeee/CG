package br.com.wilde;

import com.badlogic.gdx.math.Vector3;

public class Utils {

	private Utils() { }
	
	public static void printVector(Vector3 vector) {
		System.out.println("x: " + vector.x);
		System.out.println("y: " + vector.y);
		System.out.println("z: " + vector.z);
	}
}