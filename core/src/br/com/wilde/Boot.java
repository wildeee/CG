package br.com.wilde;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;

public class Boot extends ApplicationAdapter {
	
	private final Joystick joystick;
	
	public Environment lights;
	public ModelBatch modelBatch;
	public Model setaX;
	public Model setaY;
	public Model setaZ;
	public ModelInstance instanceX;
	public ModelInstance instanceY;
	public ModelInstance instanceZ;

	public Boot(Joystick joystick) {
		this.joystick = joystick;
	}

	@Override
	public void create() {
		
		Camera.INSTANCE.startMoving(joystick);
		lights = new Environment();
		lights.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		lights.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		modelBatch = new ModelBatch();

		ModelBuilder modelBuilder = new ModelBuilder();
		setaX = modelBuilder.createArrow(Vector3.Zero, new Vector3(8, 0, 0), new Material(ColorAttribute.createDiffuse(Color.BLUE)), Usage.Position | Usage.Normal);
		setaY = modelBuilder.createArrow(Vector3.Zero, new Vector3(0, 8, 0), new Material(ColorAttribute.createDiffuse(Color.RED)), Usage.Position | Usage.Normal);
		setaZ = modelBuilder.createArrow(Vector3.Zero, new Vector3(0, 0, 8), new Material(ColorAttribute.createDiffuse(Color.GREEN)), Usage.Position | Usage.Normal);
		
		instanceX = new ModelInstance(setaX);
		instanceY = new ModelInstance(setaY);
		instanceZ = new ModelInstance(setaZ);
	}

	@Override
	public void render() {
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
 
        modelBatch.begin(Camera.INSTANCE);
        modelBatch.render(instanceX, lights);
        modelBatch.render(instanceY, lights);
        modelBatch.render(instanceZ, lights);
        modelBatch.end();
	}
	
	@Override
	public void dispose() {
		modelBatch.dispose();
		setaX.dispose();
		setaY.dispose();
		setaZ.dispose();
	}
}