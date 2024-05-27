package com.mygdx.vampiresurvivors;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.vampiresurvivors.screens.GameScreen;

public class VampireSurvivors extends Game {

	private static final float VIRTUAL_WIDTH = 128;
	private static final float VIRTUAL_HEIGHT = 72;

	private AssetManager assetManager;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Viewport viewport;

	private Texture img;
	
	@Override
	public void create () {
		assetManager = new AssetManager();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
		viewport = new FitViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, camera);
		viewport.apply();

		setScreen(new GameScreen(this));
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);

		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public void resize(final int width, final int height) {
		viewport.update(width, height);
	}

	public AssetManager getAssetManager() {
		return assetManager;
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	public Viewport getViewport() {
		return viewport;
	}
}
