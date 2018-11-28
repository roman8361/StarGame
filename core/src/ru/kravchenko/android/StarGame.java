package ru.kravchenko.android;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
//	TextureRegion region;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("sky.jpg");
		Vector2 v1 = new Vector2(1f, 1f);
		Vector2 v2 = new Vector2(-1, 1f);
		System.out.println(Math.acos(v1.dot(v2)));

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
//		batch.draw(region,0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

}


//		v1.add(v2);
//		System.out.println("v1.x = " + v1.x + " v1.y = " + v1.y);
//		v1.set(1f, 2f);
//		v2.set(4f, 4f);
//		System.out.println("***************");
//		System.out.println("v1.x = " + 1 + " v1.y = " + 2);
//		System.out.println("v2.x = " + 4 + " v2.y = " + 4);
//		v2.sub(v1);
//		System.out.println("v2.x = " + v2.x + " v2.y = " + v2.y);
//		System.out.println("***************");
//		v1.set(1f, 2f);
//		v2.set(4f, 4f);
//		v1.sub(v2);
//		System.out.println("v1.x = " + v1.x + " v1.y = " + v1.y);
//		v1.scl(4f);
//		System.out.println("v1.x = " + v1.x + " v1.y = " + v1.y);

//	Vector2 v3 = v2.cpy().sub(v1);
//		v3.nor();
//				System.out.println("v1.x = " + v1.x + " v1.y = " + v1.y);
//				System.out.println("v2.x = " + v2.x + " v2.y = " + v2.y);
//				System.out.println("v3.x = " + v3.x + " v3.y = " + v3.y);
//				System.out.println("v3.len = " + v3.len());
//				v3.nor();
//				System.out.println("v3.x = " + v3.x + " v3.y = " + v3.y);