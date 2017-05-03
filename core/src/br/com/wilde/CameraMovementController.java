package br.com.wilde;

public class CameraMovementController implements Runnable {
	
	private final Joystick joystick;
	private Float stepSize = 0.8f;
	
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
		
		if (joystick.movingForward()){
			Camera.INSTANCE.position.x -= stepSize;
		}
		
		if (joystick.movingLeft()){
			Camera.INSTANCE.position.z += stepSize;
		}
		
		if (joystick.movingBackward()){
			Camera.INSTANCE.position.x += stepSize;
		}
		
		if (joystick.movingRight()){
			Camera.INSTANCE.position.z -= stepSize;
		}
		
		Camera.INSTANCE.update();
	}
}