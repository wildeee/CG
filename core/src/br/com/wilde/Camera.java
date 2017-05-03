package br.com.wilde;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;

public class Camera extends PerspectiveCamera {
	
	public static final Camera INSTANCE = new Camera();
	
	private Camera() {
		super(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		this.position.set(10f, 0f, 0f);
		this.lookAt(0, 0, 0);
		this.near = 1f;
		this.far = 300f;
		this.update();
	}
	
	public void startMoving(Joystick joystick){
		CameraMovementController mover = new CameraMovementController(joystick);
		Thread threadCameraMovement = new Thread(mover);
		threadCameraMovement.start();
	}
}