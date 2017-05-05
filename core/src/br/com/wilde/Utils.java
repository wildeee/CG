package br.com.wilde;

import com.badlogic.gdx.math.Vector3;

public class Utils {

	private Utils() { }
	
	public static void printVector(Vector3 vector) {
		System.out.println("\n\n\nx: " + vector.x);
		System.out.println("y: " + vector.y);
		System.out.println("z: " + vector.z);
	}
	
	public static double angleBetween(Vector3 v1, Vector3 v2) {
		float produtoEscalar = v1.dot(v2);
		float normasMultiplicadas = v1.len() * v2.len();
		return Math.acos(produtoEscalar / normasMultiplicadas);
	}
}