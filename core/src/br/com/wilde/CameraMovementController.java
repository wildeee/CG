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
		
		Double angle = Utils.angleBetween(Camera.INSTANCE.direction, Vector3.X);
		
		Double distanceX = Math.abs(Math.sin(angle) * STEP_SIZE);
		Double distanceZ = Math.abs(Math.cos(angle) * STEP_SIZE);
		
		Double deltaX, deltaZ;
		
		Float signumX = Math.signum(Camera.INSTANCE.direction.x);
		Float signumZ = Math.signum(Camera.INSTANCE.direction.z);
		
		Utils.printVector(Camera.INSTANCE.position);
		
		if (joystick.movingForward()){
			deltaX = distanceX * signumX;
			deltaZ = distanceZ * signumZ;
			
			Camera.INSTANCE.position.x += deltaX;
			Camera.INSTANCE.position.z += deltaZ;
		}
		
		if (joystick.movingLeft()){
			Camera.INSTANCE.position.z += STEP_SIZE;
		}
		
		if (joystick.movingBackward()){
			deltaX = distanceX * signumX;
			deltaZ = distanceZ * signumZ;
			
			Camera.INSTANCE.position.x -= deltaX;
			Camera.INSTANCE.position.z -= deltaZ;
		}
		
		if (joystick.movingRight()){
			Camera.INSTANCE.position.z -= STEP_SIZE;
		}
		
		Camera.INSTANCE.update();
	}
}