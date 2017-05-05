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
		
		if (joystick.movingLeft()){
			Camera.INSTANCE.position.z += STEP_SIZE;
		}
		
		if (joystick.movingRight()){
			Camera.INSTANCE.position.z -= STEP_SIZE;
		}
		
		Camera.INSTANCE.update();
	}

	private void checkMovementForwardOrBackward() {
		Boolean movingForward = joystick.movingForward();
		Boolean movingBackward = joystick.movingBackward();
		
		if (!(movingForward ^ movingBackward)) {
			return;
		}
		
		Double angle = Utils.angleBetween(Camera.INSTANCE.direction, Vector3.X);
		Double deltaX = calculateDeltaX(angle);
		Double deltaZ = calculateDeltaZ(angle);
		
		if (movingForward) {
			moveForwardOrBackward(deltaX, deltaZ);
		} else {
			moveForwardOrBackward(-deltaX, -deltaZ);
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
}