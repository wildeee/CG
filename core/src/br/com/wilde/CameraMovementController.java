package br.com.wilde;

import com.badlogic.gdx.math.Vector3;

public class CameraMovementController implements Runnable {
	
	private final Joystick joystick;
	private static final Float STEP_SIZE = 1f;
	
	public CameraMovementController(Joystick joystick) {
		this.joystick = joystick;
	}

	@Override
	public void run() {
		try {
			while (Boolean.TRUE){
				moveCamera();
				Thread.sleep(17);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void moveCamera() {
		Utils.printVector(Camera.INSTANCE.position);
		checkMovementForwardOrBackward();
		checkMovementLeftOrRight();
		
		Camera.INSTANCE.update();
	}

	private void checkMovementForwardOrBackward() {
		Boolean movingForward = joystick.movingForward();
		Boolean movingBackward = joystick.movingBackward();
		
		if (!(movingForward ^ movingBackward)) {
			return;
		}
		
		Vector3 directionFloor = new Vector3(Camera.INSTANCE.direction);
		directionFloor.y = 0;
		Double angle = Utils.angleBetween(directionFloor, Vector3.X);
		Double deltaX = calculateDeltaX(angle);
		Double deltaZ = calculateDeltaZ(angle);
		
		if (movingForward) {
			moveForwardOrBackward(deltaX, deltaZ);
		} else {
			moveForwardOrBackward(-deltaX, -deltaZ);
		}
	}
	
	private void checkMovementLeftOrRight() {
		Boolean movingLeft = joystick.movingLeft();
		Boolean movingRight = joystick.movingRight();
		
		if (!(movingLeft ^ movingRight)){
			return;
		}
		
		Vector3 perpendicularDirection = getPerpendicularDirection();
		Double angle = Utils.angleBetween(perpendicularDirection, Vector3.X);
		
		Double distanceX = Math.abs(Math.cos(angle) * STEP_SIZE);
		Double distanceZ = Math.abs(Math.sin(angle) * STEP_SIZE);
		
		Float signumX = Math.signum(perpendicularDirection.x);
		Float signumZ = Math.signum(perpendicularDirection.z);
		
		Double deltaX = distanceX * signumX;
		Double deltaZ = distanceZ * signumZ;
		
		if (movingLeft) {
			Camera.INSTANCE.position.x -= deltaX;
			Camera.INSTANCE.position.z -= deltaZ;
		}
		
		if (movingRight) {
			Camera.INSTANCE.position.x += deltaX;
			Camera.INSTANCE.position.z += deltaZ;
		}
		
	}
	
	private Double calculateDeltaX(Double directionAngleWithXAxis) {
		Double distanceX = Math.abs(Math.sin(directionAngleWithXAxis) * STEP_SIZE);
		Float signumX = Math.signum(Camera.INSTANCE.direction.x);
		return distanceX * signumX;
	}
	
	private Double calculateDeltaZ(Double directionAngleWithXAxis) {
		Double distanceZ = Math.abs(Math.cos(directionAngleWithXAxis) * STEP_SIZE);
		Float signumZ = Math.signum(Camera.INSTANCE.direction.z);
		return distanceZ * signumZ;
	}
	
	private void moveForwardOrBackward(Double deltaX, Double deltaZ){
		Camera.INSTANCE.position.x += deltaX;
		Camera.INSTANCE.position.z += deltaZ;
	}
	
	private Vector3 getPerpendicularDirection() {
		Vector3 perpendicularDirection = new Vector3();
		perpendicularDirection.x = -Camera.INSTANCE.direction.z;
		perpendicularDirection.y = 0;
		perpendicularDirection.z = Camera.INSTANCE.direction.x;
		return perpendicularDirection;
	}
}