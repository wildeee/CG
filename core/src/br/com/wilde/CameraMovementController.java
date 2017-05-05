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
		Double deltaX = calculateDeltaXForwardBackward(angle);
		Double deltaZ = calculateDeltaZForwardBackward(angle);
		
		if (movingForward) {
			moveOnTheFloor(deltaX, deltaZ);
		} else {
			moveOnTheFloor(-deltaX, -deltaZ);
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
		
		Double deltaX = calculateDeltaXLeftRight(angle, perpendicularDirection);
		Double deltaZ = calculateDeltaZLeftRight(angle, perpendicularDirection);
		
		if (movingLeft) {
			moveOnTheFloor(-deltaX, -deltaZ);
		} else {
			moveOnTheFloor(deltaX, deltaZ);
		}
	}
	
	private Double calculateDeltaXForwardBackward(Double directionAngleWithXAxis) {
		Double distanceX = Math.abs(Math.sin(directionAngleWithXAxis) * STEP_SIZE);
		Float signumX = Math.signum(Camera.INSTANCE.direction.x);
		return distanceX * signumX;
	}
	
	private Double calculateDeltaZForwardBackward(Double directionAngleWithXAxis) {
		Double distanceZ = Math.abs(Math.cos(directionAngleWithXAxis) * STEP_SIZE);
		Float signumZ = Math.signum(Camera.INSTANCE.direction.z);
		return distanceZ * signumZ;
	}
	
	private Double calculateDeltaXLeftRight(Double directionAngleWithXAxis, Vector3 direction) {
		Double distanceX = Math.abs(Math.cos(directionAngleWithXAxis) * STEP_SIZE);
		Float signumX = Math.signum(direction.x);
		return distanceX * signumX;
	}
	
	private Double calculateDeltaZLeftRight(Double directionAngleWithXAxis, Vector3 direction) {
		Double distanceZ = Math.abs(Math.sin(directionAngleWithXAxis) * STEP_SIZE);
		Float signumZ = Math.signum(direction.z);
		return distanceZ * signumZ;
	}
	
	private void moveOnTheFloor(Double deltaX, Double deltaZ){
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