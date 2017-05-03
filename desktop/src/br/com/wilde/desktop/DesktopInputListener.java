package br.com.wilde.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

import br.com.wilde.Joystick;

public class DesktopInputListener implements Joystick {
	
	@Override
	public Boolean movingForward() {
		return Gdx.input.isKeyPressed(Keys.W);
	}
	
	@Override
	public Boolean movingLeft() {
		return Gdx.input.isKeyPressed(Keys.A);
	}

	@Override
	public Boolean movingBackward() {
		return Gdx.input.isKeyPressed(Keys.S);
	}

	@Override
	public Boolean movingRight() {
		return Gdx.input.isKeyPressed(Keys.D);
	}
}